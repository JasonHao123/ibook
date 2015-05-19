package jason.app.ibook.job.service;

import jason.app.ibook.commons.api.model.Category;
import jason.app.ibook.commons.api.model.ICategory;
import jason.app.ibook.job.api.dao.IJobDao;
import jason.app.ibook.job.api.model.Job;
import jason.app.ibook.job.api.service.IJobService;

import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jobService")
public class JobServiceImpl implements IJobService {
    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    private SolrServer server = new HttpSolrServer("http://localhost:8983/solr/job");
    @Autowired
    private IJobDao jobDao;

    @Override
    @Transactional
    public Job createJob(Job job) {
        // TODO Auto-generated method stub
        job = jobDao.create(job);
        logger.info("job id" + job.getId());
        /**
         * Exchange exchange = new DefaultExchange(template.getCamelContext()); exchange.getIn().setBody(job);
         * 
         * Subject subject = new Subject(); subject.getPrincipals().add((Principal) SecurityContextHolder.getContext().getAuthentication());
         * exchange.getIn().setHeader(Exchange.AUTHENTICATION, subject); exchange.getIn().setHeader("recipients", "direct:b,direct:c");
         * 
         * Future<Exchange> future = template.asyncSend("direct:hello", exchange);
         * 
         * // You can do other things, whilst waiting for the invocation to complete
         * 
         * // Now, retrieve the resulting exchange from the Future try { Exchange result = future.get(); System.out.println(result.getOut().toString()); } catch
         * (InterruptedException e) { e.printStackTrace(); } catch (ExecutionException e) { e.printStackTrace(); }
         */

        try {
            server.addBean(job);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return job;
    }

    @Override
    public List<Job> search(String query, List<ICategory> categories) {

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
                    if(cnt.getCount()>0) {
                        map.put(cnt.getName(), cnt.getName() + "(" + cnt.getCount() + ")");
                    }
                }
            }
            facet = rsp.getFacetField("subCategoryName");
            if (facet != null) {
                for (Count cnt : facet.getValues()) {
                    map.put(cnt.getName(), cnt.getName() + "(" + cnt.getCount() + ")");
                }
            }
            for (ICategory category : categories) {
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
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return beans;
    }

    @Override
    public Job getJobById(Long id) {
        // TODO Auto-generated method stub
        return jobDao.find(id);
    }

}
