package com.github.freddd.game

import com.github.freddd.player.{Player, Level, Mode}
import Mode.Mode
import Level.Level


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
    println(" 1) Player X starts, after that it's alternating turns (X,O,X,O etc) ")
    println(" 2) Either player can win by having three of their symbols (X or O) ")
    println("    in a row (horizontal, vertical or diagonal) ")
    println(" 3) In cases of multiple rounds, the player that won last round will start as O" )
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
        case 1 => {
          Level.EASY
        }
        case 2 => {
          Level.MEDIUM
        }
        case 3 => {
          Level.IMPOSSIBLE
        }
        case _ => {
          Level.UNDEFINED
        }
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

  }

  /**
   * Handling game play parameters
   * @param parameters
   * @return
   */
  private def parameters(parameters: Parameters): Parameters = {
    parameters.mode = mode()

    parameters.mode match {
      case Mode.AI => {

        parameters.level = Some(level())
      }
    }

    parameters.starting = starts(parameters)

    parameters
  }

  /**
   * Deciding who will start
   * @param parameters
   * @return
   */
  private def starts(parameters: Parameters): Player = {
    parameters.first match {
      case false => {
        parameters.lastWinner match {
          case Some(_) => parameters.
        }
      }
    }
  }

}
