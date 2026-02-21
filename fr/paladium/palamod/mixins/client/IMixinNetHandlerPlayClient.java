package fr.paladium.palamod.mixins.client;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NetHandlerPlayClient.class})
public abstract class IMixinNetHandlerPlayClient {
  @Shadow
  @Final
  private NetworkManager field_147302_e;
  
  @Inject(method = {"addToSendQueue"}, at = {@At("HEAD")}, cancellable = true)
  public void addSendToQueue(Packet packet, CallbackInfo info) {
    if ((Minecraft.func_71410_x()).field_71439_g != null && (Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.LAG)) {
      info.cancel();
      (new Thread(() -> {
            try {
              Thread.sleep(((new Random()).nextInt(500) + 500));
              this.field_147302_e.func_150725_a(packet, new io.netty.util.concurrent.GenericFutureListener[0]);
            } catch (Exception e) {
              e.printStackTrace();
            } 
          })).start();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\IMixinNetHandlerPlayClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */