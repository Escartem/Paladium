package fr.paladium.palamod.modules.nemesis.module.base.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayerMP;

public abstract class NemesisPacket {
  public void read(ByteBuf buf) {}
  
  public void write(ByteBuf buf) {}
  
  @SideOnly(Side.CLIENT)
  public void processClient() {}
  
  @SideOnly(Side.SERVER)
  public void processServer(@NonNull EntityPlayerMP player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\base\network\NemesisPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */