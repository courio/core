package io.cour.model

import com.outr.arango.Id
import io.youi.net.{ContentType, URL}

case class ResourcePreview(id: Id[ResourcePreview],
                           name: String,
                           `type`: ContentType,
                           size: Long,
                           preview: Option[URL],
                           link: URL)