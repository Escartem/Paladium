package fr.paladium.palaautomation.common.packet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.util.PipeUtils;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class CSPipeMachineUpdatePacket extends ForgePacket {
  @PacketData
  private double x;
  
  @PacketData
  private double y;
  
  @PacketData
  private double z;
  
  public CSPipeMachineUpdatePacket() {}
  
  public CSPipeMachineUpdatePacket(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  @SideOnly(Side.CLIENT)
  public static void send(IPipeMachine pipeMachine) {
    if (pipeMachine == null)
      return; 
    TileEntity tile = (TileEntity)pipeMachine;
    CSPipeMachineUpdatePacket packet = new CSPipeMachineUpdatePacket(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    packet.send();
  }
  
  public void processServer(EntityPlayerMP player) {
    if (!PipeUtils.isUseableByPlayer(this.x, this.y, this.z, (EntityPlayer)player))
      return; 
    TileEntity tile = player.field_70170_p.func_147438_o(MathHelper.func_76128_c(this.x), MathHelper.func_76128_c(this.y), MathHelper.func_76128_c(this.z));
    if (!(tile instanceof IPipeMachine))
      return; 
    IPipeMachine pipeMachine = (IPipeMachine)tile;
    pipeMachine.sendUpdatePacket(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\packet\CSPipeMachineUpdatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */