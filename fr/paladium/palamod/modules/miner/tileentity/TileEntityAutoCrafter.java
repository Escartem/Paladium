package fr.paladium.palamod.modules.miner.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.lib.apollon.container.abstracts.PaladiumTileInventory;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.miner.utils.AutoCrafterManager;
import fr.paladium.palamod.modules.miner.utils.AutoCrafterRecipe;
import fr.paladium.palamod.modules.miner.utils.SubAutoCrafterRecipe;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityAutoCrafter extends PaladiumTileInventory implements ISidedInventory {
  public void setCraftingTime(int craftingTime) {
    this.craftingTime = craftingTime;
  }
  
  public void setCraftingResult(ItemStack craftingResult) {
    this.craftingResult = craftingResult;
  }
  
  public void setRecipe(AutoCrafterRecipe recipe) {
    this.recipe = recipe;
  }
  
  private int craftingTime = 0;
  
  private ItemStack craftingResult;
  
  private AutoCrafterRecipe recipe;
  
  public int getCraftingTime() {
    return this.craftingTime;
  }
  
  public ItemStack getCraftingResult() {
    return this.craftingResult;
  }
  
  public AutoCrafterRecipe getRecipe() {
    return this.recipe;
  }
  
  private static final int[] slotsTop = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
  
  private static final int[] slotsBottom = new int[] { 9, 10, 11, 12, 13, 14, 15, 16, 17 };
  
  public TileEntityAutoCrafter() {
    super("container.autocrafter", 18);
  }
  
  public void func_145845_h() {
    if (this.recipe == null)
      return; 
    Map<ItemAutoCrafterEntry, Integer> needed = new HashMap<>();
    for (SubAutoCrafterRecipe subRecipe : this.recipe.getRessources()) {
      ItemStack comparStack = subRecipe.getItem().func_77946_l();
      ItemAutoCrafterEntry entry = new ItemAutoCrafterEntry(comparStack);
      if (needed.containsKey(entry)) {
        needed.put(entry, Integer.valueOf(((Integer)needed.get(entry)).intValue() + (subRecipe.getItem().func_77946_l()).field_77994_a));
      } else {
        needed.put(entry, Integer.valueOf((subRecipe.getItem().func_77946_l()).field_77994_a));
      } 
    } 
    Map<ItemStack, Integer> stackedContent = new HashMap<>();
    for (ItemStack item : getContent()) {
      if (item != null) {
        ItemStack comparStack = item.func_77946_l();
        comparStack.field_77994_a = 1;
        boolean contains = false;
        int value = 0;
        for (ItemStack stacked : stackedContent.keySet()) {
          if (stacked.func_77969_a(comparStack)) {
            contains = true;
            value = ((Integer)stackedContent.get(stacked)).intValue();
            break;
          } 
        } 
        if (contains) {
          stackedContent.put(comparStack, Integer.valueOf(value + (item.func_77946_l()).field_77994_a));
        } else {
          stackedContent.put(comparStack, Integer.valueOf((item.func_77946_l()).field_77994_a));
        } 
      } 
    } 
    boolean valid = true;
    for (ItemAutoCrafterEntry need : needed.keySet()) {
      boolean exist = false;
      for (ItemStack item : stackedContent.keySet()) {
        if (item.func_77969_a(need.getItem()) && (
          (Integer)stackedContent.get(item)).intValue() >= ((Integer)needed.get(need)).intValue())
          exist = true; 
      } 
      if (!exist) {
        valid = false;
        break;
      } 
    } 
    if (!valid) {
      needed = new HashMap<>();
      for (SubAutoCrafterRecipe subRecipe : this.recipe.getRessources()) {
        if (subRecipe.getRessources() != null) {
          for (ItemStack ressource : subRecipe.getRessources()) {
            ItemStack comparStack = ressource.func_77946_l();
            ItemAutoCrafterEntry entry = new ItemAutoCrafterEntry(comparStack);
            if (needed.containsKey(entry)) {
              needed.put(entry, Integer.valueOf(((Integer)needed.get(entry)).intValue() + (ressource.func_77946_l()).field_77994_a));
            } else {
              needed.put(entry, Integer.valueOf((ressource.func_77946_l()).field_77994_a));
            } 
          } 
        } else {
          ItemStack comparStack = subRecipe.getItem().func_77946_l();
          ItemAutoCrafterEntry entry = new ItemAutoCrafterEntry(comparStack);
          if (needed.containsKey(entry)) {
            needed.put(entry, Integer.valueOf(((Integer)needed.get(entry)).intValue() + (subRecipe.getItem().func_77946_l()).field_77994_a));
          } else {
            needed.put(entry, Integer.valueOf((subRecipe.getItem().func_77946_l()).field_77994_a));
          } 
        } 
      } 
      valid = true;
      for (ItemAutoCrafterEntry need : needed.keySet()) {
        boolean exist = false;
        for (ItemStack item : stackedContent.keySet()) {
          if (item.func_77969_a(need.getItem()) && (
            (Integer)stackedContent.get(item)).intValue() >= ((Integer)needed.get(need)).intValue())
            exist = true; 
        } 
        if (!exist) {
          valid = false;
          break;
        } 
      } 
    } 
    if (valid)
      if (this.craftingResult != null && this.recipe != null && this.craftingTime == 10) {
        if (!this.field_145850_b.field_72995_K) {
          Map<ItemAutoCrafterEntry, Integer> needed2 = new HashMap<>(needed);
          if (canMergeItemStackIntoResult(this.recipe.getResult().func_77946_l())) {
            int slot = 0;
            for (ItemStack item : getContent()) {
              if (item != null)
                for (ItemAutoCrafterEntry need : needed.keySet()) {
                  if (item.func_77969_a(need.getItem())) {
                    int needCount = ((Integer)needed2.get(need)).intValue();
                    int toRemove = Math.min(item.field_77994_a, needCount);
                    ItemStack newItemStack = item.func_77946_l();
                    newItemStack.field_77994_a -= toRemove;
                    needed2.put(need, Integer.valueOf(needCount - toRemove));
                    func_70299_a(slot, (newItemStack.field_77994_a <= 0) ? null : newItemStack);
                  } 
                }  
              slot++;
            } 
            boolean success = true;
            for (ItemAutoCrafterEntry need : needed2.keySet()) {
              if (((Integer)needed2.get(need)).intValue() > 0) {
                success = false;
                break;
              } 
            } 
            if (success && 
              mergeItemStackIntoResult(this.recipe.getResult().func_77946_l())) {
              EventUtils.spawnParticle(this.field_145850_b, "smoke", this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, 10, 0.05D);
              this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "random.fizz", 0.05F, 0.05F);
            } 
          } 
        } 
        this.craftingTime = 0;
      } else {
        this.craftingTime++;
      }  
  }
  
  @SideOnly(Side.CLIENT)
  public float getCraftProgression() {
    return this.craftingTime / 10.0F;
  }
  
  private boolean canMergeItemStackIntoResult(ItemStack mergedItemStack) {
    boolean success = false;
    for (int i = 9; i < func_70302_i_(); i++) {
      ItemStack item = func_70301_a(i);
      if (item == null || (item.func_77969_a(mergedItemStack.func_77946_l()) && item.field_77994_a + (mergedItemStack.func_77946_l()).field_77994_a <= func_70297_j_() && item.field_77994_a + (mergedItemStack.func_77946_l()).field_77994_a <= item.func_77976_d())) {
        success = true;
        break;
      } 
    } 
    return success;
  }
  
  private boolean mergeItemStackIntoResult(ItemStack mergedItemStack) {
    boolean success = false;
    for (int i = 9; i < func_70302_i_(); i++) {
      ItemStack item = func_70301_a(i);
      if (item == null) {
        func_70299_a(i, mergedItemStack.func_77946_l());
        success = true;
        break;
      } 
      if (item.func_77969_a(mergedItemStack.func_77946_l()) && item.field_77994_a + (mergedItemStack.func_77946_l()).field_77994_a <= func_70297_j_() && item.field_77994_a + (mergedItemStack.func_77946_l()).field_77994_a <= item.func_77976_d()) {
        item.field_77994_a += (mergedItemStack.func_77946_l()).field_77994_a;
        success = true;
        break;
      } 
    } 
    return success;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : (
      (player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public int[] func_94128_d(int side) {
    return (side == 0) ? slotsBottom : slotsTop;
  }
  
  public boolean func_102007_a(int slot, ItemStack item, int side) {
    return (slot < 9 && side != 0) ? func_94041_b(slot, item) : false;
  }
  
  public boolean func_102008_b(int slot, ItemStack item, int side) {
    return (side == 0) ? ((slot >= 9)) : false;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (compound.func_74764_b("craftingResult")) {
      this.craftingResult = ItemStack.func_77949_a(compound.func_74775_l("craftingResult"));
      for (String key : AutoCrafterManager.getRecipes().keySet()) {
        if (((AutoCrafterRecipe)AutoCrafterManager.getRecipes().get(key)).getResult().func_77969_a(this.craftingResult)) {
          this.recipe = (AutoCrafterRecipe)AutoCrafterManager.getRecipes().get(key);
          break;
        } 
      } 
    } 
    this.craftingTime = compound.func_74762_e("CraftingTime");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    if (this.craftingResult != null) {
      NBTTagCompound craftingResultCompound = new NBTTagCompound();
      this.craftingResult.func_77955_b(craftingResultCompound);
      compound.func_74782_a("craftingResult", (NBTBase)craftingResultCompound);
    } 
  }
  
  public static class ItemAutoCrafterEntry {
    private final ItemStack item;
    
    public ItemAutoCrafterEntry(ItemStack item) {
      this.item = item;
    }
    
    public ItemStack getItem() {
      return this.item;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { this.item.func_77973_b(), Integer.valueOf(this.item.func_77960_j()) });
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (!(obj instanceof ItemAutoCrafterEntry))
        return false; 
      ItemAutoCrafterEntry other = (ItemAutoCrafterEntry)obj;
      if (this.item == null || other.item == null)
        return false; 
      return this.item.func_77969_a(other.item);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\tileentity\TileEntityAutoCrafter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */