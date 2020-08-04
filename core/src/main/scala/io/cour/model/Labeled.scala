package io.cour.model

import com.outr.arango.Id

case class Labeled[T](id: Id[T], label: String)