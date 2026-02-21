package fr.paladium.palamod.mixins.client.multiplayer;

import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({ServerData.class})
public class IMixinServerData {
  @Overwrite
  public ServerData.ServerResourceMode func_152586_b() {
    return ServerData.ServerResourceMode.ENABLED;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\multiplayer\IMixinServerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */