package fr.paladium.palaschematicmod.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.tileentity.TileEntityBlockPalaSchematic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class ClientSchematicSessionListener {
  public static ClientSchematicSessionListener INSTANCE;
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onWorldRenderLast(RenderWorldLastEvent event) {
    Minecraft mc = Minecraft.func_71410_x();
    if (mc.field_71439_g == null || !mc.field_71439_g.field_71075_bZ.field_75098_d)
      return; 
    EntityClientPlayerMP entityClientPlayerMP = mc.field_71439_g;
    double playerX = ((EntityPlayer)entityClientPlayerMP).field_70142_S + (((EntityPlayer)entityClientPlayerMP).field_70165_t - ((EntityPlayer)entityClientPlayerMP).field_70142_S) * event.partialTicks;
    double playerY = ((EntityPlayer)entityClientPlayerMP).field_70137_T + (((EntityPlayer)entityClientPlayerMP).field_70163_u - ((EntityPlayer)entityClientPlayerMP).field_70137_T) * event.partialTicks;
    double playerZ = ((EntityPlayer)entityClientPlayerMP).field_70136_U + (((EntityPlayer)entityClientPlayerMP).field_70161_v - ((EntityPlayer)entityClientPlayerMP).field_70136_U) * event.partialTicks;
    for (Object obj : ((EntityPlayer)entityClientPlayerMP).field_70170_p.field_147482_g) {
      if (!(obj instanceof TileEntityBlockPalaSchematic))
        continue; 
      TileEntityBlockPalaSchematic tileEntity = (TileEntityBlockPalaSchematic)obj;
      if (tileEntity.getSchematicName() == null || tileEntity.getSchematicName().isEmpty() || tileEntity.getSizeX() <= 0 || tileEntity.getSizeY() <= 0 || tileEntity.getSizeZ() <= 0)
        continue; 
      BlockPos center = new BlockPos(tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
      BlockPos firstPos = new BlockPos(center.getX() + tileEntity.getRelativeX(), center.getY() + tileEntity.getRelativeY(), center.getZ() + tileEntity.getRelativeZ());
      BlockPos secondPos = new BlockPos(firstPos.getX() + tileEntity.getSizeX(), firstPos.getY() + tileEntity.getSizeY(), firstPos.getZ() + tileEntity.getSizeZ());
      AxisAlignedBB selectionBox = AxisAlignedBB.func_72330_a(Math.min(firstPos.getX(), secondPos.getX()), Math.min(firstPos.getY(), secondPos.getY()), Math.min(firstPos.getZ(), secondPos.getZ()), (Math.max(firstPos.getX(), secondPos.getX()) + 1), (Math.max(firstPos.getY(), secondPos.getY()) + 1), (Math.max(firstPos.getZ(), secondPos.getZ()) + 1));
      drawSelectionBox(selectionBox, playerX, playerY, playerZ);
    } 
  }
  
  private void drawSelectionBox(AxisAlignedBB box, double offsetX, double offsetY, double offsetZ) {
    GL11.glPushMatrix();
    GL11.glEnable(3042);
    OpenGlHelper.func_148821_a(770, 771, 1, 0);
    GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.4F);
    GL11.glLineWidth(2.0F);
    GL11.glDisable(3553);
    GL11.glDepthMask(false);
    drawOutlinedBoundingBox(box.func_72325_c(-offsetX, -offsetY, -offsetZ));
    GL11.glDepthMask(true);
    GL11.glDisable(3042);
    GL11.glEnable(3553);
    GL11.glPopMatrix();
  }
  
  private void drawOutlinedBoundingBox(AxisAlignedBB box) {
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78371_b(3);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72338_b, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72338_b, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72339_c);
    tessellator.func_78381_a();
    tessellator.func_78371_b(3);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72337_e, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72337_e, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72339_c);
    tessellator.func_78381_a();
    tessellator.func_78371_b(1);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72339_c);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72338_b, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72337_e, box.field_72339_c);
    tessellator.func_78377_a(box.field_72336_d, box.field_72338_b, box.field_72334_f);
    tessellator.func_78377_a(box.field_72336_d, box.field_72337_e, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72338_b, box.field_72334_f);
    tessellator.func_78377_a(box.field_72340_a, box.field_72337_e, box.field_72334_f);
    tessellator.func_78381_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\client\listener\ClientSchematicSessionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */