package com.bytetrend.sandbox.scala.concurrent

import java.util.concurrent._

import scala.util.DynamicVariable

object parallel {

  val forkJoinPool = new ForkJoinPool
  val scheduler =
    new DynamicVariable[TaskScheduler](new DefaultTaskScheduler)

  def parallel[A, B](taskA: => A, taskB: => B): (A, B) = {
    scheduler.value.parallel(taskA, taskB)
  }

  def parallel[A, B, C, D](taskA: => A, taskB: => B, taskC: => C, taskD: => D): (A, B, C, D) = {
    val ta = task {
      taskA
    }
    val tb = task {
      taskB
    }
    val tc = task {
      taskC
    }
    val td = taskD
    (ta.join(), tb.join(), tc.join(), td)
  }

  def task[T](body: => T): ForkJoinTask[T] = {
    scheduler.value.schedule(body)
  }

  abstract class TaskScheduler {
    def schedule[T](body: => T): ForkJoinTask[T]

    def parallel[A, B](taskA: => A, taskB: => B): (A, B) = {
      val right = task {
        taskB
      }
      val left = taskA
      (left, right.join())
    }
  }

  class DefaultTaskScheduler extends TaskScheduler {
    def schedule[T](body: => T): ForkJoinTask[T] = {
      val t = new RecursiveTask[T] {
        def compute = body
      }
      Thread.currentThread match {
        case wt: ForkJoinWorkerThread =>
          t.fork()
        case _ =>
          forkJoinPool.execute(t)
      }
      t
    }
  }

}

object pnorm {
  def pNorm(a: Array[Int], p: Double): Int =
    power(sumSegment(a, p, 0, a.length), 1 / p)

  def pNormTwoPart(a: Array[Int], p: Double): Int = {
    val m = a.length / 2
    val (sum1, sum2) = (sumSegment(a, p, 0, m),
      sumSegment(a, p, m, a.length))
    power(sum1 + sum2, 1 / p)
  }

  def sumSegment(a: Array[Int], p: Double, s: Int, t: Int): Int = {
    var i = s;
    var sum: Int = 0
    while (i < t) {
      sum = sum + power(a(i), p)
      i = i + 1
    }
    sum
  }

  def power(x: Int, p: Double): Int = math.exp(p * math.log(math.abs(x))).toInt

}