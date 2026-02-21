package fr.paladium.palamod.mixins.client.render;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.PoissonAvril;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerBodyDilemmaEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.HabitMakesMonkEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({AbstractClientPlayer.class})
public abstract class IMixinAbstractClientPlayer {
  @Shadow
  public static final ResourceLocation field_110314_b = new ResourceLocation("textures/entity/steve.png");
  
  @Shadow
  private ResourceLocation field_110312_d;
  
  @Overwrite
  public ResourceLocation func_110306_p() {
    ResourceLocation fishLocation = PoissonAvril.getSkin((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    if (fishLocation != null)
      return fishLocation; 
    ResourceLocation monkLocation = HabitMakesMonkEvent.getSkin((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    if (monkLocation != null)
      return monkLocation; 
    if (MonthlyUtils.hasPotionEffect((EntityLivingBase)(Minecraft.func_71410_x()).field_71439_g, (Potion)PLuckyBlock.SUMMER_BODY))
      return SummerBodyDilemmaEvent.SUMMER_BODY_TEXTURE; 
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.STEVE))
      return field_110314_b; 
    return (this.field_110312_d == null) ? field_110314_b : this.field_110312_d;
  }
  
  @Overwrite
  public static ResourceLocation func_110311_f(String p_110311_0_) {
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a((Potion)PLuckyBlock.STEVE))
      return field_110314_b; 
    return new ResourceLocation("skins/" + StringUtils.func_76338_a(p_110311_0_));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\render\IMixinAbstractClientPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */