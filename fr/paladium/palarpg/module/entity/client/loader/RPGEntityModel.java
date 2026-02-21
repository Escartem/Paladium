package fr.paladium.palarpg.module.entity.client.loader;

import fr.paladium.palaforgeutils.lib.resource.FileResourceLocation;
import fr.paladium.palarpg.module.entity.EntityModule;
import java.io.File;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;

public class RPGEntityModel {
  private transient String id;
  
  private transient GeoModel geoModel;
  
  private transient AnimationFile animationFile;
  
  private transient FileResourceLocation textureResource;
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setGeoModel(GeoModel geoModel) {
    this.geoModel = geoModel;
  }
  
  public void setAnimationFile(AnimationFile animationFile) {
    this.animationFile = animationFile;
  }
  
  public void setTextureResource(FileResourceLocation textureResource) {
    this.textureResource = textureResource;
  }
  
  public String getId() {
    return this.id;
  }
  
  public GeoModel getGeoModel() {
    return this.geoModel;
  }
  
  public AnimationFile getAnimationFile() {
    return this.animationFile;
  }
  
  public FileResourceLocation getTextureResource() {
    return this.textureResource;
  }
  
  private String model = "model.json";
  
  public String getModel() {
    return this.model;
  }
  
  private String animation = "animation.json";
  
  public String getAnimation() {
    return this.animation;
  }
  
  private String texture = "texture.png";
  
  public String getTexture() {
    return this.texture;
  }
  
  public RPGEntityModel setModel(String model) {
    this.model = model;
    return this;
  }
  
  public RPGEntityModel setAnimation(String animation) {
    this.animation = animation;
    return this;
  }
  
  public RPGEntityModel setTexture(String texture) {
    this.texture = texture;
    return this;
  }
  
  public void onLoad(File dir) {
    if (this.texture == null)
      throw new RuntimeException("Unable to find texture configuration"); 
    this.textureResource = new FileResourceLocation(new File(dir, this.texture));
    if (!this.textureResource.getFile().exists())
      throw new RuntimeException("Unable to find texture file: " + this.textureResource.getFile().getAbsolutePath()); 
    if (this.model == null)
      throw new RuntimeException("Unable to find model configuration"); 
    File modelFile = new File(dir, this.model);
    if (!modelFile.exists())
      throw new RuntimeException("Unable to find model file: " + modelFile.getAbsolutePath()); 
    this.geoModel = LindwormLoader.loadModel(modelFile);
    if (this.animation == null || this.animation.trim().isEmpty()) {
      EntityModule.logger.info("No animation file set for the model %s", new Object[] { this.id });
      return;
    } 
    File animFile = new File(dir, this.animation);
    if (!animFile.exists()) {
      EntityModule.logger.info("Unable to find animation file %s, for the model %s ", new Object[] { animFile.getAbsolutePath(), this.id });
      return;
    } 
    this.animationFile = LindwormLoader.loadAnimation(animFile);
  }
  
  public boolean hasAnimation() {
    return (this.animationFile != null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\client\loader\RPGEntityModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */