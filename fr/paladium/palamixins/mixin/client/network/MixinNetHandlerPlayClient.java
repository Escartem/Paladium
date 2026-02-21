package fr.paladium.palamixins.mixin.client.network;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NetHandlerPlayClient.class})
public class MixinNetHandlerPlayClient {
  @Shadow
  private WorldClient field_147300_g;
  
  @Inject(method = {"handleSpawnPlayer"}, at = {@At("HEAD")}, cancellable = true)
  public void onHandleSpawnPlayer(S0CPacketSpawnPlayer packet, CallbackInfo ci) {
    Entity entity = this.field_147300_g.func_73045_a(packet.func_148943_d());
    if (entity != null)
      ci.cancel(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\client\network\MixinNetHandlerPlayClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */