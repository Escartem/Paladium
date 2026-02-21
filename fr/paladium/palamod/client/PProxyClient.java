package fr.paladium.palamod.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.api.KeysRegister;
import fr.paladium.palamod.client.events.ClientHandler;
import fr.paladium.palamod.client.events.GuiHandler;
import fr.paladium.palamod.client.utils.Utils;
import fr.paladium.palamod.common.PProxyCommon;
import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import noppes.npcs.CustomNpcs;
import org.lwjgl.opengl.Display;
import sun.misc.Unsafe;

public class PProxyClient extends PProxyCommon {
  private static GuiHandler guiHandler;
  
  private static ClientHandler clientHandler;
  
  public static BigDecimal Player_Money;
  
  public static BigDecimal Player_boutique;
  
  public static boolean accept = false;
  
  public EntityPlayer getPlayerEntity(MessageContext ctx) {
    return (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g;
  }
  
  public void initialize() {
    super.initialize();
    Display.setTitle("Paladium - " + Utils.getPlayerUsername());
    KeysRegister.init();
    guiHandler = new GuiHandler();
    FMLCommonHandler.instance().bus().register(guiHandler);
    MinecraftForge.EVENT_BUS.register(guiHandler);
    clientHandler = new ClientHandler();
    FMLCommonHandler.instance().bus().register(clientHandler);
    MinecraftForge.EVENT_BUS.register(clientHandler);
    CustomNpcs.OpsOnly = false;
    Player_Money = new BigDecimal(25.254444444444445D);
    Player_boutique = new BigDecimal(3);
    if (Constants.MOD_ENV != Constants.Environment.DEV)
      try {
        getClass();
        Class<?> classe = Class.forName("net.minecraft.client.Minecraft");
        Field f = null;
        try {
          f = classe.getDeclaredField("theMinecraft");
        } catch (NoSuchFieldException|SecurityException e) {
          try {
            f = classe.getDeclaredField("field_71432_P");
          } catch (NoSuchFieldException|SecurityException noSuchFieldException) {}
        } 
        if (f != null) {
          f.setAccessible(true);
          Object o = null;
          try {
            o = f.get((Object)null);
          } catch (IllegalArgumentException|IllegalAccessException illegalArgumentException) {}
          Field dir = null;
          try {
            dir = classe.getField("mcDataDir");
          } catch (NoSuchFieldException|SecurityException e) {
            try {
              dir = classe.getField("field_71412_D");
            } catch (NoSuchFieldException|SecurityException noSuchFieldException) {}
          } 
          if (o == null)
            return; 
          try {
            File file = (File)dir.get(o);
            if (!file.getAbsolutePath().contains("paladium"))
              try {
                Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
                Field f1 = unsafeClass.getDeclaredField("theUnsafe");
                f1.setAccessible(true);
                Unsafe unsafe = (Unsafe)f1.get((Object)null);
                unsafe.getByte(0L);
              } catch (Exception exception) {} 
          } catch (IllegalArgumentException|IllegalAccessException illegalArgumentException) {}
        } 
        for (String env : System.getenv().keySet()) {
          if (env.toLowerCase().contains("wine")) {
            try {
              Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
              Field f1 = unsafeClass.getDeclaredField("theUnsafe");
              f1.setAccessible(true);
              Unsafe unsafe = (Unsafe)f1.get((Object)null);
              unsafe.getByte(0L);
              break;
            } catch (Exception exception) {}
            return;
          } 
        } 
      } catch (ClassNotFoundException classNotFoundException) {} 
  }
  
  public boolean shouldAddParticles(Random random) {
    int setting = (Minecraft.func_71410_x()).field_71474_y.field_74362_aa;
    if (setting == 2)
      return false; 
    if (setting == 0)
      return true; 
    return (random.nextInt(2 * (setting + 1)) == 0);
  }
  
  public static GuiHandler getGuiHandler() {
    return guiHandler;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\PProxyClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */