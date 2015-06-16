package jason.app.ibook.cloudsearch.local;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrUploadHandler {
	private Logger logger = LoggerFactory.getLogger(SolrUploadHandler.class);
    private SolrServer server = new HttpSolrServer("http://localhost:8983/solr/job");

	public String handle(Object job) {
		logger.info("handle upload locally");
		try {
            server.addBean(job);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return null;
	}
}
