package fr.paladium.palashop.provider.box.common.network.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.palashop.provider.box.common.dto.box.BoxData;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import fr.paladium.palashop.provider.box.common.manager.BoxManager;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SCPacketBoxReward extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private String boxId;
  
  @PacketData
  private String playerName;
  
  @PacketData
  private String serverName;
  
  @PacketData
  private String rewardName;
  
  @PacketData
  private ShopRarity rewardRarity;
  
  public SCPacketBoxReward() {}
  
  public SCPacketBoxReward(int entityId, String boxId, String playerName, String serverName, String rewardName, ShopRarity rewardRarity) {
    this.entityId = entityId;
    this.boxId = boxId;
    this.playerName = playerName;
    this.serverName = serverName;
    this.rewardName = rewardName;
    this.rewardRarity = rewardRarity;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    Optional<BoxData> optionalBox = BoxManager.getBox(this.boxId);
    if (!optionalBox.isPresent())
      return; 
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    BoxData box = optionalBox.get();
    entityClientPlayerMP.func_145747_a((IChatComponent)new ChatComponentText(""));
    entityClientPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bLe joueur §6" + this.playerName + "§r§b a gagné " + ShopRarityConstant.getChatColor(this.rewardRarity) + this.rewardName + "§b dans la box " + box.getName() + "§r§b sur le serveur §6" + this.serverName + "§r§b !"));
    entityClientPlayerMP.func_145747_a((IChatComponent)new ChatComponentText(""));
    Entity entity = ((EntityPlayer)entityClientPlayerMP).field_70170_p.func_73045_a(this.entityId);
    if (entity instanceof EntityBox) {
      EntityBox boxEntity = (EntityBox)entity;
      if (boxEntity.getClientState() != null && boxEntity.getClientState().isActive())
        return; 
    } 
    SoundRecord.create(box.getResource().getSound().getBroadcast().toString()).volume(1.5F).build().play();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\network\client\SCPacketBoxReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */