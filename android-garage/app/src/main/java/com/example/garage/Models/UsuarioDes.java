package com.example.garage.Models;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UsuarioDes implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement usuarios = json.getAsJsonObject();
        if (json.getAsJsonObject() != null){
            usuarios = json.getAsJsonObject();
        }
        return (new Gson().fromJson(usuarios,Usuario.class));
    }
}
