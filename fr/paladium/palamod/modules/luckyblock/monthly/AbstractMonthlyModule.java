package fr.paladium.palamod.modules.luckyblock.monthly;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.TickableFeature;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraftforge.common.MinecraftForge;

public abstract class AbstractMonthlyModule {
  private int currentPacketId;
  
  private final SideType side;
  
  private final LuckyType type;
  
  private SimpleNetworkWrapper network;
  
  public int getCurrentPacketId() {
    return this.currentPacketId;
  }
  
  public SideType getSide() {
    return this.side;
  }
  
  public LuckyType getType() {
    return this.type;
  }
  
  public SimpleNetworkWrapper getNetwork() {
    return this.network;
  }
  
  public AbstractMonthlyModule(SideType side) {
    this(side, null);
  }
  
  public AbstractMonthlyModule(SideType side, LuckyType type) {
    if (side == SideType.BOTH && type == null)
      throw new IllegalArgumentException("You must specify a LuckyType if the SideType is not BOTH"); 
    this.side = side;
    this.type = type;
    this.currentPacketId = PLuckyBlock.instance.currentPacketId;
    if (this.side == SideType.BOTH) {
      this.network = NetworkRegistry.INSTANCE.newSimpleChannel("LB-" + type.name());
    } else {
      this.network = null;
    } 
  }
  
  public void registerTickables() {}
  
  public void registerTickable(ATickable<?> tickable) {
    if (tickable == null)
      throw new IllegalArgumentException("Tickable cannot be null"); 
    TickableFeature.getInstance().getTickables().add(tickable);
  }
  
  public void registerItems() {}
  
  public void registerBlocks() {}
  
  public void registerTileEntities() {}
  
  public void registerEntities() {}
  
  public void registerRenderers() {}
  
  public void registerCrafts() {}
  
  public void registerEventHandlers() {}
  
  public void registerCommands(FMLServerStartingEvent event) {}
  
  public void registerPacket(Class<? extends ForgePacket> clazz) {
    if (this.network == null)
      return; 
    PacketUtils.registerPacket(this.network, clazz);
  }
  
  public void registerPacket(Class handler, Class packet, Side side) {
    if (this.network == null)
      return; 
    this.network.registerMessage(handler, packet, this.currentPacketId, side);
    this.currentPacketId++;
  }
  
  public void registerExtendedProperties() {}
  
  public void registerPackets() {}
  
  public void registerTasks() {}
  
  public void registerDialogManagers() {}
  
  public void registerPotions() {}
  
  public void registerLuckyStats() {}
  
  public void registerDialogManager(AbstractDialogManager dialogManager) {
    PLuckyBlock.instance.getDialogManagers().add(dialogManager);
  }
  
  public void registerEventHandler(Object object) {
    FMLCommonHandler.instance().bus().register(object);
    MinecraftForge.EVENT_BUS.register(object);
  }
  
  public void registerEventHandler(Class<?>... clazz) {
    try {
      for (Class<?> c : clazz)
        registerEventHandler(c.newInstance()); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\AbstractMonthlyModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */