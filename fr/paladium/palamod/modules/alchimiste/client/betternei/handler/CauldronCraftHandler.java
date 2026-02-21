package fr.paladium.palamod.modules.alchimiste.client.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.client.container.ContainerFake;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.alchimiste.client.betternei.ui.CauldronCraftOverlay;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.CauldronRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CauldronCraftHandler extends ACraftHandler<ContainerFake> {
  public String getId() {
    return "CAULDRON";
  }
  
  public Class<ContainerFake> getApplicableContainer() {
    return ContainerFake.class;
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)CauldronCraftOverlay.class;
  }
  
  public ItemStack getIcon() {
    return (new ItemStack((Item)ItemsRegister.LIGHTNING_POTION)).func_151001_c("Cauldron");
  }
  
  public void loadCrafts() {
    PAlchimiste.cauldronController.getRecipes().forEach(cRecipe -> {
          Craft craft = new Craft();
          craft.setOutputItemStack(cRecipe.getOutput());
          int i = 0;
          for (ItemStack item : cRecipe.getInputs())
            craft.loadSlot(i++, item); 
          registerCraft(craft);
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\betternei\handler\CauldronCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */