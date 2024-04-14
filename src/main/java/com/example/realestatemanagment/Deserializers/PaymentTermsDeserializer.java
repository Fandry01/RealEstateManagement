package com.example.realestatemanagment.Deserializers;

import com.example.realestatemanagment.Enums.HouseTypes;
import com.example.realestatemanagment.Enums.PaymentTerms;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class PaymentTermsDeserializer extends JsonDeserializer<PaymentTerms> {
    @Override
    public PaymentTerms deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (value.isEmpty()) {
            return PaymentTerms.DEFAULT; // Default for empty input
        }
        return PaymentTerms.valueOf(value);
    }

}
