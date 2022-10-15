package koncept.knowledgedomain

import koncept.knowledgedomain.web.CreateKnowledgeRequest
import org.springframework.stereotype.Service

@Service
class KnowledgeService(private val knowledgeRepository: KnowledgeRepository) {

    fun createKnowledge(createKnowledgeRequest: CreateKnowledgeRequest): Knowledge {
        var parent: Knowledge? = null
        createKnowledgeRequest.parentId?.run {
            parent = knowledgeRepository.findById(this).get()
        }
        val knowledge = Knowledge().apply {
            parentKnowledge = parent
            name = createKnowledgeRequest.name
            uri = createKnowledgeRequest.uri
        }
        return knowledgeRepository.save(knowledge)
    }

    fun getKnowledge(id: Long): Knowledge {
        return knowledgeRepository.findById(id).get()
    }
}