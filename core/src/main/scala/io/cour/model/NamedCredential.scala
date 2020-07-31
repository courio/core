package io.cour.model

import com.outr.arango.Id
import io.youi.net.URL

case class NamedCredential[T](id: Id[T],
                              username: String,
                              name: Option[String],
                              lastModified: Long) {
  lazy val icon: URL = URL(s"https://s3.us-west-1.wasabisys.com/courio/user/${id.value}-icon.png?m=$lastModified")
  lazy val profile: URL = URL(s"https://s3.us-west-1.wasabisys.com/courio/user/${id.value}-profile.png?m=$lastModified")

  def include(filter: String): Int = if (filter.nonEmpty) {
    val u = username.toLowerCase
    val n = name.getOrElse("").toLowerCase
    if (filter == u || (name.nonEmpty && filter == n)) {
      3
    } else if (filter.startsWith(u) || (name.nonEmpty && filter.startsWith(n))) {
      2
    } else if (u.contains(filter) || (name.nonEmpty && n.contains(filter))) {
      1
    } else {
      0
    }
  } else {
    1
  }

  override def toString: String = name match {
    case Some(n) => s"$username ($n)"
    case None => username
  }

  override def equals(obj: Any): Boolean = obj match {
    case that: NamedCredential[_] => this.id._id == that.id._id
    case _ => false
  }

  def credentialed: NamedCredential[Credentialed] = this.asInstanceOf[NamedCredential[Credentialed]]
}