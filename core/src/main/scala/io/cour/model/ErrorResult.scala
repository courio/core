package io.cour.model

case class ErrorResult(message: String) {
  var throwable: Option[Throwable] = None
}