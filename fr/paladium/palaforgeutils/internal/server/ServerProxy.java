package fr.paladium.palaforgeutils.internal.server;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.internal.common.CommonProxy;
import fr.paladium.palaforgeutils.lib.api.config.GatewayConfig;
import fr.paladium.palaforgeutils.lib.api.gateway.GatewayUtils;
import fr.paladium.palaforgeutils.lib.command.Command;
import fr.paladium.palaforgeutils.lib.command.CommandBuilder;
import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palaforgeutils.lib.command.annotated.test.AnnotatedCommandTestMoney;
import fr.paladium.palaforgeutils.lib.command.impl.dispatch.DispatchCommand;
import fr.paladium.palaforgeutils.lib.command.impl.dispatch.network.RBPacketDispatchCommand;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.PalaGiveCommand;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palaforgeutils.lib.config.PalaForgeConfigManager;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.localdata.event.LocalDataEventHandler;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitNetwork;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.internal.InternalRabbitService;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.internal.event.ServerChangeEvent;
import fr.paladium.palaforgeutils.lib.server.internal.listener.ServerInformationsPlayerListener;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy extends CommonProxy {
  private final Map<Class<? extends Command>, CommandBuilder> commands = new HashMap<>();
  
  public Map<Class<? extends Command>, CommandBuilder> getCommands() {
    return this.commands;
  }
  
  private final Map<ASubCommand, SubCommandBuilder> subCommands = new HashMap<>();
  
  private RabbitNetwork rabbitNetwork;
  
  private InternalRabbitService rabbitService;
  
  public Map<ASubCommand, SubCommandBuilder> getSubCommands() {
    return this.subCommands;
  }
  
  public RabbitNetwork getRabbitNetwork() {
    return this.rabbitNetwork;
  }
  
  public InternalRabbitService getRabbitService() {
    return this.rabbitService;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    try {
      GatewayConfig config = (GatewayConfig)JsonConfigLoader.loadConfig(new File(event.getModConfigurationDirectory(), "gateway.json"), GatewayConfig.class);
      GatewayUtils.init(config.url);
      MinecraftForge.EVENT_BUS.post((Event)new ServerChangeEvent.Pre(Side.SERVER, Server.getServerType()));
      PalaForgeConfigManager configManager = (PalaForgeConfigManager)JsonConfigLoader.loadConfig(new File(event.getModConfigurationDirectory(), "palaforge-utils.json"), PalaForgeConfigManager.class);
      configManager.setInstance();
      MinecraftForge.EVENT_BUS.post((Event)new ServerChangeEvent.Post(Side.SERVER, Server.getServerType()));
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    } 
    this.rabbitService = new InternalRabbitService(PalaForgeConfigManager.getInstance().getRabbitCredentials(), 60000L);
    this.rabbitNetwork = RabbitNetwork.createNetwork("palaforge-utils");
    if (PalaForgeConfigManager.getInstance().getServerType() == null || PalaForgeConfigManager.getInstance().getServerType().isEmpty())
      System.err.println("[PalaForgeConfigManager] Server type is null, please check your configuration."); 
    if (PalaForgeConfigManager.getInstance().getServerName() == null || PalaForgeConfigManager.getInstance().getServerName().isEmpty())
      System.err.println("[PalaForgeConfigManager] Server name is null, please check your configuration."); 
    addListener(new Class[] { LocalDataEventHandler.class });
    addListener(new Class[] { FMLServerScheduler.class });
    addListener(new Class[] { ServerInformationsPlayerListener.class });
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    super.onServerStarting(event);
    for (Map.Entry<Class<? extends Command>, CommandBuilder> entry : this.commands.entrySet()) {
      try {
        Command command = ((Class<Command>)entry.getKey()).newInstance();
        command.setBuilder(entry.getValue());
        event.registerServerCommand((ICommand)command);
      } catch (Exception e) {
        System.out.println("[Paladium] Unable to register command " + ((CommandBuilder)entry.getValue()).getName());
        e.printStackTrace();
      } 
    } 
    this.subCommands.keySet().forEach(subCommand -> {
          subCommand.addHelpSubCommand();
          subCommand.initSubIndexes();
          event.registerServerCommand((ICommand)subCommand);
        });
    CommandRegistry.register();
    if (ForgeEnv.isIDE())
      CommandRegistry.register(new Class[] { AnnotatedCommandTestMoney.class }); 
    if (PalaForgeConfigManager.getInstance().isEnablePalaGive()) {
      PalaGiveManager.inst().init();
      CommandRegistry.register(new Class[] { PalaGiveCommand.class });
    } else {
      System.out.println("[PalaForgeUtils] PalaGive is disabled.");
    } 
    CommandRegistry.register(new Class[] { DispatchCommand.class });
    this.rabbitNetwork.registerPacket(RBPacketDispatchCommand.class);
  }
  
  public void registerCommand(Class<? extends Command> command, CommandBuilder builder) {
    this.commands.put(command, builder);
  }
  
  public ASubCommand registerSubCommand(Class<? extends ASubCommand> subCommandClass, SubCommandBuilder builder) {
    try {
      ASubCommand subCommand = subCommandClass.newInstance();
      subCommand.setBuilder(builder);
      this.subCommands.put(subCommand, builder);
      return subCommand;
    } catch (Exception e) {
      throw new RuntimeException("Unable to register subcommand " + builder.getName(), e);
    } 
  }
  
  public ASubCommand registerSubCommand(ASubCommand subCommand) {
    try {
      this.subCommands.put(subCommand, subCommand.getBuilder());
      return subCommand;
    } catch (Exception e) {
      throw new RuntimeException("Unable to register subcommand " + subCommand.getBuilder().getName(), e);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\internal\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */