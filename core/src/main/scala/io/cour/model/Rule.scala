package io.cour.model

import fabric._

case class Rule(label: String, data: Json) extends Ordered[Rule] {
  override def compare(that: Rule): Int = this.label.compare(that.label)
}

object Rule {
  val Read: Rule = Rule("Read", obj())
  val Write: Rule = Rule("Write", obj())
  val Administrate: Rule = Rule("Administrate", obj())
}