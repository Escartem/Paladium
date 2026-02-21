package fr.paladium.palamod.modules.chisel.block.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.chisel.block.BlockChisel;
import java.util.LinkedList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockChiselMetadata extends BlockChisel {
  private final List<BlockChiselMetadataObject> objects;
  
  private IIcon[] icons;
  
  public BlockChiselMetadata(@NonNull Material material, float hardness) {
    super(material, hardness);
    if (material == null)
      throw new NullPointerException("material is marked non-null but is null"); 
    this.objects = new LinkedList<>();
  }
  
  public void func_149651_a(IIconRegister register) {
    if (this.icons == null) {
      this.icons = new IIcon[this.objects.size()];
    } else if (this.icons.length != this.objects.size()) {
      throw new IllegalStateException("Icons array size does not match objects size. Please ensure to register the correct number of objects before registering icons.");
    } 
    for (int i = 0; i < this.objects.size(); i++) {
      BlockChiselMetadataObject object = this.objects.get(i);
      this.icons[i] = register.func_94245_a(object.getTexture());
    } 
    super.func_149651_a(register);
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return this.icons[meta % this.icons.length];
  }
  
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < this.objects.size(); i++)
      list.add(new ItemStack(item, 1, i)); 
  }
  
  public int func_149643_k(World world, int x, int y, int z) {
    return world.func_72805_g(x, y, z);
  }
  
  public void addObject(String name, String texture) {
    if (isFull())
      throw new IllegalStateException("Cannot add more than 16 objects to a BlockChiselMetadata"); 
    this.objects.add(new BlockChiselMetadataObject(name, texture));
  }
  
  public boolean isFull() {
    return (this.objects.size() >= 16);
  }
  
  public BlockChiselMetadataObject getObject(int metadata) {
    if (metadata < 0 || metadata >= this.objects.size())
      throw new IndexOutOfBoundsException("Metadata must be between 0 and " + (this.objects.size() - 1)); 
    return this.objects.get(metadata);
  }
  
  @NonNull
  public List<BlockChiselMetadataObject> getObjects() {
    return this.objects;
  }
  
  public static class ItemBlockChiselMetadata extends ItemBlock {
    public ItemBlockChiselMetadata(@NonNull Block block) {
      super(block);
      if (block == null)
        throw new NullPointerException("block is marked non-null but is null"); 
      func_77656_e(0);
      func_77627_a(true);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int damage) {
      return this.field_150939_a.func_149735_b(2, damage);
    }
    
    public int func_77647_b(int damage) {
      return damage;
    }
    
    public String func_77667_c(@NonNull ItemStack item) {
      if (item == null)
        throw new NullPointerException("item is marked non-null but is null"); 
      return "tile." + ((BlockChiselMetadata)this.field_150939_a).getObject(item.func_77960_j()).getName();
    }
  }
  
  public static class BlockChiselMetadataObject {
    private final String name;
    
    private final String texture;
    
    public String toString() {
      return "BlockChiselMetadata.BlockChiselMetadataObject(name=" + getName() + ", texture=" + getTexture() + ")";
    }
    
    public BlockChiselMetadataObject(String name, String texture) {
      this.name = name;
      this.texture = texture;
    }
    
    public String getName() {
      return this.name;
    }
    
    public String getTexture() {
      return this.texture;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\block\impl\BlockChiselMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */