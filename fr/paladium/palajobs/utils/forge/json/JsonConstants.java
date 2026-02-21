package fr.paladium.palajobs.utils.forge.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConstants {
  public static final Gson GSON = (new GsonBuilder())
    .setPrettyPrinting()
    .serializeNulls()
    .disableHtmlEscaping()
    .create();
  
  public static final String JSON_EXTENSION = ".json";
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\forge\json\JsonConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */