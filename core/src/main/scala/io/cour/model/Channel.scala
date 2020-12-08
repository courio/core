package io.cour.model

import com.outr.arango.{Field, Id, Index, IndexType, Serialization}
import io.cour.query.SearchFilter

case class Channel(label: String,
                   aliasId: Option[Id[AliasPreview]],
                   filters: List[SearchFilter],
                   scope: ChannelScope,
                   created: Long = System.currentTimeMillis(),
                   modified: Long = System.currentTimeMillis(),
                   _id: Id[Channel] = Channel.id()) extends CourioDocument[Channel] {
  lazy val filtersSet: Set[SearchFilter] = filters.toSet
}

object Channel extends CourioModel[Channel] {
  val label: Field[String] = Field[String]("label")
  val aliasId: Field[Option[Id[AliasPreview]]] = Field[Option[Id[AliasPreview]]]("aliasId")
  val filters: Field[List[SearchFilter]] = Field[List[SearchFilter]]("filters")
  val scope: Field[ChannelScope] = Field[ChannelScope]("scope")

  override def indexes: List[Index] = Index(IndexType.Persistent, List(label, aliasId), unique = true) ::
    index(label, aliasId, scope) :::
    super.indexes

  override val collectionName: String = "channels"
  override implicit val serialization: Serialization[Channel] = Serialization.auto[Channel]
}