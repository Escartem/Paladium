package fr.paladium.palamod.modules.luckyblock.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class TileEntityFakeCommandBlock extends TileEntity {
  private final CommandBlockLogic logic = new CommandBlockLogic() {
      public ChunkCoordinates func_82114_b() {
        return new ChunkCoordinates(TileEntityFakeCommandBlock.this.field_145851_c, TileEntityFakeCommandBlock.this.field_145848_d, TileEntityFakeCommandBlock.this.field_145849_e);
      }
      
      public World func_130014_f_() {
        return TileEntityFakeCommandBlock.this.func_145831_w();
      }
      
      public void setCommand(String command) {
        func_145752_a(command);
        TileEntityFakeCommandBlock.this.func_70296_d();
      }
      
      public void func_145756_e() {
        TileEntityFakeCommandBlock.this.func_145831_w().func_147471_g(TileEntityFakeCommandBlock.this.field_145851_c, TileEntityFakeCommandBlock.this.field_145848_d, TileEntityFakeCommandBlock.this.field_145849_e);
      }
      
      @SideOnly(Side.CLIENT)
      public int func_145751_f() {
        return 0;
      }
      
      @SideOnly(Side.CLIENT)
      public void func_145757_a(ByteBuf p_145757_1_) {
        p_145757_1_.writeInt(TileEntityFakeCommandBlock.this.field_145851_c);
        p_145757_1_.writeInt(TileEntityFakeCommandBlock.this.field_145848_d);
        p_145757_1_.writeInt(TileEntityFakeCommandBlock.this.field_145849_e);
      }
      
      public void func_145755_a(World world) {
        if (world.field_72995_K)
          return; 
      }
    };
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74778_a("Command", "give @p granite 1");
    compound.func_74768_a("SuccessCount", 0);
    compound.func_74778_a("CustomName", "CommandBlock");
    super.func_145841_b(compound);
  }
  
  public void func_145845_h() {
    super.func_145845_h();
  }
  
  public boolean canUpdate() {
    return true;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    func_145841_b(dataTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    func_145839_a(nbtData);
  }
  
  public CommandBlockLogic getLogic() {
    return this.logic;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityFakeCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */