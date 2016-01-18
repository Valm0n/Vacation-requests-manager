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
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.joda.time.LocalDate;

@Entity
public class UserCalendarEntry implements Serializable {

    @Id
    private String id;
    
    private String userId;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private Boolean approved;

    public UserCalendarEntry() {}
    
    public UserCalendarEntry(String id, String userId, LocalDate startDate, LocalDate endDate, Boolean approved) {
        this.id = id;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return String.format(
                "UserCalendarEntry[userId='%s', start date = '%s', end date = '%s']",
                userId, startDate, endDate);
    }
}