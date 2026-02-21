package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class BlacklistedTooltipHandler {
  private static final String EMPTY_STRING = "";
  
  private static final List<String> UNUSABLE_DESCRIPTION = Arrays.asList(new String[] { "§cCet item n'est pas", "§cutilisable en dehors", "§cde l'event ", "§6C'est en forgeant qu'on devient forgeron" });
  
  private static final List<String> MONK_DESCRIPTION = Arrays.asList(new String[] { "§aPermet de transformer votre", "§askin en moine." });
  
  @SubscribeEvent
  public void onTooltip(ItemTooltipEvent event) {
    EntityPlayer player = event.entityPlayer;
    if (player == null || event.itemStack == null || event.itemStack.func_77973_b() == null || event.toolTip == null)
      return; 
    ItemStack stack = event.itemStack;
    List<String> tooltip = event.toolTip;
    Item item = stack.func_77973_b();
    NBTTagCompound compound = stack.func_77978_p();
    if (compound == null || compound.func_82582_d())
      return; 
    if (compound.func_74764_b("blacklisted"))
      tooltip.addAll(UNUSABLE_DESCRIPTION); 
    if (compound.func_74764_b("monk"))
      tooltip.addAll(MONK_DESCRIPTION); 
    if (compound.func_74764_b("totalTickMillis")) {
      long totalTickMillis = compound.func_74763_f("totalTickMillis");
      tooltip.addAll(getPlaneDescription(totalTickMillis));
    } 
  }
  
  private List<String> getPlaneDescription(long tick) {
    int maxAmount = 3600;
    int amount = maxAmount - ((tick <= 0L) ? 0 : (int)(tick / 20L));
    return Collections.singletonList("§6Durabilité: §e" + amount + "§7/§e" + maxAmount);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\events\BlacklistedTooltipHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */