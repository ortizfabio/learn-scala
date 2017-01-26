package com.bytetrend.sandbox.scala.interview

import org.scalatest.{FunSpec, Matchers}
import Palindrome.{isPalindrome,palindrome,palindrome2}

/**
  *
  */
class PalindromeTest extends FunSpec with Matchers {

  describe("Testing function isPalindrome") {

    it("Should return true with  Anna") {
      isPalindrome("Anna") shouldBe true
    }

    it("Should return true with  Civic") {
      isPalindrome("Civic") shouldBe true
    }

    it("Should return true with  Kayak") {
      isPalindrome("Kayak") shouldBe true
    }

    it("Should return true with  Level") {
      isPalindrome("Level") shouldBe true
    }

    it("Should return true with  Madam") {
      isPalindrome("Madam") shouldBe true
    }
    it("Should return true with  Mom") {
      isPalindrome("Mom") shouldBe true
    }

    it("Should return true with  Noon") {
      isPalindrome("Noon") shouldBe true
    }

    it("Should return true with  Racecar") {
      isPalindrome("Racecar") shouldBe true
    }


    it("Should return false with  Nope") {
      isPalindrome("Nope") shouldBe false
    }

    it("Should return false with  False") {
      isPalindrome("False") shouldBe false
    }
  }

  describe("Testing function palindrome") {

    it("Should return true with  Anna") {
      palindrome("Anna") shouldBe true
    }

    it("Should return true with  Civic") {
      palindrome("Civic") shouldBe true
    }

    it("Should return true with  Kayak") {
      palindrome("Kayak") shouldBe true
    }

    it("Should return true with  Level") {
      palindrome("Level") shouldBe true
    }

    it("Should return true with  Madam") {
      palindrome("Madam") shouldBe true
    }
    it("Should return true with  Mom") {
      palindrome("Mom") shouldBe true
    }

    it("Should return true with  Noon") {
      palindrome("Noon") shouldBe true
    }

    it("Should return true with  Racecar") {
      palindrome("Racecar") shouldBe true
    }


    it("Should return false with  Nope") {
      palindrome("Nope") shouldBe false
    }

    it("Should return false with  False") {
      palindrome("False") shouldBe false
    }
  }


  describe("Testing function palindrome2") {

    it("Should return true with  Anna") {
      palindrome2("Anna") shouldBe true
    }

    it("Should return true with  Civic") {
      palindrome2("Civic") shouldBe true
    }

    it("Should return true with  Kayak") {
      palindrome2("Kayak") shouldBe true
    }

    it("Should return true with  Level") {
      palindrome2("Level") shouldBe true
    }

    it("Should return true with  Madam") {
      palindrome2("Madam") shouldBe true
    }
    it("Should return true with  Mom") {
      palindrome2("Mom") shouldBe true
    }

    it("Should return true with  Noon") {
      palindrome2("Noon") shouldBe true
    }

    it("Should return true with  Racecar") {
      palindrome2("Racecar") shouldBe true
    }


    it("Should return false with  Nope") {
      palindrome2("Nope") shouldBe false
    }

    it("Should return false with  False") {
      palindrome2("False") shouldBe false
    }
  }

}
