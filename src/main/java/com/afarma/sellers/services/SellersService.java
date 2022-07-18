package com.afarma.sellers.services;

import com.afarma.sellers.Dtos.ResponseError;
import com.afarma.sellers.config.LoggerConfig;
import com.afarma.sellers.exceptions.InternalServerErrorException;
import com.afarma.sellers.models.Sellers;
import com.afarma.sellers.models.SellersResponse;
import com.afarma.sellers.repositories.HistoricDAO;
import com.afarma.sellers.repositories.SellersResponseRepository;
import com.afarma.sellers.repositories.SellersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class SellersService {

    private final SellersRepository sellersRepository;
    private final HistoricDAO historicDAO;
    private final SellersResponseRepository sellersResponseRepository;

    public SellersService(SellersRepository sellersRepository, HistoricDAO historicDAO, SellersResponseRepository sellersResponseRepository) {
        this.sellersRepository = sellersRepository;
        this.historicDAO = historicDAO;
        this.sellersResponseRepository = sellersResponseRepository;
    }

    public ResponseEntity<Object> createSeller(Sellers seller) {
        try{
            if (sellersRepository.findByCnpj(seller.getCnpj())!=null) {
                return ResponseEntity.badRequest()
                        .body(new ResponseError(HttpStatus.BAD_REQUEST, "Cnpj já existente."));
            }
            sellersRepository.save(seller);
        }catch (Exception exception){
            LoggerConfig.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            throw new InternalServerErrorException("Erro inesperado no momento de salvar.", exception);
        }

        return ResponseEntity.status(200).body("Cliente criado com sucesso");
    }

    public ResponseEntity<Object> inactiveSeller(Long id){
        try {
            sellersRepository.updateSituation(id);
        }catch (Exception exception){
            LoggerConfig.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            throw new InternalServerErrorException("Erro inesperado no momento do update", exception);
        }

        return ResponseEntity.status(200).body("O cliente foi inativado com sucesso");

    }

    public ResponseEntity<Object> sellerApproval(Long id){
        try {
            sellersRepository.updateStatus(id);
        }catch (Exception exception){
            LoggerConfig.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            throw new InternalServerErrorException("Erro inesperado no momento do update", exception);
        }

        return ResponseEntity.status(200).body("O cliente foi ativado com sucesso");

    }

    public Page<Sellers> searchActiveSellers(Pageable pageable){

        return sellersRepository.findActiveSellers(pageable);
    }

    public Page<SellersResponse> searchForServicesAndProducts(Pageable pageable){
        return sellersResponseRepository.findProductsAndSellers(pageable);
    }
}
