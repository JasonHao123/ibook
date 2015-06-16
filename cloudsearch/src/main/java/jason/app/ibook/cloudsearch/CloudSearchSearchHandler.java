package jason.app.ibook.cloudsearch;

import jason.app.ibook.job.api.model.Job;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cloudsearchdomain.AmazonCloudSearchDomainClient;
import com.amazonaws.services.cloudsearchdomain.model.Hit;
import com.amazonaws.services.cloudsearchdomain.model.SearchRequest;
import com.amazonaws.services.cloudsearchdomain.model.SearchResult;

public class CloudSearchSearchHandler {
	private Logger logger = LoggerFactory.getLogger(CloudSearchSearchHandler.class);

	public List<Job> sayHello(String query) {
		logger.info(query);
		AWSCredentials credential = new BasicAWSCredentials("AKIAIZEF7E7HDCY53CUA","sQny0SFSrOIb+S9XTpV3ehdWrDWruIpzk82MElRA");
		
		AmazonCloudSearchDomainClient client = new AmazonCloudSearchDomainClient(credential);
	
		client.setEndpoint("doc-job-4aryp3vv2dhmgqtbfarsd4alia.us-west-2.cloudsearch.amazonaws.com");
		SearchRequest request = new SearchRequest();
		request.setQuery("test");
		SearchResult result = client.search(request);
		List<Hit> hits = result.getHits().getHit();
		List<Job> jobs = new ArrayList<Job>();
		for(Hit hit:hits) {
			Job job = new Job();
			job.setId(hit.getId());
			job.setTitle(hit.getFields().get("title").get(0));
			jobs.add(job);
		}
		logger.info("search result"+result);
		return jobs;
		
	}
}
