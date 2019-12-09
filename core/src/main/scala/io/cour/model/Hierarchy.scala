package io.cour.model

import com.outr.arango.Id

case class Hierarchy(organizations: List[OrganizationPreview],
                     sources: List[SourcePreview],
                     streams: List[StreamPreview]) {
  lazy val organizationById: Map[Id[OrganizationPreview], OrganizationPreview] = organizations.map(o => o.id -> o).toMap
  lazy val sourceById: Map[Id[SourcePreview], SourcePreview] = sources.map(s => s.id -> s).toMap
  lazy val streamById: Map[Id[StreamPreview], StreamPreview] = streams.map(s => s._id -> s).toMap
  def sourcesByOrganization(id: Id[OrganizationPreview]): List[SourcePreview] = sources.filter(_.organizationId == id)
  def streamsBySource(id: Id[SourcePreview]): List[StreamPreview] = streams.filter(_.sourceId == id)
  def search(filter: String, limit: Int): List[StreamPreview] = {
    val f = filter.toLowerCase
    val exact = streams.filter(_.label.toLowerCase == f)
    val startsWith = streams.filter(_.label.toLowerCase.startsWith(f))
    val contains = streams.filter(_.label.toLowerCase.contains(f))
    (exact ::: startsWith ::: contains).take(limit)
  }
}