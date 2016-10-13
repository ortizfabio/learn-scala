package demo

/**
  * the function should return 5. Assume that:

• N is an integer within the range [1..100,000];
• Each element of array A is an integer within the
range [−2,147,483,648..2,147,483,647].
Complexity:
•  Worst-case time complexity is O(n)
, beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
N is an integer within the range [1..100,000];

•         Each element of array A is an integer within the range
[−2,147,483,648..2,147,483,647].

Complexity:

•         Worst-case time complexity is O


;

•         Worst-case space complexity is O


, beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified

Carnival Cruise Lines
Corporate Office
3655 NW 87th Ave
(800) 845-2599
  * Created by db2admin on 6/10/2016.
  */
object Test3 {


  def main(args: Array[String]):Unit={
    val values = Array[Int](147,483,648,2,147,483,647).toList.sorted.filter(_>0)

    def solution(a: Array[Int]): Int = {

      val l: Array[Int] = a.filter(_ > 0).sorted

      if (l.length == 0 || l(0) > 1) return 1
      var i = 0
      for (i <- 0 to l.length - 2) {
        if ((l(i + 1) - l(i) ) > 1) {
          return l(i+1)
       }
      }

      l(l.length-1) + 1

      // Works great for an unordered sequence with no duplicates and only one missing number in the sequence:

      //        val l = a.filter(_ > 0)

      //        val s = l.sum

      //        val n = l.length

      //

      //        ((n+1)*(n+2)/2) - s

    }


  }
}
