package com.bondif.authservice.dao;

import com.bondif.authservice.entities.AppRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppRoleRepository extends MongoRepository<AppRole, String> {
    public AppRole findByName(String roleName);
}
