package fr.paladium.palamixins.event.common.fml;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.handshake.FMLHandshakeMessage;
import net.minecraft.entity.player.EntityPlayerMP;

@Cancelable
public class FMLModListEvent extends Event {
  private final EntityPlayerMP player;
  
  private final FMLHandshakeMessage.ModList modList;
  
  public FMLModListEvent(EntityPlayerMP player, FMLHandshakeMessage.ModList modList) {
    this.player = player;
    this.modList = modList;
  }
  
  public EntityPlayerMP getPlayer() {
    return this.player;
  }
  
  public FMLHandshakeMessage.ModList getModList() {
    return this.modList;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\common\fml\FMLModListEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */