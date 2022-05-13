package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}

	// ---------- 회원가입 -----------

	public Long join(Member member) {
		// 같은 이름이 있는 중복 회원 X
		ValidateDuplicateMember(member); // 중복 회원 검증

		memberRepository.save(member);
		return member.getId();
	}

	private void ValidateDuplicateMember(Member member) {
		// Optional<Member> result = memberRepository.findByName(member.getName());

		memberRepository.findByName(member.getName()).ifPresent(m -> { // ifPresent 값이 있으면, Optional이기 때문에 가능
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}

	// --------------전체 회원 조회-----------------
	public List<Member> findMember() {
		return memberRepository.findAll();
	}

	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}

}
