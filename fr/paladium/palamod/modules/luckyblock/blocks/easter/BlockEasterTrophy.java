package fr.paladium.palamod.modules.luckyblock.blocks.easter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterTrophy;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEasterTrophy extends Block {
  public BlockEasterTrophy() {
    super(Material.field_151568_F);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("easter_trophy");
    func_149658_d("palamod:trophy/easter/easter_trophy");
    func_149711_c(1.0F);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityEasterTrophy();
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    super.func_149689_a(world, x, y, z, player, item);
    TileEntityEasterTrophy tile = (TileEntityEasterTrophy)world.func_147438_o(x, y, z);
    if (item.func_77942_o()) {
      if (item.func_77978_p().func_74764_b("owner"))
        tile.setOwner(item.func_77978_p().func_74779_i("owner")); 
    } else {
      tile.setOwner(player.func_70005_c_());
    } 
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K && EventUtils.canInteract(player, x, y, z)) {
      TileEntityEasterTrophy te = (TileEntityEasterTrophy)world.func_147438_o(x, y, z);
      if (te != null) {
        ItemStack item = new ItemStack(this);
        item.func_77964_b(meta);
        if (te.getOwner() != null && !te.getOwner().isEmpty()) {
          NBTTagCompound compound = new NBTTagCompound();
          if (item.func_77942_o())
            compound = item.func_77978_p(); 
          compound.func_74778_a("owner", te.getOwner());
          item.func_77982_d(compound);
        } 
        EntityItem entityItem = new EntityItem(world, x, y, z, item);
        entityItem.field_145804_b = 0;
        world.func_72838_d((Entity)entityItem);
      } 
    } 
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> drops = new ArrayList<>();
    return drops;
  }
  
  public int func_149692_a(int metadata) {
    return metadata;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderEasterTrophy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\easter\BlockEasterTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */