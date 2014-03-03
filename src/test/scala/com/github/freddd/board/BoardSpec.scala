package com.github.freddd.board

import org.specs2.mutable._

/**
 * Created by fred on 3/3/14.
 */
class BoardSpec extends Specification {
  var board: Board = new Board()
  var innerBoard: Array[Array[Option[Symbol.Symbol]]] = board.board

  "innerBoard" should {
    "columns should be of length 3" in {
      innerBoard.length mustEqual 3
    }

    "rows should be of length 3" in {
      innerBoard(0).length mustEqual 3
    }
  }

  "board.validMove" should {

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

  "board.completed" should {
    "Fail any square is empty" in {
      board.completed must beFalse
    }

    /*
    "Succeed if no square is empty" in {
      board.completed must beTrue
    }
    */
  }
}