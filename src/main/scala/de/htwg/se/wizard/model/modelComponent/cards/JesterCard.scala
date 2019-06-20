package de.htwg.se.wizard.model.modelComponent.cards

import de.htwg.se.wizard.model.SpecificPlayerInterface
import de.htwg.se.wizard.model.modelComponent.Player

case class JesterCard(owner: Option[SpecificPlayerInterface] = None) extends Card(owner) {
  def hasColor: Boolean = false

  def isWizard: Boolean = false

  def isJester: Boolean = true

  override def getStringRep: String = "Jester"
}