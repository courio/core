package io.cour.model

import com.outr.arango.{DocumentModel, Field, Id, Index, Serialization}
import io.circe.Json
import io.youi.net.URL

case class CachedMessage(organization: OrganizationPreview,
                         source: SourcePreview,
                         stream: StreamInfo,
                         creator: NamedCredential[Credentialed],
                         tags: List[Id[Tag]],
                         preview: String,
                         content: Json,
                         mentioned: List[NamedCredential[AliasPreview]],
                         links: List[String],
                         linkPreviews: List[LinkPreview],
                         edited: Boolean,
                         resources: List[ResourcePreview],
                         reactions: List[ReactionPreview],
                         fullText: String,
                         created: Long,
                         modified: Long,
                         _id: Id[CachedMessage]) extends CourioDocument[CachedMessage] {
  lazy val size: Long = resources.foldLeft(0L)((total, resource) => total + resource.size)
  lazy val messagePreview: MessagePreview = MessagePreview(
    id = _id.asInstanceOf[Id[MessagePreview]],
    streamId = stream.id,
    streamLabel = stream.name,
    sourceId = source.id,
    sourceLabel = source.name,
    creator = creator,
    tags = tags,
    text = preview,
    content = content,
    mentioned = mentioned,
    links = links,
    linkPreviews = linkPreviews,
    edited = edited,
    resources = resources,
    reactions = reactions,
    created = created,
    modified = modified
  )

  def reactionsForIds(aliasIds: Set[Id[AliasPreview]]): Set[ReactionType] = reactions.collect {
    case rp if aliasIds.contains(rp.by.id) => rp.`type`
  }.toSet
}

object CachedMessage extends DocumentModel[CachedMessage] {
  object organization extends Field[OrganizationPreview]("organization") {
    val id: Field[Id[OrganizationPreview]] = field("id")
    val name: Field[String] = field("name")
  }
  object source extends Field[SourcePreview]("source") {
    val id: Field[Id[SourcePreview]] = field("id")
    val name: Field[String] = field("name")
  }
  object stream extends Field[StreamInfo]("stream") {
    val id: Field[Id[StreamPreview]] = field("id")
    val name: Field[String] = field("name")
  }
  object creator extends Field[NamedCredential[Credentialed]]("creator") {
    val id: Field[Id[Credentialed]] = field("id")
    val username: Field[String] = field("username")
    val name: Field[Option[String]] = field("name")
    val icon: Field[URL] = field("icon")
    val profile: Field[URL] = field("profile")
  }
  val tags: Field[List[Id[Tag]]] = field("tags")
  val preview: Field[String] = field("preview")
  val content: Field[Json] = field("content")
  val mentioned: Field[List[NamedCredential[AliasPreview]]] = field("mentioned")
  val links: Field[List[String]] = field("links")
  val linkPreviews: Field[List[LinkPreview]] = field("linkPreviews")
  val edited: Field[Boolean] = field("edited")
  val resources: Field[List[ResourcePreview]] = field("resources")
  val reactions: Field[List[ReactionPreview]] = field("reactions")
  val fullText: Field[String] = field("fullText")
  val created: Field[Long] = field("created")
  val modified: Field[Long] = field("modified")
  val _id: Field[Id[CachedMessage]] = field("_id")

  override def indexes: List[Index] = index(
    organization, organization.id, organization.name, source, source.id, source.name, stream, stream.id, stream.name,
    creator, creator.id, creator.username, creator.name, tags, mentioned, links, edited, created, modified
  )

  override val collectionName: String = "cachedMessages"
  override implicit val serialization: Serialization[CachedMessage] = Serialization.auto[CachedMessage]
}