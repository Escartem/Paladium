package fr.paladium.palamod.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.IOException;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;

public class ItemStackTypeAdapter extends TypeAdapter<ItemStack> {
  public void write(JsonWriter out, ItemStack value) throws IOException {
    out.beginObject();
    out.name("name");
    out.value((value.func_77973_b()).delegate.name());
    out.name("size");
    out.value(value.field_77994_a);
    out.name("meta");
    out.value(value.func_77960_j());
    if (value.func_77978_p() != null) {
      out.name("tag");
      out.value(value.func_77978_p().toString());
    } 
    out.endObject();
  }
  
  public ItemStack read(JsonReader in) throws IOException {
    String name = null;
    int size = 0;
    int meta = 0;
    String tag = null;
    String field = null;
    try {
      in.beginObject();
      while (in.hasNext()) {
        JsonToken token = in.peek();
        if (token.equals(JsonToken.NAME))
          field = in.nextName(); 
        if ("name".equals(field)) {
          token = in.peek();
          name = in.nextString();
        } 
        if ("size".equals(field)) {
          token = in.peek();
          size = in.nextInt();
        } 
        if ("meta".equals(field)) {
          token = in.peek();
          meta = in.nextInt();
        } 
        if ("tag".equals(field)) {
          token = in.peek();
          tag = in.nextString();
        } 
      } 
      in.endObject();
      ItemStack stack = GameRegistry.makeItemStack(name, meta, size, null);
      stack.field_77994_a = size;
      try {
        if (tag != null && !tag.equals("null") && !tag.isEmpty())
          stack.func_77982_d((NBTTagCompound)JsonToNBT.func_150315_a(tag)); 
      } catch (Exception e) {
        System.err.println("Could not read NBT data from " + in.toString() + ":" + e.toString());
      } 
      return stack;
    } catch (Exception e) {
      System.err.println("Unable to make ItemStack for " + in.toString() + ":" + e.toString());
      return new ItemStack(Blocks.field_150348_b);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\ItemStackTypeAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */