package koncept.knowledgedomain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator

@Entity
class Knowledge {

    @Id
    @SequenceGenerator(name = "knowledgeIdGenerator", sequenceName = "knowledge_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knowledgeIdGenerator")
    val id: Long = 0L

    @ManyToOne
    @JoinColumn(name = "parent_id")
    var parentKnowledge: Knowledge? = null

    @Column
    var name: String = ""

    @Column
    var uri: String = "koncept"
}