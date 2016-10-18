package com.bytetrend.sandbox.scala.xml

/**
  * Created by jose.ortiz on 10/18/16.
  */


import java.io.{FileInputStream, FileNotFoundException}
import javax.xml.XMLConstants
import javax.xml.parsers.SAXParserFactory
import javax.xml.transform.Source
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

import org.xml.sax.SAXParseException
import org.xml.sax.helpers.DefaultHandler

import scala.util.Try
import scala.xml.factory.XMLLoader
import scala.xml.parsing.{FactoryAdapter, NoBindingFactoryAdapter}
import scala.xml.{Elem, SAXParser}

object ValidateWithSchema extends App {
  val schema = {
    val factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
    val xsdCatalogStream = new FileInputStream("./src/main/resources/Catalog_Import.xsd")
    val xsdCommonStream = new FileInputStream("./src/main/resources/common.xsd")
    val source: Array[Source] = Array(new StreamSource(xsdCommonStream), new StreamSource(xsdCatalogStream))
    val schema = factory.newSchema(source)
    xsdCatalogStream.close()
    xsdCommonStream.close()
    schema
  }

  val saxParser = {
    val f = SAXParserFactory.newInstance()
    f.setNamespaceAware(true)
    f.setSchema(schema)
    f.newSAXParser()
  }


  val xml1 = Option(getClass.getResourceAsStream("/Catalog_example.xml"))
  println {
    Try {
      val loader = new XMLLoader[Elem] {
        override def adapter: FactoryAdapter = new NoBindingFactoryAdapter() {
          override def error(e: SAXParseException) = {
            throw e
          }
        }

        override def parser: SAXParser = saxParser
      }
      val data: Elem = loader.load(xml1.getOrElse(throw new FileNotFoundException()))
      data
    }
  }
  xml1.get.close()
}
