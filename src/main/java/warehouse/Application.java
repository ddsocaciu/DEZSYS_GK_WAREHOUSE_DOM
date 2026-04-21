package warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import warehouse.model.ProductData;
import warehouse.model.Warehouse;
import warehouse.repository.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.List.*;
import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8080/api/warehouse/1");
		restTemplate.delete("http://localhost:8080/api/warehouse/2");
		restTemplate.delete("http://localhost:8080/api/warehouse/3");

		// Warehouse 1
		Warehouse w1 = new Warehouse(
				"10",
				"Linz Bahnhof",
				"Linz",
				"2022-01-02 01:00:00",
				4010,
				"Austria",
				Arrays.asList(
						new ProductData("10","00-443175","Bio Orangensaft Sonne","Getraenk",2500),
						new ProductData("10","00-871895","Bio Apfelsaft Gold","Getraenk",3420),
						new ProductData("10","01-926885","Ariel Waschmittel Color","Waschmittel",478),
						new ProductData("10","04-111111","Coca Cola 1L","Getraenk",1500),
						new ProductData("10","05-222222","Persil Waschmittel","Waschmittel",900)
				)
		);

		// Warehouse 2
		Warehouse w2 = new Warehouse(
				"20",
				"Wien Lager",
				"Wien",
				"2022-01-03 10:00:00",
				1010,
				"Austria",
				Arrays.asList(
						new ProductData("20","02-234811","Mampfi Katzenfutter","Tierfutter",1324),
						new ProductData("20","03-893173","Staubsaugerbeutel","Reinigung",7390),
						new ProductData("20","06-333333","Felix Katzenfutter","Tierfutter",2000),
						new ProductData("20","07-444444","Glasreiniger","Reinigung",800),
						new ProductData("20","08-555555","Fanta 1L","Getraenk",1200),
						new ProductData("20","04-111111","Coca Cola 1L","Getraenk",1000),
						new ProductData("20","05-222222","Persil Waschmittel","Waschmittel",700)
				)
		);

		restTemplate.postForObject(
				"http://localhost:8080/api/warehouse",
				w1,
				Warehouse.class
		);

		restTemplate.postForObject(
				"http://localhost:8080/api/warehouse",
				w2,
				Warehouse.class
		);

		System.out.println("=== Warehouses gespeichert ===");

		String[] categories = {
				"Getraenk","Waschmittel","Tierfutter",
				"Reinigung","Snacks","Hygiene"
		};

		for (int w = 1; w <= 5; w++) {

			List<ProductData> products = new ArrayList<>();

			for (int i = 1; i <= 60; i++) {
				products.add(new ProductData(
						String.valueOf(w),
						"P" + w + i,
						"Produkt " + i,
						categories[i % 6],
						(int)(Math.random() * 1000)
				));
			}

			Warehouse warehouse = new Warehouse(
					String.valueOf(w),
					"Warehouse " + w,
					"City " + w,
					"2022",
					1000 + w,
					"Austria",
					products
			);

			restTemplate.postForObject(
					"http://localhost:8080/api/warehouse",
					warehouse,
					Warehouse.class
			);
		}
	}

}
