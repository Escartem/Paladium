package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.palamod.modules.paladium.client.ui.util.ThreeToOneBarNode;
import fr.paladium.palamod.modules.paladium.common.container.AlchemyCreatorPotionContainer;
import fr.paladium.palamod.modules.paladium.common.logics.AlchemyCreatorLogic;
import fr.paladium.palamod.modules.smeltery.ui.util.ArrowPointing;
import fr.paladium.palamod.modules.smeltery.ui.util.LoadingArrowNode;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.progress.ProgressNode;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.FloatSignal;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class UIAlchemyCreatorPotion extends UIAlchemyCreatorBase {
  private static final Resource POTION_PLACEHOLDER = Resource.of(new ResourceLocation("palamod", "textures/gui/potion_placeholder.png"));
  
  private final FloatSignal arrowProgressSignal = new FloatSignal(0.0F);
  
  public UIAlchemyCreatorPotion(AlchemyCreatorLogic alchemyCreatorLogic) {
    super((PaladiumContainer)new AlchemyCreatorPotionContainer(alchemyCreatorLogic, (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    this.logic = alchemyCreatorLogic;
  }
  
  public void init() {
    super.init();
    ThreeToOneBarNode.create(267.0D, 222.0D, 228.0D, 75.0D)
      .regroupHeight(0.5D)
      .thickness(15.0D)
      .attach((Node)this.background);
    ((LoadingArrowNode)LoadingArrowNode.create(581.0D, 218.0D, 66.0D, 188.0D)
      .thickness(12.0D)
      .point(ArrowPointing.DOWN)
      .loadingState(0.0D)
      .onInit(t -> t.loadingState(((Float)this.arrowProgressSignal.getOrDefault()).floatValue())))
      .watch((Signal)this.arrowProgressSignal)
      .attach((Node)this.background);
    ((ProgressNode)ProgressNode.create(122.0D, 160.0D, 94.0D, 234.0D)
      .resource(UIAlchemyCreatorBase.getBubbleBackground(), UIAlchemyCreatorBase.getBubbleForeground())
      .direction(ProgressNode.ProgressDirection.BOTTOM_TO_TOP)
      .progress(((Float)this.arrowProgressSignal.getOrDefault()).floatValue())
      .onInit(t -> t.progress(((Float)this.arrowProgressSignal.getOrDefault()).floatValue())))
      
      .watch((Signal)this.arrowProgressSignal)
      .attach((Node)this.background);
    List<Slot> slots = (getContainer()).field_75151_b;
    slots.forEach(slot -> {
          if (slot.field_75224_c instanceof AlchemyCreatorLogic) {
            double x = 87.0D;
            double y = 162.0D;
            if (slot.getSlotIndex() == 0)
              x += 156.0D; 
            if (slot.getSlotIndex() == 1)
              x += 262.0D; 
            if (slot.getSlotIndex() == 2)
              x += 368.0D; 
            if (slot.getSlotIndex() == 3) {
              y += 134.0D;
              x += 262.0D;
            } 
            SlotNode slotNode = (SlotNode)SlotNode.create(x, y, 60.0D, slot.getSlotIndex(), slot.field_75224_c).attach((Node)this.background);
            if (slot.getSlotIndex() == 3)
              slotNode.placeholder(POTION_PLACEHOLDER); 
          } else if (slot.field_75224_c instanceof net.minecraft.entity.player.InventoryPlayer) {
            double x = (this.background.getWidth() - 540.0D) / 2.0D;
            double y = 500.0D;
            int i = (slot.getSlotIndex() - 9) % 9;
            int yLevel = (int)Math.floor(((slot.getSlotIndex() - 9) / 9));
            if (slot.getSlotIndex() > 9 && slot.getSlotIndex() < 36) {
              x += i * 60.0D;
              y += yLevel * 60.0D;
            } 
            if (slot.getSlotIndex() >= 0 && slot.getSlotIndex() <= 8) {
              x += slot.getSlotIndex() * 60.0D;
              y += 218.0D;
            } 
            SlotNode.create(x, y, 60.0D, slot.getSlotIndex(), slot.field_75224_c).attach((Node)this.background);
          } 
        });
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (((Float)this.arrowProgressSignal.getOrDefault()).floatValue() != this.logic.getScaledCookProgressPotion())
      this.arrowProgressSignal.set(Float.valueOf(this.logic.getScaledCookProgressPotion())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIAlchemyCreatorPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */