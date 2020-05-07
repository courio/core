package io.cour.model

import io.circe.Json

case class Rule(label: String, data: Json)

object Rule {
  val Read: Rule = Rule("Read", Json.obj())
  val Write: Rule = Rule("Write", Json.obj())
  val Administrate: Rule = Rule("Administrate", Json.obj())
}