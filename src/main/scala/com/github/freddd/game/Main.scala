package com.github.freddd.game

import com.github.freddd.player._
import scala.util.Random
import com.github.freddd.board._
import com.github.freddd.player.Mode.Mode
import com.github.freddd.player.Mode
import com.github.freddd.player.AI
import com.github.freddd.player.Level.Level
import com.github.freddd.player.Level
import com.github.freddd.board.Symbol._

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
    params = play(params)

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
    println(" c) In cases of multiple rounds, the player that won last round will start as O")
    println("    if last round was a draw the order will remain the same ")
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
  private def play(parameters: Parameters): Parameters = {
    val board = new Board()
    println("")
    println(" Starting is player (X): " + parameters.starting.name + " currently holding " + parameters.starting.wins + " and " + parameters.starting.losses)
    println(" ----------------------- ")
    println(" X,Y (1,1 -> 3,3) ")
    println(board.toString)
    println("")

    var current = parameters.starting.symbol
    var gameOver = false

    while (!gameOver) {

      parameters.mode match {
        case Mode.AI => parameters.players.find(p => p.isInstanceOf[AI]).get.symbol.equals(current) match {
          case true => moveAI(board, Level.UNDEFINED) // TODO change to level inputed by player
          case false => playerInput(board, current)
        }
        case Mode.PLAYER => playerInput(board, current)
      }

      current = current match {
        case Symbol.X => Symbol.O
        case _ => Symbol.X
      }

      println(board.toString)
      gameOver = board.draw || board.win(Symbol.X) || board.win(Symbol.O) // TODO create a convenience method in Board
    }

    setPlayerData(board.draw, parameters, parameters.players.find(p => p.symbol.equals(board.winner)))
  }

  /**
   * Calls for determining the AI's next move
   */
  private def moveAI(board: Board, level: Level){

  }

  /**
   * Ask the player for his/her next move
   * @param board the board
   * @param current the current symbol
   */
  private def playerInput(board: Board, current: Symbol){
    var inputNOK = true

    while(inputNOK){
      print(" Player: " + current +  " - Play (X, Y): ")
      val move: String = Console.readLine()


      try {
        val x = move.split(",")(0).toInt - 1
        val y = move.split(",")(1).toInt - 1

        board.validMove(x, y) match {
          case true => board.move(current, x, y); inputNOK = false
          case false => println(" Please try again, there is already a symbol in the square or it's out of bounds ")
        }
      } catch {
        case e: NumberFormatException => println(" It has to be a number.... ")
      }
    }
  }

  /**
   * Handling game play parameters
   * @param parameters new parameters or parameters set in the previous round
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
   * Setting the player data after a game
   * @param draw whether or not the game was a draw
   * @param params game parameters
   * @param winner the winner of the previous game (if there was one)
   * @return
   */
  private def setPlayerData(draw: Boolean, params: Parameters, winner: Option[Player]): Parameters = {
    draw match {
      case true => println( "A draw!! Better luck next time"); params.players = params.players.map(p => p.draw)
      case _ => {
        val w = winner.get
        w.win
        params.lastWinner = Some(w)

        val loser = params.players.find(p => !p.symbol.equals(w.symbol)).get
        loser.loss

        println(" The winner is: " + w.name + "currently holding a record of " + w.wins + " and " + w.losses + " losses")
      }
    }

    params
  }

  /**
   * Asking for player name
   * @param number the order of the player being asked for its name
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
    val starting = parameters.first match {
      case true => Random.shuffle(parameters.players).head
      case false => parameters.players.find(p => !p.equals(parameters.lastWinner.get)).getOrElse(parameters.lastWinner.get)
    }

    parameters.players.find(p => !p.equals(starting)).get.symbol = Symbol.O
    starting.symbol = Symbol.X

    starting
  }

}
