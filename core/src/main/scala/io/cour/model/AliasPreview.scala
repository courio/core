package io.cour.model

import com.outr.arango.{Id, Unique}

case class AliasPreview(id: Id[AliasPreview], name: String) {
  override def toString: String = s"@$name"
}

object AliasPreview {
  def id(value: String = Unique()): Id[AliasPreview] = Id(value, "aliases")
}