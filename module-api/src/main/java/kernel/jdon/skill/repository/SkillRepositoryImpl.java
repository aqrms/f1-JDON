package kernel.jdon.skill.repository;

import static kernel.jdon.skill.domain.QSkill.*;
import static kernel.jdon.skillhistory.domain.QSkillHistory.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kernel.jdon.skill.dto.object.FindHotSkillDto;
import kernel.jdon.skill.dto.object.QFindHotSkillDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SkillRepositoryImpl implements SkillRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<FindHotSkillDto> findHotSkillList() {
		final int hotSkillKeywordCount = 10;

		return jpaQueryFactory
			.select(new QFindHotSkillDto(skill.id, skill.keyword))
			.from(skillHistory)
			.innerJoin(skill)
			.on(skillHistory.jobCategory.id.eq(skill.jobCategory.id)
				.and(skillHistory.keyword.eq(skill.keyword)))
			.groupBy(skill.id, skill.keyword)
			.orderBy(skill.keyword.count().desc())
			.limit(hotSkillKeywordCount)
			.fetch();
	}
}