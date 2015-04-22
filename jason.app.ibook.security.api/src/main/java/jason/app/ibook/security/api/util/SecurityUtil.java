package jason.app.ibook.security.api.util;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
    public static String getPrincipalName(Authentication auth) {
        if (auth.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) auth.getPrincipal()).getUsername();
        }

        if (auth.getPrincipal() instanceof Principal) {
            return ((Principal)auth.getPrincipal()).getName();
        }

        return (auth.getPrincipal() == null) ? "" : auth.getPrincipal().toString();
    }
}
