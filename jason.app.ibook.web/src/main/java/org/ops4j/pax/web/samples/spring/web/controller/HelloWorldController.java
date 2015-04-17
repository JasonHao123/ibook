package org.ops4j.pax.web.samples.spring.web.controller;


import jason.app.ibook.security.api.service.ISecurityService;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloWorldController {
    @Autowired
    private ISecurityService service;


    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("helloWorld");

		if(service!=null && request.getParameter("username")!=null) {
		    service.createUser(request.getParameter("username"), request.getParameter("password"),Arrays.asList(request.getParameter("roles").split(",")));
		}

/**		InvocationHandler handler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // TODO Auto-generated method stub
                return null;
            }
		    
		};
		MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
		                            MyInterface.class.getClassLoader(),
		                            new Class[] { MyInterface.class },
		                            handler);
		                            */
		String message = "Done! Spring MVC works like a charm! : - ) ";
		modelAndView.addObject("message", message);
		return modelAndView;
	}


}
