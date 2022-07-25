package com.afarma.sellers.services;

import com.afarma.sellers.Dtos.ResponseError;
import com.afarma.sellers.config.LoggerConfig;
import com.afarma.sellers.exceptions.InternalServerErrorException;
import com.afarma.sellers.models.Sellers;
import com.afarma.sellers.models.SellersDTO;
import com.afarma.sellers.models.SellersResponse;
import com.afarma.sellers.repositories.SellersResponseRepository;
import com.afarma.sellers.repositories.SellersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;


@Service
public class SellersService {

    private final SellersRepository sellersRepository;
    private final SellersResponseRepository sellersResponseRepository;

    public SellersService(SellersRepository sellersRepository,SellersResponseRepository sellersResponseRepository) {
        this.sellersRepository = sellersRepository;
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

    public Page<SellersResponse> searchForServicesAndProducts(Pageable pageable, SellersDTO sellersDTO){

        if (sellersDTO.getActive() == null || sellersDTO.getActive() == true )
            if(!sellersDTO.getDescription().isEmpty()){
                return sellersResponseRepository.findProductsAndSellers(pageable,sellersDTO.getDescription().toUpperCase(),sellersDTO.getActive(),
                        sellersDTO.getDateIni(),sellersDTO.getDateFin());
            }else{
                return sellersResponseRepository.findProductsAndSellersWithoutLike(pageable,sellersDTO.getActive(),
                        sellersDTO.getDateIni(),sellersDTO.getDateFin());
            }
        else {
            if(!sellersDTO.getDescription().isEmpty()){
                return sellersResponseRepository.findProductsAndSellersFalse(pageable,sellersDTO.getDescription().toUpperCase(),
                        sellersDTO.getDateIni(),sellersDTO.getDateFin());
            }else{
                return sellersResponseRepository.findProductsAndSellersWithoutLikeFalse(pageable,sellersDTO.getDateIni(),sellersDTO.getDateFin());
            }
        }
    }

    public void activateProducts(String type, Long productId){

        try {

        if(type.equals("PRO")){
            Boolean statusProduct = sellersRepository.findProducts(productId);
            if(statusProduct.equals(true)){
                sellersRepository.updateStatusFalseProduct(productId);
            }else {
                sellersRepository.updateStatusTrueProduct(productId);
            }
        }
        if (type.equals("SER")){
            Boolean statusService = sellersRepository.findServices(productId);
            if(statusService.equals(true)){
                sellersRepository.updateStatusFalseService(productId);
            }else {
                sellersRepository.updateStatusTrueService(productId);
            }
        }

        }catch (Exception exception){
            LoggerConfig.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            throw new InternalServerErrorException("Erro inesperado no momento do update", exception);
        }
        ResponseEntity.status(200);
    }
}
