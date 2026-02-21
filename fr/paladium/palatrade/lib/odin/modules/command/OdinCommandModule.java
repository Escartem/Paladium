package fr.paladium.palatrade.lib.odin.modules.command;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palatrade.lib.odin.modules.AOdinModule;
import fr.paladium.palatrade.lib.odin.modules.command.internal.builder.OdinCommandBuilder;
import fr.paladium.palatrade.lib.odin.modules.command.lib.Command;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.command.ICommand;

public class OdinCommandModule extends AOdinModule {
  private static OdinCommandModule instance;
  
  private Map<Class<? extends Command>, OdinCommandBuilder> commands;
  
  public OdinCommandModule(String modId) {
    super(modId, "command", "1.0.0");
    instance = this;
    this.commands = new HashMap<>();
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {}
  
  public void onInit(FMLInitializationEvent event) {}
  
  public void onPostInit(FMLPostInitializationEvent event) {}
  
  public void onServerStarting(FMLServerStartingEvent event) {
    for (Map.Entry<Class<? extends Command>, OdinCommandBuilder> entry : this.commands.entrySet()) {
      try {
        Command command = ((Class<Command>)entry.getKey()).newInstance();
        command.setBuilder(entry.getValue());
        event.registerServerCommand((ICommand)command);
      } catch (Exception e) {
        System.out.println("[Odin] Unable to register command " + ((OdinCommandBuilder)entry.getValue()).getName());
        e.printStackTrace();
      } 
    } 
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {}
  
  public void registerCommand(Class<? extends Command> command, OdinCommandBuilder builder) {
    this.commands.put(command, builder);
  }
  
  public OdinCommandBuilder getCommandBuilder() {
    return new OdinCommandBuilder();
  }
  
  public static OdinCommandModule getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\command\OdinCommandModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */