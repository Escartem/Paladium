package fr.paladium.palamod.modules.paladium.client.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.paladium.client.betternei.ui.PalaForgeCraftOverlay;
import fr.paladium.palamod.modules.paladium.common.container.ForgeContainer;
import fr.paladium.palamod.modules.paladium.utils.EnumAllowItemsForge;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class PalaForgeCraftHandler extends ACraftHandler<ForgeContainer> {
  public String getId() {
    return "PALAFORGE";
  }
  
  public Class<ForgeContainer> getApplicableContainer() {
    return ForgeContainer.class;
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)PalaForgeCraftOverlay.class;
  }
  
  public ItemStack getIcon() {
    return new ItemStack((Block)BlocksRegister.FORGE);
  }
  
  public void loadCrafts() {
    for (EnumAllowItemsForge forgeCraft : EnumAllowItemsForge.values()) {
      Craft craft = new Craft();
      craft.setOutputItemStack(new ItemStack(forgeCraft.getItemDrop(), forgeCraft.getMaxDrop()));
      craft.loadSlot(0, new ItemStack(forgeCraft.getItemAllowed()));
      registerCraft(craft);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\handler\PalaForgeCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */