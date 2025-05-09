package br.ucsal;

import br.ucsal.domain.users.Employee;
import br.ucsal.domain.users.User;
import br.ucsal.domain.enums.Role;
import br.ucsal.infrastructure.IEmployeeRepository;
import br.ucsal.infrastructure.IUserRepository;
import br.ucsal.service.interfaces.IEncryptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IEncryptionService encryptionService;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        createDefaultAdminIfNotExists();
        createDefaultEmployeeIfNotExists();
    }

    private void createDefaultAdminIfNotExists() {
        if (userRepository.findByusername("admin").isEmpty()) {

            User admin = new User(
                "Administrador", 
                "admin@admin.com", 
                "admin", 
                encryptionService.encode("admin"), 
                Role.ADMIN);

            userRepository.save(admin);
            System.out.println("Default admin created.");
        } else {
            System.out.println("Default admin already exists.");
        }
    }

    private void createDefaultEmployeeIfNotExists() {
        if (userRepository.findByusername("employee").isEmpty()) {

            User user = new User(
                "Employee", 
                "employee@employee.com", 
                "employee", 
                encryptionService.encode("employee"), 
                Role.EMPLOYEE);

            userRepository.save(user);
            System.out.println("Default user created for employee.");

            Employee employee = new Employee();
            employee.setUser(user); 
            
            employeeRepository.save(employee);
            System.out.println("Default employee created.");
        } else {
            System.out.println("Default employee already exists.");
        }
    }
}