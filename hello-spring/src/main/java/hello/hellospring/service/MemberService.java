package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

@Service
public class MemberService {

	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) { //MemberServiceTest에서 새로운 객체를 만들지 않고 사용 가능 => dependency injection
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
