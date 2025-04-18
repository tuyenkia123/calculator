package vn.salary.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.salary.calculator.model.entity.Revenue;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

}
