package fr.paladium.palashop.provider.box.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.provider.box.common.dto.box.BoxData;
import fr.paladium.palashop.provider.box.common.manager.BoxManager;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.List;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.impl.model.LindwormModel;

public class ItemBoxKeyLindworm extends Item {
  private final String boxId;
  
  @SideOnly(Side.CLIENT)
  private transient Resource texture;
  
  @SideOnly(Side.CLIENT)
  private transient LindwormModel<IAnimatable> model;
  
  public String getBoxId() {
    return this.boxId;
  }
  
  public Resource getTexture() {
    return this.texture;
  }
  
  public LindwormModel<IAnimatable> getModel() {
    return this.model;
  }
  
  @SideOnly(Side.CLIENT)
  public ItemBoxKeyLindworm(@NonNull String boxId, @NonNull String name, @NonNull Resource texture, @NonNull LindwormModel<IAnimatable> model) {
    if (boxId == null)
      throw new NullPointerException("boxId is marked non-null but is null"); 
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    if (model == null)
      throw new NullPointerException("model is marked non-null but is null"); 
    func_77655_b(name);
    func_77625_d(16);
    this.boxId = boxId;
    this.texture = texture;
    this.model = model;
  }
  
  @SideOnly(Side.SERVER)
  public ItemBoxKeyLindworm(@NonNull String boxId, @NonNull String name) {
    if (boxId == null)
      throw new NullPointerException("boxId is marked non-null but is null"); 
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    func_77655_b(name);
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


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\item\ItemBoxKeyLindworm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */