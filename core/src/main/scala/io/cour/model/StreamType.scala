package io.cour.model

import io.circe.Decoder.Result
import io.circe.{Decoder, DecodingFailure, Encoder, HCursor, Json}

sealed trait StreamType

object StreamType {
  case object Courio extends StreamType
  case object Twitter extends StreamType

  implicit val decoder: Decoder[StreamType] = new Decoder[StreamType] {
    override def apply(c: HCursor): Result[StreamType] = c.value.asString match {
      case Some(s) => s match {
        case "courio" => Right(Courio)
        case "twitter" => Right(Twitter)
        case _ => Left(DecodingFailure(s"Unsupported value $s", Nil))
      }
      case None => Right(Courio)
    }
  }

  implicit val encoder: Encoder[StreamType] = new Encoder[StreamType] {
    override def apply(a: StreamType): Json = a match {
      case Courio => Json.fromString("courio")
      case Twitter => Json.fromString("twitter")
    }
  }
}