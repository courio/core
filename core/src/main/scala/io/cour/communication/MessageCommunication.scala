package io.cour.communication

import com.outr.arango.Id
import com.outr.hookup.{client, server}
import io.cour.model.{AliasPreview, ErrorResult, Group, MessagePreview, MessageResults, Permission, PermissionAndUsername, StreamPreview, StreamsAndAliases, Tag}
import io.cour.query.SearchQuery

import scala.concurrent.Future

trait MessageCommunication {
  @server def query(query: SearchQuery, offset: Int, limit: Int): Future[MessageResults]

  @server def send(streamId: Id[StreamPreview], message: String): Future[Either[ErrorResult, Unit]]

  @server def sendDirect(username: String, message: String): Future[Either[ErrorResult, Unit]]

  @server def deleteMessage(id: Id[MessagePreview]): Future[Either[ErrorResult, Unit]]

  @server def streamsAndAliases(filter: String, streamsMax: Int, aliasesMax: Int): Future[StreamsAndAliases]

  @server def streamPermissions(id: Id[StreamPreview]): Future[List[PermissionAndUsername]]

  @server def saveStream(streamId: Option[Id[StreamPreview]],
                         name: String,
                         description: Option[String],
                         tags: List[Id[Tag]],
                         addAliasPermissions: List[Permission[AliasPreview]],
                         removeAliasPermissions: List[Permission[AliasPreview]],
                         addGroupPermissions: List[Permission[Group]],
                         removeGroupPermissions: List[Permission[Group]]): Future[Either[ErrorResult, Id[StreamPreview]]]

  @server def saveStreamSettings(streamId: Id[StreamPreview],
                                 customName: Option[String],
                                 notifications: Boolean,
                                 subscribed: Boolean): Future[Either[ErrorResult, Unit]]

  @server def deleteStream(streamId: Id[StreamPreview]): Future[Either[ErrorResult, Unit]]

  @client def receive(message: MessagePreview): Future[Unit]

  @client def notify(message: MessagePreview): Future[Unit]

  @client def refresh(): Future[Unit]
}