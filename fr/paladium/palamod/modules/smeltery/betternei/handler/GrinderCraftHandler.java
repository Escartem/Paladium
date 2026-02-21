package fr.paladium.palamod.modules.smeltery.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemRepairableArmor;
import fr.paladium.palamod.modules.smeltery.betternei.craft.GrinderCraft;
import fr.paladium.palamod.modules.smeltery.betternei.ui.GrinderCraftOverlay;
import fr.paladium.palamod.modules.smeltery.crafting.GrinderRecipe;
import fr.paladium.palamod.modules.smeltery.inventory.GrinderContainer;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Blocks;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GrinderCraftHandler extends ACraftHandler<GrinderContainer> {
  public String getId() {
    return "GRINDER";
  }
  
  public Class<GrinderContainer> getApplicableContainer() {
    return GrinderContainer.class;
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)GrinderCraftOverlay.class;
  }
  
  public ItemStack getIcon() {
    return (new ItemStack((Block)PSRegister_Blocks.GRINDER_BLOCK)).func_151001_c("Grinder");
  }
  
  public void loadCrafts() {
    GrinderRecipe.getManager().getSmeltingList().forEach((items, result) -> {
          GrinderCraft craft = new GrinderCraft();
          int cost = GrinderRecipe.getManager().getSmeltingAmmount(result);
          for (int i = 0; i < items.length; i++)
            craft.loadSlot(i + 1, items[i]); 
          craft.setOutputItemStack(result);
          craft.setPaladiumCost(cost);
          craft.setCraftType(GrinderCraft.GrinderCraftType.CRAFTING);
          registerCraft((Craft)craft);
        });
    GrinderRecipe.getManager().getUpgradeList().forEach((upgrade, tool) -> {
          GrinderCraft craft = new GrinderCraft();
          int upgradeType = GrinderRecipe.getManager().getUpgrade(upgrade);
          ItemStack result = new ItemStack(tool);
          UpgradeHelper.applyUpgrade(result, upgradeType);
          craft.loadSlot(3, new ItemStack(upgrade));
          craft.loadSlot(4, new ItemStack(tool));
          craft.setOutputItemStack(result);
          craft.setCraftType(GrinderCraft.GrinderCraftType.UPGRADE);
          registerCraft((Craft)craft);
        });
    GrinderCraft craft = new GrinderCraft();
    craft.setCraftType(GrinderCraft.GrinderCraftType.FILL_TANK);
    craft.loadSlot(5, new ItemStack(BlocksRegister.BLOCK_PALADIUM));
    craft.setOutputItemStack(null);
    craft.setPaladiumCost(9);
    registerCraft((Craft)craft);
    List<Item> paladiumItems = Arrays.asList(new Item[] { ItemsRegister.PALADIUM_INGOT, ItemsRegister.PALADIUM_SWORD, ItemsRegister.PALADIUM_PICKAXE, ItemsRegister.PALADIUM_BOOTS, ItemsRegister.PALADIUM_LEGGINGS, ItemsRegister.PALADIUM_CHESTPLATE, ItemsRegister.PALADIUM_HELMET });
    paladiumItems.forEach(item -> {
          GrinderCraft fillCraft = new GrinderCraft();
          ItemStack stack = new ItemStack(item);
          int amount = 0;
          fillCraft.setCraftType(GrinderCraft.GrinderCraftType.FILL_TANK);
          fillCraft.loadSlot(5, stack);
          fillCraft.setOutputItemStack(null);
          if (item instanceof ItemRepairableArmor) {
            amount = (stack.func_77958_k() - stack.func_77960_j()) * ((ItemRepairableArmor)stack.func_77973_b()).getCost() / stack.func_77958_k();
          } else if (item instanceof fr.paladium.palamod.modules.paladium.common.items.weapons.BaseItemSword) {
            amount = (stack.func_77958_k() - stack.func_77960_j()) * 2 / stack.func_77958_k();
          } else if (item instanceof fr.paladium.palamod.modules.paladium.common.items.tools.BaseItemPickaxe) {
            amount = (stack.func_77958_k() - stack.func_77960_j()) * 3 / stack.func_77958_k();
          } 
          fillCraft.setPaladiumCost(amount);
          registerCraft((Craft)fillCraft);
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\betternei\handler\GrinderCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */