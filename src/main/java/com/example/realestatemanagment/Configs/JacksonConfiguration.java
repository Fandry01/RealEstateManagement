package com.example.realestatemanagment.Configs;

import com.example.realestatemanagment.Deserializers.HouseTypesDeserializer;
import com.example.realestatemanagment.Deserializers.MaintenanceTypesDeserializer;
import com.example.realestatemanagment.Deserializers.PaymentTermsDeserializer;
import com.example.realestatemanagment.Deserializers.PrioritiesDeserializer;
import com.example.realestatemanagment.Enums.HouseTypes;
import com.example.realestatemanagment.Enums.MaintenanceTypes;
import com.example.realestatemanagment.Enums.PaymentTerms;
import com.example.realestatemanagment.Enums.Priority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.deserializerByType(HouseTypes.class, new HouseTypesDeserializer());
        builder.deserializerByType(MaintenanceTypes.class, new MaintenanceTypesDeserializer());
        builder.deserializerByType(PaymentTerms.class, new PaymentTermsDeserializer());
        builder.deserializerByType(Priority.class,new PrioritiesDeserializer());
        return builder;
    }
}
