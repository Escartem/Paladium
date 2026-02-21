package fr.paladium.palamod.modules.luckyblock.items.may;

import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.ILuckyStatsOwnedReward;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BaseItemTicket extends Item implements ITooltipInformations, ILuckyStatsOwnedReward {
  public BaseItemTicket(String name) {
    func_77655_b(name);
    func_111206_d("palamod:" + name);
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.field_72995_K && itemStack != null && itemStack.func_77942_o() && itemStack.func_77978_p().func_74764_b("owner")) {
      NBTTagCompound tag = itemStack.func_77978_p();
      if (!tag.func_74779_i("owner").equals(FastUUID.toString((Entity)player))) {
        PlayerUtils.sendMessage(player, "Ce n'est pas ton ticket.");
        return itemStack;
      } 
      Long cooldown = null;
      if (tag.func_74764_b("cooldown"))
        cooldown = Long.valueOf(tag.func_74763_f("cooldown")); 
      if (cooldown != null && TimeUtil.now() - cooldown.longValue() < 86400L) {
        long remainingTime = TimeUtil.now() - cooldown.longValue();
        int hours = (int)(remainingTime / 3600L);
        int minutes = (int)(remainingTime % 3600L / 60L);
        String remainingTimeStr = String.format("%02dh%02d", new Object[] { Integer.valueOf(hours), Integer.valueOf(minutes) });
        PlayerUtils.sendMessage(player, "Réessaye dans " + remainingTimeStr + ".");
        return itemStack;
      } 
      tag.func_74772_a("cooldown", TimeUtil.now());
      String currentWorld = CommonModule.getInstance().getConfig().getServerName().toUpperCase();
      Vec3 pos = null;
      switch (currentWorld) {
        case "EGOPOLIS":
          pos = Vec3.func_72443_a(-52.0D, 14.0D, -43.0D);
          break;
        case "AELORIA":
          pos = Vec3.func_72443_a(-3.0D, 55.0D, -67.0D);
          break;
        case "XANOTH":
          pos = Vec3.func_72443_a(58.0D, 27.0D, -62.0D);
          break;
        case "KILMORDRA":
          pos = Vec3.func_72443_a(11.0D, 29.0D, 55.0D);
          break;
        case "RUNEGARD":
          pos = Vec3.func_72443_a(91.0D, 53.0D, 31.0D);
          break;
      } 
      if (pos == null) {
        PlayerUtils.sendMessage(player, "Essaye dans un autre monde.");
        return itemStack;
      } 
      itemStack.func_77982_d(tag);
      Player pl = Bukkit.getPlayer(player.func_110124_au());
      Location location = new Location(pl.getWorld(), pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
      pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
    } 
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Effectuez un clic-droit sur l’objet pour", "être téléporté dans l’atelier du forgeron.", "Objet ne pouvant être utilisé qu’une fois par jour." };
  }
  
  public String getValue(EntityPlayer player) {
    return FastUUID.toString((Entity)player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\may\BaseItemTicket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */