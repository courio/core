package io.cour.model

case class NotificationEntry(username: String, message: String) {
  lazy val preview: String = s"@$username: $message"
}