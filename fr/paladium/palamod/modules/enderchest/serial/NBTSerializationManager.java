package fr.paladium.palamod.modules.enderchest.serial;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.paladium.palamod.modules.enderchest.serial.itemstack.ItemStackSerializer;
import fr.paladium.palamod.modules.enderchest.serial.nbt.utils.NBTSerializer;
import java.util.Base64;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTSerializationManager {
  private static final String[] excludes = new String[] { "Pos", "Rotation", "Motion", "FallDistance", "OnGround", "PortalCooldown", "Dimension", "HasSpawnDimensionSet" };
  
  public static JsonObject serialize(Entity entity) {
    NBTTagCompound nbt = new NBTTagCompound();
    entity.func_70109_d(nbt);
    return serialize((NBTBase)nbt);
  }
  
  public static JsonObject serialize(NBTBase nbt) {
    NBTTagCompound compound = (NBTTagCompound)nbt;
    NBTTagList inventory = compound.func_150295_c("Inventory", 10);
    NBTTagList newInventory = new NBTTagList();
    for (int i = 0; i < inventory.func_74745_c(); i++) {
      NBTTagCompound item = inventory.func_150305_b(i);
      ItemStack it = ItemStack.func_77949_a(item);
      NBTTagCompound comp1 = new NBTTagCompound();
      comp1.func_74768_a("Slot", item.func_74762_e("Slot"));
      comp1.func_74778_a("item", Base64.getEncoder()
          .encodeToString(ItemStackSerializer.write(it).getBytes()));
      newInventory.func_74742_a((NBTBase)comp1);
    } 
    compound.func_74782_a("Inventory", (NBTBase)newInventory);
    NBTTagList enderItems = compound.func_150295_c("EnderItems", 10);
    NBTTagList newEnderItems = new NBTTagList();
    for (int j = 0; j < enderItems.func_74745_c(); j++) {
      NBTTagCompound item = enderItems.func_150305_b(j);
      ItemStack it = ItemStack.func_77949_a(item);
      NBTTagCompound comp1 = new NBTTagCompound();
      comp1.func_74768_a("Slot", item.func_74762_e("Slot"));
      comp1.func_74778_a("item", Base64.getEncoder()
          .encodeToString(ItemStackSerializer.write(it).getBytes()));
      newEnderItems.func_74742_a((NBTBase)comp1);
    } 
    compound.func_74782_a("EnderItems", (NBTBase)newEnderItems);
    compound.func_74768_a("Sync_Serial_Version", 1);
    JsonElement element = NBTSerializer.toJson((NBTBase)compound);
    JsonObject obj = element.getAsJsonObject();
    for (String str : excludes)
      obj.entrySet().removeIf(entry -> ((String)entry.getKey()).equals(str)); 
    return obj;
  }
  
  public static NBTTagCompound unSerialize(String json, Entity target) {
    if (target.field_70170_p.field_72995_K)
      return null; 
    NBTTagCompound oldNBT = new NBTTagCompound();
    target.func_70109_d(oldNBT);
    NBTBase nbt = NBTSerializer.toNbt((JsonElement)(new Gson()).fromJson(json, JsonElement.class));
    NBTTagCompound compound = (NBTTagCompound)nbt;
    for (String str : excludes)
      compound.func_74782_a(str, oldNBT.func_74781_a(str)); 
    NBTTagList inventory = compound.func_150295_c("Inventory", 10);
    NBTTagList newInventory = new NBTTagList();
    for (int i = 0; i < inventory.func_74745_c(); i++) {
      NBTTagCompound item = inventory.func_150305_b(i);
      if (item != null && 
        item.func_74764_b("item")) {
        byte[] decodedBytes = Base64.getDecoder().decode(item.func_74779_i("item"));
        String itemStr = new String(decodedBytes);
        ItemStack it = ItemStackSerializer.read(itemStr);
        if (it != null) {
          NBTTagCompound comp1 = new NBTTagCompound();
          it.func_77955_b(comp1);
          comp1.func_74768_a("Slot", item.func_74762_e("Slot"));
          newInventory.func_74742_a((NBTBase)comp1);
        } 
      } 
    } 
    compound.func_74782_a("Inventory", (NBTBase)newInventory);
    NBTTagList enderItems = compound.func_150295_c("EnderItems", 10);
    NBTTagList newEnderItems = new NBTTagList();
    for (int j = 0; j < enderItems.func_74745_c(); j++) {
      NBTTagCompound item = enderItems.func_150305_b(j);
      if (item != null && 
        item.func_74764_b("item")) {
        byte[] decodedBytes = Base64.getDecoder().decode(item.func_74779_i("item"));
        String itemStr = new String(decodedBytes);
        ItemStack it = ItemStackSerializer.read(itemStr);
        if (it != null) {
          NBTTagCompound comp1 = new NBTTagCompound();
          it.func_77955_b(comp1);
          comp1.func_74768_a("Slot", item.func_74762_e("Slot"));
          newEnderItems.func_74742_a((NBTBase)comp1);
        } 
      } 
    } 
    compound.func_74782_a("EnderItems", (NBTBase)newEnderItems);
    return compound;
  }
  
  public static NBTTagCompound unSerialize(String json) {
    NBTTagCompound oldNBT = new NBTTagCompound();
    NBTBase nbt = NBTSerializer.toNbt((JsonElement)(new Gson()).fromJson(json, JsonElement.class));
    NBTTagCompound compound = (NBTTagCompound)nbt;
    for (String str : excludes)
      compound.func_74782_a(str, oldNBT.func_74781_a(str)); 
    NBTTagList inventory = compound.func_150295_c("Inventory", 10);
    NBTTagList newInventory = new NBTTagList();
    for (int i = 0; i < inventory.func_74745_c(); i++) {
      NBTTagCompound item = inventory.func_150305_b(i);
      if (item != null && 
        item.func_74764_b("item")) {
        byte[] decodedBytes = Base64.getDecoder().decode(item.func_74779_i("item"));
        String itemStr = new String(decodedBytes);
        ItemStack it = ItemStackSerializer.read(itemStr);
        if (it != null) {
          NBTTagCompound comp1 = new NBTTagCompound();
          it.func_77955_b(comp1);
          comp1.func_74768_a("Slot", item.func_74762_e("Slot"));
          newInventory.func_74742_a((NBTBase)comp1);
        } 
      } 
    } 
    compound.func_74782_a("Inventory", (NBTBase)newInventory);
    NBTTagList enderItems = compound.func_150295_c("EnderItems", 10);
    NBTTagList newEnderItems = new NBTTagList();
    for (int j = 0; j < enderItems.func_74745_c(); j++) {
      NBTTagCompound item = enderItems.func_150305_b(j);
      if (item != null && 
        item.func_74764_b("item")) {
        byte[] decodedBytes = Base64.getDecoder().decode(item.func_74779_i("item"));
        String itemStr = new String(decodedBytes);
        ItemStack it = ItemStackSerializer.read(itemStr);
        if (it != null) {
          NBTTagCompound comp1 = new NBTTagCompound();
          it.func_77955_b(comp1);
          comp1.func_74768_a("Slot", item.func_74762_e("Slot"));
          newEnderItems.func_74742_a((NBTBase)comp1);
        } 
      } 
    } 
    compound.func_74782_a("EnderItems", (NBTBase)newEnderItems);
    return compound;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\serial\NBTSerializationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */