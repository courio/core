package io.cour.communication

import com.outr.arango.Id
import io.cour.model.{MessagePreview, StreamPreview, TwitterAccountPreview}

import scala.concurrent.Future

trait BrowserCommunication {
  def receive(message: MessagePreview): Future[Unit]

  def notify(message: MessagePreview): Future[Unit]

  def incrementUnread(streamId: Id[StreamPreview], lastMessage: Long): Future[Unit]

  def twitterAccountAdded(account: TwitterAccountPreview): Future[Unit]

  def refresh(): Future[Unit]

  def reload(): Future[Unit]
}