package io.cour.communication

import io.cour.model.{ErrorResult, Profile}

import scala.concurrent.Future

trait UserCommunication {
  def updateProfile(username: String, fullName: String): Future[Either[ErrorResult, Profile]]

  def updateProfilePicture(base64: String): Future[Either[ErrorResult, Unit]]

  def addFCMToken(token: String): Future[Unit]

  def logIn(username: String, password: String): Future[Either[ErrorResult, Profile]]

  def logOut(): Future[Unit]

  def profile(): Future[Profile]

  def changePassword(currentPassword: Option[String], newPassword: String): Future[Either[ErrorResult, Unit]]

  def agreeToEula(): Future[Unit]
}