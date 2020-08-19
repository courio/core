package io.cour.model

import com.outr.arango.Id

import scala.collection.SortedMap

case class UserNotificationStatus(notifications: SortedMap[Id[StreamPreview], Notification]) {
  lazy val unread: Int = notifications.map(_._2.unread).sum
}