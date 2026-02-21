package fr.paladium.palamod.modules.paladium.client.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.client.betternei.ui.GolemBoxCraftOverlay;
import fr.welsymc.guardiangolem.common.containers.ContainerBox;
import fr.welsymc.guardiangolem.common.init.BlockInit;
import fr.welsymc.guardiangolem.common.init.ItemInit;
import java.util.Arrays;
import java.util.List;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GolemBoxCraftHandler extends ACraftHandler<ContainerBox> {
  public String getId() {
    return "GOLEMBOX";
  }
  
  public Class<ContainerBox> getApplicableContainer() {
    return ContainerBox.class;
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)GolemBoxCraftOverlay.class;
  }
  
  public ItemStack getIcon() {
    return new ItemStack(BlockInit.GUARDIAN_BOX_BLOCK);
  }
  
  public void loadCrafts() {
    List<Item> items = Arrays.asList(new Item[] { Items.field_151042_j, ItemsRegister.TITANE_INGOT, ItemsRegister.AMETHYST_INGOT, ItemsRegister.PALADIUM_INGOT });
    items.forEach(item -> {
          Craft craft = new Craft();
          craft.loadSlot(0, new ItemStack(ItemInit.MINI_GOLEM)).setOutputItemStack(new ItemStack(item));
          registerCraft(craft);
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\handler\GolemBoxCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */