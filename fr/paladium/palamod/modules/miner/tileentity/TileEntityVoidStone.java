package fr.paladium.palamod.modules.miner.tileentity;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.miner.dimminer.common.block.BlockWitheredReinforcedPiston;
import fr.paladium.tutorial.common.event.VoidStoneGenerateEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityVoidStone extends TileEntity {
  private int stone;
  
  private int dropped;
  
  public int getStone() {
    return this.stone;
  }
  
  public void setStone(int stone) {
    this.stone = stone;
  }
  
  public int getDropped() {
    return this.dropped;
  }
  
  public void setDropped(int dropped) {
    this.dropped = dropped;
  }
  
  public void func_145845_h() {
    if (this.field_145850_b.field_72995_K)
      return; 
    check(1, 0);
    check(0, 1);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    this.stone = compound.func_74762_e("stone");
    this.dropped = compound.func_74762_e("dropped");
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74768_a("stone", this.stone);
    compound.func_74768_a("dropped", this.dropped);
    super.func_145841_b(compound);
  }
  
  public void check(int ox, int oz) {
    if (this.field_145850_b.func_147439_a(this.field_145851_c - ox, this.field_145848_d, this.field_145849_e - oz) == BlocksRegister.WITHERED_REINFORCED_PISTON)
      if (this.field_145850_b.func_147439_a(this.field_145851_c + ox, this.field_145848_d, this.field_145849_e + oz) == BlocksRegister.WITHERED_REINFORCED_PISTON) {
        BlockWitheredReinforcedPiston piston1 = (BlockWitheredReinforcedPiston)this.field_145850_b.func_147439_a(this.field_145851_c - ox, this.field_145848_d, this.field_145849_e - oz);
        BlockWitheredReinforcedPiston piston2 = (BlockWitheredReinforcedPiston)this.field_145850_b.func_147439_a(this.field_145851_c + ox, this.field_145848_d, this.field_145849_e + oz);
        if (piston1.isIndirectlyPowered(this.field_145850_b, this.field_145851_c - ox, this.field_145848_d, this.field_145849_e - oz, 0) && piston2
          .isIndirectlyPowered(this.field_145850_b, this.field_145851_c + ox, this.field_145848_d, this.field_145849_e + oz, 0)) {
          if (this.dropped < 64)
            for (int i = 0; i < 64; i++) {
              if (this.stone >= 100) {
                this.stone -= 100;
                EntityItem entityitem = new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1.0D, this.field_145849_e, new ItemStack(Blocks.field_150343_Z));
                entityitem.field_145804_b = 10;
                this.field_145850_b.func_72838_d((Entity)entityitem);
                if (!this.field_145850_b.field_72995_K)
                  MinecraftForge.EVENT_BUS.post((Event)new VoidStoneGenerateEvent(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, 1)); 
                this.dropped++;
              } 
            }  
        } else {
          this.dropped = 0;
        } 
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\tileentity\TileEntityVoidStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */