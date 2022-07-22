package com.afarma.sellers.repositories;

import com.afarma.sellers.models.SellersResponse;
import com.afarma.sellers.models.SellersResponseId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SellersResponseRepository extends JpaRepository<SellersResponse, SellersResponseId> {

    @Query(value = "SELECT A.trading_name, B.description, B.stock, B.active , B.price, date_register, seller_id,availability,id, '' as company_name, B.type " +
            "FROM `afarma-sellers`.sellers A " +
            "LEFT JOIN " +
            "(SELECT description, stock,  active, price_sale as price , date_register, seller_id, '-' AS availability,product_id, 'PRO' as type  FROM `afarma-sellers`.products " +
            "UNION " +
            "SELECT description,  '' as stock , active, price, date_register, seller_id, availability,service_id, 'SER' as type FROM `afarma-sellers`.services) B " +
            "ON A.id = B.seller_id WHERE  COALESCE(NULLIF(:active,''), active) = active AND  date_register BETWEEN DATE_FORMAT(:dateIni,\"%Y-%m-%d\") AND DATE_FORMAT(:dateFin,\"%Y-%m-%d\") " +
            "AND COALESCE(NULLIF(:description,''), upper(B.description)) = upper(B.description) ", nativeQuery = true)
    Page<SellersResponse> findProductsAndSellers(Pageable pageable, @Param("description") String description,
                                                 @Param("active") Boolean active, @Param("dateIni") String dateIni,
                                                 @Param("dateFin") String dateFin);

    @Query(value = "SELECT A.trading_name, B.description, B.stock, B.active , B.price, date_register, seller_id,availability,id, '' as company_name, B.type " +
            "FROM `afarma-sellers`.sellers A " +
            "LEFT JOIN " +
            "(SELECT description, stock,  active, price_sale as price , date_register, seller_id, '-' AS availability,product_id, 'PRO' as type  FROM `afarma-sellers`.products " +
            "UNION " +
            "SELECT description,  '' as stock , active, price, date_register, seller_id, availability,service_id, 'SER' as type FROM `afarma-sellers`.services) B " +
            "ON A.id = B.seller_id WHERE  active = false AND  date_register BETWEEN DATE_FORMAT(:dateIni,\"%Y-%m-%d\") AND DATE_FORMAT(:dateFin,\"%Y-%m-%d\") " +
            "AND COALESCE(NULLIF(:description,''), upper(B.description)) = upper(B.description) ", nativeQuery = true)
    Page<SellersResponse> findProductsAndSellersFalse(Pageable pageable, @Param("description") String description,
                                                      @Param("dateIni") String dateIni, @Param("dateFin") String dateFin);
}
