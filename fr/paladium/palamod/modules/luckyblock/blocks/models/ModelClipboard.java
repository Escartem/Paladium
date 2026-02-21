package fr.paladium.palamod.modules.luckyblock.blocks.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class ModelClipboard {
  private IModelCustom modelBlock = AdvancedModelLoader.loadModel(new ResourceLocation("bibliocraft", "models/clipboard.obj"));
  
  public void renderBlock() {
    this.modelBlock.renderAll();
  }
  
  public void renderClipboard() {
    this.modelBlock.renderPart("clipboard");
  }
  
  public void renderCheckBox() {
    this.modelBlock.renderPart("box");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelClipboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */