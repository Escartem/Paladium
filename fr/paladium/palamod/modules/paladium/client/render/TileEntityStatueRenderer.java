package fr.paladium.palamod.modules.paladium.client.render;

import com.mojang.authlib.GameProfile;
import fr.paladium.palamod.modules.paladium.client.model.ModelStatue;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityStatue;
import fr.paladium.palamod.modules.paladium.utils.AbstractSteve;
import fr.paladium.palamod.util.FastUUID;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityStatueRenderer extends TileEntitySpecialRenderer {
  public static final ResourceLocation QUARTZ_BLOCK = new ResourceLocation("textures/blocks/quartz_block_bottom.png");
  
  public ModelStatue model = new ModelStatue();
  
  public TileEntityStatue tile;
  
  public int angle;
  
  public int degreeAngle;
  
  private float iadjust;
  
  private float kadjust;
  
  private Map<String, AbstractClientPlayer> skinList = new HashMap<>();
  
  private boolean renderSteve = false;
  
  private boolean helmLeatherLayer = false;
  
  private boolean legLeatherLayer = false;
  
  public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float ticks) {
    this.tile = (TileEntityStatue)tileEntity;
    this.angle = this.tile.getAngle();
    switch (this.angle) {
      case 0:
        this.degreeAngle = 270;
        this.iadjust = 0.5F;
        this.kadjust = 0.5F;
        break;
      case 1:
        this.degreeAngle = 180;
        this.iadjust = 0.0F;
        this.kadjust = 0.5F;
        break;
      case 2:
        this.degreeAngle = 90;
        this.iadjust = 0.0F;
        this.kadjust = 0.0F;
        break;
      case 3:
        this.degreeAngle = 0;
        this.iadjust = 0.5F;
        this.kadjust = 0.0F;
        break;
    } 
    TileEntityStatue standTile = (TileEntityStatue)tileEntity;
    AbstractSteve steve = null;
    if (standTile.getPlayerUUID() != null && !standTile.getPlayerUUID().isEmpty() && 
      !this.skinList.containsKey(standTile.getPlayerUUID())) {
      GameProfile profile = new GameProfile(FastUUID.parseUUID(standTile.getPlayerUUID()), "Steve");
      Minecraft.func_71410_x().func_152347_ac().fillProfileProperties(profile, true);
      steve = new AbstractSteve(tileEntity.func_145831_w(), profile);
      this.skinList.put(standTile.getPlayerUUID(), steve);
    } else if (this.skinList.containsKey(standTile.getPlayerUUID())) {
      steve = (AbstractSteve)this.skinList.get(standTile.getPlayerUUID());
    } 
    RenderManager.field_78725_b = x;
    RenderManager.field_78726_c = y;
    RenderManager.field_78723_d = z;
    this.renderSteve = false;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.25D + this.iadjust, y, z + 0.25D + this.kadjust);
    GL11.glRotatef((this.degreeAngle - 90), 0.0F, 1.0F, 0.0F);
    GL11.glScalef(0.005F, 0.005F, 0.005F);
    func_147499_a(QUARTZ_BLOCK);
    this.model.renderSocle();
    func_147499_a((steve == null) ? AbstractClientPlayer.field_110314_b : steve.func_110306_p());
    this.model.renderSkin();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\TileEntityStatueRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */