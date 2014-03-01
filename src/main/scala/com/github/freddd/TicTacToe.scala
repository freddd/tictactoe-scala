package com.github.freddd

import org.scalatra._
import scalate.ScalateSupport

class TicTacToe extends TictactoeStack {

  get("/") {
    contentType="text/html"

    layoutTemplate("/WEB-INF/templates/views/hello-scalate.jade")
  }
  
}
