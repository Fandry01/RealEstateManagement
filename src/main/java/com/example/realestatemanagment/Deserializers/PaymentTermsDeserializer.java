package com.example.realestatemanagment.Deserializers;

import com.example.realestatemanagment.Enums.HouseTypes;
import com.example.realestatemanagment.Enums.MaintenanceTypes;
import com.example.realestatemanagment.Enums.PaymentTerms;
import com.example.realestatemanagment.Exceptions.InvalidEnumException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class PaymentTermsDeserializer extends JsonDeserializer<PaymentTerms> {
    @Override
    public PaymentTerms deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (value.isEmpty()) {
            throw new InvalidEnumException("Invalid Payment Terms Type");
        }

        PaymentTerms paymentTerms;
        try {
            paymentTerms = PaymentTerms.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumException("Invalid Payment Terms Type");
        }

        return paymentTerms;

    }

}
