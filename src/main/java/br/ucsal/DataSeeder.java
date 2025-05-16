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
        createDefaultSkillsIfNotExists();
        createDefaultTrailsIfNotExists();
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

    private void createDefaultTrailsIfNotExists() {
        if (trailRepository.findAll().isEmpty()) {

            Skill skill = new Skill("Java I", "Beginner level of proficiency");
            skillRepository.save(skill);

            Video video1 = new Video("Learn Java in 14 Minutes (seriously)",
                    "https://www.youtube.com/watch?v=RRubcjpTkks");
            Video video2 = new Video("Java Programming for Beginners – Full Course",
                    "https://www.youtube.com/watch?v=A74TOX803D0");
            Video video3 = new Video("Curso de Java #01 - História do Java - Gustavo Guanabara",
                    "https://www.youtube.com/watch?v=sTX0UEplF54&list=PLHz_AreHm4dkI2ZdjTwZA4mPMxWTfNSpR");
            Video video4 = new Video("Curso de PROGRAMAÇÃO JAVA para INICIANTES | Tudo que você precisa para aprender Java",
                    "https://www.youtube.com/watch?v=nODe5lFcGpg");

            Trail trail = new Trail("Trilha Java Beginner Level", skill);

            trail.setVideos(List.of(video1, video2, video3, video4));
            video1.setTrail(trail);
            video2.setTrail(trail);
            trailRepository.save(trail);

            Skill skill2 = new Skill("Java II", "Mid level of proficiency");
            skillRepository.save(skill2);

            Video video5 = new Video("Intermediate Java Tutorial - 1 - Common String Methods",
                    "https://www.youtube.com/watch?v=vW53w7me4AE&list=PL27BCE863B6A864E3");
            Video video6 = new Video("Intermediate Java Tutorial - 2 - Some More String Methods",
                    "https://www.youtube.com/watch?v=Qi09pWsc7nA&list=PL27BCE863B6A864E3&index=2");
            Video video7 = new Video("#2 JAVA STATIC - LOGICA DE PROGRAMAÇÃO INTERMEDIÁRIO | #JAVA #POO",
                    "https://www.youtube.com/watch?v=aCo7vwmMYXk&list=PLRFzgeDvajp0pEe2MT222FPv0ENmmK6bR");
            Video video8 = new Video("Java Intermediário 2 - Cadastro de pesquisa com Menu",
                    "https://www.youtube.com/watch?v=Z1-R0l3xzv0&list=PLbi7I1f_PRBkIY1R740H1CkSH1nN28QGn");
            Trail trail2 = new Trail("Trilha Java Mid Level", skill2);

            trail2.setVideos(List.of(video5, video6, video7, video8));
            video3.setTrail(trail2);
            video4.setTrail(trail2);
            trailRepository.save(trail2);

            System.out.println("Default trails created.");
        } else {
            System.out.println("Default trails already exists.");
        }
    }

    private void createDefaultSkillsIfNotExists() {
        if (skillRepository.findAll().isEmpty()) {
            // Product Management Skills
            skillRepository.save(new Skill("Product Management I",
                    "Beginner level of proficiency in product strategy and roadmap planning"));
            skillRepository.save(new Skill("Product Management II",
                    "Intermediate proficiency in managing product life cycles and cross-functional teams"));
            skillRepository.save(new Skill("Agile Product Owner",
                    "Skilled in writing user stories and managing backlogs in agile environments"));

            // User Experience / Design Skills
            skillRepository.save(new Skill("User Research",
                    "Beginner level understanding of user research methods and usability testing"));
            skillRepository.save(
                    new Skill("UX Design", "Ability to design intuitive interfaces and conduct usability studies"));
            skillRepository.save(new Skill("UI Design",
                    "Proficiency in designing visual interfaces using tools like Figma or Sketch"));

            // Software Engineering Skills
            skillRepository.save(new Skill("Spring Boot", "Experience in building REST APIs using Spring Boot"));
            skillRepository
                    .save(new Skill("Frontend Development", "Skills in HTML, CSS, and JavaScript for web interfaces"));
            skillRepository.save(new Skill("React", "Proficiency in building responsive UI components using React"));
            skillRepository.save(new Skill("Python I", "Beginner level of proficiency"));
            skillRepository.save(new Skill("Python II", "Mid level of proficiency"));
            skillRepository.save(new Skill("Python III", "Expert level of proficiency"));

            // DevOps & Infrastructure Skills
            skillRepository.save(new Skill("Docker", "Beginner understanding of containerization using Docker"));
            skillRepository
                    .save(new Skill("Kubernetes", "Familiar with deploying and scaling applications using Kubernetes"));
            skillRepository.save(new Skill("CI/CD", "Experience with continuous integration and deployment pipelines"));

            // Data & AI/ML Skills
            skillRepository.save(new Skill("SQL", "Ability to write queries and analyze data using SQL"));
            skillRepository.save(
                    new Skill("Data Analysis", "Beginner level in exploring and analyzing data with Python or Excel"));
            skillRepository.save(new Skill("Machine Learning",
                    "Understanding of ML concepts and use of libraries like scikit-learn"));
            skillRepository
                    .save(new Skill("Data Engineering", "Familiar with ETL pipelines and data transformation tools"));

            // QA / Testing Skills
            skillRepository.save(new Skill("Manual Testing", "Skilled in writing and executing test cases manually"));
            skillRepository.save(
                    new Skill("Automated Testing", "Experience using Selenium or similar tools for test automation"));
            skillRepository
                    .save(new Skill("Performance Testing", "Knowledge of load testing and performance benchmarking"));

            System.out.println("Default skills created.");
        } else {
            System.out.println("Default skills already exists.");
        }
    }

}