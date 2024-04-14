package com.example.realestatemanagment.Deserializers;

import com.example.realestatemanagment.Enums.HouseTypes;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class HouseTypesDeserializer extends JsonDeserializer<HouseTypes> {
    @Override
    public HouseTypes deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (value.isEmpty()) {
            return HouseTypes.DEFAULT; // Default for empty input
        }
        return HouseTypes.valueOf(value);
    }
}
