package fr.paladium.palamod.modules.paladium.utils.usebugs.unlootable;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import fr.paladium.palamod.modules.addons.network.MessageP;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.modules.paladium.PPaladium;
import fr.paladium.palamod.modules.paladium.utils.StaffAlertTypes;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ConfigurationManager {
  public static List<IUnlootableConfiguration> configurations = new ArrayList<>();
  
  public static Map<EntityPlayer, Map<Long, Integer>> alerts = new HashMap<>();
  
  private static final int maxAlert = 10;
  
  public static void init() {}
  
  public static void addAlert(EntityPlayer pl) {
    int i = 0;
    long date = TimeUtil.now();
    if (alerts.containsKey(pl)) {
      i = ((Integer)((Map)alerts.get(pl)).values().toArray()[0]).intValue();
      date = ((Long)((Map)alerts.get(pl)).keySet().toArray()[0]).longValue();
      if (TimeUtil.fromLong(date).isAfter(TimeUtil.nowZoned().plusMinutes(20L))) {
        i = 0;
        date = TimeUtil.now();
      } 
    } 
    i++;
    Map<Long, Integer> map = new HashMap<>();
    map.put(Long.valueOf(date), Integer.valueOf(i));
    if (i >= 10);
    pl.func_145747_a((IChatComponent)new ChatComponentText("§cVous essayez de créer un §bcoffre impillable§c, si c'est une erreur merci de contacter un modérateur sur le teamspeak §e(ts.paladium-pvp.fr)"));
    pl.func_145747_a((IChatComponent)new ChatComponentText("  §7-> Tentative " + ((i < 3.3333333F) ? "§a" : ((i < 6.6666665F) ? "§e" : ((i < 10) ? "§c" : "§4"))) + i + "/" + '\n'));
    if (i >= 3.3333333F) {
      MessageP send = new MessageP(2);
      send.addValue("type", StaffAlertTypes.CHEST_UNLOOTABLE.toString());
      send.addValue("user", pl.func_70005_c_());
      send.addValue("pos", (int)pl.field_70165_t + " " + (int)pl.field_70163_u + " " + (int)pl.field_70161_v);
      send.addValue("count", "" + i);
      send.addValue("max", "10");
      try {
        ByteBuf buffer = Unpooled.copiedBuffer(send.toBytesArrays());
        PPaladium.staffalertNetwork.sendToServer(new FMLProxyPacket(buffer, "staffalerts"));
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
    alerts.put(pl, map);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\util\\usebug\\unlootable\ConfigurationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */