package com.digiprizma;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.digiprizma.dao.CategroyRepository;
import com.digiprizma.dao.ProductRepository;
import com.digiprizma.entities.Category;
import com.digiprizma.entities.Product;

@SpringBootApplication
public class CatalogueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogueServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CategroyRepository categroyRepository, ProductRepository productRepository) {
		return args->{
			Stream.of("c1 Ordianateur", "c2 Imprimante").forEach(c->{
				categroyRepository.save(new Category(c.split(" ")[0], c.split(" ")[1], new ArrayList<>()));
			});
			
			categroyRepository.findAll().forEach(System.out::println);
			
			productRepository.deleteAll();
			Category cat = categroyRepository.findById("c1").get();
			Stream.of("p1","p2","p3","p4").forEach(name->{
				Product p = productRepository.save(new Product(null, name, 100,cat));
				cat.getProducts().add(p);
				categroyRepository.save(cat);
			});
			
			Category cat2 = categroyRepository.findById("c2").get();
			Stream.of("p10","p20","p30","p40").forEach(name->{
				Product p2 = productRepository.save(new Product(null, name, 2566,cat2));
				cat2.getProducts().add(p2);
				categroyRepository.save(cat2);
			});
			
			productRepository.findAll().forEach(System.out::println);
		};
	}
}
