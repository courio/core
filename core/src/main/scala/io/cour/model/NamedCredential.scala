package io.cour.model

import com.outr.arango.Id
import io.youi.net.URL

case class NamedCredential[T](id: Id[T],
                              username: String,
                              name: Option[String],
                              lastModified: Long) {
  lazy val icon: URL = URL(s"https://s3.us-west-1.wasabisys.com/courio/user/${id.value}-icon.png?m=$lastModified")
  lazy val profile: URL = URL(s"https://s3.us-west-1.wasabisys.com/courio/user/${id.value}-profile.png?m=$lastModified")
}