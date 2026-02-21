package fr.paladium.palamod.modules.trixium.gui.container.slot;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumDeposit;
import fr.paladium.palamod.modules.trixium.api.TrixiumAPI;
import fr.paladium.palamod.modules.trixium.gui.UITrixiumCache;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import retrofit2.Response;

public class TrixiumSlot extends Slot {
  private final EntityPlayer player;
  
  public TrixiumSlot(IInventory inv, int slot, int x, int y, EntityPlayer player) {
    super(inv, slot, x, y);
    this.player = player;
  }
  
  public boolean func_75214_a(ItemStack itemStack) {
    return (itemStack.func_77973_b() == ItemsRegister.TRIXIUM || itemStack.func_77973_b() == Item.func_150898_a(BlocksRegister.BLOCK_TRIXIUM));
  }
  
  public ItemStack func_75209_a(int count) {
    return super.func_75209_a(count);
  }
  
  public int func_75219_a() {
    return 64;
  }
  
  public void func_75218_e() {
    ItemStack itemStack = func_75211_c();
    int multiplier = 0;
    if (itemStack == null || this.player == null)
      return; 
    Item item = itemStack.func_77973_b();
    if (ItemsRegister.TRIXIUM.equals(item))
      multiplier = 1; 
    if (Item.func_150898_a(BlocksRegister.BLOCK_TRIXIUM).equals(item))
      multiplier = 9; 
    if (multiplier < 1)
      return; 
    int amount = itemStack.field_77994_a * multiplier;
    func_75215_d(null);
    if (!this.player.field_70170_p.field_72995_K && this.player instanceof EntityPlayerMP) {
      UseItemAchievement.performCheck(this.player, new ItemStack(Blocks.field_150350_a), "TRIXIUM_DEPOSIT", amount);
      TrixiumAPI.getExecutor().submit(() -> {
            try {
              PlayerTrixiumDeposit deposit = new PlayerTrixiumDeposit((EntityPlayerMP)this.player);
              deposit.amount = amount;
              Response<Void> response = ApiServices.Http.getTrixiumService().deposit(FastUUID.toString((Entity)this.player), deposit).execute();
              if (!response.isSuccessful()) {
                PlayerUtil.addItemStackToInventory(itemStack, this.player.field_71071_by);
                (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue lors de l'ajout de Trixium", "trixium")).send((EntityPlayerMP)this.player);
              } 
            } catch (Exception e) {
              e.printStackTrace();
              PlayerUtil.addItemStackToInventory(itemStack, this.player.field_71071_by);
              this.player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue lors de l'ajout de Trixium."));
              (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue lors de l'ajout de Trixium", "trixium")).send((EntityPlayerMP)this.player);
            } 
          });
    } else if (this.player.field_70170_p.field_72995_K) {
      UITrixiumCache.increaseTrixium(amount);
    } 
  }
  
  public void func_82870_a(EntityPlayer p, ItemStack itemStack) {
    func_75208_c(itemStack);
    super.func_82870_a(p, itemStack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\container\slot\TrixiumSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */