package fr.paladium.palawither.common.network.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palawither.client.render.entity.RenderSupremeWither;
import fr.paladium.palawither.common.entity.targetable.EntitySupremeWither;
import fr.paladium.translate.common.texttotranslate.TTT;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class SCPacketSupremeWitherExplode extends ForgePacket {
  @PacketData
  private String name;
  
  @PacketData
  private int entityId;
  
  public SCPacketSupremeWitherExplode() {}
  
  public SCPacketSupremeWitherExplode(String name, int entityId) {
    this.name = name;
    this.entityId = entityId;
  }
  
  public SCPacketSupremeWitherExplode(@NonNull EntitySupremeWither wither) {
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    this.name = wither.getDisplayName();
    this.entityId = wither.func_145782_y();
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    entityClientPlayerMP.func_146105_b((IChatComponent)new ChatComponentText(""));
    entityClientPlayerMP.func_146105_b((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §8Le §4§l" + TTT.format(I18n.func_135052_a(this.name, new Object[0]), new Object[0]).toUpperCase() + " §8vient §8d'§4§lEXPLOSER §8sur §8le §8serveur §8emportant §8tout §8sur §8son §8passage §8!"));
    entityClientPlayerMP.func_146105_b((IChatComponent)new ChatComponentText(""));
    SoundUtils.EXPLODE.playClientSound(((EntityPlayer)entityClientPlayerMP).field_70165_t, ((EntityPlayer)entityClientPlayerMP).field_70163_u, ((EntityPlayer)entityClientPlayerMP).field_70161_v, 1.0F, 1.0F);
    SoundRecord.create((ResourceLocation)MCResource.of("palawither", "sounds/entities/supreme_wither/spawn.ogg")).category(SoundCategory.MOBS).volume(10.0F).play();
    for (Object entity : ((EntityPlayer)entityClientPlayerMP).field_70170_p.field_72996_f) {
      if (!(entity instanceof EntitySupremeWither))
        continue; 
      EntitySupremeWither wither = (EntitySupremeWither)entity;
      if (wither.func_145782_y() != this.entityId)
        continue; 
      RenderSupremeWither render = (RenderSupremeWither)RenderManager.field_78727_a.func_78713_a((Entity)wither);
      if (render == null || render.getModel() == null)
        continue; 
      LindwormAnimatable witherAnimatable = (LindwormAnimatable)render.getModel().getAnimatable((Entity)wither);
      if (witherAnimatable == null)
        continue; 
      witherAnimatable.forceAnimation("death", false);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\network\entity\SCPacketSupremeWitherExplode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */