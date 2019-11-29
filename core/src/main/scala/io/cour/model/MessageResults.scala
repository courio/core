package io.cour.model

case class MessageResults(offset: Int,
                          limit: Int,
                          total: Int,
                          count: Int,
                          messages: List[MessagePreview])