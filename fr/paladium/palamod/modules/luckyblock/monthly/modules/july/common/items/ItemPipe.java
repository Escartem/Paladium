package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemPipe extends Item {
  public static final String NAME = "pipe";
  
  private static final String CANT_USE_MESSAGE = "&cVous ne pouvez pas utiliser cet item en combat !";
  
  private static final String COOLDOWN_MESSAGE = "&cVous devez attendre %s avant de pouvoir utiliser cet item !";
  
  private static final long COOLDOWN_TIME = TimeUnit.SECONDS.toMillis(60L);
  
  public ItemPipe() {
    func_77655_b("pipe");
    func_111206_d("palamod:pipe");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    if (CooldownUtils.isOnCooldown((Entity)player, "pipe")) {
      MonthlyUtils.sendMessage(player, new String[] { String.format("&cVous devez attendre %s avant de pouvoir utiliser cet item !", new Object[] { DurationConverter.fromMillisToString(CooldownUtils.getCooldown((Entity)player, "pipe") - System.currentTimeMillis()) }) });
      return itemStack;
    } 
    if (LuckyBlockUtils.isInCombat(player)) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous ne pouvez pas utiliser cet item en combat !" });
      return itemStack;
    } 
    CooldownUtils.setCooldown((Entity)player, "pipe", COOLDOWN_TIME);
    return smoke(itemStack, player);
  }
  
  private ItemStack smoke(ItemStack stack, EntityPlayer player) {
    World world = player.field_70170_p;
    if (!(world instanceof WorldServer))
      return stack; 
    double posX = player.field_70165_t;
    double posY = player.field_70163_u;
    double posZ = player.field_70161_v;
    WorldServer worldServer = (WorldServer)world;
    worldServer.func_147487_a("largesmoke", posX, posY, posZ, 2000, 2.2D, 1.0D, 2.2D, 0.1D);
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemPipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */