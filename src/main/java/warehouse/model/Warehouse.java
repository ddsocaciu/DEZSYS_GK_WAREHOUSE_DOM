package warehouse.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "warehouse")
public class Warehouse {

    @Id
    private String id;

    private String warehouseID;
    private String warehouseName;
    private String warehouseCity;

    private String timestamp;
    private int warehousePostalCode;
    private String warehouseCountry;

    private List<ProductData> productData;

    // Konstruktor
    public Warehouse() {}

    public Warehouse(String warehouseID, String warehouseName, String warehouseCity,
                     String timestamp, int warehousePostalCode, String warehouseCountry,
                     List<ProductData> productData) {

        this.warehouseID = warehouseID;
        this.warehouseName = warehouseName;
        this.warehouseCity = warehouseCity;
        this.timestamp = timestamp;
        this.warehousePostalCode = warehousePostalCode;
        this.warehouseCountry = warehouseCountry;
        this.productData = productData;
    }

    // Getter & Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseCity() {
        return warehouseCity;
    }

    public void setWarehouseCity(String warehouseCity) {
        this.warehouseCity = warehouseCity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getWarehousePostalCode() {
        return warehousePostalCode;
    }

    public void setWarehousePostalCode(int warehousePostalCode) {
        this.warehousePostalCode = warehousePostalCode;
    }

    public String getWarehouseCountry() {
        return warehouseCountry;
    }

    public void setWarehouseCountry(String warehouseCountry) {
        this.warehouseCountry = warehouseCountry;
    }

    public List<ProductData> getProductData() {
        return productData;
    }

    public void setProductData(List<ProductData> productData) {
        this.productData = productData;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "warehouseID='" + warehouseID + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseCity='" + warehouseCity + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", warehousePostalCode=" + warehousePostalCode +
                ", warehouseCountry='" + warehouseCountry + '\'' +
                ", productData=" + productData +
                '}';
    }
}