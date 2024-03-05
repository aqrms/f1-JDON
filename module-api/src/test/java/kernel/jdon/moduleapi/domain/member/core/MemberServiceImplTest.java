package kernel.jdon.moduleapi.domain.member.core;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kernel.jdon.moduleapi.domain.member.infrastructure.MemberFactoryImpl;
import kernel.jdon.moduledomain.jobcategory.domain.JobCategory;
import kernel.jdon.moduledomain.member.domain.Gender;
import kernel.jdon.moduledomain.member.domain.Member;

@ExtendWith(MockitoExtension.class)
@DisplayName("Member Service 테스트")
class MemberServiceImplTest {

	@Mock
	private MemberReader memberReader;
	@Mock
	private MemberInfoMapper memberInfoMapper;

	@Mock
	private MemberFactoryImpl memberFactory;

	@InjectMocks
	private MemberServiceImpl memberService;

	@Test
	@DisplayName("1: 사용자 정보 요청 시, get 메서드가 memberId에 해당되는 멤버의 정보를 응답으로 반환한다.")
	void givenMemberId_whenGetMember_thenReturnMemberInfo() {
		//given
		final var memberId = 1L;
		final var findMember = mockMember();
		final var mockFindMemberResponse = MemberInfo.FindMemberResponse.builder().email("email").build();
		final var mockSkillIdList = mockSkillIdList();

		//when
		when(memberReader.findById(memberId)).thenReturn(findMember);
		when(memberReader.findSkillIdListByMember(findMember)).thenReturn(mockSkillIdList);
		when(memberInfoMapper.of(findMember, mockSkillIdList)).thenReturn(mockFindMemberResponse);
		final var response = memberService.getMember(memberId);

		//then
		assertThat(response).isEqualTo(mockFindMemberResponse);
		assertThat(response.getEmail()).isEqualTo(findMember.getEmail());

		//verify
		verify(memberReader, times(1)).findById(memberId);
		verify(memberReader, times(1)).findSkillIdListByMember(findMember);
		verify(memberInfoMapper, times(1)).of(findMember, mockSkillIdList);
	}

	@Test
	@DisplayName("2: 사용자 정보 수정 요청 시, modify 메서드가 동작 결과로 memberId를 응답으로 반환한다.")
	void givenMemberUpdateInfo_whenModifyMember_thenReturnMemberId() {
		//given
		final var mockFindMember = mockMember();
		final var memberId = mockFindMember.getId();
		final var mockUpdateCommand = mock(MemberCommand.UpdateMemberRequest.class);

		//when
		when(memberReader.findById(memberId)).thenReturn(mockFindMember);
		final var response = memberService.modifyMember(memberId, mockUpdateCommand);

		//then
		assertThat(response.getMemberId()).isEqualTo(memberId);

		//verify
		verify(memberReader, times(1)).findById(memberId);
		verify(memberFactory, times(1)).update(mockFindMember, mockUpdateCommand);
	}
	
	private Member mockMember() {
		return Member.builder()
			.id(1L)
			.email("email")
			.nickname("nickname")
			.birth("2020-02-02")
			.gender(Gender.MALE)
			.jobCategory(mock(JobCategory.class))
			.build();
	}

	private List<Long> mockSkillIdList() {
		return List.of(1L, 2L, 3L);
	}
}