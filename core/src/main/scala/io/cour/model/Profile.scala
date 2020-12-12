package io.cour.model

import com.outr.arango.Id
import io.youi.Unique

case class Profile(userId: Id[Profile],
                   aliasId: Id[AliasPreview],
                   username: String,
                   aliases: List[AliasPreview],
                   fullName: Option[String],
                   email: Option[String],
                   emailVerified: Boolean,
                   defaultPool: Id[PoolPreview],
                   fcmTokens: Set[String],
                   hasPassword: Boolean,
                   eulaAgreed: Boolean,
                   blockedAliasIds: Set[Id[AliasPreview]],
                   theme: String = Theme.Dark,
                   caching: Boolean = false,
                   lastModified: Long) {
  lazy val aliasIds: Set[Id[AliasPreview]] = aliases.map(_.id).toSet

  def isEmpty: Boolean = userId.value == "empty"
  def nonEmpty: Boolean = !isEmpty
}

object Profile {
  lazy val empty: Profile = Profile(
    userId = Profile.id("empty"),
    aliasId = AliasPreview.id("empty"),
    username = "",
    aliases = Nil,
    fullName = None,
    email = None,
    emailVerified = false,
    defaultPool = PoolPreview.id("empty"),
    fcmTokens = Set.empty,
    hasPassword = false,
    eulaAgreed = false,
    blockedAliasIds = Set.empty,
    theme = Theme.Dark,
    caching = false,
    lastModified = 0L
  )

  def id(value: String = Unique()): Id[Profile] = Id(value, "users")
}