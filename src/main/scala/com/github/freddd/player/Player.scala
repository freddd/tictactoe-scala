package com.github.freddd.player

import com.github.freddd.board._
import com.github.freddd.board.Symbol._

/**
 * Representing the common attributes of human players and the AI
 * Created by fred on 3/6/14.
 */
trait Player {
  var name: String = ""
  var _wins: Int = 0
  var _draw: Int = 0
  var _losses: Int = 0
  var moves: List[Map[Int, (Int, Int)]] = List.empty
  var symbol: Symbol = Symbol.UNDEFINED

  def wins: Int = _wins
  def losses: Int = _losses
  def draws: Int = _draw

  def draw: Player = {
    _draw += 1

    this
  }

  def win: Player = {
    _wins += 1

    this
  }

  def loss: Player = {
    _losses += 1

    this
  }
}
