package com.example.realestatemanagment.Deserializers;

import com.example.realestatemanagment.Enums.PaymentTerms;
import com.example.realestatemanagment.Enums.Priority;
import com.example.realestatemanagment.Exceptions.InvalidEnumException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.jsonwebtoken.io.Deserializer;

import java.io.IOException;

public class PrioritiesDeserializer extends JsonDeserializer<Priority> {
    @Override
    public Priority deserialize(JsonParser p, DeserializationContext ctxt)throws IOException{
        String value = p.getValueAsString();
        if (value.isEmpty()) {
            throw new InvalidEnumException("Invalid Priority Type");
        }

        Priority priority;
        try {
            priority = Priority.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumException("Invalid Priority Type");
        }

        return priority;
    }

}
