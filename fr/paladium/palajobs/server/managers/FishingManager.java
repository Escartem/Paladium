package fr.paladium.palajobs.server.managers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.java.map.player.PlayerMap;
import fr.paladium.palaforgeutils.lib.java.map.player.SessionPlayerMap;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.core.fishing.FishingCategory;
import fr.paladium.palajobs.core.packets.server.SCPacketFishing;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class FishingManager {
  private static PlayerMap<FishingCategory> map = (PlayerMap<FishingCategory>)new SessionPlayerMap<FishingCategory>() {
      public FishingCategory getDefaultValue() {
        return null;
      }
    };
  
  public static FishingCategory get(EntityPlayer player) {
    return (FishingCategory)map.get((Entity)player);
  }
  
  public static void open(EntityPlayerMP player, FishingCategory category) {
    map.put((Entity)player, category);
    PalaJobs.proxy.getNetwork().sendTo((IMessage)new SCPacketFishing(category.getName()), player);
  }
  
  public static void close(EntityPlayer player) {
    map.remove((Entity)player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\managers\FishingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */