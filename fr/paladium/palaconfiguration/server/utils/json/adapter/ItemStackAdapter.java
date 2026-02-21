package fr.paladium.palaconfiguration.server.utils.json.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaconfiguration.ConfigurationLogger;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.NonNull;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStackAdapter extends TypeAdapter<ItemStack> {
  public void write(JsonWriter jsonWriter, ItemStack stack) throws IOException {
    try {
      if (stack == null) {
        jsonWriter.nullValue();
        return;
      } 
      byte[] raw = encode(stack);
      if (raw == null) {
        jsonWriter.nullValue();
        return;
      } 
      jsonWriter.value(new String(raw));
    } catch (Exception e) {
      ConfigurationLogger.error("Error while writing ItemStack: " + stack);
      e.printStackTrace();
      jsonWriter.value(new String(encode(new ItemStack(Blocks.field_150348_b)), StandardCharsets.UTF_8));
    } 
  }
  
  public ItemStack read(JsonReader jsonReader) throws IOException {
    try {
      if (jsonReader.peek() == JsonToken.NULL) {
        jsonReader.nextNull();
        return new ItemStack(Blocks.field_150348_b);
      } 
      String raw = jsonReader.nextString();
      if (raw == null)
        return new ItemStack(Blocks.field_150348_b); 
      return decode(raw);
    } catch (Exception e) {
      if (jsonReader.peek() == JsonToken.NULL) {
        jsonReader.nextNull();
        return new ItemStack(Blocks.field_150348_b);
      } 
      String raw = jsonReader.nextString();
      ConfigurationLogger.error("Error while reading ItemStack: " + raw);
      return new ItemStack(Blocks.field_150348_b);
    } 
  }
  
  private ItemStack decode(@NonNull String item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    try {
      ItemStack stack = ItemStackSerializer.read(new String(Base64.getDecoder().decode(item), StandardCharsets.UTF_8), null);
      if (stack != null)
        return stack; 
    } catch (Exception exception) {}
    String[] elements = item.split(" ");
    if (elements.length < 1)
      return null; 
    try {
      String[] split = elements[0].split(":");
      int id = Integer.parseInt(split[0]);
      int damage = (split.length > 1) ? Integer.parseInt(split[1]) : 0;
      int stackSize = (elements.length > 2) ? Integer.parseInt(elements[2]) : 1;
      Item itemById = Item.func_150899_d(id);
      if (itemById == null)
        return null; 
      return new ItemStack(itemById, stackSize, damage);
    } catch (Exception exception) {
      String name = elements[0].contains(":") ? elements[0] : ("minecraft:" + elements[0]);
      int damage = (elements.length > 1) ? Integer.parseInt(elements[1]) : 0;
      int stackSize = (elements.length > 2) ? Integer.parseInt(elements[2]) : 1;
      ItemStack stack = GameRegistry.findItemStack(name.split(":")[0], name.split(":")[1], stackSize);
      if (stack != null) {
        stack.func_77964_b(damage);
        stack.field_77994_a = stackSize;
      } 
      return stack;
    } 
  }
  
  private byte[] encode(ItemStack stack) {
    return Base64.getEncoder().encode(ItemStackSerializer.write(stack).getBytes());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\serve\\utils\json\adapter\ItemStackAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */