package warehouse.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import warehouse.model.ProductData;
import warehouse.model.Warehouse;

public interface WarehouseRepository extends MongoRepository<Warehouse, String> {
    /**
    public ProductData findByProductID(String productID);
    public List<ProductData> findByWarehouseID(String warehouseID);
    */


    Warehouse findByWarehouseID(String warehouseID);
 }
