package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.palamod.modules.paladium.client.model.ModelAlchemyCreator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class AlchemyCreatorRender extends TileEntitySpecialRenderer {
  private final ModelAlchemyCreator model = new ModelAlchemyCreator();
  
  private final ResourceLocation textures = new ResourceLocation("palamod:textures/blocks/machines/alchemy_creator_block.png");
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float f) {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.textures);
    GL11.glPushMatrix();
    int meta = (Minecraft.func_71410_x()).field_71441_e.func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    int id = 0;
    switch (meta) {
      case 0:
        id = 0;
        break;
      case 4:
        id = 3;
        break;
      case 3:
        id = 2;
        break;
      case 5:
        id = 1;
        break;
    } 
    GL11.glRotatef((id * -90), 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    this.model.func_78088_a((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    GL11.glPopMatrix();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\AlchemyCreatorRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */