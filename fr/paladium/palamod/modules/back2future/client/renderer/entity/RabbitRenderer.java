package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.model.ModelRabbit;
import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RabbitRenderer extends RenderLiving {
  private static final ResourceLocation BROWN = new ResourceLocation("textures/entity/rabbit/brown.png");
  
  private static final ResourceLocation WHITE = new ResourceLocation("textures/entity/rabbit/white.png");
  
  private static final ResourceLocation BLACK = new ResourceLocation("textures/entity/rabbit/black.png");
  
  private static final ResourceLocation GOLD = new ResourceLocation("textures/entity/rabbit/gold.png");
  
  private static final ResourceLocation SALT = new ResourceLocation("textures/entity/rabbit/salt.png");
  
  private static final ResourceLocation WHITE_SPLOTCHED = new ResourceLocation("textures/entity/rabbit/white_splotched.png");
  
  private static final ResourceLocation TOAST = new ResourceLocation("textures/entity/rabbit/toast.png");
  
  private static final ResourceLocation CAERBANNOG = new ResourceLocation("textures/entity/rabbit/caerbannog.png");
  
  private static final ResourceLocation GAROU = new ResourceLocation("palamod", "textures/entity/wererabbit.png");
  
  public RabbitRenderer() {
    super((ModelBase)new ModelRabbit(), 0.3F);
  }
  
  protected void func_77041_b(EntityLivingBase entityliving, float patialTickTime) {
    GL11.glScalef(0.65F, 0.65F, 0.65F);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    EntityRabbit rabbit = (EntityRabbit)entity;
    String s = EnumChatFormatting.func_110646_a(rabbit.func_70005_c_());
    if (rabbit.getRabbitType() == 98)
      return GAROU; 
    if (s != null && s.equals("Toast"))
      return TOAST; 
    switch (rabbit.getRabbitType()) {
      default:
        return BROWN;
      case 1:
        return WHITE;
      case 2:
        return BLACK;
      case 3:
        return WHITE_SPLOTCHED;
      case 4:
        return GOLD;
      case 5:
        return SALT;
      case 99:
        break;
    } 
    return CAERBANNOG;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\RabbitRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */