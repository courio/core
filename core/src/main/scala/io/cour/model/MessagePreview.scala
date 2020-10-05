package io.cour.model

import com.outr.arango.{Field, Id, Serialization}
import io.youi.net.URL

case class MessagePreview(_id: Id[MessagePreview],
                          organization: Labeled[OrganizationPreview],
                          source: Labeled[SourcePreview],
                          stream: Labeled[StreamPreview],
                          parentId: Option[Id[MessagePreview]],
                          creator: NamedCredential[Credentialed],
                          tags: List[Id[Tag]],
                          content: MessageContent,
                          mentions: List[NamedCredential[Credentialed]],
                          reactions: List[ReactionPreview],
                          references: MessageReferencePreviews,
                          edited: Boolean,
                          created: Long,
                          modified: Long) extends CourioDocument[MessagePreview] {
  lazy val size: Long = references.resources.foldLeft(0L)((total, resource) => total + resource.size)

  def reactionsForIds(aliasIds: Set[Id[AliasPreview]]): Set[ReactionType] = reactions.collect {
    case rp if aliasIds.contains(rp.by.id) => rp.`type`
  }.toSet
}

object MessagePreview extends CourioModel[MessagePreview] {
  val organization: LabeledField[OrganizationPreview] = new LabeledField[OrganizationPreview]("organization")
  val source: LabeledField[SourcePreview] = new LabeledField[SourcePreview]("source")
  val stream: LabeledField[StreamPreview] = new LabeledField[StreamPreview]("stream")
  val parentId: Field[Option[Id[MessagePreview]]] = field("parentId")
  val creator: NamedCredentialField[Credentialed] = new NamedCredentialField[Credentialed]("creator")
  val tags: Field[List[Id[Tag]]] = field("tags")
  object content extends Field[MessageContent]("content") {
    val original: Field[String] = field("original")
    val plainText: Field[String] = field("plainText")
    val parts: Field[List[MessagePart]] = field("parts")
    val parserVersion: Field[Int] = field("parserVersion")
  }
  val mentions: Field[List[NamedCredential[Credentialed]]] = field("mentions")
  val reactions: Field[List[ReactionPreview]] = field("reactions")
  val references: Field[MessageReferencePreviews] = field("references")
  val edited: Field[Boolean] = field("edited")

  override val collectionName: String = "messagePreviews"
  override implicit val serialization: Serialization[MessagePreview] = Serialization.auto[MessagePreview]

  class LabeledField[T](fieldName: String) extends Field[Labeled[T]](fieldName) {
    val id: Field[Id[T]] = field("id")
    val label: Field[String] = field("label")
  }

  class NamedCredentialField[T](fieldName: String) extends Field[NamedCredential[T]](fieldName) {
    val id: Field[Id[T]] = field("id")
    val username: Field[String] = field("username")
    val name: Field[Option[String]] = field("name")
    val lastModified: Field[Long] = field("lastModified")
    val iconOverride: Field[Option[URL]] = field("iconOverride")
    val profileOverride: Field[Option[URL]] = field("profileOverride")
  }
}