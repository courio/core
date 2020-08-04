package io.cour.model

case class MessageReferences(links: List[LinkPreview],
                             emails: List[String],
                             images: List[Image],
                             videos: List[Video],
                             resources: List[ResourcePreview])