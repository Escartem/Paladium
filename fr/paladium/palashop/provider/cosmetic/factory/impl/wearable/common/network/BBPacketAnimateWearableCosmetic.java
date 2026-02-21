package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.WearableCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class BBPacketAnimateWearableCosmetic extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private String cosmeticId;
  
  @PacketData
  private String animationName;
  
  @PacketData
  private boolean self;
  
  public BBPacketAnimateWearableCosmetic() {}
  
  public BBPacketAnimateWearableCosmetic(int entityId, String cosmeticId, String animationName, boolean self) {
    this.entityId = entityId;
    this.cosmeticId = cosmeticId;
    this.animationName = animationName;
    this.self = self;
  }
  
  @SideOnly(Side.CLIENT)
  public BBPacketAnimateWearableCosmetic(@NonNull String cosmeticId, @NonNull String animationName) {
    if (cosmeticId == null)
      throw new NullPointerException("cosmeticId is marked non-null but is null"); 
    if (animationName == null)
      throw new NullPointerException("animationName is marked non-null but is null"); 
    this.entityId = -1;
    this.cosmeticId = cosmeticId;
    this.animationName = animationName;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    Entity entity = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(this.self ? (Minecraft.func_71410_x()).field_71439_g.func_145782_y() : this.entityId);
    if (entity == null)
      return; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get(entity);
    if (cosmeticPlayer == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(WearableCosmeticFactory.ID);
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    for (ICosmetic cosmetic : ((CosmeticPlayer.EquippedCosmetic)optionalEquippedCosmetic.get()).getCosmetics()) {
      if (cosmetic instanceof WearableCosmeticClient) {
        WearableCosmeticClient wearableCosmetic = (WearableCosmeticClient)cosmetic;
        if (wearableCosmetic.isLoaded() && wearableCosmetic.getId().equals(this.cosmeticId)) {
          ((LindwormAnimatable)wearableCosmetic.getModel().getOrCreateAnimatable(entity)).playAnimation(this.animationName, false);
          break;
        } 
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    if (cosmeticPlayer == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(WearableCosmeticFactory.ID);
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    for (ICosmetic cosmetic : ((CosmeticPlayer.EquippedCosmetic)optionalEquippedCosmetic.get()).getCosmetics()) {
      if (cosmetic instanceof WearableCosmetic) {
        WearableCosmetic wearableCosmetic = (WearableCosmetic)cosmetic;
        if (wearableCosmetic.getId().equals(this.cosmeticId)) {
          EntityTracker tracker = ((WorldServer)player.field_70170_p).func_73039_n();
          for (EntityPlayer entityPlayer : tracker.getTrackingPlayers((Entity)player)) {
            if (!(entityPlayer instanceof EntityPlayerMP))
              continue; 
            (new BBPacketAnimateWearableCosmetic(player.func_145782_y(), this.cosmeticId, this.animationName, false)).send((EntityPlayerMP)entityPlayer);
          } 
          (new BBPacketAnimateWearableCosmetic(player.func_145782_y(), this.cosmeticId, this.animationName, true)).send(player);
          break;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\common\network\BBPacketAnimateWearableCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */