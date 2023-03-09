package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.controller.dto.DTO.BestSellerDto;
import co.edu.umanizales.manage_store.controller.dto.DTO.BestStoreDto;
import co.edu.umanizales.manage_store.model.Sale;
import co.edu.umanizales.manage_store.model.Seller;
import co.edu.umanizales.manage_store.model.Store;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class SaleService {
    private List<Sale> sales;

    public SaleService() {

        this.sales = new ArrayList<>();

    }

    public void addSale(Sale sale) {
        this.sales.add(sale);
    }

    public int getTotalSales()
    {
        int sum = 0;
        for(Sale sale:sales){
            sum = sum + sale.getQuantity();
        }
        return sum;
    }

    public int getTotalSalesBySeller(String codeSeller)
    {
        int sum = 0;
        for(Sale sale:sales){
            if(sale.getSeller().getCode().equals(codeSeller)){
                sum = sum + sale.getQuantity();
            }
        }
        return sum;
    }

    public BestSellerDto getBestSeller(List<Seller> sellers)
    {
        //Referencia como mayor;
        BestSellerDto bestSellerDto = new BestSellerDto(new Seller(), 0);
        //Recorremos todas las ventas
        for(Seller seller:sellers)
        {
            int quant = getTotalSalesBySeller(seller.getCode());
            if(quant >= bestSellerDto.getQuantity()){
                bestSellerDto = new BestSellerDto(seller,quant);
            }
        }
        return bestSellerDto;
    }

    public BestStoreDto getBestStore(List<Store>stores)
    {
        //Referencia como mayor;
        BestStoreDto bestStoreDto = new BestStoreDto(new Store(), 0);
        //Recorremos todas las ventas
        for(Store store:stores) {
            int quant = getTotalSalesBySeller(store.getCode());
            if(quant >= bestStoreDto.getQuanity(){
                bestStoreDto = new BestStoreDto(store,quant);
            }
        }
        return bestStoreDto;
    }
}



