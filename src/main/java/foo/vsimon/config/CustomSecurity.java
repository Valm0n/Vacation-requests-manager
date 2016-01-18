/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foo.vsimon.config;

import foo.vsimon.controller.UserController;
import foo.vsimon.model.RoleValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author vsimon
 */
@Component("CustomSecurity")
public class CustomSecurity {
    
    @Autowired
    UserController userController;
    
    public boolean isAdmin(){
        try {
            return userController.getCurrentUser().getUserRole().equals(RoleValues.Admin);
        } catch (Exception e){
            //todo: catch exception
        }
        return false;
    }
}
