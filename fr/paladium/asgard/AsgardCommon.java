package fr.paladium.asgard;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.asgard.packet.PacketChatWithType;
import fr.paladium.asgard.packet.S45PacketTitle;
import fr.paladium.asgard.packet.handler.ActionBarHandler;
import fr.paladium.asgard.packet.handler.TitleHandler;
import java.lang.reflect.Method;
import net.minecraft.network.EnumConnectionState;
import org.objectweb.asm.Type;

public class AsgardCommon {
  private static SimpleNetworkWrapper chancel;
  
  public static SimpleNetworkWrapper getChancel() {
    return chancel;
  }
  
  void modPost(FMLPostInitializationEvent evt) {
    chancel = NetworkRegistry.INSTANCE.newSimpleChannel("Asgard");
    chancel.registerMessage(TitleHandler.class, S45PacketTitle.class, 0, Side.CLIENT);
    chancel.registerMessage(ActionBarHandler.class, PacketChatWithType.class, 1, Side.CLIENT);
    String mn = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(EnumConnectionState.class.getName(), "func_150756_b", 
        Type.getMethodDescriptor(Type.VOID_TYPE, new Type[] { Type.INT_TYPE, Type.getType(Class.class) }));
    try {
      Method regCPmsg = EnumConnectionState.class.getDeclaredMethod(mn, new Class[] { int.class, Class.class });
      regCPmsg.setAccessible(true);
      regCPmsg.invoke(EnumConnectionState.PLAY, new Object[] { Integer.valueOf(69), S45PacketTitle.class });
      regCPmsg.setAccessible(false);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    if (evt.getSide() == Side.CLIENT)
      new AsgardClient(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\AsgardCommon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */