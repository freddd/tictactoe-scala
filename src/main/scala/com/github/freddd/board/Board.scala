package com.github.freddd.board

import Symbol._

/**
 * Representing the tic tac toe board
 * Created by fred on 3/3/14.
 */
class Board {
  var board = Array.tabulate[Option[Symbol]](3,3)((x, y) => None) //Array.ofDim[Option[Symbol]](3,3)

  /**
   * Convenience method to move a symbol into a square
   * @param symbol X or O
   * @param x x-axis
   * @param y y-axis
   */
  def move(symbol: Symbol, x: Int, y: Int): Boolean = validMove(x,y) match {
    case true => board(x)(y) = Some(symbol); true
    case false => false
  }

  /**
   * Convenience method to make sure that the move is valid (and not out of bounds or trying to populate a square that is already populated
   * @param x x-axis
   * @param y y-axis
   * @return
   */
  def validMove(x: Int, y: Int): Boolean = (x <= 2 && y <= 2) && !board(x)(y).isDefined

  /**
   * Convenience method to reset the board to play another game
   */
  def reset(): Unit = {board = Array.tabulate[Option[Symbol]](3,3)((x, y) => None)}

  /**
   * Convenience method to know whether the game is filled or not
   * @return
   */
  def filled: Boolean = board forall { p => p forall { p2 => p2.isDefined} }

  /**
   * Convenience method to know whether or not the game is a draw
   * @return
   */
  def draw: Boolean = {
    filled match {
      case true => !win(Symbol.O) && !win(Symbol.X)
      case _ => false
    }
  }

  /**
   * Convenience method to know if the game has been won by player with symbol
   * @param symbol X or O
   * @return
   */
  def win(symbol: Symbol): Boolean = horizontal(symbol) || vertical(symbol) || diagonal(symbol)

  /**
   * Returning the winner
   * @return
   */
  def winner: Symbol = {
    draw match {
      case true => Symbol.UNDEFINED
      case _ => {
        win(Symbol.O) match {
          case true => Symbol.O
          case _ => Symbol.X
        }
      }
    }
  }

  /**
   * Used by <method>win</method> to assess if either player has 3 symbols in a row horizontally
   * @param symbol X or O
   * @return
   */
  private[board] def horizontal(symbol: Symbol): Boolean = {
    board exists ( row => row forall (col => col.isDefined && col.get.equals(symbol)))
  }

  /**
   * Used by <method>win</method> to assess if either player has 3 symbols in a row vertically
   * @param symbol X or O
   * @return
   */
  private[board] def vertical(symbol: Symbol): Boolean = {
    //board.flatten.zipWithIndex.filter(p => p._2 % 3 == 0).forall(p => p._1.isDefined && p._1.get.equals(symbol))

    val all: Array[Option[Symbol]] = board.flatten
    validate(symbol, all(0)) && validate(symbol, all(3)) && validate(symbol, all(6)) ||
    validate(symbol, all(1)) && validate(symbol, all(4)) && validate(symbol, all(7)) ||
    validate(symbol, all(2)) && validate(symbol, all(5)) && validate(symbol, all(8))
  }

  /**
   * Validating whether or not a square is populated, if it's populated it validates whether or not the
   * symbol is the same as the one in the square
   * @param symbol X or O
   * @param square A square on the board
   * @return
   */
  private[board] def validate(symbol: Symbol, square: Option[Symbol]): Boolean = {
    square match {
      case Some(_) => square.get.equals(symbol)
      case _ => false
    }
  }

  /**
   * Used by <method>win</method> to assess if either player has 3 symbols in a row diagonally
   * @param symbol X or O
   * @return
   */
  private[board] def diagonal(symbol: Symbol): Boolean = {
    val all: Array[Option[Symbol]] = board.flatten
    validate(symbol, all(0)) && validate(symbol, all(4)) && validate(symbol, all(8)) ||
    validate(symbol, all(2)) && validate(symbol, all(4)) && validate(symbol, all(6))
  }

  /**
   * Returning the current state of the board
   * @return
   */
  override def toString: String = {
    var str: String = ""

    board.foreach { row => str += " | " + row(0).getOrElse(" ") + " | " + row(1).getOrElse(" ") + " | " + row(2).getOrElse(" ") + " | \n"}

    str
  }
}
