package io.cour.model

case class MessageReferencePreviews(links: List[LinkPreview],
                                    emails: List[String],
                                    images: List[Image],
                                    videos: List[Video],
                                    resources: List[ResourcePreview])