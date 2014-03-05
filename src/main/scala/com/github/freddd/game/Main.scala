package com.github.freddd.game

import com.github.freddd.board.Level.Level
import com.github.freddd.board.Mode.Mode


/**
 * Main method to run the program
 * Created by fred on 3/5/14.
 */
object Main extends App {
  var running: Boolean = true

  while(running){
    printHeader()
    chooseMode()
  }

  private def printHeader(){
    println(" - - - - - - - - -")
    println(" Tic tac toe ")
    println(" - - - - - - - - -")
  }

  private def chooseMode(): Mode = {
    println(" 1) Play against AI ")
    println(" 2) Play against player ")

    print(" Option: ")
    val mode: Int = Console.readInt()

    mode match {
      case 1 => {
        chooseLevel()

      }
      case 2 => {

      }
      case _ => {

      }
    }
  }

  private def chooseLevel(): Level = {
    println("")
    println(" Please choose level of the AI ")
    println(" 1) Easy ")
    println(" 2) Medium ")
    println(" 3) Impossible ")
    print(" Option: ")

    val level: Int = Console.readInt()
    level match {
      case 1 => {

      }
      case 2 => {

      }
      case 3 => {

      }
      case _ => {

      }
    }
  }

}
