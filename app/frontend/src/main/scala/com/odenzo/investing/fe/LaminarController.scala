package com.odenzo.investing.fe

import cats.*
import cats.data.*
import cats.effect.{IO, Resource}
import cats.implicits.*
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.html

class LaminarController(parentId: Option[dom.Element]):
  scribe.info("Laminar COntroller being constructred")

  val pagesMenu: NonEmptyList[Page] = NonEmptyList.of(Page("Home", "Home"), Page("E-Trade", "etrade"), Page("IBKR FlexQueries", "ibkr"))

  val currentPage: Var[Page]                           = Var(pagesMenu.head)
  val mainContainer: ReactiveHtmlElement[html.Element] = main(child <-- ChangePage(currentPage.signal))
  val pages                                            = new ToC(this)

  private def makeMenuBar(): ReactiveHtmlElement[html.Element] =
    nav(pagesMenu.map(v => makeMenuButton(v.name, v.key)).toList)

  def ChangePage(value: StrictSignal[Page]): Signal[ReactiveHtmlElement[html.Div]] =
    value.map {
      case Page(_, "Home")   => pages.home()
      case Page(_, "etrade") => pages.etrade()
      case Page(_, "ibkr")   => pages.ibkr()
      case Page(_, _)        => pages.home()
    }

  private def makeMenuButton(label: String, key: String): ReactiveHtmlElement[html.Button] =
    button(
      className            := "MenuItem",
      idAttr               := s"Menu_$label",
      dataAttr("pageCode") := key,
      label,
      fontWeight <-- currentPage.signal.map(cp => if cp.key == key then "bold" else "normal"),
      onClick.map { ev =>
        val pageCode = ev.target.getAttribute("pageCode")
        pagesMenu.find(_.key === key).getOrElse(pagesMenu.head)
      } --> currentPage.writer
    )

  def pageContainer(): List[ReactiveHtmlElement[html.Element]] =
    // Page container items will live across page changes
    scribe.info(s"Page Container Called")
    val navheader = header(makeMenuBar())
    pages.home()
    List(navheader, mainContainer)
