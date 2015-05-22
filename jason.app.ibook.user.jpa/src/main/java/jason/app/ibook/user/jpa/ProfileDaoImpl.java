package jason.app.ibook.user.jpa;

import jason.app.ibook.user.api.dao.IProfileDao;
import jason.app.ibook.user.api.model.AgentProfile;
import jason.app.ibook.user.api.model.CompanyProfile;
import jason.app.ibook.user.api.model.Profile;
import jason.app.ibook.user.api.model.TalentProfile;
import jason.app.ibook.user.jpa.entity.AgentProfileImpl;
import jason.app.ibook.user.jpa.entity.CompanyProfileImpl;
import jason.app.ibook.user.jpa.entity.ProfileImpl;
import jason.app.ibook.user.jpa.entity.TalentProfileImpl;

import javax.persistence.EntityManager;

public class ProfileDaoImpl implements IProfileDao {
    private EntityManager em;
    
    public void setEntityManager(EntityManager e) {
        em = e;
    }

    @Override
    public Profile save(Profile profile) {
        // TODO Auto-generated method stub
        ProfileImpl profileImpl = null;
        if(profile instanceof TalentProfile) {
            TalentProfileImpl profileImpl2 = new TalentProfileImpl();
            profileImpl2.setRealname(profile.getRealname());
            profileImpl = profileImpl2;
        }else if(profile instanceof AgentProfile) {
            AgentProfileImpl profileImpl2 = new AgentProfileImpl();
            profileImpl2.setRealname(profile.getRealname());
            profileImpl = profileImpl2;
        }else if(profile instanceof CompanyProfile) {
            profileImpl = new CompanyProfileImpl();
        }
        profileImpl.setUsername(profile.getUsername());
        em.persist(profileImpl);
        em.flush();
        profile.setId(profileImpl.getId().toString());
        return profile;
    }
}
