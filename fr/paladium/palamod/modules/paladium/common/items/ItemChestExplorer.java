package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemChestExplorer extends BaseItem implements ITooltipWiki {
  private final List<String> registers = new ArrayList<>();
  
  public ItemChestExplorer(String texture) {
    super(texture);
    func_77625_d(1);
  }
  
  public void addTile(String tile) {
    this.registers.add(tile);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
    if (!world.field_72995_K) {
      MovingObjectPosition movingobjectposition = func_77621_a(world, entityPlayer, true);
      if (movingobjectposition == null)
        return itemStack; 
      if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
        int x = movingobjectposition.field_72311_b;
        int y = movingobjectposition.field_72312_c;
        int z = movingobjectposition.field_72309_d;
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile == null)
          return itemStack; 
        if (tile instanceof net.minecraft.inventory.IInventory && isRegisterTile(tile.getClass().toString().toLowerCase())) {
          if (entityPlayer.func_70093_af()) {
            UseItemAchievement.performCheck(entityPlayer, itemStack, "CHEST_EXPLORER");
            entityPlayer.openGui(PalaMod.instance, PGuiRegistry.GUI_CHESTEXPLORER, world, x, y, z);
          } 
          return itemStack;
        } 
      } 
    } 
    return itemStack;
  }
  
  private boolean isRegisterTile(String tile) {
    for (String reg : this.registers) {
      if (tile.contains(reg.toLowerCase()))
        return true; 
    } 
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pillage#7.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemChestExplorer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */