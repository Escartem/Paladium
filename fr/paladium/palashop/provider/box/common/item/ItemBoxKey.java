package fr.paladium.palashop.provider.box.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.provider.box.common.dto.box.BoxData;
import fr.paladium.palashop.provider.box.common.manager.BoxManager;
import java.util.List;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBoxKey extends Item {
  private final String boxId;
  
  public String getBoxId() {
    return this.boxId;
  }
  
  public ItemBoxKey(@NonNull String boxId, @NonNull String name, @NonNull String texture) {
    if (boxId == null)
      throw new NullPointerException("boxId is marked non-null but is null"); 
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    func_77655_b(name);
    func_111206_d(texture);
    func_77625_d(16);
    this.boxId = boxId;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    BoxData boxData = BoxManager.getBox(this.boxId).orElse(null);
    if (boxData == null) {
      list.add("§cCette box n'existe plus.");
      return;
    } 
    list.add("");
    list.add("§r§bUtilisez cette clé au spawn pour ouvrir la box §r§b" + boxData.getName() + "§r§b.");
  }
  
  public String func_77653_i(ItemStack item) {
    BoxData boxData = BoxManager.getBox(this.boxId).orElse(null);
    if (boxData == null)
      return "§cBox inconnue"; 
    return "§rClé " + boxData.getName() + " Box§r";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\item\ItemBoxKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */