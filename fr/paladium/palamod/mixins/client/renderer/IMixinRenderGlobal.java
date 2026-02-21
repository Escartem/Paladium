package fr.paladium.palamod.mixins.client.renderer;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderGlobal.class})
public abstract class IMixinRenderGlobal {
  @Shadow
  private Minecraft field_72777_q;
  
  @Inject(method = {"playAuxSFX"}, at = {@At("HEAD")}, cancellable = true)
  private void beforePlayAuxSFX(EntityPlayer entity, int f1, int x, int y, int z, int f5, CallbackInfo ci) {
    if (f1 == 2001 && this.field_72777_q.field_71439_g.func_82165_m(PLuckyBlock.QUACK.field_76415_H)) {
      Block block = Block.func_149729_e(f5 & 0xFFF);
      if (block.func_149688_o() != Material.field_151579_a)
        this.field_72777_q.func_147118_V().func_147682_a((ISound)new PositionedSoundRecord(new ResourceLocation("palamod", "quack"), (block.field_149762_H.func_150497_c() + 1.0F) / 2.0F, block.field_149762_H.func_150494_d() * 0.8F, x + 0.5F, y + 0.5F, z + 0.5F)); 
      this.field_72777_q.field_71452_i.func_147215_a(x, y, z, block, f5 >> 12 & 0xFF);
      ci.cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\renderer\IMixinRenderGlobal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */