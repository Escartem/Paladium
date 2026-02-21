package fr.paladium.palamod.mixins.world;

import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityWirelessLever;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({World.class})
public abstract class IMixinWirelessLever {
  @Shadow
  public WorldProvider field_73011_w;
  
  @Inject(method = {"getBlockPowerInput"}, at = {@At("HEAD")}, cancellable = true)
  public void checkWirelessRedstone(int p_94577_1_, int p_94577_2_, int p_94577_3_, CallbackInfoReturnable<Integer> ci) {
    try {
      if (this.field_73011_w == null || MinecraftServer.func_71276_C() == null)
        return; 
      WorldServer worldServer = MinecraftServer.func_71276_C().func_71218_a(this.field_73011_w.field_76574_g);
      if (worldServer == null || ((World)worldServer).field_147482_g == null)
        return; 
      for (Object obj : ((World)worldServer).field_147482_g) {
        if (obj instanceof TileEntityWirelessLever) {
          TileEntityWirelessLever te = (TileEntityWirelessLever)obj;
          if (te.getX() == p_94577_1_ && te.getY() == p_94577_2_ && te.getZ() == p_94577_3_ && te
            .isActive()) {
            ci.setReturnValue(Integer.valueOf(15));
            ci.cancel();
          } 
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\world\IMixinWirelessLever.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */