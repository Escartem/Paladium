package fr.paladium.palamod.modules.enderchest.serial.nbt.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import fr.paladium.palamod.modules.enderchest.serial.nbt.ANBTSerializer;
import fr.paladium.palamod.modules.enderchest.serial.nbt.utils.NBTListUtil;
import fr.paladium.palamod.modules.enderchest.serial.nbt.utils.NBTSerializer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;

public class NBTListSerializer extends ANBTSerializer<NBTTagList> {
  public String identifier() {
    return null;
  }
  
  public NBTTagList unSerialize(JsonElement json) {
    JsonArray jsonArray = (JsonArray)json;
    NBTTagList nbtList = new NBTTagList();
    for (JsonElement element : jsonArray)
      nbtList.func_74742_a(NBTSerializer.toNbt(element)); 
    return nbtList;
  }
  
  public JsonElement serialize(NBTTagList nbt) {
    JsonArray jsonArray = new JsonArray();
    for (int itr = 0; itr < nbt.func_74745_c(); itr++) {
      if (nbt.func_150303_d() == 8) {
        jsonArray.add(NBTSerializer.toJson((NBTBase)new NBTTagString(nbt.func_150307_f(itr))));
      } else if (nbt.func_150303_d() == 3) {
        int v = NBTListUtil.getInt(nbt, itr);
        jsonArray.add(NBTSerializer.toJson((NBTBase)new NBTTagInt(v)));
      } else if (nbt.func_150303_d() == 1) {
        byte v = NBTListUtil.getByte(nbt, itr);
        jsonArray.add(NBTSerializer.toJson((NBTBase)new NBTTagByte(v)));
      } else if (nbt.func_150303_d() == 4) {
        long v = NBTListUtil.getLong(nbt, itr);
        jsonArray.add(NBTSerializer.toJson((NBTBase)new NBTTagLong(v)));
      } else if (nbt.func_150303_d() == 2) {
        short v = NBTListUtil.getShort(nbt, itr);
        jsonArray.add(NBTSerializer.toJson((NBTBase)new NBTTagShort(v)));
      } else if (nbt.func_150303_d() == 11) {
        jsonArray.add(NBTSerializer.toJson((NBTBase)new NBTTagIntArray(nbt.func_150306_c(itr))));
      } else if (nbt.func_150303_d() == 5) {
        jsonArray.add(NBTSerializer.toJson((NBTBase)new NBTTagFloat(nbt.func_150308_e(itr))));
      } else if (nbt.func_150303_d() == 6) {
        jsonArray.add(NBTSerializer.toJson((NBTBase)new NBTTagDouble(nbt.func_150309_d(itr))));
      } else {
        jsonArray.add(NBTSerializer.toJson((NBTBase)nbt.func_150305_b(itr)));
      } 
    } 
    return (JsonElement)jsonArray;
  }
  
  public Class<NBTTagList> instance() {
    return NBTTagList.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\nbt\objects\NBTListSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */