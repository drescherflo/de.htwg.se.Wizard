package de.htwg.se.wizard.model.modelComponent.modelBaseImpl.cards

import de.htwg.se.wizard.model.modelComponent.modelBaseImpl.Player
import org.scalatest.{Matchers, WordSpec}

class WizardCardSpec extends WordSpec with Matchers {
  "A WizardCard" when {
    "is a WizardCard without owner" should {
      val wizardCard = WizardCard()
      "is a wizard" in {
        wizardCard.isWizard should be(true)
      }
      "is no jester" in {
        wizardCard.isJester should be(false)
      }
      "has no colour" in {
        wizardCard.hasColor should be(false)
      }
      "has no owner" in {
        wizardCard.hasOwner should be(false)
        wizardCard.ownerName should be("unknown")
      }

      "is able to store itself in an xml representation and restore successfully" in {
        val xml = wizardCard.toXML
        val newCard = wizardCard.fromXML(xml)
        newCard should be(wizardCard)
      }

      "is able to store itself in a json representation and restore successfully" in {
        val json = wizardCard.toJson
        val newCard = wizardCard.fromJson(json)
        newCard should be(wizardCard)
      }
    }

    "is a WizardCard with owner 'TestPlayer'" should {
      val wizardCardWithOwner = WizardCard(Some(Player("TestPlayer")))
      "has owner" in {
        wizardCardWithOwner.hasOwner should be(true)
      }
      "has owner 'TestPlayer'" in {
        wizardCardWithOwner.ownerName should be("TestPlayer")
      }
      "is able to store itself in an xml representation and restore successfully" in {
        val xml = wizardCardWithOwner.toXML
        val newCard = wizardCardWithOwner.fromXML(xml)
        newCard should be(wizardCardWithOwner)
      }

      "is able to store itself in a json representation and restore successfully" in {
        val json = wizardCardWithOwner.toJson
        val newCard = wizardCardWithOwner.fromJson(json)
        newCard should be(wizardCardWithOwner)
      }
    }

    "should set Owner correctly" in {
      val wizardCardWithoutOwner = WizardCard()
      val testPlayer = Player("TestPlayer")
      val cardWithOwner = Card.setOwner(wizardCardWithoutOwner, testPlayer)
      cardWithOwner.ownerName should be("TestPlayer")
    }

    "has the right String representation" should {
      val wizardCard = WizardCard()
      "Have a nice String representation" in {
        wizardCard.toString should be("cards/Wizard")
      }
    }
  }
}
