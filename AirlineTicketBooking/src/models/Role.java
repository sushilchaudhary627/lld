package models;

import constants.RoleName;

public class Role {
    RoleName roleName;

    public Boolean  isAdminRole(){
        return roleName.equals(RoleName.ADMIN);
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }
}
