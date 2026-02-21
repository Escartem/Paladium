package fr.paladium.palamixins.mixin.common.fml;

import cpw.mods.fml.common.network.handshake.NetworkDispatcher;
import fr.paladium.palamixins.accessor.common.fml.NetworkDispatcherAccessor;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({NetworkDispatcher.class})
public class MixinNetworkDispatcher implements NetworkDispatcherAccessor {
  @Shadow
  private EntityPlayerMP player;
  
  public EntityPlayerMP getPlayer() {
    return this.player;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\fml\MixinNetworkDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */