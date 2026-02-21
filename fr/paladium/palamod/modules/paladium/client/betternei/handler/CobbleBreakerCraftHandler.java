package fr.paladium.palamod.modules.paladium.client.betternei.handler;

import fr.paladium.betternei.api.craft.ACraftHandler;
import fr.paladium.betternei.api.craft.Craft;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.client.betternei.craft.CobbleBreakerCraft;
import fr.paladium.palamod.modules.paladium.client.betternei.ui.CobbleBreakerCraftOverlay;
import fr.paladium.palamod.modules.paladium.common.container.ContainerCobbleBreaker;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CobbleBreakerCraftHandler extends ACraftHandler<ContainerCobbleBreaker> {
  public static final Map<Item, Float> PARTICLES = new LinkedHashMap<>();
  
  static {
    PARTICLES.put(ItemsRegister.PARTICLE_IRON, Float.valueOf(0.04F));
    PARTICLES.put(ItemsRegister.PARTICLE_GOLD, Float.valueOf(0.032F));
    PARTICLES.put(ItemsRegister.PARTICLE_DIAMOND, Float.valueOf(0.02F));
    PARTICLES.put(ItemsRegister.PARTICLE_AMETHYST, Float.valueOf(0.016F));
    PARTICLES.put(ItemsRegister.PARTICLE_TITANE, Float.valueOf(0.008F));
    PARTICLES.put(ItemsRegister.PARTICLE_PALADIUM, Float.valueOf(0.008F));
  }
  
  public String getId() {
    return "COBBLEBREAKER";
  }
  
  public Class<ContainerCobbleBreaker> getApplicableContainer() {
    return ContainerCobbleBreaker.class;
  }
  
  public <T extends fr.paladium.betternei.client.ui.overlay.UIItemOverlay<?>> Class<T> getOverlay() {
    return (Class)CobbleBreakerCraftOverlay.class;
  }
  
  public ItemStack getIcon() {
    return new ItemStack((Block)BlocksRegister.COBBLEBREAKER);
  }
  
  public void loadCrafts() {
    AtomicInteger slotId = new AtomicInteger(5);
    PARTICLES.forEach((item, percentage) -> {
          ItemStack particle = new ItemStack(item);
          CobbleBreakerCraft cobbleCraft = new CobbleBreakerCraft();
          cobbleCraft.setPercentage(percentage.floatValue());
          cobbleCraft.loadSlot(0, new ItemStack(Blocks.field_150347_e));
          cobbleCraft.loadSlot(6 - slotId.get(), particle);
          cobbleCraft.setOutputItemStack(particle);
          registerCraft((Craft)cobbleCraft);
          CobbleBreakerCraft voidstoneCraft = new CobbleBreakerCraft();
          voidstoneCraft.setPercentage(percentage.floatValue());
          voidstoneCraft.loadSlot(0, new ItemStack((Item)ItemsRegister.VOIDSTONE_MINAGE));
          voidstoneCraft.loadSlot(6 - slotId.getAndDecrement(), particle);
          cobbleCraft.setOutputItemStack(particle);
          registerCraft((Craft)voidstoneCraft);
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\betternei\handler\CobbleBreakerCraftHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */