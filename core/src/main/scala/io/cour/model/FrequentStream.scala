package io.cour.model

import com.outr.arango.Id

case class FrequentStream(streamId: Id[StreamPreview], frequency: Int) extends Ordered[FrequentStream] {
  override def compare(that: FrequentStream): Int = that.frequency.compareTo(this.frequency)
}