package com.afarma.sellers.repositories;


import com.afarma.sellers.config.MySqlConfig;
import com.afarma.sellers.models.Sellers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HistoricDAO {

    private final JdbcTemplate jdbcTemplate;

    public HistoricDAO(MySqlConfig profimetricsConfig) {
        this.jdbcTemplate = new JdbcTemplate(profimetricsConfig.euDataSource());
    }

    public void insertSeller(Sellers sellers){
        String query ="INSERT INTO sellers " +
                "(trading_name, company_name, cpf, cnpj, email, phone, category) " +
                "VALUES('"+sellers.getTradingName()+"', '"+sellers.getCompanyName()+"', '"+sellers.getCpf()+"', " +
                "'"+sellers.getCnpj()+"', '"+sellers.getEmail()+"', '"+sellers.getPhone()+"', '"+sellers.getCategory()+"');";
        jdbcTemplate.execute(query);
    }

    public Long getCnpj(String cnpj) {
        String query = "SELECT COUNT(*) FROM `afarma-sellers`.sellers WHERE cnpj = '"+cnpj+"' ;";

        return jdbcTemplate.queryForObject(query, Long.class);
    }

}


