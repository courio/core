package io.cour.model

import com.outr.arango.Field

import com.outr.arango.{DocumentModel, Id, Serialization}
import io.youi.net.URL

case class LinkPreview(url: String,
                       title: String,
                       siteName: Option[String],
                       description: Option[String],
                       byline: Option[String],
                       `type`: Option[String],
                       favIcon: Option[URL],
                       preview: Option[URL],
                       previewWidth: Option[Int],
                       previewHeight: Option[Int],
                       created: Long = System.currentTimeMillis(),
                       modified: Long = System.currentTimeMillis(),
                       _id: Id[LinkPreview] = LinkPreview.id()) extends CourioDocument[LinkPreview]

object LinkPreview extends DocumentModel[LinkPreview] {
  val url: Field[String] = Field[String]("url")
  val title: Field[String] = Field[String]("title")
  val siteName: Field[Option[String]] = Field[Option[String]]("siteName")
  val description: Field[Option[String]] = Field[Option[String]]("description")
  val byline: Field[Option[String]] = Field[Option[String]]("byline")
  val `type`: Field[Option[String]] = Field[Option[String]]("type")
  val favIcon: Field[Option[URL]] = Field[Option[URL]]("favIcon")
  val preview: Field[Option[URL]] = Field[Option[URL]]("preview")
  val previewWidth: Field[Option[Int]] = Field[Option[Int]]("previewWidth")
  val previewHeight: Field[Option[Int]] = Field[Option[Int]]("previewHeight")
  val created: Field[Long] = Field[Long]("created")
  val modified: Field[Long] = Field[Long]("modified")
  val _id: Field[com.outr.arango.Id[io.cour.model.LinkPreview]] = Field[com.outr.arango.Id[io.cour.model.LinkPreview]]("_id")

  override val collectionName: String = "linkPreviews"
  override implicit val serialization: Serialization[LinkPreview] = Serialization.auto[LinkPreview]
}