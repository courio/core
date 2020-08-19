package io.cour.model

import com.outr.arango.Id
import io.youi.Unique

case class StreamPreview(sourceId: Id[SourcePreview],
                         label: String,
                         description: Option[String],
                         tags: List[Id[Tag]],
                         rules: Set[Rule],
                         groupPermissions: List[Permission[Group]] = Nil,
                         customLabel: Option[String],
                         notifications: Option[Boolean],
                         directMessage: Boolean,
                         lastMessage: Long,
                         lastSeen: Long,
                         unread: Int,
                         `type`: StreamType,
                         _id: Id[StreamPreview]) {
  lazy val publicRules: Set[Rule] = groupPermissions.filter(_.id == Credentialed.Public).map(_.rule).toSet
  lazy val allRules: Set[Rule] = rules ++ publicRules

  lazy val personalizedLabel: String = customLabel.getOrElse(label)

  lazy val isAdmin: Boolean = allRules.contains(Rule.Administrate)
  lazy val canWrite: Boolean = allRules.contains(Rule.Write) || isAdmin
  lazy val canRead: Boolean = allRules.contains(Rule.Read) || canWrite
}

object StreamPreview {
  def id(value: String = Unique()): Id[StreamPreview] = Id(value, "streams")
}