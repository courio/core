package io.cour.model

import fabric.rw.RW

sealed trait StreamType

object StreamType {
  implicit val rw: RW[StreamType] = RW.enumeration(List(Courio, Twitter))

  case object Courio extends StreamType
  case object Twitter extends StreamType
}