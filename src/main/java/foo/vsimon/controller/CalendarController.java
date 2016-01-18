/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foo.vsimon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import foo.vsimon.entity.UserCalendarEntry;
import foo.vsimon.repository.UserCalendarEntryRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vsimon
 */
@RestController
@RequestMapping("/calendar")
public class CalendarController {
    
    @Autowired
    UserCalendarEntryRepository repository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CalendarController.class);
    
    @RequestMapping(value="/user/{id}", method=RequestMethod.GET)
    public @ResponseBody List<UserCalendarEntry> getEntriesForUser(@PathVariable("id") String userId) throws JsonProcessingException {
        LOGGER.info("Getting Calendar entries for given user");
        List<UserCalendarEntry> entries =  repository.findByUserId(userId);
        return entries;
    }
    
    @PreAuthorize("@CustomSecurity.isAdmin()")
    @RequestMapping(value="/validate/{id}/{accept}", method=RequestMethod.GET)
    public @ResponseBody UserCalendarEntry validateEntry(@PathVariable("id") String entryId, 
            @PathVariable("accept") boolean accept) throws JsonProcessingException {
        UserCalendarEntry entryToValidate = repository.findById(entryId);
        entryToValidate.setApproved(accept);
        return repository.save(entryToValidate);
    }
    
    @PreAuthorize("@CustomSecurity.isAdmin()")
    @RequestMapping(value="/reject/{id}", method=RequestMethod.DELETE)
    public void rejectEntry(@PathVariable("id") String entryId) throws JsonProcessingException {
        UserCalendarEntry entryToDelete = repository.findById(entryId);
        repository.delete(entryToDelete);
    }
}
