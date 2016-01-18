/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foo.vsimon.model;

/**
 *
 * @author vsimon
 */
import java.util.HashMap;
import java.util.Map;

public enum RoleValues {
    User("ROLE_USER"), 
    Validator("ROLE_VALIDATOR"), 
    Editor("ROLE_EDITOR"),
    Admin("ROLE_ADMIN");
    
    private static final Map<String, RoleValues> rolesMap = new HashMap<String, RoleValues>();
    static {
        for (RoleValues r : RoleValues.values()) {
            rolesMap.put(r.getLabel(), r);
        }
    }
    
    private final String label;

    private RoleValues(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
    public static RoleValues getRoleByLabel(String label){
        return rolesMap.get(label);
    }
}
