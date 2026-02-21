package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities.EntityCaptainFlint;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemFlintEgg extends Item implements ITooltipInformations {
  public static final String NAME = "flint_egg";
  
  public static final String DESCRIPTION = "Permet de faire apparaître le Captaine Flint. Il aura des histoires pour vous, apparemment il connaît une planque incroyable où se trouvent des morceaux de métal étrange ! Peut être équipé d’une étiquette en Paladium";
  
  private static final String NBT_OWNER_FIELD = "owner";
  
  private static final String NBT_COOLDOWN_FIELD = "cooldown";
  
  private static final String ENTITY_UNIQUE_ID_FIELD = "entityUniqueId";
  
  public static final String ENTITY_NAME = "Flint";
  
  private static final String COOLDOWN_MESSAGE = "&cVous devez attendre %s avant de pouvoir utiliser cet item !";
  
  private static final String NOT_OWNER_MESSAGE = "&cVous n'êtes pas le propriétaire !";
  
  private static final long COOLDOWN_DURATION_MILLIS = TimeUnit.MINUTES.toMillis(5L);
  
  public ItemFlintEgg() {
    func_77655_b("flint_egg");
    func_111206_d("palamod:flint_egg");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    Location location = new Location((Entity)player);
    if (itemStack.func_77978_p() == null) {
      initNbt(itemStack, player, location);
      return itemStack;
    } 
    NBTTagCompound compound = itemStack.func_77978_p();
    if (isOnCooldown(itemStack, compound, player))
      return itemStack; 
    if (!isOwner(itemStack, compound, player)) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous n'êtes pas le propriétaire !" });
      return itemStack;
    } 
    if (LuckyBlockUtils.isInCombat(player, true))
      return itemStack; 
    updateAndSpawn(location, itemStack, compound);
    return itemStack;
  }
  
  private boolean isOwner(ItemStack stack, NBTTagCompound compound, EntityPlayer player) {
    if (!compound.func_74764_b("owner"))
      return false; 
    String uniqueId = compound.func_74779_i("owner");
    if (!uniqueId.equals(player.func_110124_au().toString())) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous n'êtes pas le propriétaire !" });
      return false;
    } 
    return true;
  }
  
  private void updateAndSpawn(Location location, ItemStack stack, NBTTagCompound compound) {
    long now = System.currentTimeMillis();
    UUID uniqueId = UUID.fromString(compound.func_74779_i("entityUniqueId"));
    World world = location.getWorld();
    EntityLivingBase entity = MonthlyUtils.getLivingEntityByUniqueId(world, uniqueId);
    if (entity != null)
      entity.func_70106_y(); 
    uniqueId = spawnCaptain(location);
    compound.func_74772_a("cooldown", now + COOLDOWN_DURATION_MILLIS);
    compound.func_74778_a("entityUniqueId", uniqueId.toString());
    stack.func_77982_d(compound);
  }
  
  private boolean isOnCooldown(ItemStack stack, NBTTagCompound compound, EntityPlayer player) {
    if (!compound.func_74764_b("cooldown"))
      return true; 
    long cooldown = compound.func_74763_f("cooldown");
    long now = System.currentTimeMillis();
    if (cooldown > now) {
      MonthlyUtils.sendMessage(player, new String[] { String.format("&cVous devez attendre %s avant de pouvoir utiliser cet item !", new Object[] { DurationConverter.fromMillisToString(cooldown - now) }) });
      return true;
    } 
    return false;
  }
  
  private UUID spawnCaptain(Location location) {
    World world = location.getWorld();
    EntityCaptainFlint pirate = new EntityCaptainFlint(world, "Flint", "palamod:textures/entity/npc/pirate_king.png", location.getX(), location.getY() - 2.0D, location.getZ(), COOLDOWN_DURATION_MILLIS, COOLDOWN_DURATION_MILLIS, true);
    world.func_72838_d((Entity)pirate);
    return pirate.func_110124_au();
  }
  
  private void initNbt(ItemStack stack, EntityPlayer player, Location location) {
    if (stack.func_77978_p() != null)
      return; 
    NBTTagCompound compound = new NBTTagCompound();
    UUID uniqueId = spawnCaptain(location);
    compound.func_74778_a("owner", player.func_110124_au().toString());
    compound.func_74772_a("cooldown", System.currentTimeMillis() + COOLDOWN_DURATION_MILLIS);
    compound.func_74778_a("entityUniqueId", uniqueId.toString());
    stack.func_77982_d(compound);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Permet de faire apparaître le Captaine Flint. Il aura des histoires pour vous, apparemment il connaît une planque incroyable où se trouvent des morceaux de métal étrange ! Peut être équipé d’une étiquette en Paladium");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemFlintEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */