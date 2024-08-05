package com.louly.soft.money.transfer.rest.api.dao.repository;


import com.louly.soft.money.transfer.rest.api.dao.Transaction;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>, JpaSpecificationExecutor<Transaction> {

    Page<Transaction> findAll(@Nullable Specification<Transaction> spec, @NonNull Pageable pageable);
}