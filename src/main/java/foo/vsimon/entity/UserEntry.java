/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foo.vsimon.entity;

/**
 *
 * @author vsimon
 */
import foo.vsimon.model.GoogleProfile;
import foo.vsimon.model.RoleValues;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class UserEntry implements Serializable {

    @Id
    private String userId;
    
    private String firstName;
    private String lastName;
    private String pictureUrl;
    private String email;
    private RoleValues userRole;

    public UserEntry() {
        this.firstName = null;
        this.lastName = null;
        this.pictureUrl = null;
        this.userId = null;
        this.email = null;
        this.userRole = null;
        
    }
    

    public UserEntry(GoogleProfile profile, RoleValues role) {
        this.userId = profile.getId();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.pictureUrl = profile.getPicture();
        this.email = profile.getEmail();
        this.userRole = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleValues getUserRole() {
        return userRole;
    }

    public void setUserRole(RoleValues userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[userId='%s', full name = '%s', role='%s']",
                userId, firstName + " " + lastName, userRole);
    }
}
