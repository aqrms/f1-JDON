package kernel.jdon.moduleapi.domain.member.infrastructure;

import org.springframework.stereotype.Component;

import kernel.jdon.member.domain.Member;
import kernel.jdon.moduleapi.domain.member.core.MemberReader;
import kernel.jdon.moduleapi.domain.member.error.MemberErrorCode;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberReaderImpl implements MemberReader {

	private final MemberRepository memberRepository;

	@Override
	public Member findById(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(MemberErrorCode.NOT_FOUND_MEMBER::throwException);
	}

	@Override
	public boolean existsById(Long memberId) {
		return memberRepository.existsById(memberId);
	}
}