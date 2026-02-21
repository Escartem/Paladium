package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items;

import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.managers.AugustConfigManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemSeptemberTicket extends Item implements ITooltipInformations {
  public static final String NAME = "september_ticket";
  
  public static final String NBT_OWNER_FIELD = "owner";
  
  public static final String NBT_COOLDOWN_FIELD = "cooldown";
  
  public static final String TELEPORTATION_MESSAGE = "§eVous avez été téléporté à l'animalerie !";
  
  public static final String COOLDOWN_MESSAGE = "&cVous devez attendre %s avant de pouvoir utiliser ce ticket !";
  
  public static final String NOT_OWNER_MESSAGE = "&cVous n'êtes pas le propriétaire de ce ticket !";
  
  public static final String DESCRIPTION = "Effectue un clic droit en tenant cet objet sur le serveur event pour être téléporté à la Palanimalerie !";
  
  public static final String SERVER_NAME = "EVENT";
  
  public static final long COOLDOWN_DURATION_MILLIS = TimeUnit.HOURS.toMillis(2L);
  
  public ItemSeptemberTicket() {
    func_77655_b("september_ticket");
    func_111206_d("palamod:september_ticket");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    if (!isOnCorrectServer(player))
      return itemStack; 
    if (LuckyBlockUtils.isInCombat(player, true))
      return itemStack; 
    if (itemStack.func_77978_p() == null) {
      initNbt(itemStack, player);
      teleport(player);
      return itemStack;
    } 
    NBTTagCompound compound = itemStack.func_77978_p();
    if (isOnCooldown(itemStack, compound, player) || !isOwner(itemStack, compound, player))
      return itemStack; 
    addCooldown(itemStack, compound);
    teleport(player);
    return itemStack;
  }
  
  private boolean isOwner(ItemStack stack, NBTTagCompound compound, EntityPlayer player) {
    if (!compound.func_74764_b("owner"))
      return false; 
    String uniqueId = compound.func_74779_i("owner");
    if (!uniqueId.equals(player.func_110124_au().toString())) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous n'êtes pas le propriétaire de ce ticket !" });
      return false;
    } 
    return true;
  }
  
  private void addCooldown(ItemStack stack, NBTTagCompound compound) {
    long now = System.currentTimeMillis();
    compound.func_74772_a("cooldown", now + COOLDOWN_DURATION_MILLIS);
    stack.func_77982_d(compound);
  }
  
  private boolean isOnCooldown(ItemStack stack, NBTTagCompound compound, EntityPlayer player) {
    if (!compound.func_74764_b("cooldown"))
      return true; 
    long cooldown = compound.func_74763_f("cooldown");
    long now = System.currentTimeMillis();
    if (cooldown > now) {
      MonthlyUtils.sendMessage(player, new String[] { String.format("&cVous devez attendre %s avant de pouvoir utiliser ce ticket !", new Object[] { DurationConverter.fromMillisToString(cooldown - now) }) });
      return true;
    } 
    return false;
  }
  
  private boolean isOnCorrectServer(EntityPlayer player) {
    String serverName = CommonModule.getInstance().getConfig().getServerName();
    if (serverName == null || !serverName.equalsIgnoreCase("EVENT")) {
      MonthlyUtils.sendMessage(player, new String[] { "Effectue un clic droit en tenant cet objet sur le serveur event pour être téléporté à la Palanimalerie !" });
      return false;
    } 
    return true;
  }
  
  private void teleport(EntityPlayer player) {
    AugustConfigManager manager = AugustConfigManager.getInstance();
    DoubleLocation location = manager.getConfig().getPetShopLocation();
    if (location == null)
      return; 
    MonthlyUtils.sendMessage(player, new String[] { "§eVous avez été téléporté à l'animalerie !" });
    location.teleportServer(player);
  }
  
  private void initNbt(ItemStack stack, EntityPlayer player) {
    if (stack.func_77978_p() != null)
      return; 
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("owner", player.func_110124_au().toString());
    compound.func_74772_a("cooldown", System.currentTimeMillis() + COOLDOWN_DURATION_MILLIS);
    stack.func_77982_d(compound);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Effectue un clic droit en tenant cet objet sur le serveur event pour être téléporté à la Palanimalerie !");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\items\ItemSeptemberTicket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */