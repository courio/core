package io.cour.model

import io.circe.Decoder.Result
import io.circe.{Decoder, DecodingFailure, Encoder, HCursor, Json}

sealed trait SourceType

object SourceType {
  case object Courio extends SourceType
  case object Twitter extends SourceType

  implicit val decoder: Decoder[SourceType] = new Decoder[SourceType] {
    override def apply(c: HCursor): Result[SourceType] = c.value.asString match {
      case Some(s) => s match {
        case "courio" => Right(Courio)
        case "twitter" => Right(Twitter)
        case _ => Left(DecodingFailure(s"Unsupported value $s", Nil))
      }
      case None => Right(Courio)
    }
  }

  implicit val encoder: Encoder[SourceType] = new Encoder[SourceType] {
    override def apply(a: SourceType): Json = a match {
      case Courio => Json.fromString("courio")
      case Twitter => Json.fromString("twitter")
    }
  }
}