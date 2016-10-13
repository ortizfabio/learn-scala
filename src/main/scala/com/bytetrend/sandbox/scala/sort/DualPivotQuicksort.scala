package com.bytetrend.sandbox.scala.sort

class DualPivotQuicksort {

	private final val INSERTION_SORT_THRESHOLD : Int = 47

  def sort(a:Array[Int]):Unit={
     sort(a,0,a.length,true) 
  }
  
  def msort[A](less: (A, A) => Boolean)(xs: List[A]): List[A] = {
    
    def merge(xs1: List[A], xs2: List[A]): List[A] =
      if (xs1.isEmpty) xs2
      else if (xs2.isEmpty) xs1
      else if (less(xs1.head, xs2.head)) xs1.head :: merge(xs1.tail, xs2)
      else xs2.head :: merge(xs1, xs2.tail)
    val n = xs.length / 2
    if (n == 0) xs
    else merge(msort(less)(xs take n), msort(less)(xs drop n))
  }

  def sort2(xs: Array[Int]) {
    def swap(i: Int, j: Int) {
      val t = xs(i); xs(i) = xs(j); xs(j) = t
    }
    def sort1(l: Int, r: Int) {
      val pivot = xs((l + r) / 2)
      var i = l; var j = r
      while (i <= j) {
        while (xs(i) < pivot) i += 1
        while (xs(j) > pivot) j -= 1
        if (i <= j) {
          swap(i, j)
          i += 1
          j -= 1
        }
      }
      if (l < j) sort1(l, j)
      if (j < r) sort1(i, r)
    }
    sort1(0, xs.length - 1)
  }

	def sort(a:Array[Int], left:Int, right:Int, leftmost:Boolean ):Unit={
	    val length : Int = right - left + 1
			if( length < INSERTION_SORT_THRESHOLD){
				if (leftmost) {
					var i: Int = left
					var j: Int = left
					while ( i < right) {
						var ai :Int = a(i + 1)
            var foundIt : Boolean = false
				    while (ai < a(j) && !foundIt) {
					    a(j + 1) = a(j)
							if (j == left) {               
								foundIt = true
							}
              j -= 1

				    }
				    a(j + 1) = ai;
				    i += 1
						j = i
            println(a.mkString(" "))
					}
				} 
				return;
			}
  }
}

object DualPivotQuicksort {
  
  
}