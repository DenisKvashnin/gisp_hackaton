package com.gisp.repository;

import com.gisp.domain.UserCount;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCountRepository extends CrudRepository<UserCount, Long> {
    List<UserCount> findAll();

    @Query("SELECT * FROM user_count WHERE inn = :inn")
    UserCount findByInn(@Param("inn") String inn);
}
