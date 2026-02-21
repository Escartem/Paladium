package fr.paladium.palaautomation.common.packet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaautomation.common.tile.impl.TileEntityCrafter;
import fr.paladium.palaautomation.common.util.PipeUtils;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BBCrafterChangeStatePacket extends ForgePacket {
  private static final long lastChange = 0L;
  
  @PacketData
  private int x;
  
  @PacketData
  private int y;
  
  @PacketData
  private int z;
  
  @PacketData
  private boolean enabled;
  
  public BBCrafterChangeStatePacket(int x, int y, int z, boolean enabled) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.enabled = enabled;
  }
  
  public BBCrafterChangeStatePacket() {}
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    World world = player.field_70170_p;
    if (world == null)
      return; 
    TileEntity tileEntity = world.func_147438_o(this.x, this.y, this.z);
    if (!(tileEntity instanceof TileEntityCrafter) || !PipeUtils.isUseableByPlayer(tileEntity, (EntityPlayer)player))
      return; 
    TileEntityCrafter tileEntityCrafter = (TileEntityCrafter)tileEntity;
    tileEntityCrafter.setEnabled(this.enabled);
    tileEntityCrafter.onPipeItemDataChanged();
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\packet\BBCrafterChangeStatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */