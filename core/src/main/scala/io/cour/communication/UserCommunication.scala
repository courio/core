package io.cour.communication

import com.outr.arango.Id
import io.cour.model.{AliasPreview, ErrorResult, NamedCredential, Profile}

import scala.concurrent.Future

trait UserCommunication {
  def createProfile(username: String, fullName: Option[String], password: String, base64: Option[String]): Future[Either[ErrorResult, Profile]]

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

  def unblockAliases(aliasIds: List[Id[AliasPreview]]): Future[Unit]

  def blockedAliases(): Future[List[NamedCredential[AliasPreview]]]
}