package io.cour.model

import com.outr.arango.{DocumentModel, Field, Id, Index}
import fabric.rw.RW

case class Group(label: String,
                 description: Option[String] = None,
                 created: Long = System.currentTimeMillis(),
                 modified: Long = System.currentTimeMillis(),
                 _id: Id[Group] = Group.id()) extends CourioDocument[Group] with Credentialed

object Group extends DocumentModel[Group] {
  override implicit val rw: RW[Group] = RW.gen

  val label: Field[String] = field[String]("label")
  val description: Field[Option[String]] = field[Option[String]]("description")
  val created: Field[Long] = field[Long]("created")
  val modified: Field[Long] = field[Long]("modified")

  override def indexes: List[Index] = label.index.persistent(unique = true) :: index(created, modified)

  override val collectionName: String = "groups"
}