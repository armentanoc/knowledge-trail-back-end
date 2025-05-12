package br.ucsal;

import br.ucsal.domain.users.Employee;
import br.ucsal.domain.users.User;
import br.ucsal.domain.videos.Video;
import br.ucsal.domain.enums.Role;
import br.ucsal.domain.skills.Skill;
import br.ucsal.domain.trails.Trail;
import br.ucsal.infrastructure.IEmployeeRepository;
import br.ucsal.infrastructure.ISkillRepository;
import br.ucsal.infrastructure.ITrailRepository;
import br.ucsal.infrastructure.IUserRepository;
import br.ucsal.service.interfaces.IEncryptionService;

import java.util.List;

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

     @Autowired
    private ITrailRepository trailRepository;

    @Autowired
    private ISkillRepository skillRepository;

    @Override
    public void run(String... args) throws Exception {
        createDefaultAdminIfNotExists();
        createDefaultEmployeeIfNotExists();
        createDefaultTrailIfNotExists();
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

    private void createDefaultTrailIfNotExists() {
        if (skillRepository.findAll().isEmpty()) {
            
            Skill skill = new Skill("Java I", "Beginner level of proficiency");
            skillRepository.save(skill);

            Video video1 = new Video("Learn Java in 14 Minutes (seriously)", "https://www.youtube.com/watch?v=RRubcjpTkks");
            Video video2 = new Video("Java Programming for Beginners – Full Course", "https://www.youtube.com/watch?v=A74TOX803D0");
            Trail trail = new Trail("Trilha Java Beginner Level", skill);

            trail.setVideos(List.of(video1, video2));
            video1.setTrail(trail);
            video2.setTrail(trail);
            trailRepository.save(trail);

            Skill skill2 = new Skill("Java II", "Mid level of proficiency");
            skillRepository.save(skill2);

            Video video3 = new Video("Intermediate Java Tutorial - 1 - Common String Methods", "https://www.youtube.com/watch?v=vW53w7me4AE&list=PL27BCE863B6A864E3");
            Video video4 = new Video("Intermediate Java Tutorial - 2 - Some More String Methods", "https://www.youtube.com/watch?v=Qi09pWsc7nA&list=PL27BCE863B6A864E3&index=2");
            Trail trail2 = new Trail("Trilha Java Mid Level", skill2);

            trail2.setVideos(List.of(video3, video4));
            video3.setTrail(trail2);
            video4.setTrail(trail2);
            trailRepository.save(trail2);

            System.out.println("Default trails created.");
        } else {
            System.out.println("Default trail already exists.");
        }
    }
    
}