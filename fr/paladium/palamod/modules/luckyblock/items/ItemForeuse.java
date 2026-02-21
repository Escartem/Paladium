package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.scheduler.PaladiumRunnable;
import fr.paladium.palamod.util.TimeUtils;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ItemForeuse extends Item implements ITooltipWiki {
  public ItemForeuse() {
    this.field_77777_bU = 1;
    func_77655_b("foreuse");
    func_111206_d("palamod:foreuse");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack p_77659_1_, final World p_77659_2_, final EntityPlayer p_77659_3_) {
    if (p_77659_3_.func_70093_af()) {
      if (!p_77659_2_.field_72995_K && p_77659_1_.func_77942_o() && 
        p_77659_1_.func_77978_p().func_74764_b("LAST_USE")) {
        long lastUse = p_77659_1_.func_77978_p().func_74763_f("LAST_USE");
        long diff = (System.currentTimeMillis() - lastUse) / 1000L;
        if (diff < 7200L) {
          String formattedTime = TimeUtils.getFullTime("§b", "§e", diff);
          p_77659_3_.func_145747_a((IChatComponent)new ChatComponentText("§cVous devez attendre " + formattedTime + " §cavant de pouvoir utiliser cette foreuse."));
          return p_77659_1_;
        } 
      } 
      Chunk chunk = p_77659_2_.func_72938_d(MathHelper.func_76128_c(p_77659_3_.field_70165_t), MathHelper.func_76128_c(p_77659_3_.field_70161_v));
      if (chunk == null || !chunk.field_76636_d) {
        p_77659_3_.func_145747_a((IChatComponent)new ChatComponentText("§cLe chunk n'est pas chargé."));
        return p_77659_1_;
      } 
      for (Object tileEntity : chunk.field_150816_i.values()) {
        if (tileEntity instanceof fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle) {
          p_77659_3_.func_145747_a((IChatComponent)new ChatComponentText("§cVous ne pouvez pas utiliser cet item dans un chunk contenant une Réceptacle de Wither."));
          return p_77659_1_;
        } 
      } 
      if (getItemCount(p_77659_3_, new ItemStack(ItemsRegister.FINDIUM)) >= 5) {
        if (!p_77659_1_.func_77942_o())
          p_77659_1_.func_77982_d(new NBTTagCompound()); 
        p_77659_1_.func_77978_p().func_74772_a("LAST_USE", System.currentTimeMillis());
        int a = 5;
        for (int i = 0; i < p_77659_3_.field_71071_by.field_70462_a.length; i++) {
          ItemStack it = p_77659_3_.field_71071_by.field_70462_a[i];
          if (it != null && a > 0 && 
            it.func_77969_a(new ItemStack(ItemsRegister.FINDIUM)) && ItemStack.func_77970_a(it, new ItemStack(ItemsRegister.FINDIUM))) {
            int v = Math.min(Math.min(64, a), it.field_77994_a);
            it.field_77994_a -= v;
            if (it.field_77994_a <= 0) {
              p_77659_3_.field_71071_by.func_70299_a(i, null);
              p_77659_3_.field_71071_by.field_70459_e = true;
            } else {
              p_77659_3_.field_71071_by.func_70299_a(i, it.func_77946_l());
              p_77659_3_.field_71071_by.field_70459_e = true;
            } 
            a -= v;
          } 
        } 
        final int minX = chunk.field_76635_g << 4;
        final int minZ = chunk.field_76647_h << 4;
        final int maxX = minX | 0xF;
        final int maxY = p_77659_2_.func_72800_K();
        final int maxZ = minZ | 0xF;
        if (p_77659_2_.field_72995_K)
          p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§e[Foreuse] Activation de la foreuse...")); 
        if (!p_77659_2_.field_72995_K)
          try {
            (new PaladiumRunnable() {
                int x = minX;
                
                public void run() {
                  int tmpX = this.x + 1;
                  int blocksReplaced = 0;
                  for (; this.x <= maxX && this.x <= tmpX; this.x++) {
                    for (int y = 0; y <= maxY; y++) {
                      for (int z = minZ; z <= maxZ; z++) {
                        if (EventUtils.canInteract(p_77659_3_, this.x, y, z)) {
                          p_77659_2_.func_147468_f(this.x, y, z);
                          blocksReplaced++;
                        } 
                      } 
                    } 
                  } 
                  if (blocksReplaced == 0) {
                    p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§e[Foreuse] §aAction terminée."));
                    cancel();
                  } 
                }
              }).runTaskTimer(10L, 10L);
          } catch (Exception|NoClassDefFoundError exception) {
            exception.printStackTrace();
          }  
      } else if (p_77659_2_.field_72995_K) {
        p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§cVous devez avoir 5 Findiums pour utiliser la foreuse."));
      } 
    } else if (p_77659_2_.field_72995_K) {
      p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§eClique droit + Shift: §7Creuse le chunk actuel"));
      p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§7- Consomme 5 Findiums"));
      p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§7- Cooldown 2H"));
    } 
    return p_77659_1_;
  }
  
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
    p_77624_3_.add("§eClique droit:");
    p_77624_3_.add("§aConsomme §c5 Findiums §aet creuse tout le chunk actuel.");
    p_77624_3_.add("§7Cooldown 2H");
    super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
  }
  
  public static int getItemCount(EntityPlayer player, ItemStack item) {
    int quantityInInventory = 0;
    InventoryPlayer inventory = player.field_71071_by;
    ItemStack[] arrayOfItemStack;
    int j = (arrayOfItemStack = inventory.field_70462_a).length;
    for (int i = 0; i < j; i++) {
      ItemStack current = arrayOfItemStack[i];
      if (!isNullItem(current) && current.func_77973_b() == item.func_77973_b() && ItemStack.func_77970_a(current, item))
        quantityInInventory += current.field_77994_a; 
    } 
    return quantityInInventory;
  }
  
  public static boolean isNullItem(ItemStack item) {
    if (item != null && item.func_77973_b() != Item.func_150898_a(Blocks.field_150350_a))
      return false; 
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.1.-luckystats-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemForeuse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */