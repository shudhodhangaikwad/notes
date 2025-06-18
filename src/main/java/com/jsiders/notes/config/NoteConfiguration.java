package com.jsiders.notes.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@OpenAPIDefinition(
        info = @Info(title = "Secure Notes",description = "Note application with security",version = "V1")
)
@Configuration
@EnableCaching
public class NoteConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public CacheManager cacheManager() {
        // Create the Caffeine builder with a removal listener
        Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .removalListener((Object key, Object value, com.github.benmanes.caffeine.cache.RemovalCause cause) -> {
                    System.out.printf("Entry evicted: key=%s, cause=%s%n", key, cause);

                });

        // Create a Spring Cache using that Caffeine builder
        CaffeineCache userCache = new CaffeineCache("users", caffeineBuilder.build());

        // Build CacheManager
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(List.of(userCache));
        return manager;
    }


}
