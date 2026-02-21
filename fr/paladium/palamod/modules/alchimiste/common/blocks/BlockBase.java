package fr.paladium.palamod.modules.alchimiste.common.blocks;

import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.BlockAlchemist;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBase extends BlockAlchemist {
  private String name;
  
  private String topTexture;
  
  private String sideTextures;
  
  private IIcon topIcon;
  
  private IIcon sideIcon;
  
  private ItemStack customDrop = null;
  
  public BlockBase(String name) {
    this(name, name, name);
  }
  
  public BlockBase(String name, String topTexture, String sideTextures) {
    super(Material.field_151576_e);
    this.name = name;
    func_149647_a(PAlchimiste.TAB);
    func_149663_c(name);
    func_149658_d("palamod:alchimiste/" + name);
    func_149711_c(8.0F);
    func_149752_b(2.0F);
    this.topTexture = "alchimiste/" + topTexture;
    this.sideTextures = "alchimiste/" + sideTextures;
  }
  
  protected void func_149642_a(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_) {
    if (this.customDrop != null) {
      if (!p_149642_1_.field_72995_K && p_149642_1_.func_82736_K().func_82766_b("doTileDrops") && !p_149642_1_.restoringBlockSnapshots) {
        if (((Boolean)this.captureDrops.get()).booleanValue()) {
          ((List<ItemStack>)this.capturedDrops.get()).add(this.customDrop.func_77946_l());
          return;
        } 
        float f = 0.7F;
        double d0 = (p_149642_1_.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
        double d1 = (p_149642_1_.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
        double d2 = (p_149642_1_.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(p_149642_1_, p_149642_2_ + d0, p_149642_3_ + d1, p_149642_4_ + d2, this.customDrop.func_77946_l());
        entityitem.field_145804_b = 10;
        p_149642_1_.func_72838_d((Entity)entityitem);
      } 
    } else {
      super.func_149642_a(p_149642_1_, p_149642_2_, p_149642_3_, p_149642_4_, p_149642_5_);
    } 
  }
  
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.sideIcon = p_149651_1_.func_94245_a("palamod:" + this.sideTextures);
    this.topIcon = p_149651_1_.func_94245_a("palamod:" + this.topTexture);
  }
  
  public IIcon func_149691_a(int side, int p_149691_2_) {
    return (side == 1) ? this.topIcon : this.sideIcon;
  }
  
  public Class<? extends TileEntity> getTileEntity() {
    return null;
  }
  
  public String getName() {
    return this.name;
  }
  
  public BlockBase setCustomDrop(ItemStack customDrop) {
    this.customDrop = customDrop;
    return this;
  }
  
  public BlockBase setLightLevel(float level) {
    this.field_149784_t = (int)(15.0F * level);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\BlockBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */