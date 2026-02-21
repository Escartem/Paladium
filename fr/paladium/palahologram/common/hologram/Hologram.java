package fr.paladium.palahologram.common.hologram;

import com.google.common.util.concurrent.AtomicDouble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palahologram.common.hologram.element.HologramElement;
import fr.paladium.palahologram.common.hologram.element.IDrawable;
import fr.paladium.palahologram.common.hologram.element.INbt;
import fr.paladium.zephyrui.lib.opengl.GLHelper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.lwjgl.opengl.GL11;

public class Hologram implements IDrawable, INbt<Hologram> {
  private DoubleLocation location;
  
  public void setLocation(DoubleLocation location) {
    this.location = location;
  }
  
  public void setSpacing(double spacing) {
    this.spacing = spacing;
  }
  
  public void setFollowPlayerY(boolean followPlayerY) {
    this.followPlayerY = followPlayerY;
  }
  
  public void setFollowPlayerX(boolean followPlayerX) {
    this.followPlayerX = followPlayerX;
  }
  
  public void setAngle(double angle) {
    this.angle = angle;
  }
  
  public Hologram() {
    this.spacing = $default$spacing();
    this.followPlayerY = $default$followPlayerY();
    this.followPlayerX = $default$followPlayerX();
    this.angle = $default$angle();
  }
  
  public Hologram(DoubleLocation location, double spacing, boolean followPlayerY, boolean followPlayerX, double angle) {
    this.location = location;
    this.spacing = spacing;
    this.followPlayerY = followPlayerY;
    this.followPlayerX = followPlayerX;
    this.angle = angle;
  }
  
  private static double $default$spacing() {
    return 0.0D;
  }
  
  private static boolean $default$followPlayerY() {
    return true;
  }
  
  private static boolean $default$followPlayerX() {
    return true;
  }
  
  private static double $default$angle() {
    return 0.0D;
  }
  
  public static HologramBuilder builder() {
    return new HologramBuilder();
  }
  
  public static class HologramBuilder {
    private DoubleLocation location;
    
    private boolean spacing$set;
    
    private double spacing$value;
    
    private boolean followPlayerY$set;
    
    private boolean followPlayerY$value;
    
    private boolean followPlayerX$set;
    
    private boolean followPlayerX$value;
    
    private boolean angle$set;
    
    private double angle$value;
    
    public HologramBuilder location(DoubleLocation location) {
      this.location = location;
      return this;
    }
    
    public HologramBuilder spacing(double spacing) {
      this.spacing$value = spacing;
      this.spacing$set = true;
      return this;
    }
    
    public HologramBuilder followPlayerY(boolean followPlayerY) {
      this.followPlayerY$value = followPlayerY;
      this.followPlayerY$set = true;
      return this;
    }
    
    public HologramBuilder followPlayerX(boolean followPlayerX) {
      this.followPlayerX$value = followPlayerX;
      this.followPlayerX$set = true;
      return this;
    }
    
    public HologramBuilder angle(double angle) {
      this.angle$value = angle;
      this.angle$set = true;
      return this;
    }
    
    public Hologram build() {
      double spacing$value = this.spacing$value;
      if (!this.spacing$set)
        spacing$value = Hologram.$default$spacing(); 
      boolean followPlayerY$value = this.followPlayerY$value;
      if (!this.followPlayerY$set)
        followPlayerY$value = Hologram.$default$followPlayerY(); 
      boolean followPlayerX$value = this.followPlayerX$value;
      if (!this.followPlayerX$set)
        followPlayerX$value = Hologram.$default$followPlayerX(); 
      double angle$value = this.angle$value;
      if (!this.angle$set)
        angle$value = Hologram.$default$angle(); 
      return new Hologram(this.location, spacing$value, followPlayerY$value, followPlayerX$value, angle$value);
    }
    
    public String toString() {
      return "Hologram.HologramBuilder(location=" + this.location + ", spacing$value=" + this.spacing$value + ", followPlayerY$value=" + this.followPlayerY$value + ", followPlayerX$value=" + this.followPlayerX$value + ", angle$value=" + this.angle$value + ")";
    }
  }
  
  public DoubleLocation getLocation() {
    return this.location;
  }
  
  private final List<HologramElement> elements = new ArrayList<>();
  
  private double spacing;
  
  private boolean followPlayerY;
  
  private boolean followPlayerX;
  
  private double angle;
  
  public List<HologramElement> getElements() {
    return this.elements;
  }
  
  public double getSpacing() {
    return this.spacing;
  }
  
  public boolean isFollowPlayerY() {
    return this.followPlayerY;
  }
  
  public boolean isFollowPlayerX() {
    return this.followPlayerX;
  }
  
  public double getAngle() {
    return this.angle;
  }
  
  public void appendElement(HologramElement element) {
    this.elements.add(element);
  }
  
  public void insertElement(int index, HologramElement element) {
    this.elements.add(index, element);
  }
  
