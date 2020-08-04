package ru.digital.league.x5.sign.bindings.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.serializer.DeserializationException;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import static org.springframework.kafka.support.serializer.ErrorHandlingDeserializer2.KEY_DESERIALIZER_EXCEPTION_HEADER;
import static org.springframework.kafka.support.serializer.ErrorHandlingDeserializer2.VALUE_DESERIALIZER_EXCEPTION_HEADER;

@Slf4j
public class DeserializeErrorHelper {

    public static <T> BiFunction<byte[], Headers, T> handleFunction(T badDtoInstance) {
        return (bytes, headers) -> {
            try {
                Header errorHeader = Optional.ofNullable(headers.lastHeader(VALUE_DESERIALIZER_EXCEPTION_HEADER)).orElse(headers.lastHeader(KEY_DESERIALIZER_EXCEPTION_HEADER));
                byte[] exceptionBytes = errorHeader.value();
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(Objects.requireNonNull(exceptionBytes)));
                DeserializationException dex = (DeserializationException) ois.readObject();
                log.error("Error deserialize message from kafka. {} {}", dex.getMessage(), new String(dex.getData()));
            } catch (Exception exception) {
                log.error("Error deserialize handle function", exception);
            }
            return badDtoInstance;
        };
    }
}
