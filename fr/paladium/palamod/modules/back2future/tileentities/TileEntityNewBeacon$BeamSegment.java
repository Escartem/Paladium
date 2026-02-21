package fr.paladium.palamod.modules.back2future.tileentities;

public class BeamSegment {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\tileentities\TileEntityNewBeacon$BeamSegment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */