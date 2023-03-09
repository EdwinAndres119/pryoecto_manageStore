package co.edu.umanizales.manage_store.controller.dto;

import co.edu.umanizales.manage_store.controller.dto.DTO.ReponseDto;
import co.edu.umanizales.manage_store.model.Store;
import co.edu.umanizales.manage_store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping
    public ResponseEntity<ReponseDto> getStores()
    {

        return new ResponseEntity<>(new ReponseDto(
                200,storeService.getStores(),null
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReponseDto> createStore(@RequestBody Store store) {
        Store findStore = storeService.getStoreById(store.getCode());
        if( findStore == null) {
            storeService.addStore(store);
            return new ResponseEntity<>(new ReponseDto(
                    200, "Tienda agregada", null
            ), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ReponseDto(
                    409, "Ya existe una tienda con ese código", null
            ), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<ReponseDto> getStoreById(@PathVariable String code)
    {
        Store findStore= storeService.getStoreById(code);
        if(findStore == null)
        {
            return new ResponseEntity<>(new ReponseDto(
                    404, "No existe una tienda con ese código", null
            ), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ReponseDto(
                    200, findStore, null
            ), HttpStatus.OK);
        }
    }

}
