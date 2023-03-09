package co.edu.umanizales.manage_store.controller.dto.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleDto {
    private String sellerId;
    private String storeId;
    private int quantity;
}
