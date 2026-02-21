package fr.paladium.palamod.modules.back2future.client.renderer.tileentity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.client.model.ModelHead;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityFancySkullRenderer extends TileEntitySpecialRenderer {
  private static final ResourceLocation skeleton_texture = Utils.getResource("textures/entity/skeleton/skeleton.png");
  
  private static final ResourceLocation wither_skeleton_texture = Utils.getResource("textures/entity/skeleton/wither_skeleton.png");
  
  private static final ResourceLocation zombie_texture = Utils.getResource("textures/entity/zombie/zombie.png");
  
  private static final ResourceLocation creeper_texture = Utils.getResource("textures/entity/creeper/creeper.png");
  
  public static TileEntityFancySkullRenderer instance;
  
  private final ModelHead model1 = new ModelHead(32);
  
  private final ModelHead model2 = new ModelHead(64);
  
  public void func_147497_a(TileEntityRendererDispatcher dispatcher) {
    super.func_147497_a(dispatcher);
    instance = this;
  }
  
  public void renderSkull(float x, float y, float z, int meta, float rotation, int type, GameProfile profile) {
    ResourceLocation texture;
    ModelHead model = this.model1;
    switch (type) {
      default:
        func_147499_a(skeleton_texture);
        break;
      case 1:
        func_147499_a(wither_skeleton_texture);
        break;
      case 2:
        func_147499_a(zombie_texture);
        model = this.model2;
        break;
      case 3:
        texture = AbstractClientPlayer.field_110314_b;
        if (profile != null) {
          Minecraft minecraft = Minecraft.func_71410_x();
          Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.func_152342_ad().func_152788_a(profile);
          if (map.containsKey(MinecraftProfileTexture.Type.SKIN))
            texture = minecraft.func_152342_ad().func_152792_a(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN); 
        } 
        func_147499_a(texture);
        break;
      case 4:
        func_147499_a(creeper_texture);
        break;
    } 
    OpenGLHelper.pushMatrix();
    OpenGLHelper.disableCull();
    if (meta != 1) {
      switch (meta) {
        case 2:
          OpenGLHelper.translate(x + 0.5F, y + 0.25F, z + 0.74F);
          break;
        case 3:
          OpenGLHelper.translate(x + 0.5F, y + 0.25F, z + 0.26F);
          rotation = 180.0F;
          break;
        case 4:
          OpenGLHelper.translate(x + 0.74F, y + 0.25F, z + 0.5F);
          rotation = 270.0F;
          break;
        default:
          OpenGLHelper.translate(x + 0.26F, y + 0.25F, z + 0.5F);
          rotation = 90.0F;
          break;
      } 
    } else {
      GL11.glTranslatef(x + 0.5F, y, z + 0.5F);
    } 
    OpenGLHelper.enableRescaleNormal();
    OpenGLHelper.scale(-1.0F, -1.0F, 1.0F);
    OpenGLHelper.enableAlpha();
    model.render(rotation);
    OpenGLHelper.popMatrix();
  }
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTick) {
    TileEntitySkull skull = (TileEntitySkull)tile;
    renderSkull((float)x, (float)y, (float)z, tile.func_145832_p() & 0x7, (skull.func_145906_b() * 360) / 16.0F, skull.func_145904_a(), skull.func_152108_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\tileentity\TileEntityFancySkullRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */