package fr.paladium.palavoicechat.client.model;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palavoicechat.client.event.RenderPlayerRepoHeadEvent;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(Side.CLIENT)
public class PalaVoiceChatModelPlayer extends ModelBiped {
  private final boolean armor;
  
  public ModelRenderer bipedHeadLower;
  
  public ModelRenderer bipedHeadUpper;
  
  public ModelRenderer bipedHeadUpperContainer;
  
  private Map<String, Float> playerAngleMap;
  
  public PalaVoiceChatModelPlayer() {
    this(0.0F, false);
  }
  
  public PalaVoiceChatModelPlayer(float scale, boolean armor) {
    this(scale, armor, 0.0F, 64, 32);
    this.playerAngleMap = new HashMap<>();
  }
  
  public PalaVoiceChatModelPlayer(float scale, boolean armor, float yOffset, int textureWidth, int textureHeight) {
    super(scale, yOffset, textureWidth, textureHeight);
    this.armor = armor;
    this.bipedHeadLower = new ModelRenderer((ModelBase)this, 0, 7);
    this.bipedHeadLower.func_78790_a(-4.0F, -1.0F, -4.0F, 8, 1, 8, scale);
    this.bipedHeadLower.func_78793_a(0.0F, -1.0F + yOffset, 0.0F);
    this.bipedHeadUpperContainer = new ModelRenderer((ModelBase)this, 0, 0);
    this.bipedHeadUpperContainer.func_78793_a(0.0F, -1.0F + yOffset, 0.0F);
    this.field_78092_r.remove(this.bipedHeadUpperContainer);
    this.bipedHeadUpper = new ModelRenderer((ModelBase)this, 0, 0);
    this.bipedHeadUpper.func_78790_a(-4.0F, -8.0F, -8.0F, 8, 7, 8, scale);
    this.bipedHeadUpper.func_78793_a(0.0F, 0.0F, 4.0F);
    this.bipedHeadUpperContainer.func_78792_a(this.bipedHeadUpper);
    this.field_78116_c = new ModelRenderer((ModelBase)this, 0, 0);
    this.field_78116_c.func_78793_a(0.0F, 0.0F, 4.0F);
    this.bipedHeadUpperContainer.func_78792_a(this.field_78116_c);
    this.field_78092_r.remove(this.field_78116_c);
    this.field_78114_d = new ModelRenderer((ModelBase)this, 32, 0);
    this.field_78114_d.func_78790_a(-4.0F, -8.0F, -8.0F, 8, 8, 8, scale + 0.5F);
    this.field_78114_d.func_78793_a(0.0F, 0.0F, 4.0F);
    this.bipedHeadUpperContainer.func_78792_a(this.field_78114_d);
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
    this.bipedHeadLower.field_78807_k = this.field_78116_c.field_78807_k;
    this.bipedHeadUpperContainer.field_78807_k = this.field_78116_c.field_78807_k;
    this.bipedHeadLower.field_78806_j = this.field_78116_c.field_78806_j;
    this.bipedHeadUpperContainer.field_78806_j = this.field_78116_c.field_78806_j;
    if (!this.armor)
      this.bipedHeadLower.func_78785_a(scale); 
    this.bipedHeadUpperContainer.func_78785_a(scale);
    this.field_78115_e.func_78785_a(scale);
    this.field_78112_f.func_78785_a(scale);
    this.field_78113_g.func_78785_a(scale);
    this.field_78123_h.func_78785_a(scale);
    this.field_78124_i.func_78785_a(scale);
  }
  
  public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    this.bipedHeadLower.field_78795_f = this.field_78116_c.field_78795_f;
    this.bipedHeadLower.field_78796_g = this.field_78116_c.field_78796_g;
    this.bipedHeadUpperContainer.field_78795_f = this.field_78116_c.field_78795_f;
    this.bipedHeadUpperContainer.field_78796_g = this.field_78116_c.field_78796_g;
    this.bipedHeadUpperContainer.field_78808_h = this.field_78116_c.field_78808_h;
    this.bipedHeadUpperContainer.field_82906_o = this.field_78116_c.field_82906_o;
    this.bipedHeadUpperContainer.field_82908_p = this.field_78116_c.field_82908_p;
    this.bipedHeadUpperContainer.field_82907_q = this.field_78116_c.field_82907_q;
    this.bipedHeadLower.field_82906_o = this.field_78116_c.field_82906_o;
    this.bipedHeadLower.field_82908_p = this.field_78116_c.field_82908_p;
    this.bipedHeadLower.field_82907_q = this.field_78116_c.field_82907_q;
    RenderPlayerRepoHeadEvent event = new RenderPlayerRepoHeadEvent((EntityPlayer)entity);
    MinecraftForge.EVENT_BUS.post((Event)event);
    if (!this.field_78116_c.field_78807_k) {
      this.bipedHeadUpper.field_78795_f = lerpByFramerate(((Float)this.playerAngleMap.getOrDefault(UUIDUtils.toString(entity), Float.valueOf(0.0F))).floatValue(), -Math.min(90.0F, event.getAngle()), 1.0F, 0.01F, true);
      this.bipedHeadUpper.field_78796_g = 0.0F;
      this.bipedHeadUpper.field_78808_h = 0.0F;
      this.playerAngleMap.put(UUIDUtils.toString(entity), Float.valueOf(this.bipedHeadUpper.field_78795_f));
    } 
    this.field_78116_c.field_78795_f = this.bipedHeadUpper.field_78795_f;
    this.field_78116_c.field_78796_g = this.bipedHeadUpper.field_78796_g;
    this.field_78116_c.field_78808_h = this.bipedHeadUpper.field_78808_h;
    this.field_78114_d.field_78795_f = this.bipedHeadUpper.field_78795_f;
    this.field_78114_d.field_78796_g = this.bipedHeadUpper.field_78796_g;
    this.field_78114_d.field_78808_h = this.bipedHeadUpper.field_78808_h;
    this.field_78114_d.field_82906_o = this.bipedHeadUpper.field_82906_o;
    this.field_78114_d.field_82908_p = this.bipedHeadUpper.field_82908_p;
    this.field_78114_d.field_82907_q = this.bipedHeadUpper.field_82907_q;
    this.field_78116_c.field_82906_o = this.bipedHeadUpper.field_82906_o;
    this.field_78116_c.field_82908_p = this.bipedHeadUpper.field_82908_p;
    this.field_78116_c.field_82907_q = this.bipedHeadUpper.field_82907_q;
    this.field_78116_c.field_78797_d = 0.0F;
    this.field_78114_d.field_78797_d = 0.0F;
    if (this.field_78117_n) {
      this.bipedHeadLower.field_78797_d = 1.0F;
      this.bipedHeadUpperContainer.field_78797_d = 1.0F;
    } else {
      this.bipedHeadLower.field_78797_d = 0.0F;
      this.bipedHeadUpperContainer.field_78797_d = 0.0F;
    } 
  }
  
  public final float lerpByFramerate(float value, float target, float speed, float snapDiff, boolean snap) {
    double diff = (target - value);
    double absDiff = Math.abs(diff);
    int fps = 30;
    try {
      fps = Integer.parseInt((Minecraft.func_71410_x()).field_71426_K.split(" fps")[0]);
    } catch (Exception exception) {}
    double offset = speed / ((fps == 0.0D) ? 1.0D : fps) / 60.0D * absDiff / 3.0D;
    if (absDiff > snapDiff) {
      value = (float)(value + ((diff > 0.0D) ? offset : -offset));
    } else if (snap) {
      value = target;
    } 
    return value;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\model\PalaVoiceChatModelPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */