package com.bytetrend.sandbox.scala.encryption

import java.util.function.Supplier

import javax.crypto.{BadPaddingException, Cipher, IllegalBlockSizeException}
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang3.StringUtils.{isBlank, isEmpty}
import org.slf4j.{Logger, LoggerFactory}

class CypherUtil(val secrect: String) {
  private val LOGGER: Logger = LoggerFactory.getLogger(classOf[CypherUtil])

  import CypherUtil._

  LOGGER.info("Initializing CypherUtil")
  assert(!isEmpty(secrect))
  private var encryptor: ThreadLocal[CypherEcryptor] = null
  private var decryptor: ThreadLocal[CypherDecryptor] = null

  @throws[CypherException]
  private def init(): Unit = {
    encryptor = ThreadLocal.withInitial(new Supplier[CypherEcryptor] {
      override def get(): CypherEcryptor = {
        try {
          new CypherEcryptor(secrect)
        } catch {
          case e => throw new CypherException(s"Exception while initializing encryptor, reason: ${e.getMessage}", e)
        }
      }
    })
    decryptor = ThreadLocal.withInitial(new Supplier[CypherDecryptor] {
      override def get(): CypherDecryptor = {
        try {
          new CypherDecryptor(secrect)
        } catch {
          case e => throw new CypherException(s"Exception while initializing decryptor, reason: ${e.getMessage}", e)
        }
      }
    })
    if (!ValidationToken.equals(decrypt(encrypt(ValidationToken)))) {
      throw new CypherException("Encryption/Decryption validation Failed")
    } else {
      println("Encryption/Decryption validation success.")

    }
  }

  def decrypt(encryptedText: String): String = {
    if (isBlank(encryptedText))
      null
    else
      new String(decryptBytes(Base64.decodeBase64(encryptedText)))
  }

  private def decryptBytes(bytes: Array[Byte]): Array[Byte] = {
    try {
      if (decryptor == null)
        init()
      decryptor.get().decrypt(bytes)
    } catch {
      case e => throw new CypherException(s"Execption caught while decrypting bytes ${e.getMessage}", e)
    }
  }

  def encrypt(plainText: String): String = {
    if (isBlank(plainText))
      null
    else
      Base64.encodeBase64String(encryptBytes(plainText.getBytes))
  }

  private def encryptBytes(bytes: Array[Byte]): Array[Byte] = {
    try {
      if (encryptor == null)
        init()
      encryptor.get().encrypt(bytes)
    } catch {
      case e => throw new CypherException(s"Execption caught while decrypting bytes ${e.getMessage}", e)
    }
  }

  private class CypherDecryptor(secrect: String) {
    val cipher: Cipher = init

    def init: Cipher = {
      try {
        val secKeySpec = new SecretKeySpec(secrect.getBytes(EncodingFormat), EncryptionAlgorithm)
        val ivParam = new IvParameterSpec(InitVector.getBytes(EncodingFormat))
        val cipher = Cipher.getInstance(CipherType)
        cipher.init(Cipher.DECRYPT_MODE, secKeySpec, ivParam)
        cipher
      } catch {
        case e => throw new CypherException(s"Exception while initializing, reason: ${e.getMessage}", e)
      }
    }

    @throws[BadPaddingException]
    @throws[IllegalBlockSizeException]
    def decrypt(encryptedData: Array[Byte]): Array[Byte] = {
      cipher.doFinal(encryptedData)
    }
  }

  private class CypherEcryptor(secrect: String) {
    val cipher: Cipher = init

    def init: Cipher = {
      try {
        val secKeySpec = new SecretKeySpec(secrect.getBytes(EncodingFormat), EncryptionAlgorithm)
        val ivParam = new IvParameterSpec(InitVector.getBytes(EncodingFormat))
        val cipher = Cipher.getInstance(CipherType)
        cipher.init(Cipher.ENCRYPT_MODE, secKeySpec, ivParam)
        cipher
      } catch {
        case e => throw new CypherException(s"Exception while initializing, reason: ${e.getMessage}", e)
      }
    }

    @throws[BadPaddingException]
    @throws[IllegalBlockSizeException]
    def encrypt(unEncryptedData: Array[Byte]): Array[Byte] = {
      cipher.doFinal(unEncryptedData)
    }
  }


}

object CypherUtil {
  private val EncodingFormat = "UTF-8"
  //16 bytes initialization Vector
  private val InitVector = "A1B2C3D4E5F6G7H8"
  private val EncryptionAlgorithm = "AES"
   //
  private val CipherType = "AES/CBC/PKCS5Padding"
  private val ValidationToken = "This T0k3n is @ Test"
}
