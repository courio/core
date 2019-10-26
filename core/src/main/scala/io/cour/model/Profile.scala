package io.cour.model

import com.outr.arango.Id

case class Profile(userId: Id[Profile],
                   aliasId: Id[AliasPreview],
                   username: String,
                   aliases: List[AliasPreview],
                   fullName: Option[String],
                   defaultPool: Id[PoolPreview],
                   fcmTokens: Set[String],
                   hasPassword: Boolean)

object Profile {
  lazy val empty: Profile = Profile(Id("", ""), Id("", ""), "", Nil, None, Id("", ""), Set.empty, hasPassword = false)
}