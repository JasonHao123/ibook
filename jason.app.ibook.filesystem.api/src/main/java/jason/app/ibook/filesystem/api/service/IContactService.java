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
package jason.app.ibook.filesystem.api.service;

import jason.app.ibook.filesystem.api.model.IContact;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;


/**
 * Interface for the application's services layer.
 *
 * @author Ben Alex
 */
public interface IContactService {
    //~ Methods ========================================================================================================

    public void addPermission(IContact contact, String recipient, int permission);

 
    public void deletePermission(IContact contact, String recipient, int permission);

    @PreAuthorize("hasRole('supervisor')")
    public void create(IContact contact,String username);

    public void delete(IContact contact);
    @PreAuthorize("hasRole('supervisor')")
    public List<IContact> getAll();

    public IContact getById(Long id);

    public IContact getRandomContact();
}
