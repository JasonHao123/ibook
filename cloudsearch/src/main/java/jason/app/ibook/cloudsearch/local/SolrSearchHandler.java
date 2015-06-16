package jason.app.ibook.cloudsearch.local;

import jason.app.ibook.job.api.model.Job;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrSearchHandler {
	private Logger logger = LoggerFactory.getLogger(SolrSearchHandler.class);
    private SolrServer server = new HttpSolrServer("http://localhost:8983/solr/job");

	public List<Job> handle(String query) {
		logger.info("handle search locally");
		SolrQuery query2 = new SolrQuery();
		query2.setQuery(query);
		query2.setFacet(true);
		query2.addFacetField("categoryName");
		query2.addFacetField("subCategoryName");

		// query2.addSortField( "price", SolrQuery.ORDER.asc );
		List<Job> beans = new ArrayList<Job>();
		try {
			QueryResponse rsp = server.query(query2);
			beans = rsp.getBeans(Job.class);
			rsp.getHighlighting();
			Hashtable<String, String> map = new Hashtable<String, String>();
			FacetField facet = rsp.getFacetField("categoryName");
			if (facet != null) {
				for (Count cnt : facet.getValues()) {
					if (cnt.getCount() > 0) {
						map.put(cnt.getName(),
								cnt.getName() + "(" + cnt.getCount() + ")");
					}
				}
			}
			facet = rsp.getFacetField("subCategoryName");
			if (facet != null) {
				for (Count cnt : facet.getValues()) {
					map.put(cnt.getName(), cnt.getName() + "(" + cnt.getCount()
							+ ")");
				}
			}
/**			for (ICategory category : categories) {
				String name = map.get(category.getName());
				if (name != null) {
					((Category) category).setName(name);
				}
				if (category.getChildren() != null) {
					for (ICategory subCategory : category.getChildren()) {
						String name2 = map.get(subCategory.getName());
						if (name2 != null) {
							((Category) subCategory).setName(name2);
						}
					}
				}
			}
			*/
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return beans;
	}
}
