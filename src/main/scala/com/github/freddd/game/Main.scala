package com.github.freddd.game

import com.github.freddd.player._
import Mode.Mode
import Level.Level
import scala.util.Random
import com.github.freddd.board._
import com.github.freddd.player.Mode.Mode
import com.github.freddd.player.Mode
import com.github.freddd.player.AI
import com.github.freddd.player.Level.Level
import com.github.freddd.player.Level


/**
 * Main method to run the program
 * Created by fred on 3/5/14.
 */
object Main extends App {
  var running: Boolean = true
  var params: Parameters = new Parameters()

  while (running) {

    // Init
    printHeader()
    params = parameters(params)

    // Play game
    play(params)

    // Play again?
    running = again()
    params.first = !running
  }

  /**
   * Printing a header on start of selection
   */
  private def printHeader() {
    println("")
    println(" - - - - - - - - - ")
    println(" Tic tac toe ")
    println(" - - - - - - - - - ")
    println(" Rules: ")
    println(" a) Player X starts (random), after that it's alternating turns (X,O,X,O etc) ")
    println(" b) Either player can win by having three of their symbols (X or O) ")
    println("    in a row (horizontal, vertical or diagonal) ")
    println(" c) In cases of multiple rounds, the player that won last round will start as O" )
    println("    if last round was a draw the order will be the same ")
    println(" - - - - - - - - - ")
  }

  /**
   * Select what mode you want to play, vs AI or player
   */
  private def mode(): Mode = {
    var mode: Mode = Mode.UNDEFINED

    while (mode.equals(Mode.UNDEFINED)) {
      println(" 1) Play against AI (Not yet implemented) ")
      println(" 2) Play against player ")

      print(" Option: ")
      val m: Int = Console.readInt()

      mode = m match {
        case 1 => Mode.AI
        case 2 => Mode.PLAYER
        case _ => Mode.UNDEFINED
      }
    }
    mode
  }

  /**
   * Select level of AI (if AI was chosen in the previous dialog)
   */
  private def level(): Level = {
    var level: Level = Level.UNDEFINED

    while (level.equals(Level.UNDEFINED)) {

      println("")
      println(" Please choose level of the AI ")
      println(" 1) Easy ")
      println(" 2) Medium ")
      println(" 3) Impossible ")
      print(" Option: ")

      val l: Int = Console.readInt()
      level = l match {
        case 1 => Level.EASY
        case 2 => Level.MEDIUM
        case 3 => Level.IMPOSSIBLE
        case _ => Level.UNDEFINED
      }
    }
    level
  }

  /**
   * Want to play again?
   * @return if the user wants to play another game
   */
  private def again(): Boolean = {

    var decided: Boolean = false
    var again: Boolean = false

    while (!decided) {

      println("")
      println(" ----------------- ")
      println(" Want to play again? ")
      println(" Yes) I love playing Tic tac toe ")
      println(" No)  I hate losing ")
      print(" Option: ")

      val a: String = Console.readLine()
      again = a match {
        case "Yes" | "yes" | "y" => {
          decided = true
          true
        }
        case "No" | "no" | "n" => {
          decided = true
          false
        }
        case _ => {
          println(" Please type 'Yes' or 'No' ")
          false
        }
      }
    }

    again
  }

  /**
   * Handling the actual game play
   */
  private def play(parameters: Parameters) {
    val board = new Board()
    println("")
    println(" Starting is player (X): " + parameters.starting.name + " currently holding " + parameters.starting.wins + " and " + parameters.starting.losses)
    println(" ----------------------- ")
    println(" " + board.toString)
    println("")

    while(!board.draw || board.win(Symbol.X) || board.win(Symbol.O)){
      println(" ")
    }

    parameters.lastWinner = parameters.players.find()
  }

  /**
   * Handling game play parameters
   * @param parameters
   * @return
   */
  private def parameters(parameters: Parameters): Parameters = {
    // Ask user for mode
    parameters.mode = mode()

    // Ask for first player name
    parameters.players = parameters.players ++ List(new Human(playerInfo(1)))

    // Either ask for AI level or Player 2 name
    parameters.mode match {
      case Mode.AI => parameters.players = parameters.players ++ List(new AI(level()))
      case _ => parameters.players = parameters.players ++ List(new Human(playerInfo(2)))
    }

    // Decide who is starting
    parameters.starting = starts(parameters)

    parameters
  }

  /**
   * Asking for player name
   * @param number
   * @return
   */
  private def playerInfo(number: Int): String = {
    println("")
    println(" What is the name of player " + number + " ?")
    print(" Name: ")
    Console.readLine()
  }

  /**
   * Deciding who will start, if this is the first match the player who will start is chosen on random
   * Else it will chose the player who lost the last gamee
   * @param parameters game parameters
   * @return the player that is to start
   */
  private def starts(parameters: Parameters): Player = {
    parameters.first match {
      case true => Random.shuffle(parameters.players).head
      case false => parameters.players.find(p => !p.equals(parameters.lastWinner.get)).getOrElse(parameters.lastWinner.get)
    }
  }

}
