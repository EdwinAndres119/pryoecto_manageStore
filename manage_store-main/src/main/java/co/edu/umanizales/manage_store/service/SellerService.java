package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.model.Seller;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Getter
public class SellerService {
    private List<Seller> sellers;

    public SellerService() {
        sellers = new ArrayList<>();
        sellers.add(new Seller("123","juan"));
        sellers.add(new Seller("345","pepito"));
    }

    public void addSeller(Seller seller) {
        sellers.add(seller);
    }{

    }
    public Seller getSellerById(String code)
    {
        for(Seller seller:sellers)
        {
            if(seller.getCode().equalsIgnoreCase(code))
            {
                return seller;
            }
        }
        return null;
    }
}
