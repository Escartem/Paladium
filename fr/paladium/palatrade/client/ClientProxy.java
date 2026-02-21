package fr.paladium.palatrade.client;

import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palatrade.common.CommonProxy;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ClientProxy extends CommonProxy {
  private String target;
  
  public void setTarget(String target) {
    this.target = target;
  }
  
  public String getTarget() {
    return this.target;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { ClientProxy.class });
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onTooltip(ItemTooltipEvent event) {
    ItemStack item = event.itemStack;
    List<String> tooltip = event.toolTip;
    if (item == null)
      return; 
    if (item.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.ItemDrawers || item.func_77973_b() instanceof com.jaquadro.minecraft.storagedrawers.item.ItemCompDrawers) {
      Block value = Block.func_149634_a(item.func_77973_b());
      if (value instanceof com.jaquadro.minecraft.storagedrawers.block.BlockDrawers || value instanceof com.jaquadro.minecraft.storagedrawers.block.BlockDrawersCustom || value instanceof com.jaquadro.minecraft.storagedrawers.block.BlockCompDrawers) {
        if (!item.func_77942_o() || !item.func_77978_p().func_74764_b("tile"))
          return; 
        boolean compacting = value instanceof com.jaquadro.minecraft.storagedrawers.block.BlockCompDrawers;
        NBTTagList slots = item.field_77990_d.func_74775_l("tile").func_150295_c("Slots", 10);
        IDrawer[] drawers = new IDrawer[slots.func_74745_c()];
        boolean hasItems = false;
        int i;
        for (i = 0; i < drawers.length; i++) {
          NBTTagCompound slot = slots.func_150305_b(i);
          hasItems = (hasItems || slot.func_74764_b("Item"));
        } 
        if (!hasItems)
          return; 
        tooltip.add("");
        int n;
        for (i = 0, n = drawers.length; i < n; i++) {
          NBTTagCompound slot = slots.func_150305_b(i);
          if (slot.func_74764_b("Item")) {
            Item ii = (Item)GameData.getItemRegistry().func_82594_a(slot.func_74779_i("Item"));
            byte tagId = slot.func_150299_b("Item");
            if (tagId == 2 || tagId == 3 || tagId == 4 || tagId == 5 || tagId == 6) {
              ii = Item.func_150899_d(slot.func_74765_d("Item"));
            } else if (tagId == 8) {
              ii = (Item)GameData.getItemRegistry().func_82594_a(slot.func_74779_i("Item"));
            } else if (ii == null) {
              Block b = Block.func_149729_e(slot.func_74765_d("Item"));
              if (b != null)
                ii = Item.func_150898_a(b); 
            } 
            if (ii != null) {
              ItemStack stack = new ItemStack(ii);
              stack.func_77964_b(slot.func_74765_d("Meta"));
              if (slot.func_74764_b("Tags"))
                stack.func_77982_d(slot.func_74775_l("Tags")); 
              if (compacting) {
                int count = item.field_77990_d.func_74775_l("tile").func_74762_e("Count");
                byte conv = item.field_77990_d.func_74775_l("tile").func_74771_c("Conv" + i);
                double dividedCount = Math.floor((count / conv));
                if (dividedCount >= 1.0D) {
                  tooltip.add("§8» [§7" + String.format("%.0f", new Object[] { Double.valueOf(dividedCount) }) + "§8] §6" + stack.func_77973_b().func_77653_i(stack));
                  break;
                } 
              } 
              if (slot.func_74764_b("Count")) {
                int count = slot.func_74762_e("Count");
                tooltip.add("§8» [§7" + count + "§8] §6" + stack.func_77973_b().func_77653_i(stack));
              } else {
                tooltip.add("§8» [§71§8] §6" + stack.func_77973_b().func_77653_i(stack));
              } 
            } 
          } 
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */