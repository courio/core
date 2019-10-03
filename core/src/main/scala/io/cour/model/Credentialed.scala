package io.cour.model

import com.outr.arango.Id

trait Credentialed

object Credentialed {
  val Public: Id[Group] = Group.id("public")

  def apply[C <: Credentialed](id: Id[C]): Id[Credentialed] = id.asInstanceOf[Id[Credentialed]]
  def apply[C <: Credentialed](ids: Seq[Id[C]]): List[Id[Credentialed]] = ids.toList.map(_.asInstanceOf[Id[Credentialed]])
}