package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	
	@AfterEach //테스트 안되는거 해결 : 각 테스트가 종료될 때마다 기능 실행, 메모리 DB에 저장된 데이터 삭제
	public void afterEach() {
		repository.clearStore();
	}
	
	// MemberRepository 는 인터페이스임
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");

		repository.save(member);

		Member result = repository.findById(member.getId()).get(); // optional은 get으로 바로 꺼낼 수 있음
		assertThat(member).isEqualTo(result);
		// Assertions.assertEquals(member, result);

	}

	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("159");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("shouzhen");
		repository.save(member2);

		// Optional<Member> result = repository.findByName("159");
		Member result = repository.findByName("159").get();
		
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("159");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("shouzhenn");
		repository.save(member2);
	
		
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(3);
	
	}

	}
