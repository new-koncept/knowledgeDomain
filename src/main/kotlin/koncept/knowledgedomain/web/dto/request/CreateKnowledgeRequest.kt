package koncept.knowledgedomain.web.dto.request

data class CreateKnowledgeRequest(val parentId: Long? = null, val name: String, val namespace: String)
