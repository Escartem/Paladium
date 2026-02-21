package fr.paladium.palamod.mixins.container;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.enderchest.PEnderChest;
import fr.paladium.palamod.modules.enderchest.inventory.OfflineInventoryEnderChest;
import fr.paladium.palamod.modules.enderchest.rabbitmq.packet.SyncRawGetRequestPacket;
import fr.paladium.palamod.util.PlayerUtil;
import java.net.InetAddress;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ContainerChest.class})
public abstract class IMixinContainerChest extends Container {
  @Shadow
  private IInventory field_75155_e;
  
  @SideOnly(Side.SERVER)
  @Inject(method = {"onContainerClosed"}, at = {@At("HEAD")})
  public void onContainerClosed(EntityPlayer player, CallbackInfo ci) {
    if (this.field_75155_e instanceof OfflineInventoryEnderChest && !player.field_70170_p.field_72995_K) {
      OfflineInventoryEnderChest inventory = (OfflineInventoryEnderChest)this.field_75155_e;
      if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
        try {
          List<ItemStack> content = new LinkedList<>();
          for (int i = 0; i < inventory.func_70302_i_(); i++)
            content.add(func_75147_a(this.field_75155_e, i).func_75211_c()); 
          PEnderChest.instance.getWaitingSave().put(inventory.getOwner(), new AbstractMap.SimpleEntry<>(inventory.getEditor().func_110124_au(), content));
          (new SyncRawGetRequestPacket(inventory.getOwner(), InetAddress.getLocalHost().getHostName())).send(PEnderChest.instance.getRabbit());
        } catch (Exception e) {
          e.printStackTrace();
        }  
    } else if (this.field_75155_e instanceof InventoryEnderChest && !player.field_70170_p.field_72995_K) {
      InventoryEnderChest enderChest = player.func_71005_bN();
      for (int i = 0; i < enderChest.func_70302_i_(); i++) {
        if (enderChest.func_70301_a(i) != null) {
          ItemStack stack = enderChest.func_70301_a(i).func_77946_l();
          if (PEnderChest.instance.getConfig().isBlackListed(enderChest.func_70301_a(i))) {
            enderChest.func_70299_a(i, null);
            PlayerUtil.addItemStackToInventory(stack, player.field_71071_by);
            player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6EnderChest§8] §6L'item §e" + stack.func_82833_r() + " §6n'est §6pas §6autorisé §6dans §6l'EnderChest."));
          } 
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\container\IMixinContainerChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */