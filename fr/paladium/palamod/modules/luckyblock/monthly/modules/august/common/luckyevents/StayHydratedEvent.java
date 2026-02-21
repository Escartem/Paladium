package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.HydrationData;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCHydrationPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class StayHydratedEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Reste hydraté";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 350;
  
  private static final String TEXTURE_PATH = "august/stay_hydrated";
  
  private static final String MESSAGE = "&eC'est l'été, il fait chaud ! Veillez à bien vous hydrater !";
  
  public static StayHydratedEvent INSTANCE;
  
  private final HashSet<HydrationData> datas;
  
  public HashSet<HydrationData> getDatas() {
    return this.datas;
  }
  
  public StayHydratedEvent() {
    INSTANCE = this;
    this.datas = new HashSet<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eC'est l'été, il fait chaud ! Veillez à bien vous hydrater !" });
    addData(player);
    MonthlyUtils.startTimedHeliosEvent(player, 
        getClass(), HydrationData.EXPIRATION_DELAY, 
        System.currentTimeMillis());
  }
  
  @SubscribeEvent
  public void onDeath(LivingDeathEvent event) {
    Entity entity = event.entity;
    if (entity.field_70170_p.field_72995_K)
      return; 
    if (!(entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)entity;
    HydrationData data = getData(player);
    if (data == null)
      return; 
    removeData(player, data);
  }
  
  @SubscribeEvent
  public void onUse(PlayerUseItemEvent.Finish event) {
    EntityPlayer p = event.entityPlayer;
    ItemStack itemStack = event.item;
    if (p.field_70170_p.field_72995_K)
      return; 
    EntityPlayerMP player = (EntityPlayerMP)p;
    if (itemStack == null || itemStack.func_77973_b() != Items.field_151068_bn || itemStack.func_77960_j() != 0)
      return; 
    HydrationData data = getData(player);
    if (data == null)
      return; 
    data.hydrate(player, 20);
  }
  
  public void removeData(EntityPlayerMP player, HydrationData data) {
    data.setCreationMillis(-1L);
    PalaMod.network.sendTo((IMessage)new SCHydrationPacket(data), player);
    MonthlyUtils.stopHeliosEvent(player, getClass());
    this.datas.remove(data);
  }
  
  public void addData(EntityPlayerMP player) {
    HydrationData existingData = getData(player);
    if (existingData != null)
      return; 
    HydrationData newData = new HydrationData((EntityPlayer)player);
    this.datas.add(newData);
  }
  
  public HydrationData getData(EntityPlayerMP player) {
    return this.datas.stream()
      .filter(data -> data.getPlayerUniqueId().equals(player.func_110124_au()))
      .findFirst()
      .orElse(null);
  }
  
  public String getName() {
    return "Reste hydraté";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "august/stay_hydrated";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Tu es déshydraté ! Bois vite de l'eau !" };
  }
  
  public String getAction() {
    return "temps restant : ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\StayHydratedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */