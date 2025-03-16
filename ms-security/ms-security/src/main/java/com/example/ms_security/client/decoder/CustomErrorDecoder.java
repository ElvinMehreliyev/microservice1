package com.example.ms_security.client.decoder;

import com.example.ms_security.dto.ErrorResponse;
import com.example.ms_security.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.util.ArrayList;

import static com.example.ms_security.client.decoder.JsonNodeFieldName.ERRORS;
import static com.example.ms_security.client.decoder.JsonNodeFieldName.MESSAGE;
import static com.example.ms_security.enums.ErrorMessage.CLIENT_ERROR;
import static com.example.ms_security.util.MapperUtil.MAPPER_UTIL;

public class CustomErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {
        String errorMessage = CLIENT_ERROR.getMessage();
        int statusCode = response.status();
        ArrayList<String> errors = new ArrayList<>();

        JsonNode jsonNode;

        try (var body = response.body().asInputStream()) {
            jsonNode = MAPPER_UTIL.map(body, JsonNode.class);
        } catch (Exception exception) {
            throw new CustomFeignException(ErrorResponse.builder()
                    .code(statusCode)
                    .message(errorMessage)
                    .errors(errors)
                    .build());
        }

        if (jsonNode.has(MESSAGE.getValue())){
            errorMessage = jsonNode.get(MESSAGE.getValue()).asText();
        }

        if(jsonNode.has(ERRORS.getValue()) && jsonNode.get(ERRORS.getValue()).isArray()){
            jsonNode.get(ERRORS.getValue()).forEach(error -> errors.add(error.asText()));
        }
        return new CustomFeignException(ErrorResponse.builder()
                .code(statusCode)
                .message(errorMessage)
                .errors(errors)
                .build());
    }
}
