package jason.app.ibook.job.service;

import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.job.api.dao.IJobDao;
import jason.app.ibook.job.api.model.Job;
import jason.app.ibook.job.api.service.IJobService;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jobService")
public class JobServiceImpl implements IJobService {
	private static final Logger logger = LoggerFactory
			.getLogger(JobServiceImpl.class);
	
    @EndpointInject(uri = "direct:hello")
    private ProducerTemplate template;

	@Autowired
	private IJobDao jobDao;

	@Override
	@Transactional
	public Job createJob(Job job) {
		// TODO Auto-generated method stub
		job = jobDao.create(job);
		logger.info("job id" + job.getId());

		Exchange exchange = new DefaultExchange(template.getCamelContext());
		exchange.getIn().setBody(job);
/*
		Subject subject = new Subject();
		subject.getPrincipals().add(
				(Principal) SecurityContextHolder.getContext()
						.getAuthentication());
		exchange.getIn().setHeader(Exchange.AUTHENTICATION, subject);
		exchange.getIn().setHeader("recipients", "direct:b,direct:c");
*/
		Future<Exchange> future = template.asyncSend("direct:hello", exchange);

		// You can do other things, whilst waiting for the invocation to
		// complete

		// Now, retrieve the resulting exchange from the Future
		try {
			Exchange result = future.get();
			System.out.println(result.getOut().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return job;
	}

	@Override
	public List<Job> search(String query, List<ICategory> categories) {
		Exchange exchange = new DefaultExchange(template.getCamelContext());
		exchange.getIn().setBody(query);
		Future<Exchange> future = template.asyncSend("direct-vm:cloudsearch", exchange);
		try {
			Exchange result = future.get();
			return (List<Job>) result.getIn().getBody();
		//	System.out.println(result.getOut().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Job getJobById(Long id) {
		// TODO Auto-generated method stub
		return jobDao.find(id);
	}

}
