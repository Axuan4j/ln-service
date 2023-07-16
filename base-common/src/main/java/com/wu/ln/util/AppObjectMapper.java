package com.wu.ln.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class AppObjectMapper extends ObjectMapper {

    private final Logger logger = LoggerFactory.getLogger(AppObjectMapper.class);

    public AppObjectMapper() {
        super();
        this.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public String writeValueAsString(Object value) {
        try {
            return super.writeValueAsString(value);
        } catch (Exception e) {
            logger.error("writeValueAsString exception: {}", e.getMessage());
        }
        return null;
    }
}
