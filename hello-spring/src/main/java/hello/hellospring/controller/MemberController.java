package hello.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	
	private final MemberService memberService;

	@Autowired //spring컨테이너에서 memberService를 가져옴 / controller <-> service 연결
	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
}
