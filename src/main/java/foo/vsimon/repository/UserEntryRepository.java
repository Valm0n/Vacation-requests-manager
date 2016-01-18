/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foo.vsimon.repository;

import foo.vsimon.entity.UserEntry;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author vsimon
 */
public interface UserEntryRepository extends CrudRepository<UserEntry, Long> {
    
    UserEntry findByUserId(String userId);
    List<UserEntry> findAll();
}
