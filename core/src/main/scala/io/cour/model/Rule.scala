package io.cour.model

import com.outr.arango.Id
import io.circe.Json

case class Rule(label: String, pluginId: Option[Id[Plugin]], data: Json)

object Rule {
  val Read = Rule("Read", None, Json.obj())
  val Write = Rule("Write", None, Json.obj())
  val Administrate = Rule("Administrate", None, Json.obj())
}