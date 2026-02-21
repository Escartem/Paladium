package fr.paladium.palashop.provider.box.common.dto.box;

import fr.paladium.palashop.provider.box.common.manager.BoxManager;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class BoxClient {
  private final String id;
  
  private final String description;
  
  private final String marketing;
  
  private final Integer number;
  
  private final List<IShopItem> rewards;
  
  public String getId() {
    return this.id;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getMarketing() {
    return this.marketing;
  }
  
  public Integer getNumber() {
    return this.number;
  }
  
  public List<IShopItem> getRewards() {
    return this.rewards;
  }
  
  public BoxClient(@NonNull Box box, @NonNull EntityPlayer player) {
    if (box == null)
      throw new NullPointerException("box is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    this.id = box.getId();
    this.description = box.getDescription();
    this.marketing = box.getMarketing();
    this.number = box.getNumber(player);
    this.rewards = (List<IShopItem>)box.getRewards().stream().filter(reward -> (reward.getType() == BoxReward.Type.SHOP_ITEM)).sorted(Comparator.comparingInt(BoxReward::getWeight)).map(BoxReward::getShopItem).collect(Collectors.toCollection(java.util.LinkedList::new));
  }
  
  @NonNull
  public BoxData getData() {
    return BoxManager.getBox(this.id).get();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\BoxClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */