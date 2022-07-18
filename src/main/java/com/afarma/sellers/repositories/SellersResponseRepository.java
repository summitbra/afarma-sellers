package com.afarma.sellers.repositories;

import com.afarma.sellers.models.SellersResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellersResponseRepository extends JpaRepository<SellersResponse, Long> {

    @Query(value = "SELECT A.trading_name, B.description, B.stock, B.active , B.price, date_register, seller_id,availability,id " +
            "FROM `afarma-sellers`.sellers A " +
            "LEFT JOIN " +
            "(SELECT description, stock,  active, price_sale as price , date_register, seller_id, '-' AS availability,product_id  FROM `afarma-sellers`.products " +
            "UNION " +
            "SELECT description,  stock , active, price, date_register, seller_id, availability,service_id FROM `afarma-sellers`.services) B " +
            "ON A.id = B.seller_id WHERE B.seller_id", nativeQuery = true)
    Page<SellersResponse> findProductsAndSellers(Pageable pageable);
}
