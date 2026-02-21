package fr.paladium.palarpg.module.dungeon.client.render.block;

import fr.paladium.palarpg.common.api.BlocksRegister;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonRoom;
import fr.paladium.zephyrui.lib.utils.pair.Pair;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class TileEntityDungeonRoomRenderer extends TileEntitySpecialRenderer {
  private static final ModelBiped BIPED_MODEL = new ModelBiped();
  
  private static final ModelBiped ZOMBIE_MODEL = (ModelBiped)new ModelZombie();
  
  private RenderBlocks renderBlocks;
  
  static {
    BIPED_MODEL.field_78091_s = false;
    ZOMBIE_MODEL.field_78091_s = false;
  }
  
  public void func_147496_a(World world) {
    this.renderBlocks = new RenderBlocks((IBlockAccess)world);
  }
  
  public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float ticks) {
    if (!(Minecraft.func_71410_x()).field_71439_g.field_71075_bZ.field_75098_d || !(Minecraft.func_71410_x()).field_71474_y.field_74330_P || !(tileEntity instanceof TileEntityDungeonRoom))
      return; 
    TileEntityDungeonRoom tile = (TileEntityDungeonRoom)tileEntity;
    Block block = tile.func_145838_q();
    if (block == BlocksRegister.DUNGEON_ROOM_SPAWN || block == BlocksRegister.DUNGEON_ROOM_ENTITY || block == BlocksRegister.DUNGEON_ROOM_CHEST) {
      GL11.glPushMatrix();
      GL11.glTranslated(x + 0.5D, y + 2.0D, z + 0.5D);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
      GL11.glRotated(tile.getRotation(), 0.0D, 1.0D, 0.0D);
      OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
      GL11.glDisable(3553);
      OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
      if (block == BlocksRegister.DUNGEON_ROOM_SPAWN || block == BlocksRegister.DUNGEON_ROOM_CHEST) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(AbstractClientPlayer.field_110314_b);
        BIPED_MODEL.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
      } else if (block == BlocksRegister.DUNGEON_ROOM_ENTITY) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("textures/entity/zombie/zombie.png"));
        ZOMBIE_MODEL.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
      } 
      GL11.glPopMatrix();
    } else {
      Pair<Integer> application = tile.getApplicationY();
      GL11.glPushMatrix();
      GL11.glTranslated(x, y, z);
      OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
      GL11.glDisable(3553);
      OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
      GL11.glDisable(2896);
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(false);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
      GL11.glLineWidth(2.0F);
      RenderGlobal.func_147590_a(AxisAlignedBB.func_72330_a(0.0D, (((Integer)application.getFirst()).intValue() - tile.field_145848_d), 0.0D, 1.0D, (((Integer)application.getSecond()).intValue() - tile.field_145848_d + 1), 1.0D), Color.BLUE.getRGB());
      GL11.glDepthMask(true);
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      GL11.glPopMatrix();
    } 
    GL11.glPushMatrix();
    GL11.glTranslated(x, y, z);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
    GL11.glDisable(3553);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glDisable(2929);
    GL11.glDepthMask(true);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F);
    IIcon icon = block.func_149673_e((IBlockAccess)tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 0);
    if (icon != null) {
      Minecraft.func_71410_x().func_110434_K().func_110577_a(TextureMap.field_110575_b);
      Tessellator tessellator = Tessellator.field_78398_a;
      this.renderBlocks.func_147775_a(block);
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      this.renderBlocks.func_147768_a(block, 0.0D, 0.0D, 0.0D, icon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      this.renderBlocks.func_147806_b(block, 0.0D, 0.0D, 0.0D, icon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      this.renderBlocks.func_147761_c(block, 0.0D, 0.0D, 0.0D, icon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      this.renderBlocks.func_147734_d(block, 0.0D, 0.0D, 0.0D, icon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      this.renderBlocks.func_147798_e(block, 0.0D, 0.0D, 0.0D, icon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      this.renderBlocks.func_147764_f(block, 0.0D, 0.0D, 0.0D, icon);
      tessellator.func_78381_a();
    } 
    GL11.glDepthMask(true);
    GL11.glEnable(2929);
    GL11.glDisable(3042);
    GL11.glEnable(3553);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\render\block\TileEntityDungeonRoomRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */