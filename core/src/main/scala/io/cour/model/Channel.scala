package io.cour.model

import com.outr.arango.{Field, Id, Index, IndexType, Serialization}
import io.cour.query.SearchFilter

case class Channel(label: String,
                   userId: Id[Profile],
                   filters: List[SearchFilter],
                   created: Long = System.currentTimeMillis(),
                   modified: Long = System.currentTimeMillis(),
                   _id: Id[Channel] = Channel.id()) extends CourioDocument[Channel]

object Channel extends CourioModel[Channel] {
  val label: Field[String] = Field[String]("label")
  val userId: Field[Id[Profile]] = Field[Id[Profile]]("userId")
  val filters: Field[List[SearchFilter]] = Field[List[SearchFilter]]("filters")

  override def indexes: List[Index] = Index(IndexType.Persistent, List(label, userId), unique = true) ::
    index(label, userId) :::
    super.indexes

  override val collectionName: String = "channels"
  override implicit val serialization: Serialization[Channel] = Serialization.auto[Channel]
}