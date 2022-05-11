package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("hello") //url 맵핑
	public String hello(Model model) {
		model.addAttribute("attributeName", "AttributeValue_hello!!");
		return "hello"; // resources -> static -> hello.html
		
		// resource/static에 해당 리소스가 있는지 확인 후 확장자가 없는 요청일 경우 return "html이름" templates/하위에 존재하는 "html이름" 찾아감
	}
}
