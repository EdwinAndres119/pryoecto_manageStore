package co.edu.umanizales.manage_store.controller.dto.DTO;

import co.edu.umanizales.manage_store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BestStoreDto {
    private Store store;
    private int quanity;

}
