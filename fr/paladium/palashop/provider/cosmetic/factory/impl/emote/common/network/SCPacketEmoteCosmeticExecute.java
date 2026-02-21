package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto.EmoteCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.listener.EmoteCosmeticRenderPlayerListener;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.dto.EmoteCosmetic;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class SCPacketEmoteCosmeticExecute extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private String emoteId;
  
  @PacketData
  private boolean self;
  
  public SCPacketEmoteCosmeticExecute() {}
  
  public SCPacketEmoteCosmeticExecute(int entityId, String emoteId, boolean self) {
    this.entityId = entityId;
    this.emoteId = emoteId;
    this.self = self;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    Entity entity = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(this.self ? (Minecraft.func_71410_x()).field_71439_g.func_145782_y() : this.entityId);
    if (entity == null)
      return; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get(entity);
    if (cosmeticPlayer == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(EmoteCosmeticFactory.ID);
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    EmoteCosmeticRenderPlayerListener.willPlay = true;
    String animationName = null;
    EmoteCosmeticClient emote = null;
    for (ICosmetic cosmetic : ((CosmeticPlayer.EquippedCosmetic)optionalEquippedCosmetic.get()).getCosmetics()) {
      if (cosmetic instanceof EmoteCosmeticClient) {
        EmoteCosmeticClient cosmeticEmote = (EmoteCosmeticClient)cosmetic;
        if (cosmeticEmote.isLoaded())
          if (!cosmeticEmote.getId().equals(this.emoteId)) {
            LindwormAnimatable animatable = (LindwormAnimatable)cosmeticEmote.getEmoteModel().getAnimatable(entity);
            if (animatable != null)
              animatable.stopAnimation(); 
            if (cosmeticEmote.hasEffectModel() && cosmeticEmote.getEffectModel().isAnimated()) {
              LindwormAnimatable effectAnimatable = (LindwormAnimatable)cosmeticEmote.getEffectModel().getAnimatable(entity);
              if (effectAnimatable != null)
                effectAnimatable.stopAnimation(); 
            } 
          } else if (emote == null) {
            String firstAnimation = cosmeticEmote.getEmoteModel().getAnimationFile().getFirstAnimation();
            if (firstAnimation != null) {
              animationName = firstAnimation;
              emote = cosmeticEmote;
            } 
          }  
      } 
    } 
    if (emote == null) {
      EmoteCosmeticRenderPlayerListener.willPlay = false;
      return;
    } 
    ((LindwormAnimatable)emote.getEmoteModel().getOrCreateAnimatable(entity)).playAnimation(animationName, ((EmoteCosmetic.EmoteCosmeticProperties)emote.getProperties()).isLoop());
    if (emote.hasEffectModel() && emote.getEffectModel().isAnimated()) {
      String effectAnimationName = emote.getEffectModel().getAnimationFile().getFirstAnimation();
      if (effectAnimationName != null)
        ((LindwormAnimatable)emote.getEffectModel().getOrCreateAnimatable(entity)).playAnimation(effectAnimationName, ((EmoteCosmetic.EmoteCosmeticProperties)emote.getProperties()).isLoop()); 
    } 
    if (this.self)
      (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 1; 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\common\network\SCPacketEmoteCosmeticExecute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */