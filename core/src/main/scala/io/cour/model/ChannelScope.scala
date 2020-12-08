package io.cour.model

import io.circe.Decoder.Result
import io.circe.{Decoder, DecodingFailure, Encoder, HCursor, Json}

sealed trait ChannelScope

object ChannelScope {
  case object Private extends ChannelScope
  case object Public extends ChannelScope
  case object Global extends ChannelScope

  implicit val decoder: Decoder[ChannelScope] = new Decoder[ChannelScope] {
    override def apply(c: HCursor): Result[ChannelScope] = c.value.asString match {
      case Some(s) => s match {
        case "private" => Right(Private)
        case "public" => Right(Public)
        case "global" => Right(Global)
        case _ => Left(DecodingFailure(s"Unsupported value $s", Nil))
      }
      case None => Left(DecodingFailure(s"ChannelScope not a string: ${c.value}", Nil))
    }
  }

  implicit val encoder: Encoder[ChannelScope] = new Encoder[ChannelScope] {
    override def apply(a: ChannelScope): Json = a match {
      case Private => Json.fromString("private")
      case Public => Json.fromString("public")
      case Global => Json.fromString("global")
    }
  }
}