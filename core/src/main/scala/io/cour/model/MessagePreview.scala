package io.cour.model

import com.outr.arango.Id
import io.circe.Json
import io.youi.Unique
import io.youi.net.URL

case class MessagePreview(id: Id[MessagePreview],
                          streamId: Id[StreamPreview],
                          streamLabel: String,
                          sourceId: Id[SourcePreview],
                          sourceLabel: String,
                          organizationId: Id[OrganizationPreview],
                          organizationLabel: String,
                          creator: NamedCredential[Credentialed],
                          tags: List[Id[Tag]],
                          text: String,
                          content: Json,
                          mentioned: List[NamedCredential[AliasPreview]],
                          links: List[String],
                          linkPreviews: List[LinkPreview],
                          images: List[Image],
                          videos: List[Video],
                          edited: Boolean,
                          resources: List[ResourcePreview],
                          reactions: List[ReactionPreview],
                          created: Long,
                          modified: Long) {
  def isEmpty: Boolean = this == MessagePreview.empty
  def nonEmpty: Boolean = !isEmpty

  lazy val size: Long = resources.foldLeft(0L)((total, resource) => total + resource.size)

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
    organizationId = Id("", ""),
    organizationLabel = "",
    creator = NamedCredential(Credentialed(Id("", "")), "", None, 0L),
    tags = Nil,
    text = "",
    content = Json.obj(),
    mentioned = Nil,
    links = Nil,
    linkPreviews = Nil,
    images = Nil,
    videos = Nil,
    edited = false,
    resources = Nil,
    reactions = Nil,
    created = 0L,
    modified = 0L
  )

  def id(value: String = Unique()): Id[MessagePreview] = Id(value, "messages")
}