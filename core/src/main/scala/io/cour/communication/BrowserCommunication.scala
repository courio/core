package io.cour.communication

import io.cour.model.MessagePreview

import scala.concurrent.Future

trait BrowserCommunication {
  def receive(message: MessagePreview): Future[Unit]

  def notify(message: MessagePreview): Future[Unit]

  def refresh(): Future[Unit]

  def reload(): Future[Unit]
}