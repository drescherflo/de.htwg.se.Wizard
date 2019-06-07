package de.htwg.se.wizard.model

import de.htwg.se.wizard.model.cards.{Card, JesterCard}
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable.ListBuffer

class PlayerSpec extends WordSpec with Matchers {

  "A Player" when {
    "new" should {
      val player = Player("Name")
      "have a name" in {
        player.name should be("Name")
      }
      "have a nice String representation" in {
        player.toString should be("Name")
      }

      "has no cards" in {
        player.playerCards should be(None)
      }

      "get the correct String for his turn" in {
        val player = Player("TestPlayer")
        val list = List[Card](JesterCard(Some(player)))
        val player1 = player.copy(playerCards = Some(list))
        Player.playerTurn(player1, 1) should startWith(
          """Round 1 - Player: TestPlayer
            |Select one of the following cards:
            |{ cards/Jester }""".stripMargin
        )
      }

        "get correct string for stitches" in {
          val player = Player("TestPlayer")
          val list = List[Card](JesterCard(Some(player)))
          val player1 = player.copy(playerCards = Some(list))
          Player.playerPrediction(player1,1,Some("blue")) should startWith(
            """Round 1 - Player: TestPlayer
              |Trump Color: blue
              |Your Cards: { cards/Jester }
              |Enter the amount of stitches you think you will get: """.stripMargin)


        }



      }
      /*
      "get the correct String for stitches" in {
        player.playerCards = Some(ListBuffer(JesterCard(Some(player))))
        Player.playerPrediction(player, 3, Some("blue")) should be(
          """Round 3 - Player: Name
            |Trump Color: blue
            |Your Cards: { C:Jester }
            |Enter the amount of stitches you think you will get: """.stripMargin)
      }*/
    }

}
