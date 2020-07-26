package io.cour.model

case class MessageContent(original: String, plainText: String, parts: List[MessagePart], parserVersion: Int)