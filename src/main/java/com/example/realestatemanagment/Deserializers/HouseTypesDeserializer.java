package com.example.realestatemanagment.Deserializers;

import com.example.realestatemanagment.Enums.HouseTypes;
import com.example.realestatemanagment.Exceptions.InvalidEnumException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class HouseTypesDeserializer extends JsonDeserializer<HouseTypes> {
    @Override
    public HouseTypes deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (value == null || value.isEmpty()) {
            throw new InvalidEnumException("invalid HouseType");
        }

        HouseTypes houseTypes;
        try {
            houseTypes = HouseTypes.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumException("Invalid HouseType");
        }
        return houseTypes;

    }
}
