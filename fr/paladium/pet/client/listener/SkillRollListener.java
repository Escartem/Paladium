package fr.paladium.pet.client.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.pet.client.PetClientProxy;
import fr.paladium.pet.client.ui.skill.SkillSelector;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.packet.skill.BBRequestSkillRollPacket;
import fr.paladium.pet.common.network.packet.skill.CSActiveSpellPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.input.Mouse;

public class SkillRollListener {
  private boolean locked = false;
  
  private final SkillSelector overlay = new SkillSelector();
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onOverlay(RenderGameOverlayEvent event) {
    if (event.type != RenderGameOverlayEvent.ElementType.HOTBAR)
      return; 
    Minecraft mc = Minecraft.func_71410_x();
    if (mc.field_71462_r != null) {
      if (this.locked) {
        this.locked = false;
        mc.func_71381_h();
      } 
      return;
    } 
    KeyBinding binding = PetClientProxy.getInstance().getKeySkillRoll();
    if (binding.func_151470_d()) {
      if (!this.locked) {
        PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new BBRequestSkillRollPacket());
        this.locked = true;
      } 
      if (Mouse.isGrabbed()) {
        mc.field_71417_B.func_74373_b();
        mc.field_71415_G = false;
      } 
      this.overlay.draw(event.mouseX, event.mouseY, event.partialTicks, event);
    } else if (this.locked) {
      for (int i = 0; i < this.overlay.getIsHover().size(); i++) {
        boolean isHover = ((Boolean)this.overlay.getIsHover().get(i)).booleanValue();
        if (isHover) {
          PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new CSActiveSpellPacket(i));
          break;
        } 
      } 
      mc.func_71381_h();
      this.locked = false;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\listener\SkillRollListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */