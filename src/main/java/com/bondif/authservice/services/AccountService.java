package com.bondif.authservice.services;

import com.bondif.authservice.entities.AppRole;
import com.bondif.authservice.entities.AppUser;

import java.util.Collection;

public interface AccountService {
    Collection<AppUser> users();

    Collection<AppRole> roles();

    AppUser saveUser(String username, String password, String confirmedPassword, String appRole);

    AppRole saveRole(String role);

    void deleteUser(String id);

    void deleteRole(String id);

    AppUser addRoleToUser(String userId, String role);

    AppUser getUserByUsername(String username);
}
