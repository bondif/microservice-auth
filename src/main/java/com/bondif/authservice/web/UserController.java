package com.bondif.authservice.web;

import com.bondif.authservice.entities.AppRole;
import com.bondif.authservice.entities.AppUser;
import com.bondif.authservice.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {

    private AccountService userService;

    public UserController(AccountService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Collection<AppUser> users() {
        return this.userService.users();
    }

    @GetMapping("/roles")
    public Collection<AppRole> roles() {
        return this.userService.roles();
    }

    @PostMapping("/users")
    public AppUser saveUser(@RequestParam String username, @RequestParam String password, @RequestParam String confirmedPassword, String role) {
        return this.userService.saveUser(username, password, confirmedPassword, role);
    }

    @PostMapping("/roles")
    public AppRole saveRole(@RequestParam String role) {
        return this.userService.saveRole(role);
    }

    @PostMapping("/users/{userId}/roles")
    public AppUser addRole(@PathVariable String userId, @RequestParam String role) {
        return this.userService.addRoleToUser(userId, role);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        this.userService.deleteUser(id);
    }

    @DeleteMapping("/roles/{id}")
    public void deleteRole(@PathVariable String id) {
        this.userService.deleteRole(id);
    }
}
