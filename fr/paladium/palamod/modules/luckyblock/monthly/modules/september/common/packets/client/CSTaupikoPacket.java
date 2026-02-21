package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityTaupiko;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSTaupikoPacket implements IMessage {
  private static final double MAX_PACKET_DISTANCE = 5.0D;
  
  private UUID entityUniqueId;
  
  private int value;
  
  public CSTaupikoPacket() {
    this.entityUniqueId = UUID.randomUUID();
    this.value = -1;
  }
  
  public CSTaupikoPacket(UUID entityUniqueId, int value) {
    this.entityUniqueId = entityUniqueId;
    this.value = value;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.entityUniqueId = FastUUID.parseUUID(ByteBufUtils.readUTF8String(buf));
    this.value = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, FastUUID.toString(this.entityUniqueId));
    buf.writeInt(this.value);
  }
  
  public static class Handler implements IMessageHandler<CSTaupikoPacket, IMessage> {
    public IMessage onMessage(CSTaupikoPacket message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT)
        return null; 
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      List<EntityTaupiko> entities = player.field_70170_p.func_72872_a(EntityTaupiko.class, player.field_70121_D
          .func_72314_b(5.0D, 5.0D, 5.0D));
      Iterator<EntityTaupiko> iterator = entities.iterator();
      if (iterator.hasNext()) {
        EntityTaupiko entity = iterator.next();
        entity.handlePacket(player, message.value);
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\client\CSTaupikoPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */