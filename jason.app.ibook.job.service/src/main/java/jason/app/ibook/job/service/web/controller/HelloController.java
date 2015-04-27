package jason.app.ibook.job.service.web.controller;

import java.security.Principal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @EndpointInject(uri = "direct:hello")
    private ProducerTemplate template;
    
    @RequestMapping("/hello")
    public @ResponseBody String hello(HttpServletRequest request) {
        Exchange exchange = new DefaultExchange(template.getCamelContext());
        exchange.getIn().setBody("Hello");

            Subject subject = new Subject();
            subject.getPrincipals().add((Principal) SecurityContextHolder.getContext().getAuthentication());
            exchange.getIn().setHeader(Exchange.AUTHENTICATION, subject);
        exchange.getIn().setHeader("Authorization",request.getHeader("Authorization"));
        Future<Exchange> future = template.asyncSend("direct:hello", exchange);

        // You can do other things, whilst waiting for the invocation to complete

        // Now, retrieve the resulting exchange from the Future
        try {
            Exchange result = future.get();
            return result.getOut().toString();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "OK";
    }
}
