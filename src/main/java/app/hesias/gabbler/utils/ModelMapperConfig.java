package app.hesias.gabbler.utils;

import app.hesias.gabbler.Model.Entity.Gab;
import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Model.Entity.User;
import app.hesias.gabbler.Model.Entity.UserRelationship;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.google.common.collect.Iterables.skip;

@Configuration
public class ModelMapperConfig {
     @Bean
     public ModelMapper modelMapper() {
         ModelMapper modelMapper = new ModelMapper();
         modelMapper.getConfiguration()
                 .setMatchingStrategy(MatchingStrategies.STRICT)
                 .setPropertyCondition(context -> context.getSource() != null);

         modelMapper.addMappings(new PropertyMap<User, User>() {
             @Override
             protected void configure() {
                 skip(destination.getIdUser());
                 skip(destination.getCreatedAt());
             }
         });
         modelMapper.addMappings(new PropertyMap<Gab, Gab>() {

             @Override
             protected void configure() {
                 skip(destination.getIdGab());
                 skip(destination.getCreatedAt());
             }
         });
         modelMapper.addMappings(new PropertyMap<Interaction, Interaction>() {
             @Override
             protected void configure() {
                 skip(destination.getIdInteraction());
             }
         });
         modelMapper.addMappings(new PropertyMap<UserRelationship, UserRelationship>() {
             @Override
             protected void configure() {
                 skip(destination.getIdRelationship());
                 skip(destination.getCreatedAt());
             }
         });
         
         return modelMapper;
     }
}
