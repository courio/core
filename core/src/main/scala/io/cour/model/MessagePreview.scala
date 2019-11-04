package io.cour.model

import com.outr.arango.Id
import io.circe.Json
import io.youi.Unique

case class MessagePreview(id: Id[MessagePreview],
                          streamId: Id[StreamPreview],
                          streamLabel: String,
                          sourceId: Id[SourcePreview],
                          sourceLabel: String,
                          creator: NamedCredential[Credentialed],
                          tags: List[Id[Tag]],
                          text: String = "",
                          content: Json,
                          mentioned: List[NamedCredential[AliasPreview]],
                          links: List[String],
                          linkPreviews: List[LinkPreview],
                          edited: Boolean,
                          resources: List[ResourcePreview],
                          size: Long,
                          reactions: List[ReactionPreview],
                          created: Long,
                          modified: Long) {
  def isEmpty: Boolean = this == MessagePreview.empty
  def nonEmpty: Boolean = !isEmpty

  def reactionsForIds(aliasIds: Set[Id[AliasPreview]]): Set[ReactionType] = reactions.collect {
    case rp if aliasIds.contains(rp.by.id) => rp.`type`
  }.toSet
}

object MessagePreview {
  lazy val empty: MessagePreview = MessagePreview(
    id = Id("", ""),
    streamId = Id("", ""),
    streamLabel = "",
    sourceId = Id("", ""),
    sourceLabel = "",
    creator = NamedCredential(Credentialed(Id("", "")), "", None),
    tags = Nil,
    text = "",
    content = Json.obj(),
    mentioned = Nil,
    links = Nil,
    linkPreviews = Nil,
    edited = false,
    resources = Nil,
    size = 0L,
    reactions = Nil,
    created = 0L,
    modified = 0L
  )

  def id(value: String = Unique()): Id[MessagePreview] = Id(value, "messages")
}