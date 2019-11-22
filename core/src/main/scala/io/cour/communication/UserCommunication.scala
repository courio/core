package io.cour.communication

import com.outr.arango.Id
import io.cour.model.{AliasPreview, ErrorResult, Profile}

import scala.concurrent.Future

trait UserCommunication {
  def updateProfile(username: String, fullName: String): Future[Either[ErrorResult, Profile]]

  def updateProfilePicture(base64: String): Future[Either[ErrorResult, Unit]]

  def addFCMToken(token: String): Future[Unit]

  def logIn(username: String, password: String): Future[Either[ErrorResult, Profile]]

  def logOut(): Future[Unit]

  def updateStatus(focused: Boolean): Future[Unit]

  def profile(): Future[Profile]

  def changePassword(currentPassword: Option[String], newPassword: String): Future[Either[ErrorResult, Unit]]

  def agreeToEula(): Future[Unit]

  def blockAlias(aliasId: Id[AliasPreview]): Future[Unit]

  def unblockAlias(aliasId: Id[AliasPreview]): Future[Unit]
}