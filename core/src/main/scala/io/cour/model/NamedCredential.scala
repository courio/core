package io.cour.model

import com.outr.arango.Id
import io.youi.net.URL

case class NamedCredential[T](id: Id[T],
                              username: String,
                              name: Option[String],
                              lastModified: Long,
                              icon: URL,
                              profile: URL) {
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
}