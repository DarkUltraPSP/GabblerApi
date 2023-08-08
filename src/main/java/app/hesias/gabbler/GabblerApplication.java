package app.hesias.gabbler;

import app.hesias.gabbler.Model.InteractionType;
import app.hesias.gabbler.Model.UserRelationshipType;
import app.hesias.gabbler.Repository.InteractionTypeRepo;
import app.hesias.gabbler.Service.InteractionType.InteractionTypeService;
import app.hesias.gabbler.Service.InteractionType.InteractionTypeServiceImpl;
import app.hesias.gabbler.Service.UserRelationshipType.UserRelationshipTypeService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class GabblerApplication {
    InteractionTypeService interactionTypeService;
    UserRelationshipTypeService userRelationshipTypeService;

    public static void main(String[] args) {
        SpringApplication.run(GabblerApplication.class, args);
    }

    @Bean
    CommandLineRunner init (InteractionTypeRepo interactionTypeRepo) {
        return args -> {
            if (!existsInteractionType("like")) {
                InteractionType interactionType = new InteractionType();
                interactionType.setLibelle("like");
                interactionTypeService.createInteractionType(interactionType);
            }
            if (!existsInteractionType("dislike")) {
                InteractionType interactionType = new InteractionType();
                interactionType.setLibelle("dislike");
                interactionTypeService.createInteractionType(interactionType);
            }

            if (!existsUserRelationshipType("follow")) {
                UserRelationshipType userRelationshipType = new UserRelationshipType();
                userRelationshipType.setLibelle("follow");
                userRelationshipTypeService.createUserRelationshipType(userRelationshipType);
            }
            if (!existsUserRelationshipType("block")) {
                UserRelationshipType userRelationshipType = new UserRelationshipType();
                userRelationshipType.setLibelle("block");
                userRelationshipTypeService.createUserRelationshipType(userRelationshipType);
            }
        };
    }

    private boolean existsInteractionType(String libelle) {
        return interactionTypeService.getInteractionTypeByLibelle(libelle) != null;
    }

    private boolean existsUserRelationshipType(String libelle) {
        return userRelationshipTypeService.getUserRelationshipTypeByLibelle(libelle) != null;
    }
}
