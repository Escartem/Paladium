package fr.paladium.palarpg.module.dungeon.common.network.chest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SCPacketRPGDungeonChestRewardBroadcast extends ForgePacket {
  @PacketData
  private String playerName;
  
  @PacketData
  private String dungeonName;
  
  @PacketData
  private ItemStack item;
  
  public SCPacketRPGDungeonChestRewardBroadcast() {}
  
  public SCPacketRPGDungeonChestRewardBroadcast(String playerName, String dungeonName, ItemStack item) {
    this.playerName = playerName;
    this.dungeonName = dungeonName;
    this.item = item;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (this.playerName == null || this.dungeonName == null || this.item == null)
      return; 
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    entityClientPlayerMP.func_145747_a((IChatComponent)new ChatComponentText(""));
    entityClientPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bLe joueur §6" + this.playerName + "§r§b vient d'obtenir §6" + this.item.func_82833_r() + "§r§b dans le donjon §6" + this.dungeonName + "§r§b !"));
    entityClientPlayerMP.func_145747_a((IChatComponent)new ChatComponentText(""));
    SoundUtils.FIREWORK_LAUNCH.playClientSound(1.0F, 1.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\chest\SCPacketRPGDungeonChestRewardBroadcast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */