package io.cour.model

import com.outr.arango.Id

case class Permission[C](id: Id[C], rule: Rule) extends Ordered[Permission[C]] {
  override def compare(that: Permission[C]): Int = (this.id, that.rule).compare((that.id, that.rule))
}