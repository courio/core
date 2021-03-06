package io.cour.model

import com.outr.arango.Id
import io.cour.query.SearchFilter

case class Hierarchy(organizations: List[OrganizationPreview],
                     sources: List[SourcePreview],
                     streams: List[StreamPreview],
                     recentStreams: List[Id[StreamPreview]] = Nil,
                     frequentStreams: List[FrequentStream] = Nil,
                     channels: List[Channel] = Nil) {
  lazy val organizationById: Map[Id[OrganizationPreview], OrganizationPreview] = organizations.map(o => o.id -> o).toMap
  lazy val sourceById: Map[Id[SourcePreview], SourcePreview] = sources.map(s => s.id -> s).toMap
  lazy val streamById: Map[Id[StreamPreview], StreamPreview] = streams.map(s => s._id -> s).toMap
  lazy val unread: Int = streams.map(_.unread).sum
  lazy val favorites: List[StreamPreview] = streams.filter(_.favorite)

  def channelByFilters(filters: List[SearchFilter]): Option[Channel] = {
    val set = filters.toSet
    channels.find(_.filtersSet == set)
  }

  def sourcesByOrganization(id: Id[OrganizationPreview]): List[SourcePreview] = sources.filter(_.organizationId == id)
  def streamsBySource(id: Id[SourcePreview]): List[StreamPreview] = streams.filter(_.sourceId == id)
  def search(filter: String, limit: Int): List[StreamPreview] = {
    val f = filter.toLowerCase
    val exact = streams.filter(_.label.toLowerCase == f)
    val startsWith = streams.filter(_.label.toLowerCase.startsWith(f))
    val contains = streams.filter(_.label.toLowerCase.contains(f))
    (exact ::: startsWith ::: contains).take(limit)
  }
  def update(streamId: Id[StreamPreview])(f: StreamPreview => StreamPreview): Hierarchy = {
    val updated = streams.map { s =>
      if (s._id == streamId) {
        f(s)
      } else {
        s
      }
    }.sortBy(- _.lastMessage)
    copy(streams = updated)
  }
  def update(streamId: Id[StreamPreview], lastMessage: Long): Hierarchy = update(streamId)(s => s.copy(lastMessage = math.max(s.lastMessage, lastMessage)))
  def markRead(streamIds: Set[Id[StreamPreview]]): Hierarchy = {
    val updated = streams.map { s =>
      if (streamIds.contains(s._id)) {
        s.copy(unread = 0, lastSeen = s.lastMessage)
      } else {
        s
      }
    }
    copy(streams = updated)
  }
  def incrementUnread(streamId: Id[StreamPreview], lastMessage: Long): Hierarchy = {
    val updated = streams.map { s =>
      if (s._id == streamId) {
        s.copy(unread = s.unread + 1, lastMessage = math.max(s.lastMessage, lastMessage))
      } else {
        s
      }
    }.sortBy(- _.lastMessage)
    copy(streams = updated)
  }
}

