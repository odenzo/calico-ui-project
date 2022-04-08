package com.odenzo.investing.fe

import cats.effect.*
import cats.effect.implicits.*
import cats.effect.kernel.Outcome
import cats.effect.unsafe.IORuntime
import cats.effect.unsafe.implicits.*
import com.raquo.laminar.api.L.*

import org.scalajs.dom
import org.scalajs.dom.*
import scribe.Level

object LaminarMain {

  def main(args: Array[String]): Unit = {
    scribe.info(s"Laminar Main Running")
    val minLevel = Level.Debug
    // ScribeJSLoggingConfig.initJS(minLevel)
    scribe.warn(s"Min Log is $minLevel")

    val activityElement = document.getElementById("activity")

    scribe.info("Making Second Controller")
    val controller = LaminarController(Option.empty[dom.Element])
    scribe.info(s"Made IO-C ${pprint(controller)}")

    val elem         = dom.document.querySelector("#laminar")
    val content: Div = div(controller.pageContainer())
    renderOnDomContentLoaded(elem, content)

    scribe.info("Existing Main RUN")
  }
}
