package com.bytetrend.sandbox.scala.algo

object NumberReverse {

	final def reverseNumber(number:Int):Int={
		var reverse:Int = 0
		var current: Int = number
		while( current != 0){
			reverse = reverse*10 + current%10
			current = current/10 
		}
		reverse
	}

	 def main(args: Array[String]): Unit = {
		 val in = new java.util.Scanner(System.in)
		 System.out.println("Enter a number:")
		 // number
		 val number = in.nextInt

		 println(reverseNumber(number))
  	}
}