package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.palamod.modules.hunter.proxies.CommonProxy;
import fr.paladium.palamod.modules.paladium.client.model.ModelTypeset;
import fr.paladium.palamod.modules.paladium.client.model.ModelTypesetBase;
import fr.paladium.palamod.modules.paladium.client.model.ModelTypesetBook;
import fr.paladium.palamod.modules.paladium.common.blocks.TileEntityTypeMachine;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class TileEntityTypeMachineRenderer extends TileEntitySpecialRenderer {
  private ModelTypeset typeModel = new ModelTypeset();
  
  private ModelTypesetBook bookModel = new ModelTypesetBook();
  
  private ModelTypesetBase baseModel = new ModelTypesetBase();
  
  private TileEntityTypeMachine typeTile;
  
  private int typeAngle;
  
  private int degreeAngle;
  
  private float iadjust;
  
  private float kadjust;
  
  private int chaseNum;
  
  private boolean hasBook;
  
  private boolean bookSaved;
  
  private boolean hasNewPlate;
  
  private boolean hasOldPlate;
  
  private boolean hasBottomLeftChase;
  
  private String bookname;
  
  private boolean hasEnchantedBook;
  
  private boolean hasEnchantedPlate;
  
  private boolean hasAtlasBook;
  
  private boolean hasAtlasPlate;
  
  public void func_147500_a(TileEntity tileEntity, double i, double j, double k, float tick) {
    this.typeTile = (TileEntityTypeMachine)tileEntity;
    this.typeAngle = tileEntity.func_145832_p();
    this.chaseNum = this.typeTile.getChaseNum();
    this.hasBook = this.typeTile.signedBookCheck();
    this.bookSaved = this.typeTile.hasSavedBook();
    this.hasNewPlate = this.typeTile.hasNewPlate();
    this.hasOldPlate = this.typeTile.hasOldPlate();
    this.hasBottomLeftChase = this.typeTile.hasLowerChase();
    this.bookname = this.typeTile.getBookname();
    this.hasEnchantedBook = this.typeTile.enchantedBookCheck();
    this.hasEnchantedPlate = this.typeTile.hasEnchantedPlate();
    switch (this.typeAngle) {
      case 0:
        this.degreeAngle = 270;
        this.iadjust = -0.04F;
        this.kadjust = 0.0F;
        break;
      case 1:
        this.degreeAngle = 180;
        this.iadjust = -0.02F;
        this.kadjust = -0.02F;
        break;
      case 2:
        this.degreeAngle = 90;
        this.iadjust = 0.0F;
        this.kadjust = 0.0F;
        break;
      case 3:
        this.degreeAngle = 0;
        this.iadjust = -0.02F;
        this.kadjust = 0.02F;
        break;
    } 
    GL11.glPushMatrix();
    GL11.glTranslated(i + 0.5199999809265137D + this.iadjust, j + 0.6200000047683716D + 0.30000001192092896D, k + 0.5D + this.kadjust);
    GL11.glRotatef(this.degreeAngle, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    func_147499_a(CommonProxy.TYPESET_TOP);
    this.typeModel.renderTop();
    if (this.chaseNum > 0)
      this.typeModel.renderChaseStack(this.chaseNum); 
    if (this.hasNewPlate)
      this.typeModel.renderlowerRightPlate(); 
    if (this.hasOldPlate)
      this.typeModel.renderlowerLeftPlate(); 
    if (this.hasBottomLeftChase)
      this.typeModel.renderLeftChase(); 
    if (this.hasEnchantedPlate);
    func_147499_a(CommonProxy.TYPESET_BASE);
    this.baseModel.renderBase();
    if (this.hasBook) {
      if (this.bookSaved || this.hasAtlasBook) {
        func_147499_a(CommonProxy.TYPESET_BOOK2);
      } else {
        func_147499_a(CommonProxy.TYPESET_BOOK1);
      } 
      this.bookModel.renderBook();
    } 
    if (this.hasEnchantedBook) {
      func_147499_a(CommonProxy.TYPESET_BOOK3);
      this.bookModel.renderBook();
    } 
    if (this.hasEnchantedBook || this.hasEnchantedPlate || this.hasAtlasPlate) {
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
          this.bookModel.renderBook(); 
        if (this.hasEnchantedPlate || this.hasAtlasPlate)
          this.typeModel.renderlowerRightPlate(); 
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
    if (this.typeTile.getShowLevels()) {
      renderText(
          StatCollector.func_74838_a("typesetting.requires") + " " + this.typeTile.getLevels() + " " + 
          StatCollector.func_74838_a("typesetting.levels"), 0.0D, 0.0D, 0.0D, Color.WHITE
          .getRGB());
      if (this.hasEnchantedBook) {
        String ench1 = "";
        String ench2 = "";
        String ench3 = "";
        String ench4 = "";
        NBTTagList taglist = getEnchantmentTagList(this.typeTile.getEntchantedBook());
        int tagscount = 0;
        if (taglist != null)
          tagscount = taglist.func_74745_c(); 
        if (tagscount > 0) {
          short var7 = taglist.func_150305_b(0).func_74765_d("id");
          short var8 = taglist.func_150305_b(0).func_74765_d("lvl");
          if (Enchantment.field_77331_b[var7] != null)
            ench1 = Enchantment.field_77331_b[var7].func_77316_c(var8); 
        } 
        if (tagscount > 1) {
          short var17 = taglist.func_150305_b(1).func_74765_d("id");
          short var18 = taglist.func_150305_b(1).func_74765_d("lvl");
          if (Enchantment.field_77331_b[var17] != null)
            ench2 = Enchantment.field_77331_b[var17].func_77316_c(var18); 
        } 
        if (tagscount > 2) {
          short var17 = taglist.func_150305_b(2).func_74765_d("id");
          short var18 = taglist.func_150305_b(2).func_74765_d("lvl");
          if (Enchantment.field_77331_b[var17] != null)
            ench3 = Enchantment.field_77331_b[var17].func_77316_c(var18); 
        } 
        if (tagscount > 3) {
          short var17 = taglist.func_150305_b(3).func_74765_d("id");
          short var18 = taglist.func_150305_b(3).func_74765_d("lvl");
          if (Enchantment.field_77331_b[var17] != null)
            ench4 = Enchantment.field_77331_b[var17].func_77316_c(var18); 
        } 
        renderText(ench1, ench2, ench3, ench4, tagscount, 0.0D, 0.0D);
      } 
      this.typeTile.setShowLevels(false);
    } 
    if (this.typeTile.getShowBookname()) {
      renderText(this.bookname, 0.0D, 0.0D, 0.0D, Color.WHITE.getRGB());
      this.typeTile.setShowBookname(false);
    } 
    if (this.typeTile.getShowChaseText() && this.chaseNum > 0) {
      int chasesize = (this.typeTile.func_70301_a(1)).field_77994_a;
      String chaseText = chasesize + " " + StatCollector.func_74838_a("typesetting.chase");
      renderText(chaseText, 0.0D, 0.0D, 32.0D, Color.WHITE.getRGB());
      this.typeTile.setChaseText(false);
    } 
    GL11.glPopMatrix();
  }
  
  public void renderText(String text, double xadjust, double yadjust, double zadjust, int color) {
    FontRenderer fontRender = func_147498_b();
    GL11.glDepthMask(false);
    GL11.glScalef(0.005F, 0.005F, 0.005F);
    GL11.glTranslated(0.0D + xadjust, 0.0D + yadjust, -5.0D + zadjust);
    int adjust = fontRender.func_78256_a(text) / 2;
    fontRender.func_85187_a(text, -adjust, -8, color, true);
    GL11.glDepthMask(true);
  }
  
  public void renderText(String text, String text2, String text3, String text4, int numlines, double xadjust, double yadjust) {
    FontRenderer fontRender = func_147498_b();
    GL11.glDepthMask(false);
    GL11.glTranslated(-44.0D + xadjust, -2.0D + yadjust, -94.0D);
    int adjust = fontRender.func_78256_a(text) / 2;
    fontRender.func_85187_a(text, -adjust, 0, Color.WHITE.getRGB(), false);
    if (numlines > 1) {
      int adjust2 = fontRender.func_78256_a(text2) / 2;
      fontRender.func_85187_a(text2, -adjust2, 10, Color.WHITE.getRGB(), false);
    } 
    if (numlines > 2) {
      int adjust3 = fontRender.func_78256_a(text3) / 2;
      fontRender.func_85187_a(text3, -adjust3, 20, Color.WHITE.getRGB(), false);
    } 
    if (numlines > 3) {
      int adjust4 = fontRender.func_78256_a(text4) / 2;
      fontRender.func_85187_a(text4, -adjust4, 30, Color.WHITE.getRGB(), false);
    } 
    GL11.glDepthMask(true);
  }
  
  public NBTTagList getEnchantmentTagList(ItemStack stack) {
    return (stack.field_77990_d != null && stack.field_77990_d.func_74764_b("StoredEnchantments")) ? (NBTTagList)stack.field_77990_d
      .func_74781_a("StoredEnchantments") : new NBTTagList();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\TileEntityTypeMachineRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */