package fr.paladium.palawither.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palawither.api.ItemsRegister;
import fr.paladium.palawither.common.utils.WitherUtils;
import fr.paladium.palawither.common.wither.base.IWither;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;

public class ClientWitherListener {
  private static final double SCAN_RADIUS = 5.0D;
  
  @SubscribeEvent
  public void onWitherSound(PlaySoundEvent17 event) {
    if ((Minecraft.func_71410_x()).field_71439_g == null || !(event.sound instanceof PositionedSound))
      return; 
    PositionedSound sound = (PositionedSound)event.sound;
    double x = sound.func_147649_g();
    double y = sound.func_147654_h();
    double z = sound.func_147651_i();
    AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a(x - 5.0D, y - 5.0D, z - 5.0D, x + 5.0D, y + 5.0D, z + 5.0D);
    List<Object> entities = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_72872_a(Entity.class, boundingBox);
    for (Object entityObj : entities) {
      EntityLivingBase entityLivingBase;
      if (!(entityObj instanceof Entity))
        continue; 
      Entity entity = (Entity)entityObj;
      if (entity instanceof EntityFireball)
        entityLivingBase = ((EntityFireball)entity).field_70235_a; 
      if (!WitherUtils.isWither((Entity)entityLivingBase))
        continue; 
      Optional<IWither> optional = WitherUtils.getWither((Entity)entityLivingBase);
      if (!optional.isPresent())
        continue; 
      IWither wither = optional.get();
      if (!wither.hasUpgrade(ItemsRegister.FURTIVE_WITHER_UPGRADE))
        continue; 
      event.result = null;
      event.manager.field_148622_c.func_147683_b(event.sound);
    } 
  }
  
  @SubscribeEvent
  public void onWitherSound(PlaySoundAtEntityEvent event) {
    if (!WitherUtils.isWither(event.entity))
      return; 
    Optional<IWither> optional = WitherUtils.getWither(event.entity);
    if (!optional.isPresent())
      return; 
    IWither wither = optional.get();
    if (!wither.hasUpgrade(ItemsRegister.FURTIVE_WITHER_UPGRADE))
      return; 
    event.setCanceled(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\listener\ClientWitherListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */