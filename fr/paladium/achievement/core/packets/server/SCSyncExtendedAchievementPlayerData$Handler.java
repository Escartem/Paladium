package fr.paladium.achievement.core.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.achievement.core.data.ExtendedAchievementPlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Handler implements IMessageHandler<SCSyncExtendedAchievementPlayerData, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCSyncExtendedAchievementPlayerData message, MessageContext ctx) {
    if (SCSyncExtendedAchievementPlayerData.access$000(message) == null)
      return null; 
    ExtendedAchievementPlayerData eep = ExtendedAchievementPlayerData.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
    eep.loadNBTData(SCSyncExtendedAchievementPlayerData.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\packets\server\SCSyncExtendedAchievementPlayerData$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */