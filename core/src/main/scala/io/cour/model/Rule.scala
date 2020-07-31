package io.cour.model

import io.circe.Json

case class Rule(label: String, data: Json) extends Ordered[Rule] {
  override def compare(that: Rule): Int = this.label.compare(that.label)
}

object Rule {
  val Read: Rule = Rule("Read", Json.obj())
  val Write: Rule = Rule("Write", Json.obj())
  val Administrate: Rule = Rule("Administrate", Json.obj())
}