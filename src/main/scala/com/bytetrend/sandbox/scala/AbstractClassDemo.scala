package com.bytetrend.sandbox.scala


object AbstractClassDemo {

  class Database {
    def save = println("save called")

    def update = println("update called")

    def delete = println("delete called")
  }

  abstract class BaseController(db: Database) {

    def save {
      db.save
    }

    def update {
      db.update
    }

    def delete {
      db.delete
    }

    // abstract
    def connect

    // an abstract method that returns a String
    def getStatus: String

    // an abstract method that takes a parameter
    def setServerName(serverName: String)
  }

  abstract class WidgetController(db: Database) extends BaseController(db)
}
