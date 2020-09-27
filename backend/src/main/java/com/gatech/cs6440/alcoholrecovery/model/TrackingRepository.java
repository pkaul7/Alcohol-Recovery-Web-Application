package com.gatech.cs6440.alcoholrecovery.model;



import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface TrackingRepository extends PagingAndSortingRepository<Tracking,Integer> {
    List<Tracking> findByUserId(@Param("id") int userId);
    List<Tracking> findByUserId(@Param("id") int userId, Sort sort);
    @Query(value="SELECT e.* from Tracking e where e.alcohol > 0.0  and e.user_id=:userId order by e.date_time desc limit 1", nativeQuery=true)       // using @query
    Tracking findLastDrinkByUserId(@Param("userId") int userId);
}


