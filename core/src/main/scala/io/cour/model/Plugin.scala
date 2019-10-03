package io.cour.model

import com.outr.arango.Field

import com.outr.arango.{DocumentModel, Id, Serialization}

case class Plugin(status: PluginStatus,
                  created: Long = System.currentTimeMillis(),
                  modified: Long = System.currentTimeMillis(),
                  _id: Id[Plugin] = Plugin.id()) extends CourioDocument[Plugin]

object Plugin extends DocumentModel[Plugin] {
  val status: Field[io.cour.model.PluginStatus] = Field[io.cour.model.PluginStatus]("status")
  val created: Field[Long] = Field[Long]("created")
  val modified: Field[Long] = Field[Long]("modified")
  val _id: Field[com.outr.arango.Id[io.cour.model.Plugin]] = Field[com.outr.arango.Id[io.cour.model.Plugin]]("_id")

  override val collectionName: String = "plugins"
  override implicit val serialization: Serialization[Plugin] = Serialization.auto[Plugin]
}