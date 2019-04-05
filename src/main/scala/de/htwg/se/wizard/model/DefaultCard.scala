package de.htwg.se.wizard.model

case class DefaultCard(color: String, number: Integer, owner: Player) extends Card {
  def hasColor: Boolean = true
  def isWizard: Boolean = false
  def isJester: Boolean = false
  def hasOwner: Boolean = owner != null
}
