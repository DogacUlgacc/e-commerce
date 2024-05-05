package dogacege.ECommerce.service;

import dogacege.ECommerce.entity.Order;
import dogacege.ECommerce.entity.Product;
import dogacege.ECommerce.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

   private final ProductRepository productRepository ;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long productId) {
    productRepository.deleteById(productId);
    }

    public Product updateProduct(Product newProduct) {
        // product kimliğini al
        Long productId = newProduct.getProductId();

        // Veritabanından mevcut product al
        Optional<Product> existinProductOptional = productRepository.findById(productId);
        if (existinProductOptional.isPresent()) {
            // Mevcut product al
            Product existingProduct = existinProductOptional.get();

            // Yeni bilgilerle mevcut product güncelle
            existingProduct.setProductName(newProduct.getProductName());
            existingProduct.setDescription(newProduct.getDescription());
            existingProduct.setPrice(newProduct.getPrice());
            existingProduct.setQuantity(newProduct.getQuantity());
            // Güncellenmiş product veritabanına kaydet
            return productRepository.save(existingProduct);
        } else {
            // product bulunamadı, null dön
            return null;
        }
    }
}
