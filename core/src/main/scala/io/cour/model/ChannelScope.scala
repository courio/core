package io.cour.model

import fabric.rw.RW

sealed trait ChannelScope

object ChannelScope {
  implicit val rw: RW[ChannelScope] = RW.enumeration(List(Private, Public, Global))

  case object Private extends ChannelScope
  case object Public extends ChannelScope
  case object Global extends ChannelScope
}