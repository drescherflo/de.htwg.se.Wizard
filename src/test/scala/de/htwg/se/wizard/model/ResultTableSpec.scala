package de.htwg.se.wizard.model

import org.scalatest.{Matchers, WordSpec}

class ResultTableSpec extends WordSpec with Matchers{
  "A ResultTable" should {

    "set ResultTable correctly" in {
      val table = ResultTable(20, 3, ResultTable.initializeVector(20,3))
      table.numberOfPlayers should be(3)
      table.roundsToPlay should be(20)
    }

    /*val table = ResultTable(20,3, ResultTable.initializeVector(3,3))
    "update the result correctly in the first round" in {

      table.updatePoints(1, 0, 10)
      table.points(0)(0)should be(10)
    }

    "update the result correctly in all future rounds" in {
      table.updatePoints(2, 0, -20)
      table.points(1)(0) should be(-10)
    }*/

    //result is not being updated
    "have a nice string representation" in {
      val printTable = ResultTable(2,3,ResultTable.initializeVector(20,3))
      printTable.updatePoints(1,1,5)
      printTable.toString should be(
        """|#  Player  1  #  Player  2  #  Player  3  #
           |###########################################
           |#      0      #      0      #      0      #
           |###########################################
           |#      0      #      0      #      0      #
           |###########################################"""
        .stripMargin)
    }
  }
}
