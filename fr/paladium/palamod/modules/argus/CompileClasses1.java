package fr.paladium.palamod.modules.argus;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraftforge.common.MinecraftForge;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class CompileClasses1 {
  public static MyHashSet<String> get(MyHashSet<String> a) {
    a.p((Collection)ot());
    return a;
  }
  
  public static MyHashSet<String> ot() {
    MyHashSet<String> p = new MyHashSet();
    try {
      for (EventBus bus : new EventBus[] { MinecraftForge.EVENT_BUS, MinecraftForge.ORE_GEN_BUS, MinecraftForge.TERRAIN_GEN_BUS, FMLCommonHandler.instance().bus() }) {
        Class<?> clazz = EventBus.class;
        Field f = clazz.getDeclaredField((new Object() {
              int t;
              
              public String toString() {
                byte[] buf = new byte[9];
                this.t = -1950985553;
                buf[0] = (byte)(this.t >>> 15);
                this.t = -304395409;
                buf[1] = (byte)(this.t >>> 11);
                this.t = 1316201079;
                buf[2] = (byte)(this.t >>> 21);
                this.t = -1796673470;
                buf[3] = (byte)(this.t >>> 17);
                this.t = -1299072949;
                buf[4] = (byte)(this.t >>> 23);
                this.t = -1224022912;
                buf[5] = (byte)(this.t >>> 23);
                this.t = -1316885339;
                buf[6] = (byte)(this.t >>> 5);
                this.t = -168380094;
                buf[7] = (byte)(this.t >>> 7);
                this.t = 784429959;
                buf[8] = (byte)(this.t >>> 8);
                return new String(buf);
              }
            }).toString());
        BlockSafeChest.f(f);
        Map<Object, ArrayList> hm = (ConcurrentHashMap)f.get(bus);
        Collection<Object> pep = hm.keySet();
        pep.forEach(_o -> p.c(_o.getClass().getName()));
        clazz = EventBus.class;
        f = clazz.getDeclaredField((new Object() {
              int t;
              
              public String toString() {
                byte[] buf = new byte[14];
                this.t = 2008921719;
                buf[0] = (byte)(this.t >>> 7);
                this.t = 110427554;
                buf[1] = (byte)(this.t >>> 20);
                this.t = 60797629;
                buf[2] = (byte)(this.t >>> 19);
                this.t = -375470270;
                buf[3] = (byte)(this.t >>> 4);
                this.t = -889945707;
                buf[4] = (byte)(this.t >>> 2);
                this.t = -1812145221;
                buf[5] = (byte)(this.t >>> 6);
                this.t = 416016730;
                buf[6] = (byte)(this.t >>> 17);
                this.t = -1699856048;
                buf[7] = (byte)(this.t >>> 13);
                this.t = -282596035;
                buf[8] = (byte)(this.t >>> 2);
                this.t = 1004367324;
                buf[9] = (byte)(this.t >>> 2);
                this.t = 1295458700;
                buf[10] = (byte)(this.t >>> 15);
                this.t = -2120063734;
                buf[11] = (byte)(this.t >>> 8);
                this.t = 420367963;
                buf[12] = (byte)(this.t >>> 13);
                this.t = -440825529;
                buf[13] = (byte)(this.t >>> 15);
                return new String(buf);
              }
            }).toString());
        BlockSafeChest.f(f);
        Map<Object, Object> hk = (Map<Object, Object>)f.get(bus);
        Collection<Object> pop = hk.keySet();
        pop.forEach(_o -> p.c(_o.getClass().getName()));
      } 
    } catch (Exception exception) {}
    return p;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\CompileClasses1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */