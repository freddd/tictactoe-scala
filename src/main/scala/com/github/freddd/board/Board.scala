package com.github.freddd.board

import Symbol._

/**
 * Created by fred on 3/3/14.
 */
class Board {
  var board = Array.ofDim[Option[Symbol]](3,3)

  def move(symbol: Symbol, x: Int, y: Int): Boolean = validMove(x,y) match {
    case true => board(x)(y) = Some(symbol); true
    case _ => false
  }
  def validMove(x: Int, y: Int): Boolean = (x <= 2 && y <= 2) && board(x)(y) == null
  def reset(): Unit = {board = Array.ofDim[Option[Symbol]](3,3)}
  def completed: Boolean = board forall { p => p forall { p2 => !p2.isEmpty} }
  def draw: Boolean = {
    completed match {
      case true => !win(Symbol.O) && !win(Symbol.X)
      case _ => false
    }
  }
  def win(symbol: Symbol): Boolean = {
    ???
  }
}
