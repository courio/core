package io.cour.model

import com.outr.arango.{Id, Unique}
import spice.net.{ContentType, URL}

case class ResourcePreview(id: Id[ResourcePreview],
                           name: String,
                           `type`: ContentType,
                           size: Long,
                           preview: Option[URL],
                           link: URL)

object ResourcePreview {
  def id(value: String = Unique()): Id[ResourcePreview] = Id(value, "resources")
}