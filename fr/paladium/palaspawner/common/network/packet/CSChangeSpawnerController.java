package fr.paladium.palaspawner.common.network.packet;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaspawner.common.spawner.ControllerState;
import fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CSChangeSpawnerController extends ForgePacket {
  @PacketData
  private int x;
  
  @PacketData
  private int y;
  
  @PacketData
  private int z;
  
  @PacketData
  private ControllerState state;
  
  public CSChangeSpawnerController(int x, int y, int z, ControllerState state) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.state = state;
  }
  
  public CSChangeSpawnerController() {}
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public ControllerState getState() {
    return this.state;
  }
  
  public void processServer(EntityPlayerMP player) {
    World world = player.field_70170_p;
    if (this.state == null || !world.func_72899_e(this.x, this.y, this.z))
      return; 
    if (getDistanceBetween(player.field_70165_t, player.field_70163_u, player.field_70161_v, this.x, this.y, this.z) > 5.0D)
      return; 
    TileEntity tileEntity = world.func_147438_o(this.x, this.y, this.z);
    if (!(tileEntity instanceof TileEntitySpawnerController))
      return; 
    TileEntitySpawnerController controller = (TileEntitySpawnerController)tileEntity;
    controller.setState(this.state);
  }
  
  private double getDistanceBetween(double x1, double y1, double z1, double x2, double y2, double z2) {
    double d0 = x1 - x2;
    double d1 = y1 - y2;
    double d2 = z1 - z2;
    return MathHelper.func_76133_a(d0 * d0 + d1 * d1 + d2 * d2);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\network\packet\CSChangeSpawnerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */