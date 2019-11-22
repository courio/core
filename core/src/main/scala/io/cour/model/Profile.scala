package io.cour.model

import com.outr.arango.Id

case class Profile(userId: Id[Profile],
                   aliasId: Id[AliasPreview],
                   username: String,
                   aliases: List[AliasPreview],
                   fullName: Option[String],
                   defaultPool: Id[PoolPreview],
                   fcmTokens: Set[String],
                   hasPassword: Boolean,
                   eulaAgreed: Boolean,
                   blockedAliasIds: Set[Id[AliasPreview]],
                   lastModified: Long) {
  lazy val aliasIds: Set[Id[AliasPreview]] = aliases.map(_.id).toSet
}

object Profile {
  lazy val empty: Profile = Profile(
    userId = Id("", ""),
    aliasId = Id("", ""),
    username = "",
    aliases = Nil,
    fullName = None,
    defaultPool = Id("", ""),
    fcmTokens = Set.empty,
    hasPassword = false,
    eulaAgreed = false,
    blockedAliasIds = Set.empty,
    lastModified = 0L
  )
}