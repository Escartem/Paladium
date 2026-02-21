package fr.paladium.palamod.modules.paladium.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.hunter.proxies.CommonProxy;
import fr.paladium.palamod.modules.paladium.client.model.ModelInkPlate;
import fr.paladium.palamod.modules.paladium.client.model.ModelPrintPress;
import fr.paladium.palamod.modules.paladium.client.model.ModelPrintPressBooks;
import fr.paladium.palamod.modules.paladium.common.tileentities.TileEntityPrintPress;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityPrintPressRenderer extends TileEntitySpecialRenderer {
  private ModelPrintPress pressModel = new ModelPrintPress();
  
  private ModelInkPlate inkplateModel = new ModelInkPlate();
  
  private ModelPrintPressBooks bookModels = new ModelPrintPressBooks();
  
  private TileEntityPrintPress printTile;
  
  private int pressAngle;
  
  private int degreeAngle;
  
  private float iadjust;
  
  private float kadjust;
  
  private int emptyBooks;
  
  private boolean finishedBook;
  
  private boolean hasPlate;
  
  private boolean hasEnchantedPlate;
  
  private boolean hasEnchantedBook;
  
  private int inksize;
  
  public void func_147500_a(TileEntity tileEntity, double i, double j, double k, float tick) {
    this.printTile = (TileEntityPrintPress)tileEntity;
    if (this.printTile == null || (Minecraft.func_71410_x()).field_71439_g == null)
      return; 
    GL11.glPushMatrix();
    GL11.glTranslated(i + 0.5D, j + 1.6D, k + 0.5D);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glRotated((Minecraft.func_71410_x()).field_71439_g.field_70177_z, 0.0D, 1.0D, 0.0D);
    GL11.glScaled(0.0625D, 0.0625D, 0.0625D);
    GL11.glScaled(0.3D, 0.3D, 0.3D);
    MovingObjectPosition lastPosition = (Minecraft.func_71410_x()).field_71439_g.func_70614_a(8.0D, 1.0F);
    if (lastPosition != null && MovingObjectPosition.MovingObjectType.BLOCK.equals(lastPosition.field_72313_a) && 
      this.printTile.field_145851_c == lastPosition.field_72311_b && this.printTile.field_145848_d == lastPosition.field_72312_c && this.printTile.field_145849_e == lastPosition.field_72309_d && this.printTile.pressInventory != null) {
      String encres = "§f" + ((this.printTile.pressInventory[0] == null) ? "0" : (String)Integer.valueOf((this.printTile.pressInventory[0]).field_77994_a)) + " Encres";
      String books = "§f" + ((this.printTile.pressInventory[2] == null) ? "0" : (String)Integer.valueOf((this.printTile.pressInventory[2]).field_77994_a)) + " Livres";
      int nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(encres);
      (Minecraft.func_71410_x()).field_71466_p.func_78276_b(encres, -nameSize / 2, -10, 16777215);
      (Minecraft.func_71410_x()).field_71466_p.func_78276_b(books, -nameSize / 2, -10 + 
          (Minecraft.func_71410_x()).field_71466_p.field_78288_b, 16777215);
    } 
    GL11.glPopMatrix();
    this.pressAngle = tileEntity.func_145832_p();
    this.emptyBooks = this.printTile.numberBlanks();
    this.finishedBook = this.printTile.hasSigned();
    this.hasPlate = this.printTile.hasPlate();
    this.hasEnchantedPlate = this.printTile.hasEnchantedPlate();
    this.hasEnchantedBook = this.printTile.hasEnchantedBook();
    this.inksize = this.printTile.getinkStackSize();
    int xTile = this.printTile.field_145851_c;
    int yTile = this.printTile.field_145848_d;
    int zTile = this.printTile.field_145849_e;
    float move1 = this.printTile.getmove1();
    boolean movetest1 = this.printTile.ismovetest1();
    float move2 = this.printTile.getmove2();
    boolean movetest2 = this.printTile.ismovetest2();
    boolean plateturn = this.printTile.isplateturn();
    boolean rollerturn = this.printTile.isrollerturn();
    switch (this.pressAngle) {
      case 0:
        this.degreeAngle = 270;
        this.iadjust = 0.3F;
        this.kadjust = 0.0F;
        break;
      case 1:
        this.degreeAngle = 180;
        this.iadjust = 0.15F;
        this.kadjust = 0.15F;
        break;
      case 2:
        this.degreeAngle = 90;
        this.iadjust = 0.0F;
        this.kadjust = 0.0F;
        break;
      case 3:
        this.degreeAngle = 0;
        this.iadjust = 0.15F;
        this.kadjust = -0.15F;
        break;
    } 
    GL11.glPushMatrix();
    GL11.glTranslated(i + 0.3499999940395355D + this.iadjust, j + 0.8500000238418579D + 0.30000001192092896D, k + 0.5D + this.kadjust);
    GL11.glRotatef(this.degreeAngle, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    switch (this.inksize) {
      case 1:
        func_147499_a(CommonProxy.INKPLATE1);
        break;
      case 2:
        func_147499_a(CommonProxy.INKPLATE2);
        break;
      case 3:
        func_147499_a(CommonProxy.INKPLATE3);
        break;
      case 4:
        func_147499_a(CommonProxy.INKPLATE4);
        break;
      case 5:
        func_147499_a(CommonProxy.INKPLATE5);
        break;
      case 6:
        func_147499_a(CommonProxy.INKPLATE6);
        break;
      case 7:
        func_147499_a(CommonProxy.INKPLATE7);
        break;
      case 8:
        func_147499_a(CommonProxy.INKPLATE8);
        break;
      default:
        func_147499_a(CommonProxy.INKPLATE0);
        break;
    } 
    this.inkplateModel.render();
    func_147499_a(CommonProxy.PRINTMACHINE_PNG);
    this.pressModel.renderMain();
    if (this.hasPlate)
      this.pressModel.renderPlate(); 
    if (this.printTile.isplateturn())
      if (move1 < 0.3F && !movetest1) {
        this.printTile.setMove1(move1 += 0.016666668F);
        move1 = this.printTile.getmove1();
        if (move1 >= 0.3F) {
          this.printTile.setmovetest1(true);
          movetest1 = this.printTile.ismovetest1();
        } 
      } else {
        this.printTile.setMove1(move1 -= 0.016666668F);
        if (move1 <= 0.28F) {
          this.printTile.setrollerturn(true);
          rollerturn = this.printTile.isrollerturn();
        } 
        if (move1 <= 0.0F) {
          this.printTile.setmovetest1(false);
          movetest1 = this.printTile.ismovetest1();
          this.printTile.setplateturn(false);
          plateturn = this.printTile.isplateturn();
        } 
      }  
    this.pressModel.press.field_78795_f = 0.5F - this.printTile.getmove1();
    this.pressModel.renderPress();
    if ((!this.printTile.getPressAntimation() || move2 >= 0.0F) && 
      this.printTile.isrollerturn())
      if (move2 < 1.7F && !movetest2) {
        this.printTile.setMove2(move2 += 0.071428575F);
        if (move2 >= 1.7F) {
          this.printTile.setmovetest2(true);
          movetest2 = this.printTile.ismovetest2();
        } 
      } else {
        this.printTile.setMove2(move2 -= 0.071428575F);
        if (move2 <= 1.4F) {
          this.printTile.setplateturn(true);
          plateturn = this.printTile.isplateturn();
        } 
        if (move2 <= 0.0F) {
          this.printTile.setmovetest2(false);
          movetest2 = this.printTile.ismovetest2();
          this.printTile.setrollerturn(false);
          rollerturn = this.printTile.isrollerturn();
        } 
      }  
    this.pressModel.inkRollers.field_78795_f = 0.12F + move2;
    this.pressModel.renderRollers();
    if (this.hasEnchantedBook) {
      func_147499_a(CommonProxy.PRESSBOOKS2_PNG);
    } else {
      func_147499_a(CommonProxy.PRESSBOOKS_PNG);
    } 
    if (this.emptyBooks > 0)
      this.bookModels.renderEmptyBooks(this.emptyBooks); 
    if (this.finishedBook)
      this.bookModels.renderBook(); 
    if (this.hasEnchantedPlate || this.hasEnchantedBook) {
      float tickModifier = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F * 48.0F;
      func_147499_a(CommonProxy.GLINT_PNG);
      GL11.glEnable(3042);
      float var20 = 0.5F;
      GL11.glColor4f(var20, var20, var20, 1.0F);
      GL11.glDepthFunc(514);
      GL11.glDepthMask(false);
      for (int var21 = 0; var21 < 2; var21++) {
        GL11.glDisable(2896);
        float var22 = 0.76F;
        GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
        GL11.glBlendFunc(768, 1);
        GL11.glMatrixMode(5890);
        GL11.glLoadIdentity();
        float var23 = tickModifier * (0.001F + var21 * 0.003F) * 20.0F;
        float var24 = 0.33333334F;
        GL11.glScalef(var24, var24, var24);
        GL11.glRotatef(30.0F - var21 * 60.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(0.0F, var23, 0.0F);
        GL11.glMatrixMode(5888);
        if (this.hasEnchantedBook)
          this.pressModel.renderBook(); 
        if (this.hasEnchantedPlate)
          this.pressModel.renderPlate(); 
      } 
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glMatrixMode(5890);
      GL11.glDepthMask(true);
      GL11.glLoadIdentity();
      GL11.glMatrixMode(5888);
      GL11.glEnable(2896);
      GL11.glDisable(3042);
      GL11.glDepthFunc(515);
    } 
    GL11.glEnable(2896);
    if (this.printTile.hasPlate() && this.printTile.getShowPlateText()) {
      String platename = this.printTile.plateName();
      if (this.hasEnchantedPlate) {
        renderText(platename, 0.0D, 0.0D, 0.0D, Color.RED.getRGB());
      } else {
        renderText(platename, 0.0D, 0.0D, 0.0D, Color.WHITE.getRGB());
      } 
      this.printTile.setShowPlateText(false);
    } 
    if (this.printTile.hasInk() && this.printTile.getShowInkText()) {
      int inkcount = (this.printTile.func_70301_a(0)).field_77994_a;
      String inkText = inkcount + " " + StatCollector.func_74838_a("print.ink");
      renderText(inkText, 0.0D, -16.0D, 10.0D, Color.WHITE.getRGB());
      this.printTile.setShowInkText(false);
    } 
    if (this.printTile.hasBlank() && this.printTile.getShowEmptyBookText()) {
      int bookcount = (this.printTile.func_70301_a(2)).field_77994_a;
      String bookText = bookcount + "  " + StatCollector.func_74838_a("print.book");
      renderText(bookText, -68.0D, -6.0D, -135.0D, Color.WHITE.getRGB());
      this.printTile.setShowEmptyBookText(false);
    } 
    GL11.glPopMatrix();
  }
  
  public void renderText(String text, double xadjust, double yadjust, double zadjust, int color) {
    FontRenderer fontRender = func_147498_b();
    GL11.glDepthMask(false);
    GL11.glScalef(0.005F, 0.005F, 0.005F);
    GL11.glTranslated(0.0D + xadjust, 20.0D + yadjust, -5.0D + zadjust);
    int adjust = fontRender.func_78256_a(text) / 2;
    fontRender.func_85187_a(text, -adjust, 0, color, false);
    GL11.glDepthMask(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\TileEntityPrintPressRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */