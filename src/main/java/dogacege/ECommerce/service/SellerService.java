package dogacege.ECommerce.service;

import dogacege.ECommerce.entity.Seller;
import dogacege.ECommerce.repository.SellerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getAllSeller() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long sellerId) {
        return sellerRepository.getReferenceById(sellerId);
    }

    public void deleteSeller(Long sellerId) {
         sellerRepository.deleteById(sellerId);
    }

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

}
