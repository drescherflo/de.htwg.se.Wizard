package de.htwg.se.wizard.model

import org.scalatest.{Matchers, WordSpec}

class RoundStrategySpec extends WordSpec with Matchers{
  "A RoundStrategy" when {
    "should set rounds based on players input" should {
      val roundManager = RoundManager()
      val roundManager1 = RoundStrategy.execute(3,roundManager)
      "with three players" in {
        roundManager1.getNumberOfPlayers() should be(3)
        roundManager1.numberOfRounds should be(20)
      }
      val roundManager2 = RoundStrategy.execute(4,roundManager)
      "with four Players" in {
        roundManager2.getNumberOfPlayers() should be(4)
        roundManager2.numberOfRounds should be (15)
      }
      val roundManager3 = RoundStrategy.execute(5, roundManager)
      "with five Players" in {
        roundManager3.getNumberOfPlayers() should be(5)
        roundManager3.numberOfRounds should be(12)
      }
    }


  }

}