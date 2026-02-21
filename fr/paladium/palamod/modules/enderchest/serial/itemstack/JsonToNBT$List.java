package fr.paladium.palamod.modules.enderchest.serial.itemstack;

import com.google.common.collect.Lists;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagList;

class List extends JsonToNBT.Any {
  protected java.util.List<JsonToNBT.Any> tagList = Lists.newArrayList();
  
  public List(String json) {
    this.json = json;
  }
  
  public NBTBase parse() throws NBTException {
    NBTTagList nbttaglist = new NBTTagList();
    for (JsonToNBT.Any jsontonbt$any : this.tagList)
      nbttaglist.func_74742_a(jsontonbt$any.parse()); 
    return (NBTBase)nbttaglist;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\itemstack\JsonToNBT$List.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */