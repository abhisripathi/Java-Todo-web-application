package com.in28Minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	//"say-hello" => "Hello what are you learning today?"
	
	//say-hello
	
	// http://localhost:8080/say-hello
	@RequestMapping("say-hello")
	@ResponseBody //this will return whatever is returned by this message as is to the browser.
	public String sayHello(){
		return "Hello what are you learning today?";
	}
	
	@RequestMapping("say-goodmorning")
	@ResponseBody //this will return whatever is returned by this message as is to the browser.
	public String sayGoodMorning(){
		return "Hello, Good Morning!";
	}
	
	@RequestMapping("say-hello-html")
	@ResponseBody //this will return whatever is returned by this message as is to the browser.
	public String sayHelloHtml(){
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>MY first web app</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first html page");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
	//Jsp - sayHello.jsp
	//"say-hello-jsp" => sayHello.jsp
	// /src/main/resources/METS_INF/resources/WEB-INF/jsp/sayHello.jsp
	///src/main/resources/METS_INF/resources/WEB-INF/jsp/welcome.jsp
	// /src/main/resources/METS_INF/resources/WEB-INF/jsp/login.jsp
	@RequestMapping("say-hello-jsp")
	public static String sayHelloJsp(){
		return "sayHello";
	}
}
