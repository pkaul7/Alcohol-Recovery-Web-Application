package com.gatech.cs6440.alcoholrecovery.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    public User findByUserName(String userName);
}



