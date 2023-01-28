package io.cour.model

import com.outr.arango.{DocumentModel, Field, Id, Index}
import fabric.rw.RW

case class Tag(created: Long = System.currentTimeMillis(),
               modified: Long = System.currentTimeMillis(),
               _id: Id[Tag]) extends CourioDocument[Tag] {
  def name: String = _id.value
}

object Tag extends DocumentModel[Tag] {
  override implicit val rw: RW[Tag] = RW.gen

  val created: Field[Long] = field[Long]("created")
  val modified: Field[Long] = field[Long]("modified")

  def apply(name: String): Tag = Tag(_id = id(name.toLowerCase))

  override def indexes: List[Index] = index(created, modified)

  override val collectionName: String = "tags"
}