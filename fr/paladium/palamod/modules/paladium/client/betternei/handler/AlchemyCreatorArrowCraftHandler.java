package fr.paladium.palamod.modules.paladium.client.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.paladium.client.betternei.ui.AlchemyCreatorArrowCraftOverlay;
import fr.paladium.palamod.modules.paladium.common.container.AlchemyCreatorArrowContainer;
import fr.paladium.palamod.modules.paladium.common.crafting.AlchemyCreatorArrowRecipies;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class AlchemyCreatorArrowCraftHandler extends ACraftHandler<AlchemyCreatorArrowContainer> {
  public String getId() {
    return "ALCHEMY_CREATOR_ARROW";
  }
  
  public Class<AlchemyCreatorArrowContainer> getApplicableContainer() {
    return AlchemyCreatorArrowContainer.class;
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)AlchemyCreatorArrowCraftOverlay.class;
  }
  
  public ItemStack getIcon() {
    return new ItemStack((Block)BlocksRegister.ALCHEMY_CREATOR_BLOCK);
  }
  
  public void loadCrafts() {
    AlchemyCreatorArrowRecipies.getManager().getSmeltingList().forEach((items, result) -> {
          Craft craft = new Craft();
          for (int i = 0; i < items.length; i++)
            craft.getSlotItem().put(Integer.valueOf(i + 4), items[i]); 
          craft.setOutputItemStack(result);
          registerCraft(craft);
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\handler\AlchemyCreatorArrowCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */