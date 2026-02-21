package fr.paladium.palamod.modules.luckyblock.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.gui.containers.ContainerWishCard;
import fr.paladium.palamod.modules.luckyblock.network.CSSendWishCard;
import fr.paladium.palamod.modules.spellsv2.gui.buttons.GuiTexturedButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiWishCard extends GuiContainer {
  private final ResourceLocation background = new ResourceLocation("palamod", "textures/gui/wish_card/background.png");
  
  private final ResourceLocation slotTexture = new ResourceLocation("palamod", "textures/gui/wish_card/slot.png");
  
  private GuiTextField receiver;
  
  public GuiWishCard(InventoryPlayer inventoryPlayer) {
    super((Container)new ContainerWishCard(inventoryPlayer));
  }
  
  public void func_73866_w_() {
    this.field_147000_g = 270;
    this.field_146999_f = 460;
    super.func_73866_w_();
    this.field_146292_n.clear();
    this.receiver = new GuiTextField(this.field_146289_q, this.field_147003_i + this.field_146999_f / 100 * 36, this.field_147009_r + this.field_147000_g / 100 * 56, this.field_146999_f / 100 * 13, 15);
    this.receiver.func_146185_a(false);
    this.receiver.func_146195_b(true);
    this.field_146292_n.add(new GuiTexturedButton(0, this.field_147003_i + this.field_146999_f / 2 - this.field_146999_f / 100 * 7, this.field_147009_r + this.field_147000_g - this.field_147000_g / 100 * 17, this.field_146999_f / 100 * 14, this.field_147000_g / 100 * 10, new ResourceLocation("palamod", "textures/gui/wish_card/send.png"), new ResourceLocation("palamod", "textures/gui/wish_card/send_hover.png")));
  }
  
  protected void func_146284_a(GuiButton button) {
    if (button.field_146127_k == 0) {
      EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
      String receiver = this.receiver.func_146179_b().replace(" ", "");
      if (receiver.isEmpty()) {
        entityClientPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§cVeuillez entrer le nom du joueur à qui vous voulez envoyer l'objet."));
        return;
      } 
      if (this.field_147002_h.func_75139_a(0).func_75216_d()) {
        PalaMod.getNetHandler().sendToServer((IMessage)new CSSendWishCard(receiver));
        this.field_146297_k.func_147108_a(null);
      } 
    } 
    super.func_146284_a(button);
  }
  
  public void func_73876_c() {
    this.receiver.func_146178_a();
    super.func_73876_c();
  }
  
  protected void func_73869_a(char c, int key) {
    this.receiver.func_146201_a(c, key);
    if (key == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i())
      return; 
    super.func_73869_a(c, key);
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
    this.receiver.func_146192_a(mouseX, mouseY, mouseButton);
    super.func_73864_a(mouseX, mouseY, mouseButton);
  }
  
  public void func_146281_b() {
    super.func_146281_b();
    this.field_146297_k.field_71439_g.field_71174_a
      .func_147297_a((Packet)new C0DPacketCloseWindow(this.field_146297_k.field_71439_g.field_71070_bA.field_75152_c));
  }
  
  protected void func_146976_a(float tick, int mouseX, int mouseY) {
    GuiUtils.drawImageTransparent(this.field_147003_i, this.field_147009_r, this.background, this.field_146999_f, this.field_147000_g);
    GL11.glPushMatrix();
    GL11.glTranslatef(this.field_147003_i, this.field_147009_r, 0.0F);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glEnable(32826);
    for (Object slotObj : this.field_147002_h.field_75151_b) {
      Slot slot = (Slot)slotObj;
      GuiUtils.drawImageTransparent(slot.field_75223_e, slot.field_75221_f, this.slotTexture, 16.0D, 16.0D);
    } 
    GL11.glPopMatrix();
    this.receiver.func_146194_f();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\GuiWishCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */