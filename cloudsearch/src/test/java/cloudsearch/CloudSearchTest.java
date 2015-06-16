package cloudsearch;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cloudsearchdomain.AmazonCloudSearchDomainClient;
import com.amazonaws.services.cloudsearchdomain.model.SearchRequest;
import com.amazonaws.services.cloudsearchdomain.model.SearchResult;

public class CloudSearchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AWSCredentials credential = new BasicAWSCredentials("AKIAIZEF7E7HDCY53CUA","sQny0SFSrOIb+S9XTpV3ehdWrDWruIpzk82MElRA");
		
		AmazonCloudSearchDomainClient client = new AmazonCloudSearchDomainClient(credential);
		client.setEndpoint("search-job-4aryp3vv2dhmgqtbfarsd4alia.us-west-2.cloudsearch.amazonaws.com");
		SearchRequest request = new SearchRequest();
		request.setQuery("test");
		SearchResult result = client.search(request);
		System.out.println(result);
	}

}
