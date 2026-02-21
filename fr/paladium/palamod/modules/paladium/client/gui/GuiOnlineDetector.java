package fr.paladium.palamod.modules.paladium.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.common.container.ContainerEmpty;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityOnlineDetector;
import fr.paladium.palamod.modules.paladium.network.PacketOnlineDetector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiOnlineDetector extends GuiContainer {
  private final ResourceLocation background = new ResourceLocation("palamod:textures/gui/OnlineDetector.png");
  
  private final TileEntityOnlineDetector te;
  
  private final FontRenderer fontRendererObj;
  
  private GuiTextField usernameInput;
  
  public GuiOnlineDetector(TileEntityOnlineDetector te) {
    super((Container)new ContainerEmpty());
    this.te = te;
    this.fontRendererObj = (Minecraft.func_71410_x()).field_71466_p;
    this.field_146999_f = 161;
    this.field_147000_g = 70;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    int x = (this.field_146294_l - this.field_146999_f) / 2;
    int y = (this.field_146295_m - this.field_147000_g) / 2;
    this.usernameInput = new GuiTextField(this.fontRendererObj, x + 5, y + 20, 150, 15);
    this.usernameInput.func_146195_b(false);
    this.usernameInput.func_146205_d(true);
    this.usernameInput.func_146180_a(this.te.getPlayerName());
  }
  
  public void func_146281_b() {
    Keyboard.enableRepeatEvents(false);
  }
  
  protected void func_73864_a(int par1, int par2, int par3) {
    super.func_73864_a(par1, par2, par3);
    this.usernameInput.func_146192_a(par1, par2, par3);
  }
  
  protected void func_73869_a(char par1, int pressedKey) {
    if (pressedKey == 1 || (!this.usernameInput.func_146206_l() && pressedKey == this.field_146297_k.field_71474_y.field_151445_Q
      .func_151463_i())) {
      PacketOnlineDetector packet = new PacketOnlineDetector();
      packet.addInformations(this.usernameInput.func_146179_b(), this.te);
      PalaMod.getNetwork().sendToServer((IMessage)packet);
      this.field_146297_k.field_71439_g.func_71053_j();
    } 
    this.usernameInput.func_146201_a(par1, pressedKey);
  }
  
  protected void func_146976_a(float par1, int par2, int par3) {
    this.field_146297_k.field_71446_o.func_110577_a(this.background);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    int x = (this.field_146294_l - this.field_146999_f) / 2;
    int y = (this.field_146295_m - this.field_147000_g) / 2;
    func_73729_b(x, y, 0, 0, this.field_146999_f, this.field_147000_g);
    this.usernameInput.func_146194_f();
    this.fontRendererObj.func_78261_a("Detecting: " + this.te.getPlayerName(), x + this.field_146999_f / 2 - this.fontRendererObj
        .func_78256_a("Detecting: " + this.te.getPlayerName()) / 2, y + 44, 16777215);
  }
  
  protected void func_146979_b(int param1, int param2) {
    this.fontRendererObj.func_78276_b("Online detector", 8, 6, 4210752);
  }
  
  public void func_73876_c() {
    super.func_73876_c();
    this.usernameInput.func_146178_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\GuiOnlineDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */