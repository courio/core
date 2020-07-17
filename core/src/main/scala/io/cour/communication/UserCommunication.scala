package io.cour.communication

import com.outr.arango.Id
import io.cour.model.{AliasPreview, ErrorResult, NamedCredential, Profile, TwitterAccountPreview}
import io.youi.net.URL

import scala.concurrent.Future

trait UserCommunication {
  def createAccount(username: String, fullName: Option[String], email: Option[String], password: String, fileName: Option[String]): Future[Either[ErrorResult, Profile]]

  def updateProfile(username: String, fullName: String): Future[Either[ErrorResult, Profile]]

  def updatePicture(fileName: String): Future[Either[ErrorResult, Unit]]

  def updateEmailAddress(email: String): Future[Either[ErrorResult, Unit]]

  def confirmEmailAddress(token: String): Future[Either[ErrorResult, Profile]]

  def addFCMToken(token: String): Future[Unit]

  def removeFCMToken(token: String): Future[Unit]

  def logIn(username: String, password: String): Future[Either[ErrorResult, Profile]]

  def forgotPassword(username: String): Future[Either[ErrorResult, Unit]]

  def resetPassword(username: String, token: String, newPassword: String): Future[Either[ErrorResult, Profile]]

  def logOut(): Future[Unit]

  def updateStatus(focused: Boolean): Future[Unit]

  def profile(): Future[Profile]

  def changePassword(currentPassword: Option[String], newPassword: String): Future[Either[ErrorResult, Unit]]

  def agreeToEula(): Future[Unit]

  def blockAlias(aliasId: Id[AliasPreview]): Future[Unit]

  def unblockAliases(aliasIds: List[Id[AliasPreview]]): Future[Unit]

  def blockedAliases(): Future[List[NamedCredential[AliasPreview]]]

  def twitterAuthorizationLink(): Future[URL]

  def twitterAccounts(): Future[List[TwitterAccountPreview]]

  def deleteTwitterAccount(id: Id[TwitterAccountPreview]): Future[Either[ErrorResult, Unit]]
}