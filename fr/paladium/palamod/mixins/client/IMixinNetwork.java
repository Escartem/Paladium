package fr.paladium.palamod.mixins.client;

import fr.paladium.palamod.modules.argus.packets.PacketAnalyzer;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NetworkManager.class})
public abstract class IMixinNetwork {
  @Inject(method = {"dispatchPacket"}, at = {@At("HEAD")})
  private void onDispatchPacket(Packet p_150732_1_, GenericFutureListener[] p_150732_2_, CallbackInfo ci) {
    PacketAnalyzer.a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\IMixinNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */