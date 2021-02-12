package xyz.stasiak.herokumongodbtestapp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bson.types.Decimal128;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();

        var module = new SimpleModule();
        module.addSerializer(Decimal128.class, new Decimal128Serializer());
        module.addDeserializer(Decimal128.class, new Decimal128Deserializer());
        mapper.registerModule(module);

        return mapper;
    }

    private static class Decimal128Serializer extends JsonSerializer<Decimal128> {

        @Override
        public void serialize(Decimal128 v, JsonGenerator g, SerializerProvider p) throws IOException {
            g.writeNumber(v.bigDecimalValue());
        }
    }

    private static class Decimal128Deserializer extends JsonDeserializer<Decimal128> {

        @Override
        public Decimal128 deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                 throws IOException, JsonProcessingException {
            return new Decimal128(jsonParser.getDecimalValue());
        }
    }

}
