package io.cour.model

import io.circe.Decoder.Result
import io.circe.{Decoder, DecodingFailure, Encoder, HCursor, Json}

sealed trait ReactionType {
  lazy val name: String = getClass.getSimpleName.replaceAllLiterally("$", "").toLowerCase
}

object ReactionType {
  case object Like extends ReactionType
  case object Dislike extends ReactionType
  case object Love extends ReactionType
  case object Funny extends ReactionType
  case object Wow extends ReactionType
  case object Sad extends ReactionType
  case object Angry extends ReactionType
  case object Interesting extends ReactionType
  case object Boring extends ReactionType
  case object Inappropriate extends ReactionType

  lazy val all: List[ReactionType] = List(Like, Dislike, Love, Funny, Wow, Sad, Angry, Interesting, Boring, Inappropriate)
  private lazy val namedMap = all.map { r =>
    r.name -> r
  }.toMap

  def apply(name: String): ReactionType = namedMap.getOrElse(name.toLowerCase, throw new RuntimeException(s"No reaction type by name: $name"))

  implicit val decoder: Decoder[ReactionType] = new Decoder[ReactionType] {
    override def apply(c: HCursor): Result[ReactionType] = c.value.asString match {
      case Some(s) => namedMap.get(s.toLowerCase) match {
        case Some(r) => Right(r)
        case None => Left(DecodingFailure(s"Invalid reaction name: $s", Nil))
      }
      case None => Left(DecodingFailure(s"Unable to decode reaction from JSON: ${c.value}", Nil))
    }
  }

  implicit val encoder: Encoder[ReactionType] = new Encoder[ReactionType] {
    override def apply(a: ReactionType): Json = Json.fromString(a.name)
  }
}