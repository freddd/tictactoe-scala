package com.github.freddd.player

import com.github.freddd.board._
import com.github.freddd.board.Symbol._

/**
 * Representing the common attributes of human players and the AI
 * Created by fred on 3/6/14.
 */
trait Player {
  var name: String = ""
  var wins: Int = 0
  var losses: Int = 0
  var moves: List[Map[Int, (Int,Int)]] = List.empty
  var symbol: Symbol = Symbol.UNDEFINED
}
