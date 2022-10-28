package koncept.knowledgedomain.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KnowledgeRepository: JpaRepository<Knowledge, Long> {
    fun findAllByParentKnowledgeId(knowledgeId: Long): MutableList<Knowledge>?
}