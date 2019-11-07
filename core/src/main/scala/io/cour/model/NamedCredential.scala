package io.cour.model

import com.outr.arango.Id
import io.youi.net.URL

case class NamedCredential[T](id: Id[T],
                              username: String,
                              name: Option[String],
                              icon: URL,
                              profile: URL)