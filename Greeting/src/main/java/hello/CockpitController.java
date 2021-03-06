package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;


@Controller
@RequestMapping("/cockpit")
public class CockpitController {
	@RequestMapping(method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");
	      return "hello";
	}

}
