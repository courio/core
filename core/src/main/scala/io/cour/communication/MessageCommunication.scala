package io.cour.communication

import com.outr.arango.Id
import io.cour.model._
import io.cour.query.SearchQuery

import scala.concurrent.Future

trait MessageCommunication {
  def query(query: SearchQuery, offset: Int, limit: Int): Future[MessageResults]

  def verifyAvailableSpace(sizeInBytes: Long): Future[Boolean]

  def gifSearch(query: String, offset: Int, limit: Int): Future[GIFResults]

  def send(target: Either[Id[StreamPreview], Id[AliasPreview]],
           messageId: Id[MessagePreview],
           body: String,
           resources: List[Id[ResourcePreview]],
           files: List[String]): Future[Either[ErrorResult, MessagePreview]]

  @deprecated("Use send instead")
  def sendMessage(target: Either[Id[StreamPreview], Id[AliasPreview]],
                  messageId: Id[MessagePreview],
                  body: String,
                  resources: List[Id[ResourcePreview]]): Future[Either[ErrorResult, MessagePreview]]

  def markRead(streamIds: List[Id[StreamPreview]], lastSeen: Long): Future[Unit]

  def addReaction(messageId: Id[MessagePreview], `type`: ReactionType): Future[Unit]

  def removeReaction(messageId: Id[MessagePreview], `type`: ReactionType): Future[Unit]

  def deleteMessage(id: Id[MessagePreview]): Future[Either[ErrorResult, Unit]]

  def hierarchy(): Future[Hierarchy]

  def aliases(filter: String, limit: Int): Future[List[NamedCredential[AliasPreview]]]

  def publicStreams(filter: String, limit: Int): Future[List[StreamPreview]]

  def subscribedStreams(filter: String, limit: Int): Future[List[StreamPreview]]

  def streamById(id: Id[StreamPreview]): Future[StreamPreview]

  def streamPermissions(id: Id[StreamPreview]): Future[List[PermissionAndUsername]]

  def saveStream(streamId: Option[Id[StreamPreview]],
                 name: String,
                 description: Option[String],
                 tags: List[Id[Tag]],
                 addAliasPermissions: List[Permission[AliasPreview]],
                 removeAliasPermissions: List[Permission[AliasPreview]],
                 addGroupPermissions: List[Permission[Group]],
                 removeGroupPermissions: List[Permission[Group]]): Future[Either[ErrorResult, Id[StreamPreview]]]

  def saveStreamSettings(streamId: Id[StreamPreview],
                         customName: Option[String],
                         notifications: Boolean,
                         subscribed: Boolean): Future[Either[ErrorResult, Unit]]

  def deleteStream(streamId: Id[StreamPreview]): Future[Either[ErrorResult, Unit]]
}