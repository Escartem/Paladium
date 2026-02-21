package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.Interval;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemCorruptedLegendaryStone extends Item implements ITooltipInformations {
  public static final String NAME = "corrupted_legendary_stone";
  
  private static final String DESCRIPTION = "Le ENTITY:PLAYER qui l’utiliser peut obtenir un des LEGENDARYSTONE.EFFECT aléatoire. Cooldown variable entre TIME 6-30 HOURS";
  
  private static final Interval LEGENDARY_STONE_INTERVAL = new Interval(1, 6);
  
  private static final Interval COOLDOWN_INTERVAL = new Interval(6, 30);
  
  private static final String NBT_OWNER_ID = "ownerId";
  
  private static final String NBT_COOLDOWN = "cooldown";
  
  private static final String NOT_OWNER_MESSAGE = "§cVous n'êtes pas le propriétaire de cette pierre légendaire corrompue !";
  
  private static final String ON_COOLDOWN_MESSAGE = "§cVous devez attendre %s avant de pouvoir utiliser cette pierre légendaire corrompue !";
  
  public ItemCorruptedLegendaryStone() {
    func_77655_b("corrupted_legendary_stone");
    func_111206_d("palamod:corrupted_legendary_stone");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    NBTTagCompound compound = stack.func_77978_p();
    if (!isOwner(player, stack, compound) || isOnCooldown(player, stack, compound) || WorldGuardUtils.isItemEffectBlocked((Entity)player, stack))
      return stack; 
    int id = LEGENDARY_STONE_INTERVAL.getRandom();
    long cooldown = TimeUnit.HOURS.toMillis(COOLDOWN_INTERVAL.getRandom());
    updateNbt(player, stack, compound, cooldown);
    LegendaryStone.performEffects(id, player, world);
    return stack;
  }
  
  private void updateNbt(EntityPlayer player, ItemStack stack, NBTTagCompound compound, long cooldown) {
    if (compound == null)
      compound = new NBTTagCompound(); 
    compound.func_74778_a("ownerId", player.func_110124_au().toString());
    compound.func_74772_a("cooldown", System.currentTimeMillis() + cooldown);
    stack.func_77982_d(compound);
  }
  
  private boolean isOwner(EntityPlayer player, ItemStack stack, NBTTagCompound compound) {
    if (compound == null || !compound.func_74764_b("ownerId"))
      return true; 
    String ownerId = compound.func_74779_i("ownerId");
    if (ownerId.equals(player.func_110124_au().toString())) {
      MonthlyUtils.sendMessage(player, new String[] { "§cVous n'êtes pas le propriétaire de cette pierre légendaire corrompue !" });
      return true;
    } 
    return false;
  }
  
  private boolean isOnCooldown(EntityPlayer player, ItemStack stack, NBTTagCompound compound) {
    if (compound == null || !compound.func_74764_b("cooldown"))
      return false; 
    long now = System.currentTimeMillis();
    long cooldown = compound.func_74763_f("cooldown");
    if (now < cooldown) {
      MonthlyUtils.sendMessage(player, new String[] { String.format("§cVous devez attendre %s avant de pouvoir utiliser cette pierre légendaire corrompue !", new Object[] { DurationConverter.fromMillisToString(cooldown - now) }) });
      return true;
    } 
    return false;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Le ENTITY:PLAYER qui l’utiliser peut obtenir un des LEGENDARYSTONE.EFFECT aléatoire. Cooldown variable entre TIME 6-30 HOURS");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\items\ItemCorruptedLegendaryStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */