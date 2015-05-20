package jason.app.ibook.user.service;

import jason.app.ibook.user.api.dao.IProfileDao;
import jason.app.ibook.user.api.model.Profile;
import jason.app.ibook.user.api.service.IProfileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("profileService")
public class ProfileServiceImpl implements IProfileService {
    private SolrServer server = new HttpSolrServer("http://localhost:8983/solr/user");
    @Autowired
    private IProfileDao profileDao;

    @Override
    public Profile saveProfile(Profile profile) {
        // TODO Auto-generated method stub
        profile =  profileDao.save(profile);
        try {
            server.addBean(profile);
        } catch (IOException | SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return profile;
    }


    public List<Profile> searchTalent(String q) {
        // TODO Auto-generated method stub
        return search(q);
    }


    public List<Profile> searchAgent(String q) {
        // TODO Auto-generated method stub
        return search(q);
    }


    private List<Profile> search(String q) {
        SolrQuery query2 = new SolrQuery();
        query2.setQuery(q);

        
        // query2.addSortField( "price", SolrQuery.ORDER.asc );
        List<Profile> beans = new ArrayList<Profile>();
        try {
            QueryResponse rsp = server.query(query2);
            beans = rsp.getBeans(Profile.class);
           
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return beans;
    }
}
