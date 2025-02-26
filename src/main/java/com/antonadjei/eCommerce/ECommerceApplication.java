package com.antonadjei.eCommerce;

import com.antonadjei.eCommerce.enums.UserRoles;
import com.antonadjei.eCommerce.models.Role;
import com.antonadjei.eCommerce.models.User;
import com.antonadjei.eCommerce.repositories.RepositoryRole;
import com.antonadjei.eCommerce.repositories.RepositoryUser;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.RepositoryType;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
@Transactional
public class ECommerceApplication implements CommandLineRunner {

	private final RepositoryRole repositoryRole;
	private final PasswordEncoder passwordEncoder;
	private final RepositoryUser userRepository;
	private final EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create user roles
		Role clientRole = new Role();
		clientRole.setName(UserRoles.CLIENT.name());
		repositoryRole.save(clientRole);

		Role adminRole = new Role();
		adminRole.setName(UserRoles.ADMIN.name());
		repositoryRole.save(adminRole);

		Role superAdminRole = new Role();
		superAdminRole.setName(UserRoles.SUPER_ADMIN.name());
		repositoryRole.save(superAdminRole);

		// create Admin and Super-admin Users
		User admin = User.builder()
				.firstName("James")
				.lastName("Noells")
				.username("jamesnoells")
				.password(passwordEncoder.encode("adminpassword"))
				.email("jamesnoells@gmail.com")
				.address("1 Rue Luxembourg, 5900 Lille")
				.role(entityManager.merge(adminRole))
				.build();
		userRepository.save(admin);

		User superAdmin = User.builder()
				.firstName("Kira")
				.lastName("Brooks")
				.username("kirabrooks")
				.password(passwordEncoder.encode("superadminpassword"))
				.email("kirabrooks@gmail.com")
				.address("49 Rue Edouard, 59200 Tourcoing")
				.role(entityManager.merge(superAdminRole))
				.build();
		userRepository.save(superAdmin);
	}
}
