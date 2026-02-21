package fr.paladium.palamod.modules.paladium.client.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.paladium.client.betternei.ui.PaladiumMachineCraftOverlay;
import fr.paladium.palamod.modules.paladium.common.container.PaladiumMachineContainer;
import fr.paladium.palamod.modules.paladium.common.crafting.PaladiumMachineRecipies;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class PalaMachineCraftHandler extends ACraftHandler<PaladiumMachineContainer> {
  public String getId() {
    return "PALAMACHINE";
  }
  
  public Class<PaladiumMachineContainer> getApplicableContainer() {
    return PaladiumMachineContainer.class;
  }
  
  public void loadCrafts() {
    Map<ItemStack[], ItemStack> craftMap = PaladiumMachineRecipies.getRecipeList();
    craftMap.forEach((matrix, stackResult) -> {
          Craft craft = new Craft();
          craft.setOutputItemStack(stackResult);
          for (int i = 0; i < matrix.length; i++)
            craft.getSlotItem().put(Integer.valueOf(i), matrix[i]); 
          registerCraft(craft);
        });
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)PaladiumMachineCraftOverlay.class;
  }
  
  public ItemStack getIcon() {
    return new ItemStack(BlocksRegister.PALADIUM_MACHINE_BLOCK);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\handler\PalaMachineCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */