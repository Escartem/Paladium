package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.entity.EntityFakePlayer;
import fr.paladium.palamod.modules.pillage.PPillage;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class PacketBindSkin implements IMessage {
  int id;
  
  public PacketBindSkin() {}
  
  public PacketBindSkin(EntityFakePlayer fakePlayer) {
    this.id = fakePlayer.func_145782_y();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.id);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.id = buf.readInt();
  }
  
  public static class ClientHandler implements IMessageHandler<PacketBindSkin, IMessage> {
    public IMessage onMessage(PacketBindSkin message, MessageContext ctx) {
      Entity entity = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(message.id);
      if (entity instanceof EntityFakePlayer) {
        EntityFakePlayer fakePlayer = (EntityFakePlayer)entity;
        ResourceLocation resourceLocation = new ResourceLocation(fakePlayer.getCloneSkin());
        resourceLocation = PPillage.proxy.bindSkin(fakePlayer.getCloneUUID().toString());
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketBindSkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */