package ar.com.ml.xmen.controller;

import javax.servlet.http.HttpServletRequest;

import org.mapstruct.Context;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
    @GetMapping("/")
	public String hello(@Context HttpServletRequest request) {
        String contextPath = request.getRequestURL().toString();
		return "<h1>ML - Reclutamiento de Xmen's </h1>"
				+ "<br>"
				+ "<br><b>Invocaciones validas:</b> "+contextPath+"mutant y "+contextPath+"stats"
				+ "<br><b>Documentación:</b> "+contextPath+"swagger-ui.html";
	}

}
