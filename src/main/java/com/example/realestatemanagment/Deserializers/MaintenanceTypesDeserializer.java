package com.example.realestatemanagment.Deserializers;

import com.example.realestatemanagment.Enums.HouseTypes;
import com.example.realestatemanagment.Enums.MaintenanceTypes;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class MaintenanceTypesDeserializer extends JsonDeserializer<MaintenanceTypes> {
    @Override
    public MaintenanceTypes deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (value.isEmpty()) {
            return MaintenanceTypes.DEFAULT; // Default for empty input
        }
        return MaintenanceTypes.valueOf(value);
    }

}
