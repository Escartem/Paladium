package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.paladium.client.manager.AncientHammerEffectClientCamManager;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.manager.AncientHammerEffectCamManager;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.settings.KeyBinding;

@Packet(side = Side.CLIENT)
public class SCPacketAncientHammerEffectCam implements IMessage {
  private AncientHammerEffectCamManager.AncientHammerEffectCam cam;
  
  public SCPacketAncientHammerEffectCam() {}
  
  public SCPacketAncientHammerEffectCam(AncientHammerEffectCamManager.AncientHammerEffectCam cam) {
    this.cam = cam;
  }
  
  public void fromBytes(ByteBuf buf) {
    if (!buf.isReadable())
      return; 
    this.cam = AncientHammerEffectCamManager.AncientHammerEffectCam.of(new DoubleLocation(buf.readDouble(), buf.readDouble(), buf.readDouble()));
    this.cam.setStart(buf.readLong());
  }
  
  public void toBytes(ByteBuf buf) {
    if (this.cam == null)
      return; 
    buf.writeDouble(this.cam.getLocation().getX());
    buf.writeDouble(this.cam.getLocation().getY());
    buf.writeDouble(this.cam.getLocation().getZ());
    buf.writeLong(this.cam.getStart());
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCPacketAncientHammerEffectCam, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketAncientHammerEffectCam message, MessageContext ctx) {
      KeyBinding.func_74506_a();
      AncientHammerEffectClientCamManager.inst().setCam(message.cam);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\network\SCPacketAncientHammerEffectCam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */