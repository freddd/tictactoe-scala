package com.github.freddd.board

import org.specs2.mutable._
import Symbol._

/**
 * Tests for the tic tac toe board
 * Created by fred on 3/3/14.
 */
class BoardSpec extends Specification {

  "innerBoard" should {
    val board: Board = new Board()

    "columns should be of length 3" in {
      board.board.length mustEqual 3
    }

    "rows should be of length 3" in {
      board.board(0).length mustEqual 3
    }
  }

  "board.validMove" should {
    val board: Board = new Board()

    "Fail if the move is outside of bounds" in {
      board.validMove(3,3) must beFalse
    }

    "Fail if the square is already occupied" in {
      board.validMove(3,3) must beFalse
    }

    "Succeed if the move is within bound and the square is empty" in {
      board.validMove(1,1) must beTrue
    }
  }

  "board.filled" should {
    val board: Board = new Board()

    "Fail any square is empty" in {
      board.filled must beFalse
    }


    "Succeed if no square is empty" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.X,0,1)
      b.move(Symbol.X,0,2)
      b.move(Symbol.X,1,0)
      b.move(Symbol.X,1,1)
      b.move(Symbol.X,1,2)
      b.move(Symbol.X,2,0)
      b.move(Symbol.X,2,1)
      b.move(Symbol.X,2,2)

      b.filled must beTrue

      b.reset()
    }

  }

  "board.move" should {
    "Succeed if there is a symbol in populated square" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0) && b.board(0)(0).equals(Some(Symbol.X)) must beTrue
    }

    "Fail if there is a symbol in populated square" in {
      val b: Board = new Board()
      b.move(Symbol.X,3,3) must beFalse
    }
  }


  "board.reset" should {
    "Succeed if there is no square populated" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.reset()
      b.board(0)(0).isDefined must beFalse
    }
  }

  "board.validate" should {
    val b: Board = new Board()
    "Succeed if the same symbol is in the square as being sent in" in {
      b.validate(Symbol.X, Some(Symbol.X)) must beTrue
    }

    "Fail if the same symbol is in the square as being sent in" in {
      b.validate(Symbol.X, Some(Symbol.O)) must beFalse
    }
  }

  "board.diagonal" should {

    "Succeed if square 1, 5, 9 all have the same symbol" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.X,1,1)
      b.move(Symbol.X,2,2)

      b.diagonal(Symbol.X) must beTrue
    }

    "Succeed if square 3, 5, 7 all have the same symbol" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,2)
      b.move(Symbol.X,1,1)
      b.move(Symbol.X,2,0)

      b.diagonal(Symbol.X) must beTrue
    }

    "Fail if square 3, 5, 7 does not have the same symbol" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,2)
      b.move(Symbol.O,1,1)
      b.move(Symbol.X,2,0)

      b.diagonal(Symbol.X) must beFalse
    }
  }

  "board.vertical" should {

    "Succeed if square 1, 4, 7 all have the same symbol" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.X,1,0)
      b.move(Symbol.X,2,0)

      b.vertical(Symbol.X) must beTrue
    }

    "Fail if square 1, 4, 7 does not have the same symbol" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,1,0)
      b.move(Symbol.X,2,0)

      b.vertical(Symbol.X) must beFalse
    }
  }

  "board.horizontal" should {

    "Succeed if square 1, 2, 3 all have the same symbol" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.X,0,1)
      b.move(Symbol.X,0,2)

      b.horizontal(Symbol.X) must beTrue
    }

    "Fail if square 1, 2, 3 does not have the same symbol" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,0,1)
      b.move(Symbol.X,0,2)

      b.horizontal(Symbol.X) must beFalse
    }
  }


  "board.win" should {

    "Succeed if X or O has populated three consecutive squares" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,0,1)
      b.move(Symbol.X,0,2)
      b.move(Symbol.O,1,0)
      b.move(Symbol.X,1,1)
      b.move(Symbol.O,1,2)
      b.move(Symbol.O,2,0)
      b.move(Symbol.X,2,1)
      b.move(Symbol.X,2,2)

      b.win(Symbol.X) must beTrue
    }

    "Fail if neither X nor O has populated three consecutive squares" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,0,1)
      b.move(Symbol.X,0,2)
      b.move(Symbol.O,1,0)
      b.move(Symbol.X,1,1)
      b.move(Symbol.O,1,2)
      b.move(Symbol.O,2,0)
      b.move(Symbol.X,2,1)
      b.move(Symbol.O,2,2)

      b.win(Symbol.X) must beFalse
    }
  }

  "board.winner" should {

    "Return X if X has populated three consecutive squares" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,0,1)
      b.move(Symbol.X,0,2)
      b.move(Symbol.O,1,0)
      b.move(Symbol.X,1,1)
      b.move(Symbol.O,1,2)
      b.move(Symbol.O,2,0)
      b.move(Symbol.X,2,1)
      b.move(Symbol.X,2,2)

      b.winner.equals(Symbol.X) must beTrue
    }

    "Return UNDEFINED if the game is a draw" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,0,1)
      b.move(Symbol.X,0,2)
      b.move(Symbol.O,1,0)
      b.move(Symbol.X,1,1)
      b.move(Symbol.O,1,2)
      b.move(Symbol.O,2,0)
      b.move(Symbol.X,2,1)
      b.move(Symbol.O,2,2)

      b.winner.equals(Symbol.UNDEFINED) must beTrue
    }

  }

  "board.draw" should {

    "Succeed if neither X nor O has populated three consecutive squares" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,0,1)
      b.move(Symbol.X,0,2)
      b.move(Symbol.O,1,0)
      b.move(Symbol.X,1,1)
      b.move(Symbol.O,1,2)
      b.move(Symbol.O,2,0)
      b.move(Symbol.X,2,1)
      b.move(Symbol.O,2,2)

      b.draw must beTrue
    }

    "Fail if X or O has populated three consecutive squares" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,0,1)
      b.move(Symbol.X,0,2)
      b.move(Symbol.O,1,0)
      b.move(Symbol.X,1,1)
      b.move(Symbol.O,1,2)
      b.move(Symbol.O,2,0)
      b.move(Symbol.X,2,1)
      b.move(Symbol.X,2,2)

      b.draw must beFalse
    }
  }

  "board.gameOver" should {

    "Succeed if X or O has populated three consecutive squares" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,0,1)
      b.move(Symbol.X,0,2)
      b.move(Symbol.O,1,0)
      b.move(Symbol.X,1,1)
      b.move(Symbol.O,1,2)
      b.move(Symbol.O,2,0)
      b.move(Symbol.X,2,1)
      b.move(Symbol.X,2,2)

      b.gameOver must beTrue
    }

    "Succeed if its a draw" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,0,1)
      b.move(Symbol.X,0,2)
      b.move(Symbol.O,1,0)
      b.move(Symbol.X,1,1)
      b.move(Symbol.O,1,2)
      b.move(Symbol.O,2,0)
      b.move(Symbol.X,2,1)
      b.move(Symbol.O,2,2)

      b.gameOver must beTrue
    }

    "Fail if its not yet a draw and nobody has won" in {
      val b: Board = new Board()
      b.move(Symbol.X,0,0)
      b.move(Symbol.O,0,1)
      b.move(Symbol.X,0,2)
      b.move(Symbol.O,1,2)
      b.move(Symbol.O,2,0)
      b.move(Symbol.X,2,1)
      b.move(Symbol.O,2,2)

      b.gameOver must beFalse
    }

  }
}