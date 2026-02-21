package fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable;

import fr.paladium.lib.apollon.nodes.slot.SlotNode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public class SlotNodeSuperCraftingTable extends SlotNode {
  public SlotNodeSuperCraftingTable(EntityPlayer p_i1823_1_, IInventory p_i1823_2_, IInventory p_i1823_3_, int p_i1823_4_, int p_i1823_5_, int p_i1823_6_, World worldObj, int posX, int posY, int posZ, double size) {
    super(p_i1823_3_, p_i1823_4_, p_i1823_5_, p_i1823_6_, size, size);
  }
  
  public SlotNodeSuperCraftingTable(IInventory result, int id, int x, int y, EntityPlayer player, IInventory craftMatrix, World worldObj, int posX, int posY, int posZ, double size) {
    super(result, id, x, y, size, size);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\supercraftingtable\SlotNodeSuperCraftingTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */