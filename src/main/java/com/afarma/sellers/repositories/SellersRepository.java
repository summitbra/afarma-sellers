package com.afarma.sellers.repositories;

import com.afarma.sellers.models.Sellers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SellersRepository extends JpaRepository<Sellers, Long> {

    Sellers findByCnpj(String cnpj);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sellers SET situation = false WHERE id = :id", nativeQuery = true)
    void updateSituation(@Param("id")Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sellers SET status = true WHERE id = :id", nativeQuery = true)
    void updateStatus(@Param("id")Long id);

    @Query(value = "SELECT * FROM sellers WHERE status = FALSE AND situation = TRUE", nativeQuery = true)
    Page<Sellers> findActiveSellers(@Param("pageable") Pageable pageable);

}
