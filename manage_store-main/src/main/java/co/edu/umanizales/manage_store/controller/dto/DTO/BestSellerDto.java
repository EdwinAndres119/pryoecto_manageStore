package co.edu.umanizales.manage_store.controller.dto.DTO;

import co.edu.umanizales.manage_store.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BestSellerDto {

    private Seller seller;
    private int quantity;
}
