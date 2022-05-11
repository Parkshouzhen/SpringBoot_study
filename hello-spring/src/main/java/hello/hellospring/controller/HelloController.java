package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("hello") // url 맵핑
	public String hello(Model model) {
		model.addAttribute("attributeName", "AttributeValue_hello!!");
		return "hello"; // resources -> static -> hello.html

		// resource/static에 해당 리소스가 있는지 확인 후 확장자가 없는 요청일 경우 return "html이름"
		// templates/하위에 존재하는 "html이름" 찾아감
	}

	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam(value = "name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
		// 해당하는 템플릿이 있지만 아래의 경우에는 없음
	}
	
	@GetMapping("hello-string")
	@ResponseBody // http body부에 return 값을 직접 넣어줌
	public String helloString(@RequestParam("test") String name) { //쿼리스트링 ?test= 
		return "hello" + name; // hello(string 문자) + name 값
		// StringConverter 작동
	}
	
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
		// JsonConverter 작동
	}
	
	static class Hello{
		
		private String name;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		
		
		 
	}

}