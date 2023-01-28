package io.cour.model

import com.outr.arango.{Id, Unique}

case class OrganizationPreview(id: Id[OrganizationPreview], name: String)

object OrganizationPreview {
  def id(value: String = Unique()): Id[OrganizationPreview] = Id(value, "organizations")
}