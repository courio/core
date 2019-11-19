package io.cour.model

import com.outr.arango.{DocumentModel, Field, Id, Index, Serialization}

case class Tag(created: Long = System.currentTimeMillis(),
               modified: Long = System.currentTimeMillis(),
               _id: Id[Tag]) extends CourioDocument[Tag] {
  def name: String = _id.value
}

object Tag extends DocumentModel[Tag] {
  val created: Field[Long] = Field[Long]("created")
  val modified: Field[Long] = Field[Long]("modified")
  val _id: Field[com.outr.arango.Id[io.cour.model.Tag]] = Field[com.outr.arango.Id[io.cour.model.Tag]]("_id")

  def apply(name: String): Tag = Tag(_id = id(name.toLowerCase))

  override def indexes: List[Index] = index(created, modified)

  override val collectionName: String = "tags"
  override implicit val serialization: Serialization[Tag] = Serialization.auto[Tag]
}