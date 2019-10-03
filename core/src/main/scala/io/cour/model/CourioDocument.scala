package io.cour.model

import com.outr.arango.Document

trait CourioDocument[D <: CourioDocument[D]] extends Document[D] {
  def created: Long
  def modified: Long
}