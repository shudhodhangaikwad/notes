package com.jsiders.notes.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition(
        info = @Info(title = "Secure Notes",description = "Note application with security",version = "V1")
)
@Configuration
public class NoteConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
