package io.cour.model

import com.outr.arango.{DocumentModel, Field, Id, Index}

trait CourioModel[D <: CourioDocument[D]] extends DocumentModel[D] {
  val created: Field[Long] = Field[Long]("created")
  val modified: Field[Long] = Field[Long]("modified")
  val _id: Field[Id[D]] = Field[Id[D]]("_id")

  override def indexes: List[Index] = List(
    created.index.persistent(),
    modified.index.persistent()
  )
}