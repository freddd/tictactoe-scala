package com.github.freddd

import org.scalatra._
import scalate.ScalateSupport

class TicTacToe extends TictactoeStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }
  
}
