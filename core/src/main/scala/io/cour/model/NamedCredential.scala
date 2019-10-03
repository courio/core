package io.cour.model

import com.outr.arango.Id

case class NamedCredential[T](id: Id[T], username: String, name: Option[String])