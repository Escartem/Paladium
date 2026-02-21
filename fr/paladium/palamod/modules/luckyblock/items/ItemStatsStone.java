package fr.paladium.palamod.modules.luckyblock.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemStatsStone extends Item implements ITooltipWiki {
  public ItemStatsStone() {
    func_77656_e(6);
    func_77625_d(1);
    func_77655_b("stats_stone");
    func_111206_d("palamod:stats_stone");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
    if (p_77659_1_.func_77960_j() == 5 || (p_77659_1_.func_77978_p().func_74764_b("LAST_USE") && p_77659_1_
      .func_77978_p().func_74763_f("LAST_USE") + 1800000L >= System.currentTimeMillis()))
      return p_77659_1_; 
    if (!p_77659_2_.field_72995_K) {
      NBTTagCompound nbtTagCompound = p_77659_1_.func_77978_p();
      nbtTagCompound.func_82580_o("LAST_USE");
      nbtTagCompound.func_74772_a("LAST_USE", System.currentTimeMillis());
      p_77659_1_.func_77982_d(nbtTagCompound);
      p_77659_1_.func_77972_a(1, (EntityLivingBase)p_77659_3_);
      int countOnlinePlayer = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b.size();
      p_77659_3_
        .func_145747_a((IChatComponent)new ChatComponentText("§6§l[Lucky Block] §c§oIl y a " + countOnlinePlayer + " joueurs connectés sur " + 
            MinecraftServer.func_71276_C().func_71277_t()));
    } 
    return p_77659_1_;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister p_94581_1_) {
    super.func_94581_a(p_94581_1_);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int p_77617_1_) {
    return this.field_77791_bV;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    if (book.func_77973_b() instanceof net.minecraft.item.ItemEnchantedBook) {
      Map<Integer, Integer> enchant = EnchantmentHelper.func_82781_a(book);
      AtomicBoolean isEnhanteable = new AtomicBoolean(true);
      enchant.forEach((ench, level) -> {
            if (ench.intValue() != Enchantment.field_77347_r.field_77352_x)
              isEnhanteable.set(false); 
          });
      if (isEnhanteable.get())
        return true; 
    } 
    return false;
  }
  
  public void func_77663_a(ItemStack itemStack, World world, Entity entity, int slot, boolean inHand) {
    super.func_77663_a(itemStack, world, entity, slot, inHand);
    if (!itemStack.func_77942_o() && !world.field_72995_K) {
      NBTTagCompound nbtTagCompound = new NBTTagCompound();
      if (itemStack.func_77942_o())
        nbtTagCompound = itemStack.func_77978_p(); 
      nbtTagCompound.func_74772_a("LAST_USE", 0L);
      itemStack.func_77982_d(nbtTagCompound);
    } 
  }
  
  public boolean func_77636_d(ItemStack itemStack) {
    if (itemStack.func_77942_o() && itemStack.func_77978_p().func_74764_b("LAST_USE")) {
      Long time = Long.valueOf(itemStack.func_77978_p().func_74763_f("LAST_USE"));
      return (time.longValue() + 1800000L <= System.currentTimeMillis());
    } 
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemStatsStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */