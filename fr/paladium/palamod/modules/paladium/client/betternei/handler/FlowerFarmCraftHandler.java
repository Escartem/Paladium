package fr.paladium.palamod.modules.paladium.client.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.paladium.client.betternei.ui.FlowerFarmCraftOverlay;
import fr.paladium.palamod.modules.paladium.common.container.FlowerFarmContainer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FlowerFarmCraftHandler extends ACraftHandler<FlowerFarmContainer> {
  public String getId() {
    return "FLOWERFARM";
  }
  
  public Class<FlowerFarmContainer> getApplicableContainer() {
    return FlowerFarmContainer.class;
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)FlowerFarmCraftOverlay.class;
  }
  
  public ItemStack getIcon() {
    return new ItemStack(BlocksRegister.FLOWER_FARM);
  }
  
  public void loadCrafts() {
    for (int i = 0; i < 3; i++) {
      Craft craft = new Craft();
      craft.loadSlot(0, new ItemStack(Items.field_151100_aR, 1, 15));
      switch (i) {
        case 0:
          craft.setOutputItemStack(new ItemStack(Items.field_151014_N, 2));
          break;
        case 1:
          craft.setOutputItemStack(new ItemStack((Block)Blocks.field_150328_O));
          break;
        case 2:
          craft.setOutputItemStack(new ItemStack((Block)Blocks.field_150327_N));
          break;
      } 
      registerCraft(craft);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\handler\FlowerFarmCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */