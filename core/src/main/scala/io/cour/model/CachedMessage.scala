package io.cour.model

import com.outr.arango.{DocumentModel, Field, Id, Index, Serialization}
import io.circe.Json
import io.youi.net.URL

case class CachedMessage(organization: OrganizationPreview,
                         source: SourcePreview,
                         creator: NamedCredential[Credentialed],
                         tags: List[Id[Tag]],
                         text: String,
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
  object creator extends Field[NamedCredential[Credentialed]]("creator") {
    val id: Field[Id[Credentialed]] = field("id")
    val username: Field[String] = field("username")
    val name: Field[Option[String]] = field("name")
    val icon: Field[URL] = field("icon")
    val profile: Field[URL] = field("profile")
  }
  val tags: Field[List[Id[Tag]]] = Field("tags")
  val text: Field[String] = Field("text")
  val content: Field[Json] = Field("content")
  val mentioned: Field[List[NamedCredential[AliasPreview]]] = Field("mentioned")
  val links: Field[List[String]] = Field("links")
  val linkPreviews: Field[List[LinkPreview]] = Field("linkPreviews")
  val edited: Field[Boolean] = Field("edited")
  val resources: Field[List[ResourcePreview]] = Field("resources")
  val reactions: Field[List[ReactionPreview]] = Field("reactions")
  val fullText: Field[String] = Field("fullText")
  val created: Field[Long] = Field("created")
  val modified: Field[Long] = Field("modified")
  val _id: Field[Id[CachedMessage]] = Field("_id")

  override def indexes: List[Index] = index(
    organization, organization.id, organization.name, source, source.id, source.name, creator, creator.id,
    creator.username, creator.name, tags, mentioned, links, edited, created, modified
  )

  override val collectionName: String = "cachedMessages"
  override implicit val serialization: Serialization[CachedMessage] = Serialization.auto[CachedMessage]
}