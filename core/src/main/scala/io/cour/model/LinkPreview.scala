package io.cour.model

import com.outr.arango.{DocumentModel, Field, Id, Index}
import fabric.rw.RW
import spice.net.URL

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
  override implicit val rw: RW[LinkPreview] = RW.gen

  val url: Field[String] = field[String]("url")
  val title: Field[String] = field[String]("title")
  val siteName: Field[Option[String]] = field[Option[String]]("siteName")
  val description: Field[Option[String]] = field[Option[String]]("description")
  val byline: Field[Option[String]] = field[Option[String]]("byline")
  val `type`: Field[Option[String]] = field[Option[String]]("type")
  val favIcon: Field[Option[URL]] = field[Option[URL]]("favIcon")
  val preview: Field[Option[URL]] = field[Option[URL]]("preview")
  val previewWidth: Field[Option[Int]] = field[Option[Int]]("previewWidth")
  val previewHeight: Field[Option[Int]] = field[Option[Int]]("previewHeight")
  val created: Field[Long] = field[Long]("created")
  val modified: Field[Long] = field[Long]("modified")

  override def indexes: List[Index] = url.index.persistent(unique = true) :: index(created, modified)

  override val collectionName: String = "linkPreviews"
}