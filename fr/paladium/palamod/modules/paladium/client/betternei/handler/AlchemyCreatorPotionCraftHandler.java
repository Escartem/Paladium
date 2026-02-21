package fr.paladium.palamod.modules.paladium.client.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.paladium.client.betternei.ui.AlchemyCreatorPotionCraftOverlay;
import fr.paladium.palamod.modules.paladium.common.container.AlchemyCreatorPotionContainer;
import fr.paladium.palamod.modules.paladium.common.crafting.AlchemyCreatorPotionRecipies;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class AlchemyCreatorPotionCraftHandler extends ACraftHandler<AlchemyCreatorPotionContainer> {
  public String getId() {
    return "ALCHEMY_CREATOR_POTION";
  }
  
  public Class<AlchemyCreatorPotionContainer> getApplicableContainer() {
    return AlchemyCreatorPotionContainer.class;
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)AlchemyCreatorPotionCraftOverlay.class;
  }
  
  public void loadCrafts() {
    AlchemyCreatorPotionRecipies.getManager().getSmeltingList().forEach((stacks, result) -> {
          Craft craft = new Craft();
          for (int i = 0; i < stacks.length; i++)
            craft.getSlotItem().put(Integer.valueOf(i), stacks[i]); 
          craft.setOutputItemStack(result);
          registerCraft(craft);
        });
  }
  
  public ItemStack getIcon() {
    return new ItemStack((Block)BlocksRegister.ALCHEMY_CREATOR_BLOCK);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\handler\AlchemyCreatorPotionCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */