package co.edu.umanizales.manage_store.controller.dto;

import co.edu.umanizales.manage_store.controller.dto.DTO.ReponseDto;
import co.edu.umanizales.manage_store.controller.dto.DTO.SaleDto;
import co.edu.umanizales.manage_store.model.Sale;
import co.edu.umanizales.manage_store.model.Seller;
import co.edu.umanizales.manage_store.model.Store;
import co.edu.umanizales.manage_store.service.SaleService;
import co.edu.umanizales.manage_store.service.SellerService;
import co.edu.umanizales.manage_store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "sale")
public class SaleController {

@Autowired
    private SaleService saleService;
@Autowired
    private SellerService sellerService;
@Autowired
    private StoreService storeService;
@GetMapping
    public ResponseEntity<ReponseDto> getSales(){
    return new ResponseEntity<>(
            new ReponseDto(200, saleService.getSales(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/total")
    public ResponseEntity<ReponseDto> getTotalSales(){
        return new ResponseEntity<>(new ReponseDto(200,
                saleService.getTotalSales(), null),HttpStatus.OK);
    }

    @GetMapping(path = "/total/{code}")
    public ResponseEntity<ReponseDto> getTotalSalesBySeller(@PathVariable String code) {
        if (saleService.getTotalSalesBySeller(code) != 0) {
            return new ResponseEntity<>(new ReponseDto(200,
                    "El total de ventas del vendedor " + code + "es:" + saleService.getTotalSalesBySeller(code), null),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReponseDto(409,
                    "No hay ventas registradas", null),
                    HttpStatus.BAD_REQUEST);

        }
    }
    @PostMapping
    public ResponseEntity<ReponseDto> createSale(@RequestBody SaleDto saleDTO){
        Seller findSeller = sellerService.getSellerById(saleDTO.getSellerId());
        if( findSeller == null){
            return new ResponseEntity<>(new ReponseDto(409,
                    "El vendedor ingresado no existe",null),
                    HttpStatus.BAD_REQUEST);
        }
        Store findStore = storeService.getStoreById(saleDTO.getStoreId());
        if( findStore == null){
            return new ResponseEntity<>(new ReponseDto(409,
                    "La tienda ingresada no existe",null),
                    HttpStatus.BAD_REQUEST);
        }
        saleService.addSale(new Sale(findStore,findSeller,
                saleDTO.getQuantity()));
        return new ResponseEntity<>(new ReponseDto(200,
                "Venta adicionada",null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/averagesalesbystore")
    public ResponseEntity<ReponseDto> getAverageSalesByStore(){

        return new ResponseEntity<>(new ReponseDto(200,
                saleService.getTotalSales()/(float)storeService.getStores().size(),
                null),HttpStatus.OK);
    }
    @GetMapping(path = "/beststore")
    public ResponseEntity<ReponseDto> getBestStore(){
        return new ResponseEntity<>(new ReponseDto(200,
                saleService.getBestStore(storeService.getStores()), null),HttpStatus.OK);
    }

    @GetMapping(path = "/bestseller")
    public ResponseEntity<ReponseDto> getBestSeller(){
        return new ResponseEntity<>(new ReponseDto(200,
                saleService.getBestSeller(sellerService.getSellers()), null),HttpStatus.OK);
    }
}
