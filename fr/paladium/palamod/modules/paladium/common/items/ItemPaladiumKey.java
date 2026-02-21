package fr.paladium.palamod.modules.paladium.common.items;

import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import fr.paladium.palamod.common.items.BaseItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemPaladiumKey extends BaseItem {
  public ItemPaladiumKey(String texture) {
    super(texture);
    func_77655_b(texture);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    MovingObjectPosition result = func_77621_a(world, player, true);
    if (result == null || result.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK)
      return stack; 
    Block block = world.func_147439_a(result.field_72311_b, result.field_72312_c, result.field_72309_d);
    if (world.field_72995_K)
      return stack; 
    if (block instanceof BlockDrawers) {
      BlockDrawers drawer = (BlockDrawers)block;
      TileEntityDrawers tile = drawer.getTileEntity((IBlockAccess)world, result.field_72311_b, result.field_72312_c, result.field_72309_d);
      if (tile.getOwner() != null) {
        tile.setOwner(null);
        if (!player.field_71075_bZ.field_75098_d)
          stack.field_77994_a--; 
        player.func_146105_b((IChatComponent)new ChatComponentText("§aDrawer dévérouillé."));
      } else {
        player.func_146105_b((IChatComponent)new ChatComponentText("§cCe drawer n'est pas vérrouillé."));
      } 
      return stack;
    } 
    player.func_146105_b((IChatComponent)new ChatComponentText("§cVeuillez utiliser cet item sur un drawer vérrouillé."));
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemPaladiumKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */