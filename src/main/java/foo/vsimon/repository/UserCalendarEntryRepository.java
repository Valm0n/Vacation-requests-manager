/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foo.vsimon.repository;

import foo.vsimon.entity.UserCalendarEntry;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author vsimon
 */
public interface UserCalendarEntryRepository extends CrudRepository<UserCalendarEntry, Long> {
    
    List<UserCalendarEntry> findByUserId(String userId);
    UserCalendarEntry findById(String id);
}