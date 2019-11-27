package io.cour.query

import com.outr.arango.Id
import io.cour.model.{CachedMessage, FilterOperator, ReactionType, StreamPreview}

sealed trait SearchFilter {
  def operator: FilterOperator
  def changeOperator(operator: FilterOperator): SearchFilter
}

object SearchFilter {
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

  case class MessageId(operator: FilterOperator, id: Id[CachedMessage]) extends SearchFilter {
    override def changeOperator(operator: FilterOperator): SearchFilter = copy(operator = operator)
  }

  case class Mentioned(operator: FilterOperator, username: String) extends SearchFilter {
    override def changeOperator(operator: FilterOperator): SearchFilter = copy(operator = operator)
  }

  case class Reaction(operator: FilterOperator, reactionType: ReactionType) extends SearchFilter {
    override def changeOperator(operator: FilterOperator): SearchFilter = copy(operator = operator)
  }
}