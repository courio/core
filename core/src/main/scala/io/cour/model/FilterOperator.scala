package io.cour.model

sealed trait FilterOperator {
  override val toString: String = getClass.getSimpleName.toLowerCase.replace("$", "")
}

object FilterOperator {
  case object Include extends FilterOperator
  case object Exclude extends FilterOperator
  case object Boost extends FilterOperator

  def apply(s: String): FilterOperator = s match {
    case "exclude" => Exclude
    case "boost" => Boost
    case "include" => Include
  }
}