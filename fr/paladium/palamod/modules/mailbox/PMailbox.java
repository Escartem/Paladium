package fr.paladium.palamod.modules.mailbox;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.command.CommandBuilder;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.common.api.adapters.NullOnEmptyConverterFactory;
import fr.paladium.palamod.common.api.http.PaladiumServices;
import fr.paladium.palamod.modules.mailbox.client.commands.CommandMailbox;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailSide;
import fr.paladium.palamod.modules.mailbox.common.PMailboxProxyCommon;
import fr.paladium.palamod.modules.mailbox.common.events.MailBoxEvents;
import fr.paladium.palamod.modules.mailbox.common.packets.client.MailboxClientGetEmailsPacket;
import fr.paladium.palamod.modules.mailbox.common.packets.server.MailboxServerAdminMessagePacket;
import fr.paladium.palamod.modules.mailbox.common.packets.server.MailboxServerAnswerPacket;
import fr.paladium.palamod.modules.mailbox.common.packets.server.MailboxServerGetEmailsPacket;
import fr.paladium.palamod.modules.mailbox.common.packets.server.MailboxServerMessagePacket;
import fr.paladium.palamod.modules.mailbox.common.packets.server.MailboxServerProcessMailPacket;
import fr.paladium.palamod.modules.mailbox.rabbitmq.listeners.MailboxEveryoneNotificationListener;
import fr.paladium.palamod.modules.mailbox.rabbitmq.listeners.MailboxPersonnalNotificationListener;
import fr.paladium.palamod.modules.mailbox.server.commands.ShopDumpCommand;
import fr.paladium.palamod.modules.mailbox.server.commands.ShopMailCommand;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import fr.paladium.palamod.util.rabbitmq.RabbitCredentials;
import fr.paladium.palamod.util.rabbitmq.RabbitListener;
import fr.paladium.palamod.util.rabbitmq.RabbitService;
import java.io.File;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@ObjectHolder("palamod")
@Pulse(id = "palamod-mailbox", description = "Paladium MailBox.", forced = true)
@DoNotRename
public class PMailbox {
  @Instance("palamod-mailbox")
  public static PMailbox instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.mailbox.client.PMailboxProxyClient", serverSide = "fr.paladium.palamod.modules.mailbox.common.PMailboxProxyCommon")
  public static PMailboxProxyCommon proxy;
  
  public static SimpleNetworkWrapper networkWrapper;
  
  public static RabbitService rabbit;
  
  public static EnumMailSide SIDE = EnumMailSide.FACTION;
  
  public static String API_URL = "http://localhost:8099/mailbox/";
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    proxy.preInit();
    Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "palamod-mailbox.cfg"));
    config.load();
    SIDE = EnumMailSide.valueOf(config.getString("side", "mailbox.global", "FACTION", "Mailbox Side FACTION or MINI_GAMES"));
    API_URL = config.getString("api-url", "mailbox.global", "http://127.0.0.1:8095/mailbox/", "API URL");
    Retrofit retrofit4 = (new Retrofit.Builder()).baseUrl(API_URL).addConverterFactory((Converter.Factory)new NullOnEmptyConverterFactory()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).build();
    ApiServices.Http.setMailboxService((PaladiumServices.MAILBOX)retrofit4.create(PaladiumServices.MAILBOX.class));
    RabbitCredentials rabbitCredentials = new RabbitCredentials(config.getString("host", "mailbox.rabbitmq", "localhost", "RabbitMQ host"), config.getInt("port", "mailbox.rabbitmq", 5672, 0, 65535, "RabbitMQ port"), config.getString("username", "mailbox.rabbitmq", "admin", "RabbitMQ username"), config.getString("password", "mailbox.rabbitmq", "admin", "RabbitMQ password"), "/", 16);
    config.save();
    if (event.getSide() == Side.SERVER)
      try {
        rabbit = new RabbitService(rabbitCredentials);
        rabbit.registerListener((RabbitListener)new MailboxEveryoneNotificationListener(rabbit));
        rabbit.registerListener((RabbitListener)new MailboxPersonnalNotificationListener(rabbit));
      } catch (Exception e) {
        e.printStackTrace();
      }  
    networkWrapper = new SimpleNetworkWrapper("palamod-mailbox");
    networkWrapper.registerMessage(MailboxServerProcessMailPacket.Handler.class, MailboxServerProcessMailPacket.class, 1, Side.SERVER);
    networkWrapper.registerMessage(MailboxServerGetEmailsPacket.Handler.class, MailboxServerGetEmailsPacket.class, 2, Side.SERVER);
    networkWrapper.registerMessage(MailboxClientGetEmailsPacket.Handler.class, MailboxClientGetEmailsPacket.class, 3, Side.CLIENT);
    networkWrapper.registerMessage(MailboxServerAnswerPacket.Handler.class, MailboxServerAnswerPacket.class, 4, Side.SERVER);
    networkWrapper.registerMessage(MailboxServerMessagePacket.Handler.class, MailboxServerMessagePacket.class, 5, Side.SERVER);
    networkWrapper.registerMessage(MailboxServerAdminMessagePacket.Handler.class, MailboxServerAdminMessagePacket.class, 6, Side.SERVER);
    FMLCommonHandler.instance().bus().register(new MailBoxEvents());
    MinecraftForge.EVENT_BUS.register(new MailBoxEvents());
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    if (event.getSide() == Side.SERVER) {
      CommandBuilder.create()
        .name("shopmail")
        .permission("paladium.shopmail")
        .build(ShopMailCommand.class);
      CommandBuilder.create()
        .name("shopmaildump")
        .permission("paladium.shopmail")
        .build(ShopDumpCommand.class);
    } 
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {}
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    event.registerServerCommand((ICommand)new CommandMailbox());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\PMailbox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */