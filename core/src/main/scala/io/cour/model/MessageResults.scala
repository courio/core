package io.cour.model

import com.outr.arango.Id

case class MessageResults(offset: Int,
                          limit: Int,
                          total: Int,
                          count: Int,
                          queryTimestamp: Long,
                          previousQueryTimestamp: Option[Long],
                          messages: List[MessagePreview],
                          deleted: Set[Id[MessagePreview]],
                          update: Boolean) {
  def updateWith(results: MessageResults): MessageResults = if (results.update) {
    var m = messages
    // Remove deleted
    m = m.filterNot(mp => results.deleted.contains(mp.id))
    // Remove replaced
    val newIds = results.messages.map(_.id).toSet
    m = m.filterNot(mp => newIds.contains(mp.id))
    // Add new messages
    m = m ::: results.messages
    // Sort
    m = m.sortBy(_.created)

    MessageResults(
      offset = results.offset,
      limit = results.limit,
      total = results.total,
      count = results.count,
      queryTimestamp = results.queryTimestamp,
      previousQueryTimestamp = results.previousQueryTimestamp,
      messages = m,
      deleted = Set.empty,
      update = true
    )
  } else {
    results
  }
}