/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foo.vsimon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author vsimon
 */
public class GoogleProfile {
    
    String id;
    @JsonProperty("given_name")
    String firstName;
    @JsonProperty("family_name")
    String lastName;
    @JsonProperty("email")
    String email;
    @JsonProperty("hd")
    String hd;
    @JsonProperty("picture")
    String picture;
    
    RoleValues role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public RoleValues getRole() {
        return role;
    }

    public void setRole(RoleValues role) {
        this.role = role;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }
    
    
    
}
