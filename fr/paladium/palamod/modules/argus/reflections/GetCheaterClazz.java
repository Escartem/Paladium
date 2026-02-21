package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import io.netty.channel.ChannelDuplexHandler;
import javax.swing.JFrame;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class GetCheaterClazz {
  public static final Class<?>[] clazz = new Class[] { GuiScreen.class, ChannelDuplexHandler.class, JFrame.class, Gui.class };
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\GetCheaterClazz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */