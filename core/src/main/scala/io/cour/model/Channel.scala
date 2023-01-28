package io.cour.model

import com.outr.arango.{Field, Id, Index, IndexType}
import fabric.rw.RW
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
  override implicit val rw: RW[Channel] = RW.gen

  val label: Field[String] = field[String]("label")
  val aliasId: Field[Option[Id[AliasPreview]]] = field[Option[Id[AliasPreview]]]("aliasId")
  val filters: Field[List[SearchFilter]] = field[List[SearchFilter]]("filters")
  val scope: Field[ChannelScope] = field[ChannelScope]("scope")

  override def indexes: List[Index] = Index(IndexType.Persistent, List(label.fieldName, aliasId.fieldName), unique = true) ::
    index(label, aliasId, scope) :::
    super.indexes

  override val collectionName: String = "channels"
}