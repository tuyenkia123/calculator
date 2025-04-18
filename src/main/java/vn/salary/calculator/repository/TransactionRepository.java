package vn.salary.calculator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.salary.calculator.model.entity.Transaction;
import vn.salary.calculator.model.enumeration.Type;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t where YEAR(t.createdAt) = :year " +
            "AND MONTH(t.createdAt) = :month AND t.type IN :type " +
            "ORDER BY t.id DESC")
    Page<Transaction> findByCreatedAtOrOptionalType(int year, int month, List<Type> type, Pageable pageable);

    @Query("SELECT sum(t.money) FROM Transaction t where YEAR(t.createdAt) = :year " +
            "AND MONTH(t.createdAt) = :month AND t.type IN :type")
    Long sumByCreatedAtAndType(int year, int month, List<Type> type);

    @Query("SELECT t FROM Transaction t where t.type IN :type ORDER BY t.id DESC")
    Page<Transaction> findByCreatedAtOrOptionalListType(List<Type> type, Pageable pageable);
}
