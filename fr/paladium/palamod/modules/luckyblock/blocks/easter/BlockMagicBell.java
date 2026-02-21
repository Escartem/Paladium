package fr.paladium.palamod.modules.luckyblock.blocks.easter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityMagicBell;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMagicBell extends Block {
  public BlockMagicBell() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("magic_bell");
    func_149658_d("palamod:magic_bell_item");
    func_149711_c(1.0F);
  }
  
  public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
    if (EventUtils.canInteract(player, x, y, z))
      if (!world.field_72995_K) {
        TileEntityMagicBell tileEntity = (TileEntityMagicBell)world.func_147438_o(x, y, z);
        Integer ringings = tileEntity.getRingings();
        if (ringings != null) {
          if (ringings.intValue() >= 99) {
            if (ringings.intValue() == 99) {
              tileEntity.setRingings(Integer.valueOf(ringings.intValue() + 1));
              PlayerUtils.dropItemStack(world, x, y, z, new ItemStack(ItemsRegister.GOLDEN_EGG, 5));
            } 
          } else {
            tileEntity.setRingings(Integer.valueOf(ringings.intValue() + 1));
          } 
        } else {
          tileEntity.setRingings(Integer.valueOf(1));
        } 
      } else {
        ClientProxy.playSound("bell");
      }  
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    super.func_149689_a(world, x, y, z, player, item);
    TileEntityMagicBell tileEntity = (TileEntityMagicBell)world.func_147438_o(x, y, z);
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("ringings"))
      tileEntity.setRingings(Integer.valueOf(item.func_77978_p().func_74762_e("ringings"))); 
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K && EventUtils.canInteract(player, x, y, z)) {
      TileEntityMagicBell tileEntity = (TileEntityMagicBell)world.func_147438_o(x, y, z);
      if (tileEntity != null) {
        ItemStack item = new ItemStack(this);
        if (tileEntity.getRingings() != null) {
          NBTTagCompound nBTTagCompound = item.func_77978_p();
          if (nBTTagCompound == null)
            nBTTagCompound = new NBTTagCompound(); 
          nBTTagCompound.func_74768_a("ringings", tileEntity.getRingings().intValue());
          item.func_77982_d(nBTTagCompound);
        } 
        PlayerUtils.dropItemStack(world, x, y, z, item);
      } 
    } 
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderMagicBell;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityMagicBell();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\easter\BlockMagicBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */