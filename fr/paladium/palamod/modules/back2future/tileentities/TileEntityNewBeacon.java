package fr.paladium.palamod.modules.back2future.tileentities;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityNewBeacon extends TileEntityBeacon {
  private final List<BeamSegment> segments = Lists.newArrayList();
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    int height = (this.field_145850_b == null) ? 256 : this.field_145850_b.func_72940_L();
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), height, (this.field_145849_e + 1));
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K && this.field_145850_b.func_82737_E() % 80L == 0L)
      updateSegments(); 
  }
  
  private void updateSegments() {
    int x = this.field_145851_c;
    int y = this.field_145848_d;
    int z = this.field_145849_e;
    this.segments.clear();
    BeamSegment beamsegment = new BeamSegment(EntitySheep.field_70898_d[0]);
    this.segments.add(beamsegment);
    boolean flag = true;
    int i = y + 1;
    while (true) {
      float[] colours;
      if (i < this.field_145850_b.func_72940_L()) {
        Block iblockstate = this.field_145850_b.func_147439_a(x, i, z);
        if (iblockstate == Blocks.field_150399_cn) {
          colours = EntitySheep.field_70898_d[this.field_145850_b.func_72805_g(x, i, z)];
        } else {
          if (iblockstate != Blocks.field_150397_co) {
            if (iblockstate.func_149717_k() >= 15) {
              this.segments.clear();
              break;
            } 
            beamsegment.func_177262_a();
          } else {
            colours = EntitySheep.field_70898_d[this.field_145850_b.func_72805_g(x, i, z)];
            if (!flag)
              colours = new float[] { (beamsegment.func_177263_b()[0] + colours[0]) / 2.0F, (beamsegment.func_177263_b()[1] + colours[1]) / 2.0F, (beamsegment.func_177263_b()[2] + colours[2]) / 2.0F }; 
          } 
          i++;
        } 
      } else {
        break;
      } 
      if (!flag)
        colours = new float[] { (beamsegment.func_177263_b()[0] + colours[0]) / 2.0F, (beamsegment.func_177263_b()[1] + colours[1]) / 2.0F, (beamsegment.func_177263_b()[2] + colours[2]) / 2.0F }; 
    } 
  }
  
  public List<BeamSegment> getSegments() {
    return this.segments;
  }
  
  public static class BeamSegment {
    private final float[] colours;
    
    private int field_177265_b;
    
    public BeamSegment(float[] colours) {
      this.colours = colours;
      this.field_177265_b = 1;
    }
    
    protected void func_177262_a() {
      this.field_177265_b++;
    }
    
    public float[] func_177263_b() {
      return this.colours;
    }
    
    public int func_177264_c() {
      return this.field_177265_b;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\tileentities\TileEntityNewBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */