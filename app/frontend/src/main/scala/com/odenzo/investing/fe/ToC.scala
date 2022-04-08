package com.odenzo.investing.fe

import com.odenzo.investing.fe.pages.ETrade
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

class ToC(controller: LaminarController):
  private val container: ReactiveHtmlElement[html.Element] = controller.mainContainer

  def home(): ReactiveHtmlElement[html.Div] =
    val content = div(className := "PageTop", idAttr := "pageTop", h1("Welcome to Investment Overviews"))
    content

  def etrade(): ReactiveHtmlElement[html.Div] = {
    ETrade.content

  }
  def ibkr(): ReactiveHtmlElement[html.Div] = div(className := "PageTop", idAttr := "pageTop", p("Welcome to IBKR For Now"))

case class CategoryRow(code: Int, label: String, isExluded: Boolean, isGST: Boolean)
