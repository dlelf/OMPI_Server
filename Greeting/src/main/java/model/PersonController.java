package model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.Greeting;

@RestController
public class PersonController {
	@RequestMapping("/pers")
	public Greeting getPerson()
	{
		return new Greeting(2,"Dima");
		
	}

}
