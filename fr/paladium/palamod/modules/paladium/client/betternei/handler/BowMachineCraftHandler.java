package fr.paladium.palamod.modules.paladium.client.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.paladium.client.betternei.craft.BowMachineCraft;
import fr.paladium.palamod.modules.paladium.client.betternei.ui.BowMachineCraftOverlay;
import fr.paladium.palamod.modules.paladium.common.container.BowMachineContainer;
import fr.paladium.palamod.modules.paladium.common.crafting.BowMachineRecipies;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BowMachineCraftHandler extends ACraftHandler<BowMachineContainer> {
  public String getId() {
    return "BOW_MACHINE";
  }
  
  public Class<BowMachineContainer> getApplicableContainer() {
    return BowMachineContainer.class;
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)BowMachineCraftOverlay.class;
  }
  
  public void loadCrafts() {
    BowMachineRecipies.instance.getModifiers().forEach((item, type) -> getCrafts().add(BowMachineCraft.from(new ItemStack(item), type.intValue())));
  }
  
  public ItemStack getIcon() {
    return new ItemStack((Block)BlocksRegister.BOW_MACHINE_BLOCK);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\handler\BowMachineCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */