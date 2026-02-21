package fr.paladium.palajobs.core.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palajobs.api.event.OnPlayerFish;
import fr.paladium.palajobs.core.fishing.FishingCategory;
import fr.paladium.palajobs.core.fishing.FishingSection;
import fr.paladium.palajobs.core.fishing.FishingSectionType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.server.managers.FishingManager;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palajobs.utils.JobsUtils;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CSPacketFishingInteraction implements IMessage {
  private int sectionId;
  
  public CSPacketFishingInteraction() {}
  
  public CSPacketFishingInteraction(int sectionId) {
    this.sectionId = sectionId;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.sectionId = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.sectionId);
  }
  
  public static class Handler implements IMessageHandler<CSPacketFishingInteraction, IMessage> {
    @SideOnly(Side.SERVER)
    public IMessage onMessage(CSPacketFishingInteraction message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      JobsPlayer jobsPlayer = JobsPlayer.get((Entity)player);
      FishingCategory category = FishingManager.get((EntityPlayer)player);
      if (category == null)
        return null; 
      if (message.sectionId < 0) {
        FishingManager.close((EntityPlayer)player);
        return null;
      } 
      if (message.sectionId >= category.getSections().size()) {
        FishingManager.close((EntityPlayer)player);
        return null;
      } 
      FishingSection section = category.getSections().get(message.sectionId);
      ItemStack heldItem = player.func_70694_bm();
      if (heldItem == null || !(heldItem.func_77973_b() instanceof net.minecraft.item.ItemFishingRod))
        return null; 
      if (section.getType() == FishingSectionType.MULTIPLIER) {
        JobsUtils.damageCurrentItem((EntityPlayer)player, heldItem, 1);
        float value = ((Float)section.getValue()).floatValue();
        ItemStack reward = category.getRandomReward(player.field_70170_p.field_73012_v);
        if (reward == null)
          return null; 
        if (reward.func_77973_b() == Items.field_151122_aG) {
          ItemStack tempBook = reward.func_77946_l();
          addRandomEnchantment(player.field_70170_p.field_73012_v, tempBook, 30);
          while (JobsManager.getInstance().canUseCraftEnchantment((EntityPlayer)player, jobsPlayer, tempBook) != null) {
            tempBook = reward.func_77946_l();
            addRandomEnchantment(player.field_70170_p.field_73012_v, tempBook, 30);
          } 
          reward = tempBook;
        } 
        for (int i = 0; i < value; i++) {
          OnPlayerFish.fireEvent((EntityPlayer)player, reward.func_77946_l());
          ItemUtils.spawnItemAtEntity((Entity)player, reward.func_77946_l());
        } 
        FishingManager.close((EntityPlayer)player);
      } else if (section.getType() == FishingSectionType.LEVEl_UP) {
        FishingCategory value = (FishingCategory)section.getValue();
        if (value == null)
          return null; 
        FishingManager.open(player, (FishingCategory)section.getValue());
      } 
      return null;
    }
    
    private ItemStack addRandomEnchantment(Random random, ItemStack item, int power) {
      List<EnchantmentData> list = EnchantmentHelper.func_77513_b(random, item, power);
      boolean isBook = (item.func_77973_b() == Items.field_151122_aG);
      if (isBook)
        item.func_150996_a((Item)Items.field_151134_bR); 
      if (list != null) {
        Iterator<EnchantmentData> iterator = list.iterator();
        while (iterator.hasNext()) {
          EnchantmentData enchantmentdata = iterator.next();
          if (enchantmentdata.field_76302_b.field_77351_y.name().equalsIgnoreCase("customnpcs_enchants"))
            continue; 
          if (isBook) {
            Items.field_151134_bR.func_92115_a(item, enchantmentdata);
            continue;
          } 
          item.func_77966_a(enchantmentdata.field_76302_b, enchantmentdata.field_76303_c);
        } 
      } 
      return item;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\client\CSPacketFishingInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */