package fr.paladium.palamod.modules.smeltery.items.tools;

import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palamod.modules.smeltery.utils.ToolHandler;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemPaladiumHammer extends ItemHammer {
  public ItemPaladiumHammer() {
    super(PToolMaterial.paladium);
    func_77655_b("hammer.paladium");
    func_111206_d("palamod:hammer/paladium");
    func_77637_a((CreativeTabs)Registry.SMELTERY_TAB);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean b) {
    int[] modifiers = UpgradeHelper.getUpgradeAmmount(stack);
    if (modifiers == null)
      return; 
    int filled = stack.field_77990_d.func_74762_e("modifiersammount");
    int max = stack.field_77990_d.func_74762_e("modifiersmax") + UpgradeHelper.getModifier(stack, 7) * 2;
    String head = " ";
    if (filled == max) {
      for (int i : modifiers) {
        if (i != 7)
          head = head + UpgradeHelper.getUpgradeColor(i) + "⚫ "; 
      } 
    } else {
      if (modifiers.length != 0)
        for (int j : modifiers) {
          if (j != 7)
            head = head + UpgradeHelper.getUpgradeColor(j) + "⚫ "; 
        }  
      for (int i = 0; i < max - filled; i++)
        head = head + "§r⚪ "; 
    } 
    list.add(head);
    ArrayList<Integer> Ilist = new ArrayList<>();
    for (int i : modifiers) {
      if (i != 7 && !Ilist.contains(Integer.valueOf(i))) {
        int lvl = UpgradeHelper.getModifier(stack, i);
        String lvlDisplay = "";
        if (lvl > 1)
          lvlDisplay = " " + lvl; 
        list.add(UpgradeHelper.getUpgradeName(i) + lvlDisplay);
        Ilist.add(Integer.valueOf(i));
      } 
    } 
  }
  
  public void breakOtherBlock(EntityPlayer player, ItemStack stack, int x, int y, int z, int originX, int originY, int originZ, int side) {
    World world = player.field_70170_p;
    Material mat = world.func_147439_a(x, y, z).func_149688_o();
    if (world.func_147437_c(x, y, z))
      return; 
    ForgeDirection direction = ForgeDirection.getOrientation(side);
    int fortune = UpgradeHelper.getModifier(stack, 1);
    boolean smelt = (UpgradeHelper.getModifier(stack, 8) == 1);
    boolean obsidian = (UpgradeHelper.getModifier(stack, 6) == 1);
    int range = Math.max(0, 1);
    int rangeY = Math.max(1, range);
    boolean doX = (direction.offsetX == 0);
    boolean doY = (direction.offsetY == 0);
    boolean doZ = (direction.offsetZ == 0);
    ToolHandler.removeBlocksInIteration(player, stack, world, x, y, z, doX ? -range : 0, doY ? -1 : 0, doZ ? -range : 0, doX ? (range + 1) : 1, doY ? (rangeY * 2) : 1, doZ ? (range + 1) : 1, world
        
        .func_147439_a(x, y, z), MATERIALS, smelt, fortune, true, obsidian);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta) {
    return super.getDigSpeed(stack, block, meta) * (
      UpgradeHelper.getModifier(stack, 2) + 1);
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\tools\ItemPaladiumHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */