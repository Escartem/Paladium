package fr.paladium.palamod.mixins.client.multiplayer;

import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.world.WorldSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({PlayerControllerMP.class})
public class IMixinPlayerControllerMP {
  @Shadow
  private WorldSettings.GameType field_78779_k;
  
  @Overwrite
  public float func_78757_d() {
    return (this.field_78779_k.func_77145_d() ? 5.0F : 4.5F) + ClientProxy.playerAddedReachDistance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\multiplayer\IMixinPlayerControllerMP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */