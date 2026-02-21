package fr.paladium.palahologram.common.hologram.element.impl;

import fr.paladium.palahologram.common.hologram.element.HologramElement;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

public class ResourceHologramElement extends HologramElement {
  private static final float SCALE = 0.02666667F;
  
  public void setResource(Resource resource) {
    this.resource = resource;
  }
  
  public void setResourceUrl(String resourceUrl) {
    this.resourceUrl = resourceUrl;
  }
  
  public void setWidth(double width) {
    this.width = width;
  }
  
  public void setHeight(double height) {
    this.height = height;
  }
  
  private transient Resource resource = null;
  
  private String resourceUrl;
  
  public Resource getResource() {
    return this.resource;
  }
  
  public String getResourceUrl() {
    return this.resourceUrl;
  }
  
  private double width = 0.0D;
  
  public double getWidth() {
    return this.width;
  }
  
  private double height = 0.0D;
  
  public ResourceHologramElement(String resourceUrl) {
    this.resourceUrl = resourceUrl;
  }
  
  public void draw() {
    if (this.resource == null) {
      this.resource = Resource.of(this.resourceUrl, resource -> loadResource()).nearest();
      loadResource();
    } 
    if (this.width == 0.0D || this.height == 0.0D)
      return; 
    double alignment = (getAlignment() == Align.START) ? 0.0D : ((getAlignment() == Align.CENTER) ? 0.5D : 1.0D);
    GL11.glTranslated(this.width * 0.0266666691750288D * alignment, 0.0D, 0.0D);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    DrawUtils.RESOURCE.drawImage(0.0D, 0.0D, this.width * 0.0266666691750288D, this.height * 0.0266666691750288D, this.resource);
    GL11.glRotated(-180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glTranslated(-(this.width * 0.0266666691750288D * alignment), 0.0D, 0.0D);
  }
  
  private void loadResource() {
    if (this.resource == null) {
      if (this.width == 0.0D)
        this.width = this.resource.getWidth(); 
      if (this.height == 0.0D)
        this.height = this.resource.getHeight(); 
    } else {
      if (this.width == 0.0D)
        this.width = this.resource.getWidth(); 
      if (this.height == 0.0D)
        this.height = this.resource.getHeight(); 
    } 
  }
  
  public double getHeight() {
    return this.height * 0.0266666691750288D;
  }
  
  public HologramElement read(NBTTagCompound nbt) {
    super.read(nbt);
    this.resourceUrl = nbt.func_74779_i("url");
    this.width = nbt.func_74769_h("width");
    this.height = nbt.func_74769_h("height");
    return this;
  }
  
  public void write(NBTTagCompound nbt) {
    super.write(nbt);
    nbt.func_74778_a("url", this.resourceUrl);
    nbt.func_74780_a("width", this.width);
    nbt.func_74780_a("height", this.height);
  }
  
  public ResourceHologramElement() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\common\hologram\element\impl\ResourceHologramElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */