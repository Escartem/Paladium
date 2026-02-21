package fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.ui;

import fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.SlotNodeSuperCraftingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public class SlotNodeSuperCraftingTable extends SlotNodeSuperCraftingTable {
  public SlotNodeSuperCraftingTable(IInventory result, int id, double x, double y, double size, EntityPlayer player, IInventory craftMatrix, World worldObj, int posX, int posY, int posZ) {
    super(result, id, (int)x, (int)y, player, craftMatrix, worldObj, posX, posY, posZ, size);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\supercraftingtabl\\ui\SlotNodeSuperCraftingTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */