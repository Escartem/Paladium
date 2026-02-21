package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.KillMessageCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.server.dto.KillMessageCosmetic;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class KillMessageCosmeticServerListener {
  @SubscribeEvent
  public void onKill(LivingDeathEvent event) {
    if ((!(event.entity instanceof net.minecraft.entity.player.EntityPlayer) && !ForgeEnv.isIDE()) || !(event.source.func_76346_g() instanceof EntityPlayerMP))
      return; 
    Entity target = event.entity;
    EntityPlayerMP player = (EntityPlayerMP)event.source.func_76346_g();
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    if (cosmeticPlayer == null)
      return; 
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(KillMessageCosmeticFactory.ID);
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
    List<ICosmetic> cosmetics = Arrays.asList(equippedCosmetic.getCosmetics());
    if (cosmetics.isEmpty())
      return; 
    Collections.shuffle(cosmetics);
    for (ICosmetic cosmetic : cosmetics) {
      if (!(cosmetic instanceof KillMessageCosmetic))
        continue; 
      KillMessageCosmetic killMessageCosmetic = (KillMessageCosmetic)cosmetic;
      MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(""));
      MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §r" + killMessageCosmetic.getFormattedMessage(player.func_70005_c_(), target.func_70005_c_())));
      MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(""));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\server\listener\KillMessageCosmeticServerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */