package fr.paladium.palaforgeutils.lib.extended.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SCSyncPalaForgeUtilsExtendedEntityProperties implements IMessage {
  private UUID target;
  
  private String propName;
  
  private NBTTagCompound nbt;
  
  public SCSyncPalaForgeUtilsExtendedEntityProperties() {}
  
  public SCSyncPalaForgeUtilsExtendedEntityProperties(UUID target, String propName, NBTTagCompound nbt) {
    this.target = target;
    this.propName = propName;
    this.nbt = nbt;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.target = UUIDUtils.from(PacketSerialUtils.readString(buf));
    this.propName = PacketSerialUtils.readString(buf);
    this.nbt = PacketSerialUtils.readNbt(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    PacketSerialUtils.writeString(buf, UUIDUtils.toString(this.target));
    PacketSerialUtils.writeString(buf, this.propName);
    PacketSerialUtils.writeNbt(buf, this.nbt);
  }
  
  public static class Handler implements IMessageHandler<SCSyncPalaForgeUtilsExtendedEntityProperties, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCSyncPalaForgeUtilsExtendedEntityProperties message, MessageContext ctx) {
      if (message.propName == null || message.target == null || message.nbt == null)
        return null; 
      EntityPlayer targettedPlayer = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_152378_a(message.target);
      if (targettedPlayer == null)
        return null; 
      ExtendedEntityProperties eep = (ExtendedEntityProperties)targettedPlayer.getExtendedProperties(message.propName);
      if (eep == null) {
        System.out.println("ExtendedEntityProperties not found for " + message.propName + " with target " + message.target);
        return null;
      } 
      eep.loadNBTData(message.nbt);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\extended\network\SCSyncPalaForgeUtilsExtendedEntityProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */