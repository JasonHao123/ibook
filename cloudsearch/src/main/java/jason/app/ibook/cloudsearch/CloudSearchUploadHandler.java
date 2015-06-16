package jason.app.ibook.cloudsearch;

import java.io.ByteArrayInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cloudsearchdomain.AmazonCloudSearchDomainClient;
import com.amazonaws.services.cloudsearchdomain.model.ContentType;
import com.amazonaws.services.cloudsearchdomain.model.UploadDocumentsRequest;

public class CloudSearchUploadHandler {
	private Logger logger = LoggerFactory.getLogger(CloudSearchUploadHandler.class);

	public String sayHello(String message) {
		logger.info(message);
		AWSCredentials credential = new BasicAWSCredentials("AKIAIZEF7E7HDCY53CUA","sQny0SFSrOIb+S9XTpV3ehdWrDWruIpzk82MElRA");
		
		AmazonCloudSearchDomainClient client = new AmazonCloudSearchDomainClient(credential);
		client.setEndpoint("doc-job-4aryp3vv2dhmgqtbfarsd4alia.us-west-2.cloudsearch.amazonaws.com");
		UploadDocumentsRequest upload = new UploadDocumentsRequest();
		upload.setContentType(ContentType.Applicationjson);
		String content= "[{\"type\": \"add\",\"id\":\"123\",\"fields\" : {\"title\":\"test\",\"description\":\"hello world\"}}]";
		upload.setContentLength(Long.valueOf(content.getBytes().length));
		upload.setDocuments(new ByteArrayInputStream(content.getBytes()));
		client.uploadDocuments(upload);
		
		return "hello";
		
	}
}
