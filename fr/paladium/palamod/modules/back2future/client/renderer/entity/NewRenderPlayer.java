package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.model.ModelPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class NewRenderPlayer extends RenderPlayer {
  public static final ResourceLocation STEVE_SKIN = new ResourceLocation("palamod", "textures/entity/steve.png");
  
  private static final ModelBase STEVE = (ModelBase)new ModelPlayer(0.0F, false);
  
  private void setModel(EntityPlayer player) {
    this.field_77045_g = STEVE;
    this.field_77109_a = (ModelBiped)this.field_77045_g;
  }
  
  protected int func_77032_a(AbstractClientPlayer player, int pass, float partialTickTime) {
    setModel((EntityPlayer)player);
    return super.func_77032_a(player, pass, partialTickTime);
  }
  
  public void func_76986_a(AbstractClientPlayer player, double x, double y, double z, float someFloat, float partialTickTime) {
    setModel((EntityPlayer)player);
    super.func_76986_a(player, x, y, z, someFloat, partialTickTime);
  }
  
  protected void func_77029_c(AbstractClientPlayer player, float partialTickTime) {
    setModel((EntityPlayer)player);
    super.func_77029_c(player, partialTickTime);
  }
  
  protected ResourceLocation func_110775_a(AbstractClientPlayer player) {
    if (player.func_110306_p() == null)
      return super.func_110775_a(player); 
    return new ResourceLocation("palamod", player
        .func_110306_p().func_110623_a());
  }
  
  protected boolean func_110813_b(EntityLivingBase entity) {
    boolean isGUiEnabled = Minecraft.func_71382_s();
    boolean isPlayer = (entity != this.field_76990_c.field_78734_h);
    boolean isInvisible = !entity.func_98034_c((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    boolean isBeingRidden = (entity.field_70153_n == null);
    return (isGUiEnabled && isPlayer && isInvisible && isBeingRidden);
  }
  
  public void func_82441_a(EntityPlayer player) {
    setModel(player);
    Minecraft.func_71410_x().func_110434_K().func_110577_a(func_110775_a((Entity)player));
    super.func_82441_a(player);
    ((ModelPlayer)this.field_77109_a).bipedRightArmwear.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\NewRenderPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */