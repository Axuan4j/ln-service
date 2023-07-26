package com.wu.ln.authorization.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.wu.ln.authorization.entity.AppUserDetail;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.List;

public class AppUserDetailDeserializer extends JsonDeserializer<AppUserDetail> {

    private static final TypeReference<List<GrantedAuthority>> GRANTED_AUTHORITY_LIST = new TypeReference<>() {
    };

    @Override
    public AppUserDetail deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode jsonNode = mapper.readTree(p);
        String username = readJsonNode(jsonNode, "username").asText();
        String password = readJsonNode(jsonNode, "password").asText();
        String safeCode = readJsonNode(jsonNode, "safeCode").asText();
        Integer status = readJsonNode(jsonNode, "status").asInt();
        String phone = readJsonNode(jsonNode, "phone").asText();
        String email = readJsonNode(jsonNode, "email").asText();
        AppUserDetail appUserDetail = new AppUserDetail();
        appUserDetail.setUsername(username);
        appUserDetail.setPassword(password);
        appUserDetail.setSafeCode(safeCode);
        appUserDetail.setStatus(status);
        appUserDetail.setPhone(phone);
        appUserDetail.setEmail(email);
        return appUserDetail;
    }


    private JsonNode readJsonNode(JsonNode jsonNode, String fieldName) {
        if (jsonNode.has(fieldName)) {
            return jsonNode.get(fieldName);
        }
        return MissingNode.getInstance();
    }


}
