package com.github.freddd.board

/**
 * Representing the markers or symbols placed in a square of the tic tac toe board
 * Created by fred on 3/3/14.
 */
object Symbol extends Enumeration{
  type Symbol = Value
  val X, O, UNDEFINED = Value
}
