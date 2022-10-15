package koncept.knowledgedomain.web

data class CreateKnowledgeRequest(val parentId: Long? = null, val name: String, val uri: String)
