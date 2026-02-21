package fr.paladium.palamod.modules.miner.dimminer.common.block;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.miner.dimminer.common.block.tileentity.TileEntityWitheredObsidian;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class Size {
  private final World world;
  
  private final int field_150865_b;
  
  private final int field_150866_c;
  
  private final int field_150863_d;
  
  private int field_150864_e = 0;
  
  private ChunkCoordinates chunkCoords;
  
  private int field_150862_g;
  
  private int field_150868_h;
  
  public Size(World world, int x, int y, int z, int p_i45415_5_) {
    this.world = world;
    this.field_150865_b = p_i45415_5_;
    this.field_150863_d = BlockMinerPortal.field_150001_a[p_i45415_5_][0];
    this.field_150866_c = BlockMinerPortal.field_150001_a[p_i45415_5_][1];
    for (int i1 = y; y > i1 - 21 && y > 0 && func_150857_a(world.func_147439_a(x, y - 1, z)); y--);
    int j1 = func_150853_a(x, y, z, this.field_150863_d) - 1;
    if (j1 >= 0) {
      this.chunkCoords = new ChunkCoordinates(x + j1 * Direction.field_71583_a[this.field_150863_d], y, z + j1 * Direction.field_71581_b[this.field_150863_d]);
      this.field_150868_h = func_150853_a(this.chunkCoords.field_71574_a, this.chunkCoords.field_71572_b, this.chunkCoords.field_71573_c, this.field_150866_c);
      if (this.field_150868_h < 2 || this.field_150868_h > 21) {
        this.chunkCoords = null;
        this.field_150868_h = 0;
      } 
    } 
    if (this.chunkCoords != null)
      this.field_150862_g = func_150858_a(); 
  }
  
  protected int func_150853_a(int p_150853_1_, int p_150853_2_, int p_150853_3_, int p_150853_4_) {
    int j1 = Direction.field_71583_a[p_150853_4_];
    int k1 = Direction.field_71581_b[p_150853_4_];
    int i1;
    for (i1 = 0; i1 < 22; i1++) {
      Block block = this.world.func_147439_a(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
      if (!func_150857_a(block))
        break; 
      TileEntity tileEntity = this.world.func_147438_o(p_150853_1_ + j1 * i1, p_150853_2_ - 1, p_150853_3_ + k1 * i1);
      if (!(tileEntity instanceof TileEntityWitheredObsidian) || (tileEntity instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)tileEntity).getFeed() < 10))
        break; 
    } 
    TileEntity te = this.world.func_147438_o(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
    return (te instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)te).getFeed() >= 10) ? i1 : 0;
  }
  
  protected int func_150858_a() {
    label48: for (this.field_150862_g = 0; this.field_150862_g < 21; this.field_150862_g++) {
      int k = this.chunkCoords.field_71572_b + this.field_150862_g;
      for (int j = 0; j < this.field_150868_h; j++) {
        int m = this.chunkCoords.field_71574_a + j * Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][1]];
        int l = this.chunkCoords.field_71573_c + j * Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][1]];
        Block block = this.world.func_147439_a(m, k, l);
        if (!func_150857_a(block))
          break label48; 
        if (block == BlocksRegister.MINER_PORTAL)
          this.field_150864_e++; 
        if (j == 0) {
          TileEntity te = this.world.func_147438_o(m + Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][0]], k, l + Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][0]]);
          if (te instanceof TileEntityWitheredObsidian) {
            if (te instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)te).getFeed() < 10)
              break label48; 
          } else {
            break label48;
          } 
        } else if (j == this.field_150868_h - 1) {
          TileEntity te = this.world.func_147438_o(m + Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][1]], k, l + Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][1]]);
          if (te instanceof TileEntityWitheredObsidian) {
            if (te instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)te).getFeed() < 10)
              break label48; 
          } else {
            break label48;
          } 
        } 
      } 
    } 
    for (int i = 0; i < this.field_150868_h; i++) {
      int j = this.chunkCoords.field_71574_a + i * Direction.field_71583_a[BlockPortal.field_150001_a[this.field_150865_b][1]];
      int k = this.chunkCoords.field_71572_b + this.field_150862_g;
      int l = this.chunkCoords.field_71573_c + i * Direction.field_71581_b[BlockPortal.field_150001_a[this.field_150865_b][1]];
      TileEntity te = this.world.func_147438_o(j, k, l);
      if (!(te instanceof TileEntityWitheredObsidian) || (te instanceof TileEntityWitheredObsidian && ((TileEntityWitheredObsidian)te).getFeed() < 10)) {
        this.field_150862_g = 0;
        break;
      } 
    } 
    if (this.field_150862_g <= 21 && this.field_150862_g >= 3)
      return this.field_150862_g; 
    this.chunkCoords = null;
    this.field_150868_h = 0;
    this.field_150862_g = 0;
    return 0;
  }
  
  protected boolean func_150857_a(Block block) {
    return (block.func_149688_o() == Material.field_151579_a || block == Blocks.field_150480_ab || block == BlocksRegister.MINER_PORTAL);
  }
  
  public boolean func_150860_b() {
    return (this.chunkCoords != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21);
  }
  
  public void func_150859_c() {
    for (int i = 0; i < this.field_150868_h; i++) {
      int j = this.chunkCoords.field_71574_a + Direction.field_71583_a[this.field_150866_c] * i;
      int k = this.chunkCoords.field_71573_c + Direction.field_71581_b[this.field_150866_c] * i;
      for (int l = 0; l < this.field_150862_g; l++) {
        int i1 = this.chunkCoords.field_71572_b + l;
        this.world.func_147465_d(j, i1, k, (Block)BlocksRegister.MINER_PORTAL, this.field_150865_b, 2);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\block\BlockMinerPortal$Size.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */