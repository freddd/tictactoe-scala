package com.github.freddd

import org.scalatra.test.specs2.ScalatraSpec


// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class TicTacToeSpec extends ScalatraSpec {
  def is =
    "GET / on TicTacToe" ^
      "should return status 200" ! root200 ^
      end

  addServlet(classOf[TicTacToe], "/*")

  def root200 = get("/") {
    status must_== 200
  }
}
