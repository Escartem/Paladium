package fr.paladium.pet.common.network.packet.pet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSide;
import fr.paladium.pet.client.ui.utils.data.ConfigClientData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.server.config.global.GlobalConfig;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBUpdateClientConfigPacket extends ForgePacket {
  @PacketData(PacketSide.SERVER)
  private ConfigClientData data;
  
  public BBUpdateClientConfigPacket() {}
  
  public BBUpdateClientConfigPacket(GlobalConfig config) {
    this.data = ConfigClientData.of(config);
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new BBUpdateClientConfigPacket(GlobalConfig.get()), player);
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    ConfigClientData.set(this.data);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\pet\BBUpdateClientConfigPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */