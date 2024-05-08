package dogacege.ECommerce.controller;

import dogacege.ECommerce.entity.Seller;
import dogacege.ECommerce.service.SellerService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/all")
    public List<Seller> getAllSellers(){
        return sellerService.getAllSeller();
    }
    @GetMapping("{sellerId}")
    public Seller getSellerById(@PathVariable Long sellerId){
        return sellerService.getSellerById(sellerId);
    }
    @DeleteMapping("{sellerId}")
    public void deleteSeller(@PathVariable Long sellerId){
        sellerService.deleteSeller(sellerId);
    }
    @PostMapping("/add")
    public Seller createSeller(@RequestBody Seller seller){
        return sellerService.createSeller(seller);
    }

}
