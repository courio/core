package io.cour.model

import com.outr.arango.{Id, Unique}

case class SourcePreview(organizationId: Id[OrganizationPreview], id: Id[SourcePreview], name: String)

object SourcePreview {
  def id(value: String = Unique()): Id[SourcePreview] = Id(value, "sources")
}