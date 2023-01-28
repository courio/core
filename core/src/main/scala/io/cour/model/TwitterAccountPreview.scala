package io.cour.model

import com.outr.arango.Id
import spice.net.URL

case class TwitterAccountPreview(screenName: String,
                                 name: Option[String],
                                 email: Option[String],
                                 profileImage: Option[URL],
                                 created: Long,
                                 modified: Long,
                                 id: Id[TwitterAccountPreview])