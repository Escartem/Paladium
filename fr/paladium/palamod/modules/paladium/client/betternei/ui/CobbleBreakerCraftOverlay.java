package fr.paladium.palamod.modules.paladium.client.betternei.ui;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.betternei.api.craft.ShowMode;
import fr.paladium.betternei.api.uikit.BNEIFakeSlotNode;
import fr.paladium.betternei.client.ui.overlay.UIItemOverlay;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.client.betternei.craft.CobbleBreakerCraft;
import fr.paladium.palamod.modules.paladium.client.betternei.handler.CobbleBreakerCraftHandler;
import fr.paladium.palamod.modules.paladium.client.ui.UICobbleBreaker;
import fr.paladium.palamod.modules.paladium.client.ui.util.RectItemNode;
import fr.paladium.palamod.modules.smeltery.ui.util.ArrowPointing;
import fr.paladium.palamod.modules.smeltery.ui.util.LoadingArrowNode;
import fr.paladium.zephyrui.lib.ui.node.Node;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CobbleBreakerCraftOverlay extends UIItemOverlay<CobbleBreakerCraftHandler> {
  public CobbleBreakerCraftOverlay(CobbleBreakerCraftHandler handlers, ItemStack itemStack, ShowMode showMode) {
    super((ACraftHandler)handlers, itemStack, showMode);
  }
  
  public void postInit(Node parentNode, Craft craft) {
    CobbleBreakerCraft bCraft = (CobbleBreakerCraft)craft;
    BNEIFakeSlotNode.create(parentNode.dw(2.0D) - 30.0D, 280.0D, 60.0D)
      .item(bCraft.getItemForSlot(0))
      .attach(parentNode);
    LoadingArrowNode.create(parentNode.dw(2.0D) - 30.0D, 350.0D, 60.0D, 74.0D)
      .point(ArrowPointing.DOWN)
      .thickness(12.0D)
      .loadingState(1.0D)
      .attach(parentNode);
    double margin = parentNode.dw(2.0D) - 180.0D;
    Item[] particles = { ItemsRegister.PARTICLE_IRON, ItemsRegister.PARTICLE_GOLD, ItemsRegister.PARTICLE_DIAMOND, ItemsRegister.PARTICLE_AMETHYST, ItemsRegister.PARTICLE_TITANE, ItemsRegister.PARTICLE_PALADIUM };
    int i = 0;
    for (Item particle : particles) {
      List<String> hover = new ArrayList<>();
      if (bCraft.getItemForSlot(i + 1) != null)
        hover.add((new StringBuilder()).append(CobbleBreakerCraftHandler.PARTICLES.get(particle)).append("%").toString()); 
      RectItemNode.create(5.0D + margin + 60.0D * i, 440.0D, 50.0D, 50.0D)
        .item(particle)
        .hoverLines(() -> hover)
        .attach(parentNode);
      i++;
    } 
    BNEIFakeSlotNode.create(margin, 500.0D, 60.0D)
      .item(bCraft.getItemForSlot(1))
      .attach(parentNode);
    BNEIFakeSlotNode.create(margin + 60.0D, 500.0D, 60.0D)
      .item(bCraft.getItemForSlot(2))
      .attach(parentNode);
    BNEIFakeSlotNode.create(margin + 120.0D, 500.0D, 60.0D)
      .item(bCraft.getItemForSlot(3))
      .attach(parentNode);
    BNEIFakeSlotNode.create(margin + 180.0D, 500.0D, 60.0D)
      .item(bCraft.getItemForSlot(4))
      .attach(parentNode);
    BNEIFakeSlotNode.create(margin + 240.0D, 500.0D, 60.0D)
      .item(bCraft.getItemForSlot(5))
      .attach(parentNode);
    BNEIFakeSlotNode.create(margin + 300.0D, 500.0D, 60.0D)
      .item(bCraft.getItemForSlot(6))
      .attach(parentNode);
    BNEIFakeSlotNode.create(margin + 360.0D, 280.0D, 60.0D)
      .item(bCraft.getItemForSlot(7))
      .placeholder(UICobbleBreaker.COBBLEBREAK_TOOL)
      .attach(parentNode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betterne\\ui\CobbleBreakerCraftOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */