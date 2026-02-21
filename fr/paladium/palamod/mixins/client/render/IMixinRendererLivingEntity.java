package fr.paladium.palamod.mixins.client.render;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({RendererLivingEntity.class})
public abstract class IMixinRendererLivingEntity extends Render {
  public void func_110777_b(Entity entity) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer p = (EntityPlayer)entity;
      if (p.func_70644_a((Potion)PLuckyBlock.BAD_FACE)) {
        GL11.glPushMatrix();
        GL11.glColor4f(0.0F, 2.0F, 0.0F, 1.0F);
        func_110776_a(func_110775_a(entity));
        GL11.glPopMatrix();
      } else {
        func_110776_a(func_110775_a(entity));
      } 
    } else {
      func_110776_a(func_110775_a(entity));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\render\IMixinRendererLivingEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */