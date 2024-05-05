package dogacege.ECommerce.controller;

import dogacege.ECommerce.entity.Product;
import dogacege.ECommerce.entity.User;
import dogacege.ECommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId){
        return productService.getProductById(productId);
 }

    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        try {
            Product addedProduct = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product eklenirken bir hata oluştu.");
        }
    }
    @PutMapping("/{productId}")
    public Product updateProduct(@RequestBody Product newProduct, @PathVariable Long productId){
        newProduct.setProductId(productId);
        return productService.updateProduct(newProduct);
    }
    @DeleteMapping("{productId}")
    public void deleteProductById(@PathVariable Long productId){
        productService.deleteProductById(productId);

    }

}
