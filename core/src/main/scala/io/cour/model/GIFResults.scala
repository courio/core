package io.cour.model

case class GIFResults(total: Int, offset: Int, limit: Int, count: Int, gifs: List[GIFResult]) {
  def hasMore: Boolean = offset + count > total
}