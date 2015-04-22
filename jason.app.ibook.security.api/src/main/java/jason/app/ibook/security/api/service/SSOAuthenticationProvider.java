package jason.app.ibook.security.api.service;

import jason.app.ibook.security.api.model.RememberMeAuthentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class SSOAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            return super.authenticate(authentication);
        }else if(authentication instanceof RememberMeAuthentication) {
            RememberMeAuthentication auth = (RememberMeAuthentication) authentication;
            UserDetails user = this.getUserDetailsService().loadUserByUsername(auth.getToken().getUsername());
            if(user!=null) {
                auth.setAuthenticated(true);
                auth.setName(auth.getToken().getUsername());
                auth.setAuthorities(user.getAuthorities());
                auth.setDetails(user);
                auth.setPrincipal(user);
            }else {
                auth.setAuthenticated(false);
            }
            return auth;
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication) || RememberMeAuthentication.class.isAssignableFrom(authentication));
    }

}
