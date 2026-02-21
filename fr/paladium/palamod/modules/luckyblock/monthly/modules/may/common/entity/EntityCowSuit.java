package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.entity;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCowSuit extends EntityCow {
  public EntityCowSuit(World world) {
    super(world);
  }
  
  public EntityCowSuit(World world, double x, double y, double z) {
    super(world);
    func_70634_a(x, y, z);
  }
  
  protected void func_70628_a(boolean wasRecentlyHit, int lootingModifier) {
    ItemStack[] possibleDrops = { new ItemStack(GameRegistry.findItem("customnpcs", "npcCowleatherHead")), new ItemStack(GameRegistry.findItem("customnpcs", "npcCowleatherChest")), new ItemStack(GameRegistry.findItem("customnpcs", "npcFullLeatherHead")), new ItemStack(GameRegistry.findItem("customnpcs", "npcFullLeatherChest")), new ItemStack(ItemsRegister.RAINBOW_LEATHER, 8) };
    int dropIndex = this.field_70146_Z.nextInt(possibleDrops.length);
    ItemStack drop = possibleDrops[dropIndex];
    func_70099_a(drop, 0.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\common\entity\EntityCowSuit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */