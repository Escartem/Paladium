package fr.paladium.palamod.modules.luckyblock.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

class null extends CommandBlockLogic {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityFakeCommandBlock$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */