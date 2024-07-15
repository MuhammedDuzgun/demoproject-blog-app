package com.javaguides.blogapp;

import com.javaguides.blogapp.model.Role;
import com.javaguides.blogapp.repository.IRoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info =  @Info(
				title = "Blog App Demo Project",
				description = "Blog App Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Muhammed Duzgun",
						email = "muhammedduzgun00@gmail.com",
						url = "https://www.gundemgaming.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.gundemgaming.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Blog App Demo Projecyt Documentation",
				url = "https://github.com/MuhammedDuzgun/fukantin"
		)
)
public class DemoprojectBlogAppApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoprojectBlogAppApplication.class, args);
	}

	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
	}
}
