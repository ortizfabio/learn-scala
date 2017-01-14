package com.bytetrend.sandbox.scala

/**
  * Created by db2admin on 1/13/2017.
  */
package object impliciting {

    implicit class StringImprovements(val s: String) {
      def increment = s.map(c => (c + 1).toChar)

      def decrement = s.map(c => (c - 1).toChar)

      def hideAll: String = s.replaceAll(".", "*")
      def plusOne = s.toInt + 1
      def asBoolean = s match {
        case "0" | "zero" | "" | " " => false
        case _ => true
      }
    }

}
