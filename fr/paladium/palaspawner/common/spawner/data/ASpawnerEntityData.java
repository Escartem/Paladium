package fr.paladium.palaspawner.common.spawner.data;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaspawner.common.utils.DebugUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public abstract class ASpawnerEntityData {
  private final String entityId;
  
  private final Class<? extends EntityLiving> entityClass;
  
  private EntityLiving entity;
  
  private int experiencePoints;
  
  public String getEntityId() {
    return this.entityId;
  }
  
  public Class<? extends EntityLiving> getEntityClass() {
    return this.entityClass;
  }
  
  public int getExperiencePoints() {
    return this.experiencePoints;
  }
  
  public ASpawnerEntityData(String entityId, Class<? extends EntityLiving> entityClass) {
    this.entityId = entityId;
    this.entityClass = entityClass;
  }
  
  protected void addFewItems(int lootingModifier, List<ItemStack> stacks, Random random) {
    if (getDropItem() == null)
      return; 
    int j = random.nextInt(3);
    if (lootingModifier > 0)
      j += random.nextInt(lootingModifier + 1); 
    for (int k = 0; k < j; k++)
      stacks.add(new ItemStack(getDropItem(), 1)); 
  }
  
  protected void addRareDrops(List<ItemStack> stacks, int lootingLevel, Random random) {}
  
  public List<ItemStack> getDrops(Random random, EntityPlayerMP player, int lootCount) {
    List<ItemStack> stacks = new ArrayList<>();
    if (lootCount > 0 && !this.entityId.equals("Slime")) {
      int randomInt = random.nextInt(100);
      if (randomInt < 10 * lootCount) {
        DebugUtils.broadcastDebugMessage(new String[] { "Looting upgrade success, dropping loot a second time for entity " + getEntityId() });
        stacks = getDrops(random, player, 0);
      } 
    } 
    int lootingLevel = 0;
    if (player != null)
      lootingLevel = EnchantmentHelper.func_77519_f((EntityLivingBase)player); 
    addFewItems(lootingLevel, stacks, random);
    int rareChance = random.nextInt(200) - lootingLevel;
    if (rareChance < 5)
      addRareDrops(stacks, lootingLevel, random); 
    return stacks;
  }
  
  public void setExperiencePoints() {
    try {
      this.experiencePoints = ((Integer)ObfuscationReflectionHelper.getPrivateValue(EntityLiving.class, 
          getEntity(), new String[] { "experienceValue", "field_70728_aV" })).intValue();
    } catch (Exception exception) {
      exception.printStackTrace();
      throw new RuntimeException("Cannot get experience points from entity " + getEntity().getClass().getSimpleName());
    } 
  }
  
  @SideOnly(Side.CLIENT)
  private World getClientSideWorld() {
    return (World)(Minecraft.func_71410_x()).field_71441_e;
  }
  
  @SideOnly(Side.SERVER)
  private World getServerSideWorld() {
    return MinecraftServer.func_71276_C().func_130014_f_();
  }
  
  private World getWorld() {
    return FMLCommonHandler.instance().getSide().isClient() ? getClientSideWorld() : getServerSideWorld();
  }
  
  public EntityLiving getEntity() {
    if (this.entity == null)
      this.entity = initEntity(getWorld()); 
    return this.entity;
  }
  
  public abstract EntityLiving initEntity(World paramWorld);
  
  protected abstract Item getDropItem();
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\data\ASpawnerEntityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */