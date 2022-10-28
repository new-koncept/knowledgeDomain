package koncept.knowledgedomain.web.dto

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("knowledge")
data class KnowledgeDto(
    val id: Long, val parent: KnowledgeDto?, val name: String, val namespace: String, val children: MutableList<KnowledgeDto>? = null)