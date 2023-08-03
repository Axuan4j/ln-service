package com.wu.ln.user.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.wu.ln.user.entity.UserServiceDetail;

import java.io.IOException;

public class AppUserDetailDeserializer extends JsonDeserializer<UserServiceDetail> {

    @Override
    public UserServiceDetail deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode jsonNode = mapper.readTree(p);
        String username = readJsonNode(jsonNode, "username").asText();
        String password = readJsonNode(jsonNode, "password").asText();
        String safeCode = readJsonNode(jsonNode, "safeCode").asText();
        Integer status = readJsonNode(jsonNode, "status").asInt(0);
        String phone = readJsonNode(jsonNode, "phone").asText();
        String email = readJsonNode(jsonNode, "email").asText();
        String roles = readJsonNode(jsonNode, "roles").asText();
        boolean locked = readJsonNode(jsonNode, "locked").asBoolean(false);
        UserServiceDetail appUserDetail = new UserServiceDetail();
        appUserDetail.setUsername(username);
        appUserDetail.setPassword(password);
        appUserDetail.setSafeCode(safeCode);
        appUserDetail.setStatus(status);
        appUserDetail.setPhone(phone);
        appUserDetail.setEmail(email);
        appUserDetail.setLocked(locked);
        appUserDetail.setRoles(roles);
        return appUserDetail;
    }


    private JsonNode readJsonNode(JsonNode jsonNode, String fieldName) {
        if (jsonNode.has(fieldName)) {
            return jsonNode.get(fieldName);
        }
        return MissingNode.getInstance();
    }


}
