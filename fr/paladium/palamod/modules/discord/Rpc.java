package fr.paladium.palamod.modules.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Rpc {
  private final DiscordRPC lib = DiscordRPC.INSTANCE;
  
  private DiscordRichPresence presence;
  
  private boolean isLoaded = false;
  
  public void init(String rpc_id, String rpc_largeImageKey, String rpc_largeImageText, String rpc_smallImageKey, String rpc_smallImageText) {
    DiscordEventHandlers handlers = new DiscordEventHandlers();
    this.lib.Discord_Initialize(rpc_id, handlers, true, null);
    this.presence = new DiscordRichPresence();
    this.presence.startTimestamp = System.currentTimeMillis() / 1000L;
    this.presence.largeImageKey = rpc_largeImageKey;
    this.presence.largeImageText = rpc_largeImageText;
    this.presence.smallImageKey = rpc_smallImageKey;
    this.presence.smallImageText = rpc_smallImageText;
    this.lib.Discord_UpdatePresence(this.presence);
    this.isLoaded = true;
  }
  
  public void setDetails(String text) {
    if (this.isLoaded) {
      this.presence.details = text;
      this.lib.Discord_UpdatePresence(this.presence);
    } 
  }
  
  public void setState(String text) {
    if (this.isLoaded) {
      this.presence.state = text;
      this.lib.Discord_UpdatePresence(this.presence);
    } 
  }
  
  public void setSmallImageText(String text) {
    if (this.isLoaded) {
      this.presence.smallImageText = text;
      this.lib.Discord_UpdatePresence(this.presence);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\discord\Rpc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */