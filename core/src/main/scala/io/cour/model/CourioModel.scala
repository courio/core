package io.cour.model

import com.outr.arango.{DocumentModel, Field, Id, Index}

trait CourioModel[D <: CourioDocument[D]] extends DocumentModel[D] {
  val created: Field[Long] = field[Long]("created")
  val modified: Field[Long] = field[Long]("modified")

  override def indexes: List[Index] = List(
    created.index.persistent(),
    modified.index.persistent()
  )
}