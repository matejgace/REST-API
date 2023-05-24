package mg.RESTAPI;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import mg.RESTAPI.entity.Role;
import mg.RESTAPI.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog App REST API's",
				description = "Spring Boot Blog App REST API's Documentation",
				version = "v1.0",
				contact = @Contact (
						name = "Matej",
						email = "matejgace@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog App REST API's Documentation",
				url = "https://github.com/matejgace/REST-API"
		)
)
public class RestApiApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public void run(String... args) throws Exception {

		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);

	}
}
