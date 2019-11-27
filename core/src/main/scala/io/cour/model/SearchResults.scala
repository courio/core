package io.cour.model

case class SearchResults(offset: Int,
                         limit: Int,
                         total: Int,
                         messages: List[CachedMessage]) {
  lazy val count: Int = messages.length
}