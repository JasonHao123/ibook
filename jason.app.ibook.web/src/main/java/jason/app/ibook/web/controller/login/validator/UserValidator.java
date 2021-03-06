/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jason.app.ibook.web.controller.login.validator;

import org.springframework.security.core.userdetails.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Validates {@link WebContact}.
 *
 * @author Ben Alex
 */
public class UserValidator implements Validator {
    //~ Methods ========================================================================================================

    @SuppressWarnings("unchecked")
    public boolean supports(Class clazz) {
        return clazz.equals(User.class);
    }

    public void validate(Object obj, Errors errors) {
        User wc = (User) obj;

        if ((wc.getUsername() == null) || (wc.getUsername().length() < 3) || (wc.getUsername().length() > 50)) {
            errors.rejectValue("username", "validate.err.username", "Please input a valid email address. *");
        }
 
    }
}
