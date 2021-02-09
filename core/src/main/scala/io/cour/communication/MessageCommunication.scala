package io.cour.communication

import com.outr.arango.Id
import io.cour.model._
import io.cour.query.SearchQuery

import scala.concurrent.Future

trait MessageCommunication {
  def query(query: SearchQuery,
            offset: Int,
            limit: Int,
            lastUpdated: Option[Long]): Future[MessageResults]

  def verifyAvailableSpace(sizeInBytes: Long): Future[Boolean]

  def gifSearch(query: String, offset: Int, limit: Int): Future[GIFResults]

  def sendMessage(streamId: Id[StreamPreview], messageId: Id[MessagePreview], messageParts: List[MessagePart]): Future[Either[ErrorResult, MessagePreview]]

  @deprecated("use sendMessage instead")
  def send(target: Either[Id[StreamPreview], Id[AliasPreview]],
           messageId: Id[MessagePreview],
           body: String,
           resourceIds: List[Id[ResourcePreview]],
           fileNames: List[String],
           images: List[Image],
           videos: List[Video]): Future[Either[ErrorResult, MessagePreview]]

  def markRead(streamIds: List[Id[StreamPreview]], lastSeen: Long): Future[Unit]

  def addReaction(messageId: Id[MessagePreview], `type`: ReactionType): Future[Unit]

  def removeReaction(messageId: Id[MessagePreview], `type`: ReactionType): Future[Unit]

  def addFavorite(streamId: Id[StreamPreview]): Future[Either[ErrorResult, Unit]]

  def removeFavorite(streamId: Id[StreamPreview]): Future[Either[ErrorResult, Unit]]

  def saveChannel(channel: Channel): Future[Either[ErrorResult, Unit]]

  def deleteChannel(channelId: Id[Channel]): Future[Either[ErrorResult, Unit]]

  def deleteMessage(id: Id[MessagePreview]): Future[Either[ErrorResult, Unit]]

  def hierarchy(): Future[Hierarchy]

  def aliases(filter: String, limit: Int): Future[List[NamedCredential[AliasPreview]]]

  def publicStreams(filter: String, limit: Int): Future[List[StreamPreview]]

  def subscribedStreams(filter: String, limit: Int): Future[List[StreamPreview]]

  def stream(id: Id[StreamPreview]): Future[Either[ErrorResult, StreamPreview]]

  def streamPermissions(id: Id[StreamPreview]): Future[List[PermissionAndUsername]]

  def getOrCreateDirectStream(aliasId: Id[AliasPreview]): Future[Either[ErrorResult, Id[StreamPreview]]]

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