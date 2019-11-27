package io.cour.model

case class Hierarchy(organizations: List[OrganizationPreview],
                     sources: List[SourcePreview],
                     streams: List[StreamPreview])