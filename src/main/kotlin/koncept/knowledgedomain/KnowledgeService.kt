package koncept.knowledgedomain

import koncept.knowledgedomain.persistence.Knowledge
import koncept.knowledgedomain.persistence.KnowledgeRepository
import koncept.knowledgedomain.utils.NamespaceUtils
import koncept.knowledgedomain.web.dto.request.CreateKnowledgeRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class KnowledgeService(private val knowledgeRepository: KnowledgeRepository) {

    @Value("\${koncept.namespace.prefix}")
    lateinit var defaultRootNamespace: String

    fun createKnowledge(createKnowledgeRequest: CreateKnowledgeRequest): Knowledge {
        val parent = createKnowledgeRequest.parentId?.let {
            knowledgeRepository.findById(it).get()
        }
        val namespace = parent?.let {
            NamespaceUtils.composeNamespace(it.namespace, createKnowledgeRequest.namespace)
        } ?: NamespaceUtils.composeNamespace(defaultRootNamespace, createKnowledgeRequest.namespace)
        val knowledge = Knowledge().apply {
            parentKnowledge = parent
            name = createKnowledgeRequest.name
            this.namespace = namespace
        }
        return knowledgeRepository.save(knowledge)
    }

    fun getKnowledge(id: Long): Knowledge {
        return knowledgeRepository.findById(id).get()
    }

    fun getChildren(id: Long): MutableList<Knowledge> {
        return knowledgeRepository.findAllByParentKnowledgeId(id) ?: mutableListOf()
    }
}