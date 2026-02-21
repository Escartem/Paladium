package fr.paladium.palavoicechat.client.listener;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palavoicechat.client.ui.UIVoiceConfig;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindHandlerListener {
  public static final KeyBinding PTT = new KeyBinding("palavoice.push_to_talk.name", 30, "category.palavoicechat");
  
  public static final KeyBinding VOICE_CONFIG = new KeyBinding("palavoice.voice_config.name", 37, "category.palavoicechat");
  
  public static void init() {
    ClientRegistry.registerKeyBinding(VOICE_CONFIG);
    ClientRegistry.registerKeyBinding(PTT);
  }
  
  @SubscribeEvent
  public void onKeyPressed(InputEvent.KeyInputEvent event) {
    if (!PalaVoiceChatEntityListener.enabledVoiceServers.contains(Server.getServerType()))
      return; 
    if (VOICE_CONFIG.func_151468_f())
      ZUI.open((UI)new UIVoiceConfig()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\listener\KeyBindHandlerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */