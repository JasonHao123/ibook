package jason.app.ibook.user.api.service;

import java.util.List;

import jason.app.ibook.user.api.model.Profile;

public interface IProfileService {

    Profile saveProfile(Profile profile);

    List<Profile> searchTalent(String q);

    List<Profile> searchAgent(String q);

}
