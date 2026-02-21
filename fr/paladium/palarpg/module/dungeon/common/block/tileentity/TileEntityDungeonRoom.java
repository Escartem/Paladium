package fr.paladium.palarpg.module.dungeon.common.block.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.zephyrui.lib.utils.pair.Pair;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDungeonRoom extends TileEntity {
  public void setDirection(ForgeDirection direction) {
    this.direction = direction;
  }
  
  public void setRoom(DungeonRoom room) {
    this.room = room;
  }
  
  public ForgeDirection getDirection() {
    return this.direction;
  }
  
  public DungeonRoom getRoom() {
    return this.room;
  }
  
  private ForgeDirection direction = ForgeDirection.NORTH;
  
  private transient DungeonRoom room;
  
  public boolean canUpdate() {
    return false;
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    WorldServer worldServer;
    super.func_145839_a(nbt);
    this.direction = ForgeDirection.getOrientation(nbt.func_74762_e("direction"));
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT || !nbt.func_74764_b("worldName") || !nbt.func_74764_b("roomIndex"))
      return; 
    World world = null;
    String worldName = nbt.func_74779_i("worldName");
    for (WorldServer worldServer1 : (MinecraftServer.func_71276_C()).field_71305_c) {
      if (worldServer1.func_72912_H().func_76065_j().equals(worldName)) {
        worldServer = worldServer1;
        break;
      } 
    } 
    if (worldServer == null)
      return; 
    Optional<DungeonWorld> optionalWorld = DungeonWorld.get((World)worldServer);
    if (!optionalWorld.isPresent())
      return; 
    int roomIndex = nbt.func_74762_e("roomIndex");
    if (roomIndex < 0 || roomIndex >= ((DungeonWorld)optionalWorld.get()).getRooms().size())
      return; 
    this.room = ((DungeonWorld)optionalWorld.get()).getRooms().get(roomIndex);
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74768_a("direction", this.direction.ordinal());
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return; 
    if (this.room != null) {
      nbt.func_74778_a("worldName", this.room.getWorld().getWorld().func_72912_H().func_76065_j());
      nbt.func_74768_a("roomIndex", this.room.getWorld().getRooms().indexOf(this.room));
    } 
  }
  
  public Packet func_145844_m() {
    NBTTagCompound compound = new NBTTagCompound();
    func_145841_b(compound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public AxisAlignedBB getRenderBoundingBox() {
    Pair<Integer> application = getApplicationY();
    return AxisAlignedBB.func_72330_a(this.field_145851_c, ((Integer)application.getFirst()).intValue(), this.field_145849_e, (this.field_145851_c + 1), ((Integer)application.getSecond()).intValue(), (this.field_145849_e + 1));
  }
  
  @NonNull
  public TileEntityDungeonRoom incrementDirection() {
    switch (this.direction) {
      case NORTH:
        this.direction = ForgeDirection.EAST;
        return this;
      case EAST:
        this.direction = ForgeDirection.SOUTH;
        return this;
      case SOUTH:
        this.direction = ForgeDirection.WEST;
        return this;
      case WEST:
        this.direction = ForgeDirection.NORTH;
        return this;
    } 
    this.direction = ForgeDirection.NORTH;
    return this;
  }
  
  public float getRotation() {
    switch (this.direction) {
      case NORTH:
        return 180.0F;
      case EAST:
        return 270.0F;
      case SOUTH:
        return 0.0F;
      case WEST:
        return 90.0F;
    } 
    return 0.0F;
  }
  
  @NonNull
  public Pair<Integer> getApplicationY() {
    Block upperBlock = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
    if (upperBlock == Blocks.field_150350_a || upperBlock == func_145838_q())
      return new Pair(Integer.valueOf(this.field_145848_d), Integer.valueOf(this.field_145848_d)); 
    int minY = -1;
    int maxY = -1;
    for (int i = 0; i < 10; i++) {
      if (minY < 0) {
        if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + i, this.field_145849_e))
          minY = this.field_145848_d + i; 
      } else if (!this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + i, this.field_145849_e)) {
        maxY = this.field_145848_d + i - 1;
        break;
      } 
    } 
    if (maxY < 0)
      maxY = this.field_145850_b.func_72800_K() - minY; 
    return new Pair(Integer.valueOf(minY), Integer.valueOf(maxY));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\block\tileentity\TileEntityDungeonRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */