package com.codecool.nerdSanctuary.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JsonDateSerializer extends JsonSerializer<Calendar> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
    @Override
    public void serialize(Calendar c, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String formatedDate = dateFormat.format(c.getTime());
        jsonGenerator.writeString(formatedDate);
    }
}
