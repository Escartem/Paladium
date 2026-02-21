package fr.paladium.palamod.modules.argus;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import fr.paladium.palamod.util.MyHashSet;
import net.minecraft.client.Minecraft;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class CurrentGuiScreen {
  public static MyHashSet<String> _a = new MyHashSet();
  
  public static void load() {
    (new Thread() {
        public void run() {
          while (true) {
            try {
              Object o = CurrentGuiScreen.c();
              if (o != null)
                CurrentGuiScreen._a.c(o.getClass().getName()); 
            } catch (Exception err) {
              err.printStackTrace();
            } 
            try {
              Thread.sleep(1000L);
            } catch (Exception exception) {}
          } 
        }
      }).start();
  }
  
  private static Object c() {
    Minecraft pl = Minecraft.func_71410_x();
    if (pl == null)
      return null; 
    return pl.field_71462_r;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\CurrentGuiScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */