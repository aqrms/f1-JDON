package kernel.jdon.moduledomain.skillkeyword.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kernel.jdon.moduledomain.skill.domain.Skill;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "skill_keyword")
public class SkillKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "related_keyword", columnDefinition = "VARCHAR(50)", nullable = false)
    private String relatedKeyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", columnDefinition = "BIGINT", nullable = false)
    private Skill skill;
}

