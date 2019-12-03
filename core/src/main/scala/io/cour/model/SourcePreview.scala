package io.cour.model

import com.outr.arango.Id

case class SourcePreview(organizationId: Id[OrganizationPreview], id: Id[SourcePreview], name: String)