package io.cour.model

import com.outr.arango.Id

case class PermissionAndUsername(id: Id[AliasPreview], rules: List[Rule], username: String) {
  lazy val permissions: List[Permission[AliasPreview]] = rules.map(r => Permission(id, r))
}