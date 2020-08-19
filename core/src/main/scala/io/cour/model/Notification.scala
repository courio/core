package io.cour.model

case class Notification(stream: StreamPreview, entries: List[NotificationEntry], unread: Int, lastMessage: Long) {
  lazy val title: String = s"${stream.personalizedLabel}: $unread new messages"
  lazy val message: String = entries.map(_.preview).mkString("\n").take(300)
}