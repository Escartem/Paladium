package fr.paladium.palaforgeutils.lib.item;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class ItemUtils {
  public static void spawnItemAtEntity(Entity entity, ItemStack stack) {
    if (stack == null)
      return; 
    spawnItemInWorld(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, stack);
  }
  
  public static void spawnItemInWorld(World world, double x, double y, double z, ItemStack stack) {
    if (stack == null)
      return; 
    float f = 0.7F;
    double xV = (world.field_73012_v.nextFloat() * 0.7F) + 0.15000000596046448D;
    double yV = (world.field_73012_v.nextFloat() * 0.7F) + 0.15000000596046448D;
    double zV = (world.field_73012_v.nextFloat() * 0.7F) + 0.15000000596046448D;
    EntityItem entityitem = new EntityItem(world, x + xV, y + yV, z + zV, stack);
    world.func_72838_d((Entity)entityitem);
  }
  
  public static ItemStack parse(@NonNull String item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
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
      try {
        ItemStack itemStack = ItemStackSerializer.read(new String(Base64.getDecoder().decode(item), StandardCharsets.UTF_8), null);
        if (itemStack != null)
          return itemStack; 
      } catch (Exception exception1) {}
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
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\item\ItemUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */