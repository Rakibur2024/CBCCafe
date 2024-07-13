package com.cbccafe.repository;

import com.cbccafe.entity.User;
import com.cbccafe.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Integer countByEmployeeId(Long employeeId);
    Integer countByUserName(String userName);
    Integer countById(Long id);

    @Query("select u from User u")
    List<UserProjection> findAllUsers();

    @Query("select u from User u where u.id=:id")
    Optional<UserProjection> findUserByID(@Param("id") Long id);

    Integer countByUserNameAndIdNot(String userName, Long userId);
    Integer countByEmployeeIdAndIdNot(Long employeeId, Long userId);
    Integer countByUserNameAndPassword(String userName, String password);

    User findByUserNameAndPassword(String userName, String password);
}
