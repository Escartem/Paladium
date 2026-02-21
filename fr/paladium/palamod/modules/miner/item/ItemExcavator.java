package fr.paladium.palamod.modules.miner.item;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.smeltery.utils.ToolHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemExcavator extends ItemSpade implements ITooltipWiki {
  protected static final Material[] MATERIALS = new Material[] { Material.field_151577_b, Material.field_151578_c, Material.field_151595_p, Material.field_151597_y, Material.field_151596_z };
  
  public ItemExcavator(Item.ToolMaterial material) {
    super(material);
    func_77637_a((CreativeTabs)Registry.MINER_TAB);
  }
  
  public boolean func_150894_a(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
    MovingObjectPosition raycast = ToolHandler.raytraceFromEntity(player.field_70170_p, (Entity)player, true, 10.0D);
    if (raycast != null)
      breakOtherBlock((EntityPlayer)player, stack, x, y, z, x, y, z, raycast.field_72310_e); 
    return super.func_150894_a(stack, world, block, x, y, z, player);
  }
  
  public int func_77619_b() {
    return 0;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta) {
    return super.getDigSpeed(stack, block, meta);
  }
  
  public void breakOtherBlock(EntityPlayer player, ItemStack stack, int x, int y, int z, int originX, int originY, int originZ, int side) {
    World world = player.field_70170_p;
    if (world.func_147437_c(x, y, z))
      return; 
    ForgeDirection direction = ForgeDirection.getOrientation(side);
    int range = Math.max(0, 1);
    int rangeY = Math.max(1, range);
    boolean doX = (direction.offsetX == 0);
    boolean doY = (direction.offsetY == 0);
    boolean doZ = (direction.offsetZ == 0);
    ToolHandler.removeBlocksInIterationWithShovel(player, stack, world, x, y, z, doX ? -range : 0, doY ? -1 : 0, doZ ? -range : 0, doX ? (range + 1) : 1, doY ? (rangeY * 2) : 1, doZ ? (range + 1) : 1, world.func_147439_a(x, y, z), MATERIALS, false, 0, true, false);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#7.-les-excavatrices";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemExcavator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */