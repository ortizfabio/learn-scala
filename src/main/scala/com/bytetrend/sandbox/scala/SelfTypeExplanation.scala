package com.bytetrend.sandbox.scala

class B
trait A1 { self: B =>  }
trait A2 { foo: B => }
trait A3 { this: B => }

// All three forms are valid, and have the effect that B is assumed as the type of this in class A?.
// The variants A1, A2 introduce self (respectively, foo) as an alias for this in trait A. This is useful
// for accessing the this reference from an inner class. I.e. you could then use self instead of
// A?. This when accessing the this reference of the trait A? from a class nested within it.
// The third variant A3 does not introduce an alias for this; it just sets the self type.does
// not introduce an alias for this; it just sets the self type.