  @SideOnly(Side.CLIENT)
  public void draw() {
    if (this.elements.isEmpty() || this.location.distance((Entity)(Minecraft.func_71410_x()).field_71439_g) > ((Minecraft.func_71410_x()).field_71474_y.field_151451_c << 4))
      return; 
    GLHelper.pushMatrix();
    double sumHeight = ((Double)this.elements.stream().map(HologramElement::getHeight).reduce(Double.valueOf(0.0D), Double::sum)).doubleValue() + this.spacing * (this.elements.size() - 1);
    double posX = this.location.getX() - RenderManager.field_78725_b;
    double posY = this.location.getY() - RenderManager.field_78726_c + sumHeight;
    double posZ = this.location.getZ() - RenderManager.field_78723_d;
    AtomicDouble oy = new AtomicDouble(0.0D);
    GL11.glTranslated(posX, posY, posZ);
    this.elements.forEach(elem -> {
          GLHelper.pushMatrix();
          if (this.followPlayerX) {
            GL11.glRotated(-(Minecraft.func_71410_x()).field_71439_g.field_70177_z, 0.0D, 1.0D, 0.0D);
          } else {
            GL11.glRotated(getAngle() % 360.0D, 0.0D, 1.0D, 0.0D);
          } 
          if (this.followPlayerY) {
            GL11.glTranslated(0.0D, -(oy.get() + sumHeight), 0.0D);
            GL11.glRotated((Minecraft.func_71410_x()).field_71439_g.field_70125_A, 1.0D, 0.0D, 0.0D);
            GL11.glTranslated(0.0D, oy.get() + sumHeight, 0.0D);
          } 
          if (!this.followPlayerX && !this.followPlayerY) {
            GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
            elem.draw();
            GL11.glRotated(-180.0D, 0.0D, 1.0D, 0.0D);
          } 
          if (!this.followPlayerX && this.followPlayerY) {
            double mappedAngleRad = Math.toRadians(-this.angle);
            double playerX = (Minecraft.func_71410_x()).field_71439_g.field_70165_t;
            double playerZ = (Minecraft.func_71410_x()).field_71439_g.field_70161_v;
            double normalX = -Math.sin(mappedAngleRad);
            double normalZ = Math.cos(mappedAngleRad);
            double dotProduct = (playerX - this.location.getX()) * normalX + (playerZ - this.location.getZ()) * normalZ;
            if (dotProduct > 0.0D) {
              GL11.glTranslated(0.0D, -(oy.get() + sumHeight), 0.0D);
              GL11.glRotated(-((Minecraft.func_71410_x()).field_71439_g.field_70125_A * 2.0D), 1.0D, 0.0D, 0.0D);
              GL11.glTranslated(0.0D, oy.get() + sumHeight, 0.0D);
              GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
              elem.draw();
              GL11.glRotated(-180.0D, 0.0D, 1.0D, 0.0D);
              GL11.glTranslated(0.0D, -(elem.getHeight() + this.spacing), 0.0D);
              return;
            } 
          } 
          elem.draw();
          GLHelper.popMatrix();
          oy.addAndGet(-(elem.getHeight() + this.spacing));
          GL11.glTranslated(0.0D, -(elem.getHeight() + this.spacing), 0.0D);
        });
    GLHelper.popMatrix();
  }
  
  public Hologram read(NBTTagCompound nbt) {
    this.location = new DoubleLocation(nbt.func_74769_h("posX"), nbt.func_74769_h("posY"), nbt.func_74769_h("posZ"));
    NBTTagList list = nbt.func_150295_c("elements", 10);
    for (int i = 0; i < list.func_74745_c(); i++) {
      NBTTagCompound tag = list.func_150305_b(i);
      String clazzName = tag.func_74779_i("clazzName");
      try {
        Class<?> clazz = Class.forName(clazzName, false, Thread.currentThread().getContextClassLoader());
        if (HologramElement.class.isAssignableFrom(clazz)) {
          HologramElement hologramElement = ((HologramElement)clazz.newInstance()).read(tag);
          this.elements.add(hologramElement);
        } 
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
    this.spacing = nbt.func_74769_h("spacing");
    this.followPlayerY = nbt.func_74767_n("followPlayerY");
    this.followPlayerX = nbt.func_74767_n("followPlayerX");
    this.angle = nbt.func_74769_h("angle");
    return this;
  }
  
  public void write(NBTTagCompound nbt) {
    nbt.func_74780_a("posX", this.location.getX());
    nbt.func_74780_a("posY", this.location.getY());
    nbt.func_74780_a("posZ", this.location.getZ());
    NBTTagList nbtList = new NBTTagList();
    this.elements.forEach(elem -> {
          NBTTagCompound elemTag = new NBTTagCompound();
          elem.write(elemTag);
          nbtList.func_74742_a((NBTBase)elemTag);
        });
    nbt.func_74782_a("elements", (NBTBase)nbtList);
    nbt.func_74780_a("spacing", this.spacing);
    nbt.func_74757_a("followPlayerY", this.followPlayerY);
    nbt.func_74757_a("followPlayerX", this.followPlayerX);
    nbt.func_74780_a("angle", this.angle);
  }
  
  public String toString() {
    return "x:" + this.location.getX() + ",y:" + this.location.getY() + ",z:" + this.location.getZ() + ",spacing:" + this.spacing + ",followPlayerY:" + this.followPlayerY + ",elementSize:" + this.elements.size();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\common\hologram\Hologram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */