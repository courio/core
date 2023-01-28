package io.cour.query

import com.outr.arango.Id
import fabric.rw.RW
import io.cour.model.{FilterOperator, MessagePreview, ReactionType, StreamPreview}

sealed trait SearchFilter {
  def operator: FilterOperator
  def changeOperator(operator: FilterOperator): SearchFilter
}

object SearchFilter {
  implicit val scopeRW: RW[Scope] = RW.gen
  implicit val streamIdRW: RW[StreamId] = RW.gen
  implicit val textRW: RW[Text] = RW.gen
  implicit val tagNameRW: RW[TagName] = RW.gen
  implicit val creatorUsernameRW: RW[CreatorUsername] = RW.gen
  implicit val messageIdRW: RW[MessageId] = RW.gen
  implicit val mentionedRW: RW[Mentioned] = RW.gen
  implicit val reactionRW: RW[Reaction] = RW.gen
  implicit val rw: RW[SearchFilter] = RW.poly[SearchFilter]() {
    case "scope" => scopeRW
    case "streamId" => streamIdRW
    case "text" => textRW
    case "tagName" => tagNameRW
    case "creatorUsername" => creatorUsernameRW
    case "messageId" => messageIdRW
    case "mentioned" => mentionedRW
    case "reaction" => reactionRW
  }

  case class Scope(includePublic: Boolean, includePrivate: Boolean) extends SearchFilter {
    override def operator: FilterOperator = FilterOperator.Include

    override def changeOperator(operator: FilterOperator): SearchFilter = this
  }

  case class StreamId(operator: FilterOperator, id: Id[StreamPreview]) extends SearchFilter {
    override def changeOperator(operator: FilterOperator): SearchFilter = copy(operator = operator)
  }

  case class Text(operator: FilterOperator, text: String) extends SearchFilter {
    override def changeOperator(operator: FilterOperator): SearchFilter = copy(operator = operator)
  }

  case class TagName(operator: FilterOperator, tag: String) extends SearchFilter {
    override def changeOperator(operator: FilterOperator): SearchFilter = copy(operator = operator)
  }

  case class CreatorUsername(operator: FilterOperator, username: String) extends SearchFilter {
    override def changeOperator(operator: FilterOperator): SearchFilter = copy(operator = operator)
  }

  case class MessageId(operator: FilterOperator, id: Id[MessagePreview]) extends SearchFilter {
    override def changeOperator(operator: FilterOperator): SearchFilter = copy(operator = operator)
  }

  case class Mentioned(operator: FilterOperator, username: String) extends SearchFilter {
    override def changeOperator(operator: FilterOperator): SearchFilter = copy(operator = operator)
  }

  case class Reaction(operator: FilterOperator, reactionType: ReactionType) extends SearchFilter {
    override def changeOperator(operator: FilterOperator): SearchFilter = copy(operator = operator)
  }
}