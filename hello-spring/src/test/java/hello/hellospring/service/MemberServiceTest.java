package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);

	}
	
	@AfterEach //테스트 안되는거 해결 : 각 테스트가 종료될 때마다 기능 실행, 메모리 DB에 저장된 데이터 삭제
	public void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("같은이름");

		// when
		Long saveId = memberService.join(member);

		// then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	public void 중복_회원_예외() {
		// given
		Member member1 = new Member();
		member1.setName("같은이름");

		Member member2 = new Member();
		member2.setName("같은이름");

		// when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		/*
		 * memberService.join(member1);
		 * 
		 * try { memberService.join(member2); fail(); } catch (IllegalStateException e)
		 * { assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.S"); }
		 */

		// then
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

	}

	@Test
	void 전체회원조회() {

	}

	@Test
	void 회원조회() {

	}

}
