package fr.paladium.palawither.client.render.entity;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palawither.common.entity.targetable.EntitySupremeWither;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;
import software.bernie.geckolib3.model.impl.model.animation.impl.IdleLindwormAnimationType;

public class RenderSupremeWither extends RenderLindwormWither {
  public RenderSupremeWither() {
    super(new ResourceLocation("palawither", "models/entities/supreme_wither/model.json"), new ResourceLocation("palawither", "models/entities/supreme_wither/animation.json"), new ResourceLocation("palawither", "textures/entities/supreme_wither.png"));
    getModel().setAnimatableBuilder((lindwormModel, entity) -> new LindwormAnimatable(lindwormModel, entity, 0.0F, new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType() }));
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
    if (!(entity instanceof EntitySupremeWither))
      return; 
    EntitySupremeWither wither = (EntitySupremeWither)entity;
    LindwormAnimatable lindwormAnimatable = (LindwormAnimatable)getModel().getAnimatable((Entity)wither);
    if (wither.isDying() && (lindwormAnimatable == null || lindwormAnimatable.getCurrentAnimation() == null))
      return; 
    super.func_76986_a(entity, x, y, z, entityYaw, partialTicks);
    if (!wither.isLoading())
      return; 
    long remaining = wither.getRemainingLoadingTime();
    if (remaining <= 0L)
      return; 
    GL11.glPushMatrix();
    GL11.glPushAttrib(1048575);
    GL11.glAlphaFunc(516, 0.0F);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glEnable(2884);
    GL11.glTranslated(x, y, z);
    GL11.glScaled(3.0D, 3.0D, 3.0D);
    GL11.glTranslated(-x, -y, -z);
    GL11.glTranslated(x, y + 1.0D, z);
    GL11.glDepthMask(false);
    GL11.glTranslated(0.0D, 3.0D, 0.0D);
    GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(RenderManager.field_78727_a.field_78732_j, 1.0F, 0.0F, 0.0F);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glScaled(0.005D, 0.005D, 1.0D);
    String days = String.format("%02d", new Object[] { Long.valueOf(remaining / 86400000L) });
    String hours = String.format("%02d", new Object[] { Long.valueOf(remaining / 3600000L % 24L) });
    String minutes = String.format("%02d", new Object[] { Long.valueOf(remaining / 60000L % 60L) });
    String seconds = String.format("%02d", new Object[] { Long.valueOf(remaining / 1000L % 60L) });
    TextInfo textInfo = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 120.0F, Color.WHITE).shadow().shadow(0.0F, 8.0F);
    double width = 0.0D;
    for (int i = 0; i < 4; i++) {
      Text text = Text.create((i == 0) ? days : ((i == 1) ? hours : ((i == 2) ? minutes : seconds)), textInfo);
      width += text.getWidth();
      if (i != 3) {
        text = Text.create(" : ", textInfo);
        width += text.getWidth();
      } 
    } 
    GL11.glTranslated(-width / 2.0D, 0.0D, 0.0D);
    double ox = 0.0D;
    for (int j = 0; j < 4; j++) {
      String textStr = (j == 0) ? days : ((j == 1) ? hours : ((j == 2) ? minutes : seconds));
      Text text = Text.create(textStr, textInfo);
      Text subText = Text.create((j == 0) ? "jours" : ((j == 1) ? "heures" : ((j == 2) ? "minutes" : "secondes")), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 16.0F, Color.WHITE).shadow().shadow(0.0F, 3.0F), Align.CENTER);
      DrawUtils.TEXT.drawText(ox + text.dw(2.0D), textInfo.getHeight(), subText);
      ox += DrawUtils.TEXT.drawText(ox, 0.0D, text).getWidth();
      if (j != 3) {
        text = Text.create(" : ", textInfo);
        ox += DrawUtils.TEXT.drawText(ox, 0.0D, text).getWidth();
      } 
    } 
    GL11.glAlphaFunc(516, 0.1F);
    GL11.glPopAttrib();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\render\entity\RenderSupremeWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */