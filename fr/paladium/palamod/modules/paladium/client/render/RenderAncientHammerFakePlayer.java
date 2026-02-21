package fr.paladium.palamod.modules.paladium.client.render;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import fr.paladium.palamod.modules.paladium.common.entities.ancient.AncientHammerFakePlayerEntity;
import fr.paladium.palamod.modules.paladium.common.entities.ancient.FakeEntityPlayerMP;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class RenderAncientHammerFakePlayer extends Render {
  private static final Cache<Integer, FakeEntityPlayerMP> ENTITY_CACHE = CacheBuilder.newBuilder().expireAfterWrite(1L, TimeUnit.MINUTES).build();
  
  private static final RenderPlayer RENDER_PLAYER = (RenderPlayer)RenderManager.field_78727_a.field_78729_o.get(EntityPlayer.class);
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
    FakeEntityPlayerMP fakePlayer = getFakeEntityPlayer((AncientHammerFakePlayerEntity)entity);
    RENDER_PLAYER.func_76986_a((AbstractClientPlayer)fakePlayer, x, y, z, yaw, partialTicks);
  }
  
  private FakeEntityPlayerMP getFakeEntityPlayer(AncientHammerFakePlayerEntity entity) {
    try {
      FakeEntityPlayerMP fakePlayer = (FakeEntityPlayerMP)ENTITY_CACHE.get(Integer.valueOf(entity.func_145782_y()), () -> new FakeEntityPlayerMP(entity.field_70170_p, entity.getPlayerUUID(), entity.getPlayerName()));
      fakePlayer.field_70173_aa = entity.field_70173_aa;
      fakePlayer.field_70177_z = entity.field_70177_z;
      fakePlayer.field_70759_as = entity.field_70759_as;
      fakePlayer.field_70758_at = entity.field_70758_at;
      fakePlayer.field_70760_ar = entity.field_70760_ar;
      fakePlayer.field_70761_aq = entity.field_70761_aq;
      fakePlayer.field_70125_A = entity.field_70125_A;
      fakePlayer.field_70126_B = entity.field_70126_B;
      fakePlayer.field_70127_C = entity.field_70127_C;
      fakePlayer.field_70722_aY = entity.field_70722_aY;
      fakePlayer.field_70754_ba = entity.field_70754_ba;
      fakePlayer.field_70721_aZ = entity.field_70721_aZ;
      fakePlayer.field_70169_q = entity.field_70169_q;
      fakePlayer.field_70167_r = entity.field_70167_r;
      fakePlayer.field_70166_s = entity.field_70166_s;
      fakePlayer.field_70732_aI = entity.field_70732_aI;
      fakePlayer.field_70122_E = entity.field_70122_E;
      fakePlayer.field_70128_L = entity.field_70128_L;
      fakePlayer.field_70160_al = entity.field_70160_al;
      fakePlayer.field_82175_bq = entity.field_82175_bq;
      fakePlayer.field_70132_H = entity.field_70132_H;
      fakePlayer.field_70123_F = entity.field_70123_F;
      fakePlayer.field_70124_G = entity.field_70124_G;
      fakePlayer.func_70062_b(0, entity.func_71124_b(0));
      fakePlayer.func_70062_b(1, entity.func_71124_b(1));
      fakePlayer.func_70062_b(2, entity.func_71124_b(2));
      fakePlayer.func_70062_b(3, entity.func_71124_b(3));
      fakePlayer.func_70062_b(4, entity.func_71124_b(4));
      fakePlayer.func_70107_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
      return fakePlayer;
    } catch (ExecutionException e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return getFakeEntityPlayer((AncientHammerFakePlayerEntity)entity).func_110306_p();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\RenderAncientHammerFakePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */