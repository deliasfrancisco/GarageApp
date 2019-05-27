package com.example.garage.util;

import com.example.garage.Models.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UsuarioDeserialerizar implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement usuarios = json.getAsJsonObject();
        return (new Gson().fromJson(usuarios, Usuario.class));
    }
}