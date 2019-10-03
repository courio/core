package io.cour.query

import io.cour.model.{FilterOperator, MessagePreview, StreamPreview}
import com.outr.arango.Id
import io.youi.net.URL

case class SearchQuery(filters: List[SearchFilter] = Nil) {
  def apply(url: URL): URL = SearchQuery.toURL(this, url)

  import SearchFilter._

  lazy val scope: Scope = filters.collectFirst {
    case f: Scope => f
  }.getOrElse(Scope(includePublic = false, includePrivate = true))
  lazy val streams: List[StreamId] = filters.collect {
    case f: StreamId => f
  }
  lazy val text: List[Text] = filters.collect {
    case f: Text => f
  }
  lazy val tags: List[TagName] = filters.collect {
    case f: TagName => f
  }
  lazy val creators: List[CreatorUsername] = filters.collect {
    case f: CreatorUsername => f
  }
  lazy val messageIds: List[MessageId] = filters.collect {
    case f: MessageId => f
  }
  lazy val mentions: List[Mentioned] = filters.collect {
    case f: Mentioned => f
  }

  def withFilter(filter: SearchFilter): SearchQuery = copy((filter :: filters).distinct)
  def withoutFilter(filter: SearchFilter): SearchQuery = copy(filters.filterNot(_ == filter))
  def withoutFilters(filters: SearchFilter*): SearchQuery = copy(this.filters.filterNot(filters.contains))
  def replace(current: SearchFilter, replacement: SearchFilter): SearchQuery = copy(filters.map {
    case f if f == current => replacement
    case f => f
  })
}

object SearchQuery {
  import SearchFilter._

  def toURL(query: SearchQuery, url: URL): URL = {
    var u = url.clearParams()

    def add(key: String, value: String, operator: FilterOperator): Unit = {
      val prefix = operator match {
        case FilterOperator.Include => "+"
        case FilterOperator.Boost => "^"
        case FilterOperator.Exclude => "-"
      }
      u = u.withParam(key, s"$prefix$value")
    }

    // Scope
    if (query.scope.includePublic) {
      u = u.withParam("public", "true")
    }
    if (!query.scope.includePrivate) {
      u = u.withParam("private", "false")
    }

    // Streams
    query.streams.foreach { f =>
      add("stream", f.id.value, f.operator)
    }

    // Text
    query.text.foreach { f =>
      add("text", f.text, f.operator)
    }

    // Tags
    query.tags.foreach { f =>
      add("tag", f.tag, f.operator)
    }

    // Creators
    query.creators.foreach { f =>
      add("creator", f.username, f.operator)
    }

    // Message Ids
    query.messageIds.foreach { f =>
      add("messageId", f.id.value, f.operator)
    }

    // Mentions
    query.mentions.foreach { f =>
      add("mention", f.username, f.operator)
    }

    u
  }
  def fromURL(url: URL): SearchQuery = {
    val includePublic = url.param("public").getOrElse("false").trim.toLowerCase match {
      case "true" => true
      case "false" => false
      case _ => false
    }
    val includePrivate = url.param("private").getOrElse("true").trim.toLowerCase match {
      case "true" => true
      case "false" => false
      case _ => true
    }
    val scope = Scope(includePublic, includePrivate)
    implicit class StringExtras(s: String) {
      def op: FilterOperator = s.charAt(0) match {
        case '+' => FilterOperator.Include
        case '^' => FilterOperator.Boost
        case '-' => FilterOperator.Exclude
      }
      def value: String = s.substring(1)
    }
    val streams = url.paramList("stream").map { s =>
      StreamId(s.op, StreamPreview.id(s.value))
    }
    val text = url.paramList("text").map { s =>
      Text(s.op, s.value)
    }
    val tags = url.paramList("tag").map { s =>
      TagName(s.op, s.value)
    }
    val creators = url.paramList("creator").map { s =>
      CreatorUsername(s.op, s.value)
    }
    val messageIds = url.paramList("messageId").map { s =>
      MessageId(s.op, MessagePreview.id(s.value))
    }
    val mentions = url.paramList("mention").map { s =>
      Mentioned(s.op, s.value)
    }
    val filters = scope :: streams ::: text ::: tags ::: creators ::: messageIds ::: mentions

    SearchQuery(filters)
  }
}