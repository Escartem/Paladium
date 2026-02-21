package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities.EntitySlayer;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import fr.paladium.palamod.util.FastUUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemSlayerEgg extends Item implements ITooltipInformations {
  public static final String NAME = "slayer_egg";
  
  public static final String DESCRIPTION = "DEST.TXT : Permet de faire ACTION:SPAWN un CORRUPTION.SLAYER. Le CORRUPTON.SLAYER donne des ITEM:PURIFITE.DIVINE toutes les TIME:24 HOURS";
  
  public static final String NBT_UNIQUE_ID_FIELD = "uniqueId";
  
  public static final String NBT_COOLDOWN_FIELD = "cooldown";
  
  public static final Long COOLDOWN_DURATION = Long.valueOf(TimeUnit.HOURS.toMillis(24L));
  
  private static final String NOT_OWNER_MESSAGE = "&cVous n'êtes pas le propriétaire de ce Slayer.";
  
  private static final String ON_COOLDOWN_MESSAGE = "&cVous devez attendre &6%s&c avant de pouvoir utiliser ce Slayer.";
  
  private static final String SPAWN_MESSAGE = "&aVous avez fait apparaître un Slayer.";
  
  private static final String CANT_SPAWN_MESSAGE = "&cVous ne pouvez pas faire apparaître un Slayer ici.";
  
  public ItemSlayerEgg() {
    func_77655_b("slayer_egg");
    func_111206_d("palamod:slayer_egg");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    if (LuckyBlockUtils.isInCombat(player, true))
      return itemStack; 
    if (!LuckyBlockUtils.canUseItem((EntityPlayerMP)player, true))
      return itemStack; 
    NBTTagCompound compound = itemStack.func_77978_p();
    if (compound == null)
      itemStack.func_77982_d(initNbt(player)); 
    compound = itemStack.func_77978_p();
    if (!isOwner(player, compound)) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous n'êtes pas le propriétaire de ce Slayer." });
      return itemStack;
    } 
    long now = System.currentTimeMillis();
    if (isOnCooldown(now, compound)) {
      long cooldown = compound.func_74763_f("cooldown");
      MonthlyUtils.sendMessage(player, new String[] { String.format("&cVous devez attendre &6%s&c avant de pouvoir utiliser ce Slayer.", new Object[] { DurationConverter.fromMillisToString(cooldown - now) }) });
      return itemStack;
    } 
    applyCooldown(now, player, compound);
    spawnCrab(world, player);
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DEST.TXT : Permet de faire ACTION:SPAWN un CORRUPTION.SLAYER. Le CORRUPTON.SLAYER donne des ITEM:PURIFITE.DIVINE toutes les TIME:24 HOURS");
  }
  
  private void spawnCrab(World world, EntityPlayer player) {
    Location location = new Location((Entity)player);
    if (!EventUtils.canInteractBlock(player, location)) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous ne pouvez pas faire apparaître un Slayer ici." });
      return;
    } 
    EntitySlayer slayer = new EntitySlayer(player.field_70170_p);
    slayer.spawn(player, world, location.getX(), location.getY(), location.getZ());
    MonthlyUtils.sendMessage(player, new String[] { "&aVous avez fait apparaître un Slayer." });
  }
  
  private boolean isOnCooldown(long now, NBTTagCompound compound) {
    return (now < compound.func_74763_f("cooldown"));
  }
  
  private boolean isOwner(EntityPlayer player, NBTTagCompound compound) {
    return FastUUID.toString(player.func_110124_au()).equals(compound.func_74779_i("uniqueId"));
  }
  
  private void applyCooldown(long now, EntityPlayer player, NBTTagCompound compound) {
    compound.func_74772_a("cooldown", now + COOLDOWN_DURATION.longValue());
  }
  
  public static ItemStack init(EntityPlayer player) {
    ItemStack stack = new ItemStack(ItemsRegister.CRAB_EGG);
    stack.func_77982_d(initNbt(player));
    return stack;
  }
  
  public static NBTTagCompound initNbt(EntityPlayer player) {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("uniqueId", FastUUID.toString(player.func_110124_au()));
    compound.func_74772_a("cooldown", 0L);
    return compound;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\items\ItemSlayerEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */