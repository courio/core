package io.cour.model

import com.outr.arango.Id

case class Permission[C](id: Id[C], rule: Rule)