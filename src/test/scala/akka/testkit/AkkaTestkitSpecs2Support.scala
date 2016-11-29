package akka.testkit

import akka.actor._
import com.typesafe.config.ConfigFactory
import org.specs2.mutable._

import scala.concurrent.Future

abstract class AkkaTestkitSpecs2Support(_system: ActorSystem) extends TestKit(ActorSystem())
  with After
  with ImplicitSender {

  def this() = this(ActorSystem(
    "FaultHandlingDocSpec",
    ConfigFactory.parseString("""
        akka.loggers = ["akka.event.slf4j.Slf4jLogger"]
        akka.logger-startup-timeout = 30s
        akka.loglevel                         = "ERROR"
        akka.stdout-loglevel                  = "ERROR"
        akka.logging-filter = "akka.event.slf4j.Slf4jLoggingFilter"
        akka.log-dead-letters                 = 0
        akka.log-dead-letters-during-shutdown = off
        akka.log-config-on-start              = off
        akka.event-handlers = ["akka.event.EventHandler$DefaultListener"]
        akka.event-handler-level = "ERROR"
        akka.actor {
          debug {
            receive                      = off
            autoreceive                  = off
            lifecycle                    = off
            fsm                          = off
            event-stream                 = off
          }
        }
        akka.remote {
          log-sent-messages = off
          log-received-messages = off
        }
                              """)
  ))

  def after: Future[Terminated] = system.terminate()
}

