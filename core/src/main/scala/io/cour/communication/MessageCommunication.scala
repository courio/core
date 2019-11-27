package io.cour.communication

import com.outr.arango.Id
import io.cour.model.{AliasPreview, CachedMessage, ErrorResult, Group, Hierarchy, MessagePreview, MessageResults, Permission, PermissionAndUsername, ReactionType, ResourcePreview, SearchResults, StreamPreview, StreamsAndAliases, Tag}
import io.cour.query.SearchQuery

import scala.concurrent.Future

trait MessageCommunication {
  @deprecated("Use search instead")
  def query(query: SearchQuery, offset: Int, limit: Int): Future[MessageResults]

  def search(query: SearchQuery, offset: Int, limit: Int): Future[SearchResults]

  def verifyAvailableSpace(sizeInBytes: Long): Future[Boolean]

  def send(streamId: Id[StreamPreview], message: String, resourceIds: List[Id[ResourcePreview]]): Future[Either[ErrorResult, Unit]]

  def sendDirect(username: String, message: String, resourceIds: List[Id[ResourcePreview]]): Future[Either[ErrorResult, Unit]]

  def modifyMessage(id: Id[CachedMessage], message: String, resourceIds: List[Id[ResourcePreview]]): Future[Either[ErrorResult, Unit]]

  def addReaction(messageId: Id[CachedMessage], `type`: ReactionType): Future[Unit]

  def removeReaction(messageId: Id[CachedMessage], `type`: ReactionType): Future[Unit]

  def deleteMessage(id: Id[CachedMessage]): Future[Either[ErrorResult, Unit]]

  @deprecated("Use hierarchy and/or aliases")
  def streamsAndAliases(filter: String, streamsMax: Int, aliasesMax: Int): Future[StreamsAndAliases]

  def hierarchy(): Future[Hierarchy]

  def aliases(filter: String, limit: Int): Future[List[AliasPreview]]

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