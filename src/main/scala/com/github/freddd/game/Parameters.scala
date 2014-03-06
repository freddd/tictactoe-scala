package com.github.freddd.game

import com.github.freddd.player.{Player, Mode}
import Mode.Mode


/**
 * Case class representing the game parameters
 * Created by fred on 3/6/14.
 */
class Parameters() {
  var first: Boolean = true
  var mode: Mode = Mode.AI
  var players: List[Player] = List.empty
  var lastWinner: Option[Player] = None
  var starting: Player = null
}
