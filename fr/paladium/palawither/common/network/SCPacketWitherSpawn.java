package fr.paladium.palawither.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.translate.common.texttotranslate.TTT;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SCPacketWitherSpawn extends ForgePacket {
  @PacketData
  private String name;
  
  @PacketData
  private String sound;
  
  @PacketData
  private IWither.WitherInvokeAlert alert;
  
  @PacketData
  private String server;
  
  public SCPacketWitherSpawn() {}
  
  public SCPacketWitherSpawn(String name, String sound, IWither.WitherInvokeAlert alert, String server) {
    this.name = name;
    this.sound = sound;
    this.alert = alert;
    this.server = server;
  }
  
  public SCPacketWitherSpawn(@NonNull IWither wither, @NonNull IWither.WitherInvokeAlert alert) {
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    if (alert == null)
      throw new NullPointerException("alert is marked non-null but is null"); 
    this.name = wither.getDisplayName();
    this.sound = (wither.getInvokeSound() != null) ? wither.getInvokeSound().toString() : null;
    this.alert = alert;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (this.alert == IWither.WitherInvokeAlert.NONE)
      return; 
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    entityClientPlayerMP.func_146105_b((IChatComponent)new ChatComponentText(""));
    if (this.alert == IWither.WitherInvokeAlert.BROADCAST && this.server != null && !this.server.isEmpty()) {
      entityClientPlayerMP.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §8Un §4§l" + TTT.format(I18n.func_135052_a(this.name, new Object[0]), new Object[0]).toUpperCase() + " §8vient §8d'être §8invoqué §8sur §8le §8serveur §4§l" + this.server.toUpperCase() + " §8!"));
    } else {
      entityClientPlayerMP.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §8Un §4§l" + TTT.format(I18n.func_135052_a(this.name, new Object[0]), new Object[0]).toUpperCase() + " §8vient §8d'être §8invoqué " + ((this.alert == IWither.WitherInvokeAlert.LOCAL) ? "§8proche §8de §8vous" : "§8sur §8le §8serveur") + " §8!"));
    } 
    entityClientPlayerMP.func_146105_b((IChatComponent)new ChatComponentText(""));
    if (this.sound != null)
      SoundRecord.create(this.sound).category(SoundCategory.MOBS).volume(10.0F).play(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\network\SCPacketWitherSpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */