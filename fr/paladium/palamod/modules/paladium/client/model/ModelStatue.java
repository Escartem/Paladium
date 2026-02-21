package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelStatue {
  public IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("palamod", "models/statue.obj"));
  
  public void renderSkin() {
    this.model.renderPart("skin");
  }
  
  public void renderSocle() {
    this.model.renderPart("s");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelStatue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */