package ar.com.educacionit.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * redirect a la home de swagger
 */
@Controller
public class LandingResource {

	@RequestMapping(value="/")
	public String swaggerUi() {
		return "redirect:/swagger-ui/";
	}
}
