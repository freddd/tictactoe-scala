package com.github.freddd.game

import com.github.freddd.player.{Player, Level, Mode}
import Level.Level
import Mode.Mode


/**
 * Case class representing the game parameters
 * Created by fred on 3/6/14.
 */
class Parameters() {
  var first: Boolean = true
  var mode: Mode = Mode.AI
  var players: Seq[Player] = Seq.empty
  var lastWinner = Option[Player]
  var starting: Player = null
}
