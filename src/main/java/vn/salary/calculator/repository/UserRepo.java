package vn.salary.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.salary.calculator.model.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
}
