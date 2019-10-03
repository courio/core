package io.cour.model

import io.circe.Decoder.Result
import io.circe.{Decoder, Encoder, HCursor, Json}

sealed trait PluginStatus

object PluginStatus {
  case object Unapproved extends PluginStatus
  case object Approved extends PluginStatus
  case object Disabled extends PluginStatus

  implicit val encoder: Encoder[PluginStatus] = new Encoder[PluginStatus] {
    override def apply(a: PluginStatus): Json = Json.fromString(a match {
      case Unapproved => "unapproved"
      case Approved => "approved"
      case Disabled => "disabled"
    })
  }

  implicit val decoder: Decoder[PluginStatus] = new Decoder[PluginStatus] {
    override def apply(c: HCursor): Result[PluginStatus] = Right(c.value.asString.get match {
      case "unapproved" => Unapproved
      case "approved" => Approved
      case "disabled" => Disabled
    })
  }
}