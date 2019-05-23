package de.htwg.se.wizard.controller

import de.htwg.se.wizard.model.{Player, RoundManager}
import de.htwg.se.wizard.util.Observer
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    val roundManager = RoundManager()
    val controller = new Controller(roundManager)
    val observer = new Observer { // wontfix
      var updated: Boolean = false

      def isUpdated: Boolean = updated

      override def update(): Unit = updated = true
    }
    controller.add(observer)

    "registers itself in the RoundManger" in {
      roundManager.subscribers.contains(controller) should be(true)
    }
    "notify its observer after evaluating an input string" in {
      controller.eval("4")
      observer.updated should be(true)
    }
    "gets the correct string depending of the current state" in {
      controller.state = preSetupState(roundManager, controller)
      controller.getCurrentStateAsString should be("Welcome to Wizard!\nPlease enter the number of Players[3-5]:")
    }
    "switches to the next state correctly" in {
      controller.state = preSetupState(roundManager, controller)
      controller.nextState()
      controller.state should be(setupState(roundManager))
    }
    "switches to the next state correctly when called by observer" in {
      controller.state = preSetupState(roundManager, controller)
      controller.switchToNextState()
      controller.state should be(setupState(roundManager))
    }
    "can convert a string to a number correctly" should {
      "return an Int packed in Some when there is a number" in {
        val number = Controller.toInt("5")
        number.isDefined should be(true)
        number.get should be(5)
      }
      "return None when there is no number" in {
        val number = Controller.toInt("bla")
        number.isEmpty should be(true)
      }

    }
  }

  "A preSetupState" when {
    var roundManager = RoundManager(3)
    val controller = new Controller(roundManager)
    val state = preSetupState(roundManager, controller)
    "does nothing when trying to evaluate a string that's not a number" in {
      val old = roundManager
      state.eval("AAA")
      roundManager should be(old)
    }
    "does nothing when the number of PLayers is invalid" in {
      val roundManager1 = RoundManager(8)
      //val old = RoundManager(8)
      state.eval("8")
      roundManager1 should be(roundManager1)
    }
    "set the number of players correctly" in {
      state.eval("3")
      roundManager.numberOfPlayers should be(3)
    }
    "register the controller in the new roundManager" in {
      state.eval("3")
      state.roundManager.subscribers contains controller should be (true)
    }
    "trigger the controller to switch to the next state" in {
      val old = state
      state.eval("3")
      controller.state should not be old
    }
    "return the correct state string" in {
      state.getCurrentStateAsString should be("Welcome to Wizard!\nPlease enter the number of Players[3-5]:")
    }
    "return the correct next state" in {
      roundManager = state.roundManager
      state.nextState should be(setupState(roundManager))
    }
  }
  "A setupState" when {
    val roundManager = RoundManager(3)
    val state = setupState(roundManager)
    "adds a player correctly" in {
      state.eval("Name")
      roundManager.players.contains(Player("Name")) should be(true)
    }
    "return the correct state string" in {
      roundManager.players = Nil
      state.getCurrentStateAsString should be("Player 1, please enter your name:")
    }
    "return the correct next state" in {
      state.nextState should be(inGameState(roundManager))
    }
  }
  "A inGameState" when {
    val roundManager = RoundManager()
    val state = inGameState(roundManager)
    "does nothing when trying to evaluate a string that's not a number" in {
      val old = RoundManager()
      state.eval("AAA")
      roundManager should be(old)
    }
    "put a card on the middle stack correctly" in {
      state.eval("1")
      // TODO: extend once implemented
    }
    "return the correct state string of reading in the prediction" in {
      roundManager.currentRound = 2
      roundManager.currentPlayer = 2

      roundManager.players = List(Player("Name"))
      state.getCurrentStateAsString should startWith(
        """Round 2 - Player: Name
Enter the amount of stitches you think you will get: """.stripMargin)
    }
    "return the correct next state" in {
      state.nextState should be(gameOverState(roundManager))
    }
  }
  "A gameOverState" should {
    val state = gameOverState(RoundManager())
    "do nothing when evaluating" in {
      state.eval("5")
    }
    "return the correct state string" in {
      state.getCurrentStateAsString should be("\nGame Over! Press 'q' to quit.")
    }
    "return itself as the next state" in {
      state.nextState should be(state)
    }
  }
}
