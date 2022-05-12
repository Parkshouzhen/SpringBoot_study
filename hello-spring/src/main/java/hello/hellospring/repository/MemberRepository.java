package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

public interface MemberRepository {
	
	Member save(Member member);
	Optional<Member> findById(Long id); // optional: null값 처리 방법
	Optional<Member> findByName(String name);
	List<Member> findAll();
	
}
