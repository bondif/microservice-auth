package com.bondif.authservice.services;

import com.bondif.authservice.dao.AppRoleRepository;
import com.bondif.authservice.dao.AppUserRepository;
import com.bondif.authservice.entities.AppRole;
import com.bondif.authservice.entities.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;

    private AppRoleRepository appRoleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Collection<AppUser> users() {
        return this.appUserRepository.findAll();
    }

    @Override
    public Collection<AppRole> roles() {
        return this.appRoleRepository.findAll();
    }

    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword, String appRole) {
        AppUser user = this.appUserRepository.findByUsername(username);
        AppRole role = this.appRoleRepository.findByName(appRole);

        if (role == null) throw new RuntimeException("role doesn't exist!");
        if (user != null) throw new RuntimeException("username already exists!");
        if (!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password!");

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(this.passwordEncoder.encode(password));
        appUser.getRoles().add(role);
        appUserRepository.save(appUser);

        return appUser;
    }

    @Override
    public void deleteUser(String id) {
        Optional<AppUser> appUser = this.appUserRepository.findById(id);

        if (!appUser.isPresent()) throw new RuntimeException("User not found");

        appUserRepository.deleteById(id);
    }

    @Override
    public AppUser addRoleToUser(String userId, String r) {
        Optional<AppUser> user = this.appUserRepository.findById(userId);
        AppRole appRole = this.appRoleRepository.findByName(r);

        if (!user.isPresent()) throw new RuntimeException("User not found");
        if (appRole == null) throw new RuntimeException("Role not found");

        AppUser appUser = user.get();

        appUser.getRoles().add(appRole);

        return this.appUserRepository.save(appUser);
    }

    @Override
    public AppUser getUserByUsername(String username) {
        return this.appUserRepository.findByUsername(username);
    }

    @Override
    public AppRole saveRole(String role) {
        return this.appRoleRepository.save(new AppRole(null, role, new ArrayList<>()));
    }

    @Override
    public void deleteRole(String id) {
        Optional<AppRole> appRole = this.appRoleRepository.findById(id);

        if (!appRole.isPresent()) throw new RuntimeException("Role not found");

        appRoleRepository.deleteById(id);
    }
}
