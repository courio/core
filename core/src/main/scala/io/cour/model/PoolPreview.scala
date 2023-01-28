package io.cour.model

import com.outr.arango.{Id, Unique}

case class PoolPreview(id: Id[PoolPreview], name: String)

object PoolPreview {
  def id(value: String = Unique()): Id[PoolPreview] = Id(value, "pools")
}