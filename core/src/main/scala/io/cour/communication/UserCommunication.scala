package io.cour.communication

import com.outr.hookup.server
import io.cour.model.{ErrorResult, Profile}

import scala.concurrent.Future

trait UserCommunication {
  @server def updateProfile(username: String, fullName: String): Future[Either[ErrorResult, Profile]]

  @server def updateProfilePicture(base64: String): Future[Either[ErrorResult, Unit]]

  @server def logIn(username: String, password: String): Future[Either[ErrorResult, Profile]]

  @server def logOut(): Future[Unit]

  @server def profile(): Future[Profile]

  @server def changePassword(currentPassword: Option[String], newPassword: String): Future[Either[ErrorResult, Unit]]
}