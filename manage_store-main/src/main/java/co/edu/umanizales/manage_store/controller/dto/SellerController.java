package co.edu.umanizales.manage_store.controller.dto;

import co.edu.umanizales.manage_store.controller.dto.DTO.ReponseDto;
import co.edu.umanizales.manage_store.model.Seller;
import co.edu.umanizales.manage_store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping
    public ResponseEntity<ReponseDto> getSellers()
    {

        return new ResponseEntity<>(new ReponseDto(
                200,sellerService.getSellers(),null
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReponseDto> createSeller(@RequestBody Seller seller) {
        Seller findSeller = sellerService.getSellerById(seller.getCode());
        if( findSeller == null) {
            sellerService.addSeller(seller);
            return new ResponseEntity<>(new ReponseDto(
                    200, "Vendedor agregado", null
            ), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ReponseDto(
                    409, "Ya existe un vendedor con ese código", null
            ), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<ReponseDto> getSellerById(@PathVariable String code)
    {
        Seller findSeller= sellerService.getSellerById(code);
        if(findSeller == null)
        {
            return new ResponseEntity<>(new ReponseDto(
                    404, "No existe un vendedor con este codigo", null
            ), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ReponseDto(
                    200, findSeller, null
            ), HttpStatus.OK);
        }
    }
}
