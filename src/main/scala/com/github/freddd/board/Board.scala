package com.github.freddd.board

/**
 * Created by fred on 3/3/14.
 */
class Board {
  var board = Array.ofDim[String](3,3)

  def move(symbol: String, x: Int, y: Int): Boolean = validMove(x,y) match {
    case true => board(x)(y) = symbol; true;
    case _ => false;
  }
  def validMove(x: Int, y: Int): Boolean = (x <= 2 && y <= 2) && board(x)(y) == null;
  def reset: Unit = {board = Array.ofDim[String](3,3)}
  def gameCompleted: Boolean = board forall { p => p forall { p2 => !p2.isEmpty} }
  def draw: Boolean = ???
  def win: Boolean = ???
}
