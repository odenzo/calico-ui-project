package com.odenzo.investing.fe.pages

import cats.effect.IO

import com.raquo.laminar.api.L.*
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

case class OAuthSessionData(foo: String)
object ETrade {

  // val sessionData = Var[Option[OAuthSessionData]](Option.empty)

  val loginStatus: Div = div(
    p("Current ETrade Login Data:")
//    p(child <-- sessionData.toObservable.map {
//      case None       => span("You are not logged in.")
//      case Some(auth) => span(s"You are Logged In at $auth")
//    })
  )

  def content: Div =
    val content = div(className := "PageTop", idAttr := "pageTop", p("Welcome to ETrade For Now"), p(), loginStatus)
    //  updateLoginState(using bridge)
    content

//  /** Note this is async so will return a "flash" update after initial rendering (probably) */
//  def updateLoginState(using bridge: BridgeService): Unit = {
//    val rq           = AuthCalls.getAuthSessionRq(using bridge.ctx)
//    val rs: IO[Unit] = AuthCalls.getAuthSession(rq)(using bridge.client).map(sessionData.set)
//    import cats.effect.unsafe.implicits.given
//    rs.unsafeRunAsync {
//      case Left(err) => scribe.error("Error Calling getAuthSession")
//      case Right(_)  => scribe.info("SessoinData has been updated.")
//    }
//  }
}
