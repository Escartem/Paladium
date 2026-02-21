package fr.paladium.palamod.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

class Compound extends JsonToNBT.Any {
  protected List<JsonToNBT.Any> tagList = Lists.newArrayList();
  
  public Compound(String jsonIn) {
    this.json = jsonIn;
  }
  
  public NBTBase parse() throws NBTException {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    for (JsonToNBT.Any jsontonbt$any : this.tagList)
      nbttagcompound.func_74782_a(jsontonbt$any.json, jsontonbt$any.parse()); 
    return (NBTBase)nbttagcompound;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\JsonToNBT$Compound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */