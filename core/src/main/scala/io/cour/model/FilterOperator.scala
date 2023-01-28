package io.cour.model

import fabric.rw.RW

sealed trait FilterOperator {
  override val toString: String = getClass.getSimpleName.toLowerCase.replace("$", "")
}

object FilterOperator {
  implicit val rw: RW[FilterOperator] = RW.enumeration(List(Include, Exclude, Boost))

  case object Include extends FilterOperator
  case object Exclude extends FilterOperator
  case object Boost extends FilterOperator

  def apply(s: String): FilterOperator = s match {
    case "exclude" => Exclude
    case "boost" => Boost
    case "include" => Include
  }
}