package io.cour.model

import com.outr.arango.Id
import io.youi.net.URL

sealed trait MessagePart

object MessagePart {
  case class CodeBlock(language: Option[String], code: String) extends MessagePart
  case class CodeSnippet(code: String) extends MessagePart
  case class Email(address: String) extends MessagePart
  case class Text(content: String) extends MessagePart
  case class Image(link: String, url: String, text: String) extends MessagePart
  case class Link(raw: String, url: URL, name: String = "") extends MessagePart
  case class Mention(id: Id[Credentialed]) extends MessagePart
  case class Tag(tag: String) extends MessagePart
}