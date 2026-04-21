package warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import warehouse.model.ProductData;
import warehouse.model.Warehouse;
import warehouse.repository.WarehouseRepository;

@RestController
@RequestMapping("/api")
public class WarehouseController {

    @Autowired
    private WarehouseRepository repository;

    // ---------------------------
    // WAREHOUSE
    // ---------------------------

    // POST /warehouse
    @PostMapping("/warehouse")
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
        return repository.save(warehouse);
    }

    // GET /warehouse
    @GetMapping("/warehouse")
    public List<Warehouse> getAllWarehouses() {
        return repository.findAll();
    }

    // GET /warehouse/{id}
    @GetMapping("/warehouse/{id}")
    public Warehouse getWarehouseByID(@PathVariable String id) {
        return repository.findByWarehouseID(id);
    }

    // DELETE /warehouse/{id}
    @DeleteMapping("/warehouse/{id}")
    public String deleteWarehouse(@PathVariable String id) {
        Warehouse warehouse = repository.findByWarehouseID(id);

        if (warehouse != null) {
            repository.delete(warehouse);
            return "Warehouse deleted: " + id;
        } else {
            return "Warehouse not found: " + id;
        }
    }

    // ---------------------------
    // PRODUCT
    // ---------------------------

    // POST /product/{warehouseID}
    @PostMapping("/product/{warehouseID}")
    public Warehouse addProduct(@PathVariable String warehouseID, @RequestBody ProductData product) {

        Warehouse warehouse = repository.findByWarehouseID(warehouseID);

        if (warehouse != null) {
            warehouse.getProductData().add(product);
            return repository.save(warehouse);
        }

        return null;
    }

    // GET /product (alle Produkte aus allen Warehouses)
    @GetMapping("/product")
    public List<Warehouse> getAllProducts() {
        return repository.findAll();
    }

    // GET /product/{productID}
    @GetMapping("/product/{productID}")
    public ProductData getProductByID(@PathVariable String productID) {

        List<Warehouse> warehouses = repository.findAll();

        for (Warehouse w : warehouses) {
            for (ProductData p : w.getProductData()) {
                if (p.getProductID().equals(productID)) {
                    return p;
                }
            }
        }

        return null;
    }

    // DELETE /product/{productID}
    @DeleteMapping("/product/{productID}")
    public String deleteProduct(@PathVariable String productID) {

        List<Warehouse> warehouses = repository.findAll();

        for (Warehouse w : warehouses) {
            List<ProductData> products = w.getProductData();

            for (ProductData p : products) {
                if (p.getProductID().equals(productID)) {
                    products.remove(p);
                    repository.save(w);
                    return "Product deleted: " + productID;
                }
            }
        }

        return "Product not found: " + productID;
    }
}