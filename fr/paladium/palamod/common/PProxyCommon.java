package fr.paladium.palamod.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.common.api.http.ProfileServices;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PProxyCommon {
  public static Object EconomyProvider = null;
  
  public EntityPlayer getPlayerEntity(MessageContext ctx) {
    return (EntityPlayer)(ctx.getServerHandler()).field_147369_b;
  }
  
  public EntityPlayer getServerPlayerEntity(MessageContext ctx) {
    return (EntityPlayer)(ctx.getServerHandler()).field_147369_b;
  }
  
  public void initialize() {
    Retrofit retrofit2 = (new Retrofit.Builder()).baseUrl("https://api.mojang.com/").addConverterFactory((Converter.Factory)GsonConverterFactory.create()).build();
    ApiServices.Http.setMinecraftApi((ProfileServices)retrofit2.create(ProfileServices.class));
    FMLCommonHandler.instance().bus().register(this);
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  public void init() {}
  
  public void postInit() {}
  
  public void serverStarting(FMLServerStartingEvent event) {}
  
  public boolean shouldAddParticles(Random random) {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\PProxyCommon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */