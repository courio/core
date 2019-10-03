package io.cour.model

import com.outr.arango.Id

case class AliasPreview(id: Id[AliasPreview], name: String) {
  override def toString: String = s"@$name"
}