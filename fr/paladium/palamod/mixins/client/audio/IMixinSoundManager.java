package fr.paladium.palamod.mixins.client.audio;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({SoundManager.class})
public abstract class IMixinSoundManager {
  @Inject(method = {"playSound"}, at = {@At("HEAD")}, cancellable = true)
  private void beforePlaySound(ISound sound, CallbackInfo ci) {
    if (Minecraft.func_71410_x() != null && (Minecraft.func_71410_x()).field_71439_g != null && (Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.CINEMA_MUET))
      ci.cancel(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\audio\IMixinSoundManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */