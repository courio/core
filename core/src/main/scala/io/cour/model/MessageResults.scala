package io.cour.model

@deprecated
case class MessageResults(offset: Int,
                          limit: Int,
                          total: Int,
                          count: Int,
                          messages: List[MessagePreview])