package fr.paladium.pet.client.ui.skill;

import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.client.PetClientProxy;
import fr.paladium.pet.client.ui.utils.Polygon2D;
import fr.paladium.pet.client.ui.utils.data.SkillRollSlotData;
import glm.mat._4.Mat4;
import glm.vec._3.Vec3;
import glm.vec._4.Vec4;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class SkillSelector {
  private static ResourceLocation text = new ResourceLocation("palapet", "textures/ui/selector/sideGui.png");
  
  private static ResourceLocation selector = new ResourceLocation("palapet", "textures/ui/selector/selector.png");
  
  private ArrayList<Boolean> isHover = new ArrayList<>(
      Collections.nCopies(7, Boolean.valueOf(false)));
  
  public ArrayList<Boolean> getIsHover() {
    return this.isHover;
  }
  
  private double selectorAngle = -1.0D;
  
  private double targetSelectorAngle = -1.0D;
  
  private static double mouseAngle(int x, int y, int mx, int my) {
    return (Math.atan2((my - y), (mx - x)) + 6.283185307179586D) % 6.283185307179586D;
  }
  
  public void draw(int mx, int my, float partialTick, RenderGameOverlayEvent event) {
    int fps = Integer.parseInt((Minecraft.func_71410_x()).field_71426_K.split(" fps")[0]);
    double selectorAngleDiff = this.targetSelectorAngle - this.selectorAngle;
    double absSelectorAngleDiff = Math.abs(selectorAngleDiff);
    double selectorAngleOffset = 0.2D / (((fps == 0) ? true : fps) / 60.0F) * absSelectorAngleDiff / 3.0D;
    if (absSelectorAngleDiff > 0.2D) {
      this.selectorAngle += (selectorAngleDiff > 0.0D) ? selectorAngleOffset : -selectorAngleOffset;
    } else {
      this.selectorAngle = this.targetSelectorAngle;
    } 
    int x = event.resolution.func_78326_a() / 2;
    int y = event.resolution.func_78328_b() / 2;
    float f1 = (Minecraft.func_71410_x()).field_71474_y.field_74341_c * 0.6F + 0.2F;
    int angle = (int)(event.resolution.func_78327_c() * 0.271D);
    int circles = 6;
    drawCircle(x, y, angle, circles, mx, my, event);
    drawSelector(x, y, angle, circles, mx, my, event);
  }
  
  public void drawSelector(double x, double y, int radius, int segments, int mx, int my, RenderGameOverlayEvent event) {
    double angle = mouseAngle((int)x, (int)y, mx, my);
    float degPer = 6.2831855F / segments;
    boolean foundSelector = false;
    for (int seg = 0; seg < segments; seg++) {
      boolean mouseInSector = ((degPer * seg) < angle && angle < (degPer * (seg + 1)));
      if (mouseInSector && ((Boolean)this.isHover.get(seg)).booleanValue()) {
        if ((60 * seg) - this.targetSelectorAngle < -60.0D) {
          this.selectorAngle = this.targetSelectorAngle - 360.0D;
          this.targetSelectorAngle = (60 * seg);
        } else if ((60 * seg) - this.targetSelectorAngle > 60.0D) {
          this.selectorAngle = 360.0D - this.targetSelectorAngle;
          this.targetSelectorAngle = (60 * seg);
        } else {
          this.targetSelectorAngle = (60 * seg);
        } 
        foundSelector = true;
        break;
      } 
    } 
    if (foundSelector) {
      float width = (float)(event.resolution.func_78327_c() * 0.07559999823570251D);
      float height = (float)(event.resolution.func_78324_d() * 0.07559999823570251D);
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
      GL11.glTranslated(event.resolution.func_78327_c() / 2.0D, event.resolution.func_78324_d() / 2.0D, 100.0D);
      GL11.glRotated(98.0D + this.selectorAngle, 0.0D, 0.0D, 1.0D);
      GL11.glTranslated(-event.resolution.func_78327_c() / 2.0D, -event.resolution.func_78324_d() / 2.0D, 100.0D);
      GuiUtils.drawImageTransparent(event.resolution.func_78327_c() / 100.0D * 50.0D, event.resolution.func_78324_d() / 100.0D * 31.0D, selector, width, height);
      GL11.glPopMatrix();
    } 
  }
  
  public void drawCircle(double x, double y, int radius, int segments, int mx, int my, RenderGameOverlayEvent event) {
    float degPer = 6.2831855F / segments;
    (Minecraft.func_71410_x()).field_71424_I.func_76320_a("root.DrawWheel");
    boolean hover = false;
    for (int seg = 0; seg < segments; seg++) {
      float offsetHoverW = 1.0F;
      float offsetHoverH = 1.0F;
      float offsetZ = 100.0F;
      float rad = (seg + 0.5F) * degPer;
      float xp = (float)(x + Math.cos(rad) * radius);
      float yp = (float)(y + Math.sin(rad) * radius);
      ResourceLocation texture = text;
      Color wheelColor = new Color(10, 10, 20, 170);
      if (((Boolean)this.isHover.get(seg)).booleanValue() && !hover) {
        hover = true;
        offsetHoverW = 1.1315F;
        offsetHoverH = 1.1315F;
        offsetZ = 150.0F;
        xp = (float)(x + Math.cos(rad) * (radius + 2));
        yp = (float)(y + Math.sin(rad) * (radius + 2));
        wheelColor = new Color(239, 57, 38);
      } 
      SkillRollSlotData data = PetClientProxy.getInstance().findSkillRollData(seg);
      if (data != null && data.isUsageCooldown())
        wheelColor = new Color(255, 165, 0); 
      float xsp = xp;
      float ysp = yp;
      double mod = 1.0D;
      int xdp = (int)((xp - x) * mod + x);
      int ydp = (int)((yp - y) * mod + y);
      if (xsp < x)
        xsp -= 0.0F; 
      if (ysp < y)
        ysp -= 0.0F; 
      mod = 0.6D;
      xdp = (int)((xp - x) * mod + x);
      ydp = (int)((yp - y) * mod + y);
      (Minecraft.func_71410_x()).field_71424_I.func_76320_a("root.DrawWheelsegment");
      GL11.glPushMatrix();
      float width = (float)(event.resolution.func_78327_c() * 0.1340000033378601D * offsetHoverW);
      float height = (float)(event.resolution.func_78324_d() * 0.19869999587535858D * offsetHoverH);
      FloatBuffer matrice = BufferUtils.createFloatBuffer(16);
      Vec3 pos1 = new Vec3(xdp - width / 2.29D, ydp - height / 2.18D, 300.0D);
      Vec3 pos2 = new Vec3(xdp + width * 0.5403D - width / 2.29D, ydp + height * 0.1461D - height / 2.18D, 300.0D);
      Vec3 pos3 = new Vec3((xdp + width) - width / 2.29D, ydp + height * 0.5296D - height / 2.18D, 300.0D);
      Vec3 pos4 = new Vec3(xdp + width * 0.6282D - width / 2.29D, (ydp + height) - height / 2.18D, 300.0D);
      Vec3 pos5 = new Vec3(xdp - width / 2.29D, ydp + height * 0.6696D - height / 2.18D, 300.0D);
      GL11.glTranslated(xdp, ydp, offsetZ);
      GL11.glRotated(37.0D + 360.0D / segments * (seg + 1), 0.0D, 0.0D, 1.0D);
      GL11.glTranslated(-xdp, -ydp, offsetZ);
      wheelColor.bind();
      GuiUtils.drawImageTransparent(xdp - width / 2.29D, ydp - height / 2.18D, texture, width, height);
      Color.WHITE.bind();
      GL11.glGetFloat(2982, matrice);
      float[] f = new float[16];
      matrice.get(f);
      Mat4 view = new Mat4(f);
      Vec4 pos1rot = view.mul(new Vec4(pos1, 1.0F));
      Vec4 pos2rot = view.mul(new Vec4(pos2, 1.0F));
      Vec4 pos3rot = view.mul(new Vec4(pos3, 1.0F));
      Vec4 pos4rot = view.mul(new Vec4(pos4, 1.0F));
      Vec4 pos5rot = view.mul(new Vec4(pos5, 1.0F));
      Polygon2D polygon = new Polygon2D();
      polygon.addPoint(pos1rot.x, pos1rot.y);
      polygon.addPoint(pos2rot.x, pos2rot.y);
      polygon.addPoint(pos3rot.x, pos3rot.y);
      polygon.addPoint(pos4rot.x, pos4rot.y);
      polygon.addPoint(pos5rot.x, pos5rot.y);
      if (polygon.contains(mx, my)) {
        this.isHover.set(seg, Boolean.valueOf(true));
      } else {
        this.isHover.set(seg, Boolean.valueOf(false));
      } 
      ResourceLocation logo = PetClientProxy.getInstance().findSkillRollData(seg).getLogo();
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      float pictWidth = (float)(event.resolution.func_78327_c() * 0.05090000107884407D);
      float pictHeight = (float)(event.resolution.func_78324_d() * 0.10719999670982361D);
      GL11.glTranslated((xdp - pictWidth / 2.0F), (ydp - pictHeight / 2.0F), (offsetZ * 2.3F));
      GuiUtils.drawImageTransparent(0.0D, 0.0D, logo, pictWidth, pictHeight, false);
      GL11.glPopMatrix();
      (Minecraft.func_71410_x()).field_71424_I.func_76319_b();
    } 
    (Minecraft.func_71410_x()).field_71424_I.func_76319_b();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\skill\SkillSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */