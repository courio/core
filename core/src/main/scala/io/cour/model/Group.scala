package io.cour.model

import com.outr.arango.Field

import com.outr.arango.{DocumentModel, Id, Serialization}

case class Group(label: String,
                 description: Option[String] = None,
                 created: Long = System.currentTimeMillis(),
                 modified: Long = System.currentTimeMillis(),
                 _id: Id[Group] = Group.id()) extends CourioDocument[Group] with Credentialed

object Group extends DocumentModel[Group] {
  val label: Field[String] = Field[String]("label")
  val description: Field[Option[String]] = Field[Option[String]]("description")
  val created: Field[Long] = Field[Long]("created")
  val modified: Field[Long] = Field[Long]("modified")
  val _id: Field[com.outr.arango.Id[io.cour.model.Group]] = Field[com.outr.arango.Id[io.cour.model.Group]]("_id")

  override val collectionName: String = "groups"
  override implicit val serialization: Serialization[Group] = Serialization.auto[Group]
}