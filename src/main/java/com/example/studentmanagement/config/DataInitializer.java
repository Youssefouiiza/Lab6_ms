package com.example.studentmanagement.config;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner initDatabase(StudentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("Initialisation de la base de données avec des étudiants de test...");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                repository.save(new Student("LACHGAR", "Mohamed", sdf.parse("1985-09-01")));
                repository.save(new Student("BENJELLOUN", "Salma", sdf.parse("1985-11-20")));
                repository.save(new Student("EL AMRANI", "Yassine", sdf.parse("1990-05-12")));

                log.info("Données initiales insérées avec succès (3 étudiants créés).");
            }
        };
    }
}

