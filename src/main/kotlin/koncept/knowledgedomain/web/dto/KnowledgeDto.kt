package koncept.knowledgedomain.web.dto

data class KnowledgeDto(val id: Long, val parent: KnowledgeDto?,val name: String, val uri: String)