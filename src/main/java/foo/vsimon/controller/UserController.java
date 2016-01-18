/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foo.vsimon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import foo.vsimon.entity.UserEntry;
import foo.vsimon.model.GoogleProfile;
import foo.vsimon.model.RoleValues;
import foo.vsimon.repository.UserEntryRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vsimon
 */
@RestController
public class UserController {
    
    @Autowired
    UserEntryRepository repository;
    
    private UserEntry currentUser;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired private OAuth2RestOperations oauth2RestTemplate;

    @RequestMapping(value="/user", method=RequestMethod.GET)
    public @ResponseBody UserEntry getUser() throws JsonProcessingException {
        if(currentUser != null){
            return currentUser;
        } else {
            LOGGER.info("Searching for user details");
            GoogleProfile profile = getGoogleProfile();
            if(profile.getId() == null){
                return new UserEntry();
            } else {
                UserEntry userEntry = repository.findByUserId(profile.getId());
                if(userEntry == null){
                    userEntry = new UserEntry(profile, RoleValues.User);
                    repository.save(userEntry);
                }
                currentUser = userEntry;
                return userEntry;
            }
        }
    }
    
    public UserEntry getCurrentUser(){
        return currentUser;
    }
    
    @RequestMapping(value="/signout", method=RequestMethod.GET)
    public void logout(){
        //tokenService.(oauth2RestTemplate.getAccessToken().getValue());
        String url = "https://accounts.google.com/o/oauth2/revoke?token=" + oauth2RestTemplate.getAccessToken();
        //ResponseEntity<Object> res = oauth2RestTemplate.getForEntity(url, Object.class);
        oauth2RestTemplate.getOAuth2ClientContext().setAccessToken(null);
        currentUser = null;
    }

    @PreAuthorize("@CustomSecurity.isAdmin()")
    @RequestMapping(value="/userlist", method=RequestMethod.GET)
    public @ResponseBody List<UserEntry> getAllUsers() throws JsonProcessingException {
        return repository.findAll();
    }

    private GoogleProfile getGoogleProfile() {
        GoogleProfile user = new GoogleProfile();
        try {
            String url = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + oauth2RestTemplate.getAccessToken();
            ResponseEntity<GoogleProfile> forEntity = oauth2RestTemplate.getForEntity(url, GoogleProfile.class);
            user = forEntity.getBody();
        } catch(InsufficientAuthenticationException e){
            LOGGER.info("Anonymous. Can't fetch user details");
        } finally {
            return user;
        }
    }
}
