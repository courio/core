package io.cour.communication

import com.outr.arango.Id
import io.cour.model.{AliasPreview, ErrorResult, Group, MessagePreview, MessageResults, Permission, PermissionAndUsername, ReactionType, StreamPreview, StreamsAndAliases, Tag}
import io.cour.query.SearchQuery

import scala.concurrent.Future

trait MessageCommunication {
  def query(query: SearchQuery, offset: Int, limit: Int): Future[MessageResults]

  def verifyAvailableSpace(sizeInBytes: Long): Future[Boolean]

  def send(streamId: Id[StreamPreview], message: String): Future[Either[ErrorResult, Unit]]

  def sendDirect(username: String, message: String): Future[Either[ErrorResult, Unit]]

  def modifyMessage(id: Id[MessagePreview], message: String): Future[Either[ErrorResult, Unit]]

  def addReaction(messageId: Id[MessagePreview], `type`: ReactionType): Future[Unit]

  def removeReaction(messageId: Id[MessagePreview], `type`: ReactionType): Future[Unit]

  def deleteMessage(id: Id[MessagePreview]): Future[Either[ErrorResult, Unit]]

  def streamsAndAliases(filter: String, streamsMax: Int, aliasesMax: Int): Future[StreamsAndAliases]

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