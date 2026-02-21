package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.modules.luckyblock.CommonProxy;
import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelClipboard;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityClipboardRenderer extends TileEntitySpecialRenderer {
  private ModelClipboard model = new ModelClipboard();
  
  private int angle = 0;
  
  private TileEntityClipboard tile;
  
  private int button0state = 0;
  
  private int button1state = 0;
  
  private int button2state = 0;
  
  private int button3state = 0;
  
  private int button4state = 0;
  
  private int button5state = 0;
  
  private int button6state = 0;
  
  private int button7state = 0;
  
  private int button8state = 0;
  
  private String button0text = " ";
  
  private String button1text = " ";
  
  private String button2text = " ";
  
  private String button3text = " ";
  
  private String button4text = " ";
  
  private String button5text = " ";
  
  private String button6text = " ";
  
  private String button7text = " ";
  
  private String button8text = " ";
  
  private String titletext = " ";
  
  private int currentPage = 1;
  
  private double textSpacing = -0.0658D;
  
  private float boxSpacing = -0.0658F;
  
  public void func_147500_a(TileEntity tileEntity, double i, double j, double k, float tick) {
    this.tile = (TileEntityClipboard)tileEntity;
    if (this.tile != null) {
      this.angle = this.tile.getAngle();
      this.button0state = this.tile.button0state;
      this.button1state = this.tile.button1state;
      this.button2state = this.tile.button2state;
      this.button3state = this.tile.button3state;
      this.button4state = this.tile.button4state;
      this.button5state = this.tile.button5state;
      this.button6state = this.tile.button6state;
      this.button7state = this.tile.button7state;
      this.button8state = this.tile.button8state;
    } 
    GL11.glPushMatrix();
    GL11.glTranslated(i + 0.5D, j, k + 0.5D);
    switch (this.angle) {
      case 0:
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        break;
      case 1:
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        break;
      case 3:
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        break;
    } 
    GL11.glTranslated(-0.5D, 0.0D, 0.0D);
    func_147499_a(CommonProxy.CLIPBOARD_BLOCK);
    this.model.renderClipboard();
    switch (this.button0state) {
      case 1:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_CHECK);
        this.model.renderCheckBox();
        break;
      case 2:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_X);
        this.model.renderCheckBox();
        break;
    } 
    GL11.glTranslatef(0.0F, this.boxSpacing, 0.0F);
    switch (this.button1state) {
      case 1:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_CHECK);
        this.model.renderCheckBox();
        break;
      case 2:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_X);
        this.model.renderCheckBox();
        break;
    } 
    GL11.glTranslatef(0.0F, this.boxSpacing, 0.0F);
    switch (this.button2state) {
      case 1:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_CHECK);
        this.model.renderCheckBox();
        break;
      case 2:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_X);
        this.model.renderCheckBox();
        break;
    } 
    GL11.glTranslatef(0.0F, this.boxSpacing, 0.0F);
    switch (this.button3state) {
      case 1:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_CHECK);
        this.model.renderCheckBox();
        break;
      case 2:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_X);
        this.model.renderCheckBox();
        break;
    } 
    GL11.glTranslatef(0.0F, this.boxSpacing, 0.0F);
    switch (this.button4state) {
      case 1:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_CHECK);
        this.model.renderCheckBox();
        break;
      case 2:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_X);
        this.model.renderCheckBox();
        break;
    } 
    GL11.glTranslatef(0.0F, this.boxSpacing, 0.0F);
    switch (this.button5state) {
      case 1:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_CHECK);
        this.model.renderCheckBox();
        break;
      case 2:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_X);
        this.model.renderCheckBox();
        break;
    } 
    GL11.glTranslatef(0.0F, this.boxSpacing, 0.0F);
    switch (this.button6state) {
      case 1:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_CHECK);
        this.model.renderCheckBox();
        break;
      case 2:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_X);
        this.model.renderCheckBox();
        break;
    } 
    GL11.glTranslatef(0.0F, this.boxSpacing, 0.0F);
    switch (this.button7state) {
      case 1:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_CHECK);
        this.model.renderCheckBox();
        break;
      case 2:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_X);
        this.model.renderCheckBox();
        break;
    } 
    GL11.glTranslatef(0.0F, this.boxSpacing, 0.0F);
    switch (this.button8state) {
      case 1:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_CHECK);
        this.model.renderCheckBox();
        break;
      case 2:
        func_147499_a(CommonProxy.CLIPBOARD_BOX_X);
        this.model.renderCheckBox();
        break;
    } 
    GL11.glPopMatrix();
    if (this.tile != null) {
      renderText(this.tile.titletext, i, j, k, 0.037D, 0.825D, 0.27D);
      renderText(this.tile.button0text, i, j, k, 0.037D, 0.76D, 0.222D);
      renderText(this.tile.button1text, i, j, k, 0.037D, 0.76D + 1.0D * this.textSpacing, 0.222D);
      renderText(this.tile.button2text, i, j, k, 0.037D, 0.76D + 2.0D * this.textSpacing, 0.222D);
      renderText(this.tile.button3text, i, j, k, 0.037D, 0.76D + 3.0D * this.textSpacing, 0.222D);
      renderText(this.tile.button4text, i, j, k, 0.037D, 0.76D + 4.0D * this.textSpacing, 0.222D);
      renderText(this.tile.button5text, i, j, k, 0.037D, 0.76D + 5.0D * this.textSpacing, 0.222D);
      renderText(this.tile.button6text, i, j, k, 0.037D, 0.76D + 6.0D * this.textSpacing, 0.222D);
      renderText(this.tile.button7text, i, j, k, 0.037D, 0.76D + 7.0D * this.textSpacing, 0.222D);
      renderText(this.tile.button8text, i, j, k, 0.037D, 0.76D + 8.0D * this.textSpacing, 0.222D);
      String pageNum = "" + this.tile.currentPage;
      if (this.tile.currentPage > 9) {
        renderText(pageNum, i, j, k, 0.037D, 0.17D, 0.03D);
      } else {
        renderText(pageNum, i, j, k, 0.037D, 0.17D, 0.02D);
      } 
    } 
  }
  
  public void renderText(String text, double i, double j, double k, double iAdjust, double jAdjust, double kAdjust) {
    FontRenderer fontRender = func_147498_b();
    GL11.glPushMatrix();
    GL11.glTranslated(i + 0.5D, j, k + 0.5D);
    switch (this.angle) {
      case 0:
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        break;
      case 1:
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        break;
      case 3:
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        break;
    } 
    GL11.glTranslated(-0.5D + iAdjust, jAdjust, kAdjust);
    GL11.glDepthMask(false);
    GL11.glScalef(0.0045F, 0.0045F, 0.0045F);
    GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    fontRender.func_85187_a(text, 0, 0, 0, false);
    GL11.glDepthMask(true);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityClipboardRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */