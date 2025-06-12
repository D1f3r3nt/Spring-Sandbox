package com.diferent.springsandbox.repository;

import com.diferent.springsandbox.model.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query("SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password")
	Optional<UserEntity> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
