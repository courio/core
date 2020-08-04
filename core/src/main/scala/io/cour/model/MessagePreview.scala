package io.cour.model

import com.outr.arango.Id
import io.circe.Json
import io.youi.Unique
import io.youi.net.URL

case class MessagePreview(id: Id[MessagePreview],
                          organization: Labeled[OrganizationPreview],
                          source: Labeled[SourcePreview],
                          stream: Labeled[StreamPreview],
                          parentId: Option[Id[MessagePreview]],
                          creator: NamedCredential[Credentialed],
                          tags: List[Id[Tag]],
                          content: MessageContent,
                          mentions: List[NamedCredential[Credentialed]],
                          reactions: List[ReactionPreview],
                          references: MessageReferences,
                          edited: Boolean,
                          created: Long,
                          modified: Long) {
  lazy val size: Long = references.resources.foldLeft(0L)((total, resource) => total + resource.size)

  def reactionsForIds(aliasIds: Set[Id[AliasPreview]]): Set[ReactionType] = reactions.collect {
    case rp if aliasIds.contains(rp.by.id) => rp.`type`
  }.toSet
}

object MessagePreview {
  def id(value: String = Unique()): Id[MessagePreview] = Id(value, "messages")
}