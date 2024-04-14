package com.example.realestatemanagment.Configs;

import com.example.realestatemanagment.Deserializers.HouseTypesDeserializer;
import com.example.realestatemanagment.Enums.HouseTypes;
import com.example.realestatemanagment.Enums.MaintenanceTypes;
import com.example.realestatemanagment.Enums.PaymentTerms;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.deserializerByType(HouseTypes.class, new HouseTypesDeserializer());
        builder.deserializerByType(MaintenanceTypes.class, new HouseTypesDeserializer());
        builder.deserializerByType(PaymentTerms.class, new HouseTypesDeserializer());
        return builder;
    }
}
