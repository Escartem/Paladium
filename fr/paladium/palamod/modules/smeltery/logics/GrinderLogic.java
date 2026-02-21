package fr.paladium.palamod.modules.smeltery.logics;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.lib.apollon.container.abstracts.PaladiumTileInventory;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.types.GrinderCraftAchievement;
import fr.paladium.palamod.modules.achievements.types.ModifierApplyAchievement;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.miner.item.ItemGodPickaxe;
import fr.paladium.palamod.modules.miner.item.ItemGodPickaxeUpgrade;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemRepairableArmor;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.ItemAncientHammer;
import fr.paladium.palamod.modules.paladium.common.items.tools.BaseItemPickaxe;
import fr.paladium.palamod.modules.paladium.common.items.weapons.BaseItemSword;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palamod.modules.smeltery.crafting.GrinderRecipe;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import fr.paladium.palapass.common.quest.palamod.GrinderCraftQuest;
import fr.paladium.palapass.common.quest.palamod.GrinderSmeltQuest;
import fr.paladium.palapass.common.quest.palamod.ModifierApplyQuest;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGArmorType;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemArmor;
import fr.paladium.palarpg.module.equipment.common.manager.EquipmentRuneManager;
import fr.paladium.tutorial.common.event.GrinderCraftEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.MinecraftForge;

public class GrinderLogic extends PaladiumTileInventory implements ISidedInventory {
  private int paladiumAmount;
  
  private final int maxPaladium;
  
  private int workedTimeCreation;
  
  private int workedTimeUpgrade;
  
  private final int timeNeededCreation;
  
  private final int timeNeededUpgrade;
  
  private final int timeNeededPaladium;
  
  private int workedTimePaladium;
  
  private EntityPlayer player;
  
  public GrinderLogic() {
    super("Tile.paladiumgrinder", 6);
    this.maxPaladium = 100;
    this.timeNeededCreation = 100;
    this.timeNeededUpgrade = 100;
    this.timeNeededPaladium = 20;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : ((player.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbtTag = new NBTTagCompound();
    func_145841_b(nbtTag);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
  
  public void func_145845_h() {
    if (func_70301_a(5) != null && this.paladiumAmount < 100) {
      if (this.timeNeededPaladium > this.workedTimePaladium) {
        this.workedTimePaladium++;
      } else {
        consumePaladium();
        this.workedTimePaladium = 0;
      } 
    } else {
      this.workedTimePaladium = 0;
    } 
    if (canSmeltTool()) {
      this.workedTimeCreation++;
      if (this.workedTimeCreation >= this.timeNeededCreation) {
        smeltTool();
        this.workedTimeCreation = 0;
      } 
    } else {
      this.workedTimeCreation = 0;
    } 
    if (canSmeltUpgrade()) {
      this.workedTimeUpgrade++;
      if (this.workedTimeUpgrade >= this.timeNeededUpgrade) {
        smeltUpgrade();
        this.workedTimeUpgrade = 0;
      } 
    } else {
      this.workedTimeUpgrade = 0;
    } 
    super.func_145845_h();
  }
  
  public boolean canSmeltTool() {
    if (func_70301_a(1) == null || func_70301_a(2) == null || this.paladiumAmount == 0)
      return false; 
    ItemStack itemstack = GrinderRecipe.getManager().getSmeltingResult(new ItemStack[] { func_70301_a(1), func_70301_a(2) });
    if (itemstack == null || (func_70301_a(0) != null && func_70301_a(0).func_77973_b() != itemstack.func_77973_b()))
      return false; 
    if (func_70301_a(0) != null && ((func_70301_a(0).func_77973_b() != ItemsRegister.PALADIUM_INGOT && func_70301_a(0).func_77973_b() != Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM)) || (func_70301_a(0)).field_77994_a >= 64))
      return false; 
    if (GrinderRecipe.getManager().getSmeltingAmmount(itemstack) > this.paladiumAmount)
      return false; 
    return true;
  }
  
  public boolean canSmeltUpgrade() {
    if (func_70301_a(3) == null || func_70301_a(4) == null)
      return false; 
    if (func_70301_a(3).func_77973_b() instanceof LegendaryStone && func_70301_a(4).func_77973_b() instanceof ItemAncientArmor) {
      if (((LegendaryStone)func_70301_a(3).func_77973_b()).getEffect() == LegendaryStone.Effect.RANDOM || ItemAncientArmor.getEffect(func_70301_a(4)) != null || 64 > this.paladiumAmount)
        return false; 
      return true;
    } 
    if (func_70301_a(3).func_77973_b() instanceof LegendaryStone && func_70301_a(4).func_77973_b() instanceof ItemAncientHammer) {
      if (!ItemAncientHammer.isApplicableEffect(((LegendaryStone)func_70301_a(3).func_77973_b()).getEffect()) || ItemAncientHammer.getEffect(func_70301_a(4)) != null || 64 > this.paladiumAmount)
        return false; 
      return true;
    } 
    if (EquipmentRuneManager.isRune(func_70301_a(3)) && IRPGItem.is(func_70301_a(4))) {
      IRPGItem rpgItem = IRPGItem.get(func_70301_a(4)).get();
      int paladiumCost = 0;
      if (rpgItem instanceof fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemSword) {
        paladiumCost = 2;
      } else if (rpgItem instanceof RPGItemArmor) {
        RPGItemArmor armor = (RPGItemArmor)rpgItem;
        switch (armor.getType()) {
          case HELMET:
            paladiumCost = 5;
            break;
          case CHESTPLATE:
            paladiumCost = 8;
            break;
          case LEGGINGS:
            paladiumCost = 7;
            break;
          case BOOTS:
            paladiumCost = 4;
            break;
        } 
      } 
      if (paladiumCost > this.paladiumAmount)
        return false; 
      int runeSlot = EquipmentRuneManager.getFirstFreeRuneSlot(func_70301_a(4));
      if (runeSlot != -1)
        return true; 
    } 
    if (!GrinderRecipe.getManager().isUpgradable(func_70301_a(4).func_77973_b(), func_70301_a(3).func_77973_b()) || !UpgradeHelper.canApplyUpgrade(func_70301_a(3), GrinderRecipe.getManager().getUpgrade(func_70301_a(3).func_77973_b()), func_70301_a(4)))
      return false; 
    return true;
  }
  
  public void smeltUpgrade() {
    if (func_70301_a(3) == null || func_70301_a(4) == null || func_70301_a(3).func_77973_b() == null || func_70301_a(4).func_77973_b() == null)
      return; 
    if (func_70301_a(3).func_77973_b() instanceof LegendaryStone && func_70301_a(4).func_77973_b() instanceof ItemAncientArmor) {
      if (64 > this.paladiumAmount)
        return; 
      LegendaryStone stone = (LegendaryStone)func_70301_a(3).func_77973_b();
      if (stone.getEffect() == LegendaryStone.Effect.RANDOM || ItemAncientArmor.getEffect(func_70301_a(4)) != null)
        return; 
      ItemAncientArmor.setEffect(func_70301_a(4), stone.getEffect());
      this.paladiumAmount -= 64;
      func_70299_a(3, null);
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      func_70296_d();
      return;
    } 
    if (func_70301_a(3).func_77973_b() instanceof LegendaryStone && func_70301_a(4).func_77973_b() instanceof ItemAncientHammer) {
      if (64 > this.paladiumAmount)
        return; 
      LegendaryStone stone = (LegendaryStone)func_70301_a(3).func_77973_b();
      if (!ItemAncientHammer.isApplicableEffect(stone.getEffect()) || ItemAncientArmor.getEffect(func_70301_a(4)) != null)
        return; 
      ItemAncientHammer.setEffect(func_70301_a(4), stone.getEffect());
      this.paladiumAmount -= 64;
      func_70299_a(3, null);
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      func_70296_d();
      return;
    } 
    if (IRPGItem.is(func_70301_a(4)) && EquipmentRuneManager.isRune(func_70301_a(3))) {
      int runeSlot = EquipmentRuneManager.getFirstFreeRuneSlot(func_70301_a(4));
      if (runeSlot != -1)
        EquipmentRuneManager.apply(func_70301_a(3), func_70301_a(4), runeSlot); 
      IRPGItem rpgItem = IRPGItem.get(func_70301_a(4)).get();
      int paladiumCost = 0;
      if (rpgItem instanceof fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemSword) {
        paladiumCost = 2;
      } else if (rpgItem instanceof RPGItemArmor) {
        RPGItemArmor armor = (RPGItemArmor)rpgItem;
        switch (armor.getType()) {
          case HELMET:
            paladiumCost = 5;
            break;
          case CHESTPLATE:
            paladiumCost = 8;
            break;
          case LEGGINGS:
            paladiumCost = 7;
            break;
          case BOOTS:
            paladiumCost = 4;
            break;
        } 
      } 
      if (paladiumCost > this.paladiumAmount)
        return; 
      this.paladiumAmount -= paladiumCost;
      func_70299_a(3, null);
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      func_70296_d();
      return;
    } 
    if (!GrinderRecipe.getManager().isUpgradable(func_70301_a(4).func_77973_b(), func_70301_a(3).func_77973_b()))
      return; 
    ItemStack modifier = func_70301_a(3).func_77946_l();
    ItemStack item = func_70301_a(4).func_77946_l();
    if (modifier != null && item != null && this.player instanceof net.minecraft.entity.player.EntityPlayerMP) {
      ModifierApplyQuest.trigger(this.player, item, modifier);
      ModifierApplyAchievement.performCheck(this.player, item, modifier);
      MinecraftForge.EVENT_BUS.post((Event)new GrinderCraftEvent(this.player, item));
    } 
    int upgrade = GrinderRecipe.getManager().getUpgrade(func_70301_a(3).func_77973_b());
    ItemStack upgradeItemStack = func_70301_a(3).func_77946_l();
    (func_70301_a(3)).field_77994_a--;
    if ((func_70301_a(3)).field_77994_a <= 0)
      func_70299_a(3, null); 
    if (func_70301_a(4).func_77973_b() == ItemsRegister.GOD_PICKAXE && upgradeItemStack.func_77973_b() instanceof ItemGodPickaxeUpgrade) {
      ItemGodPickaxe pickaxe = (ItemGodPickaxe)func_70301_a(4).func_77973_b();
      ItemGodPickaxeUpgrade up = (ItemGodPickaxeUpgrade)upgradeItemStack.func_77973_b();
      if (up.getType() != null)
        pickaxe.addUpgrade(func_70301_a(4), up.getType()); 
    } else {
      UpgradeHelper.applyUpgrade(func_70301_a(4), upgrade);
    } 
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_70296_d();
  }
  
  public void smeltTool() {
    if (func_70301_a(1) == null || func_70301_a(2) == null)
      return; 
    ItemStack itemstack = GrinderRecipe.getManager().getSmeltingResult(new ItemStack[] { func_70301_a(1), func_70301_a(2) });
    if (itemstack == null)
      return; 
    this.paladiumAmount -= GrinderRecipe.getManager().getSmeltingAmmount(itemstack);
    if (!(func_70301_a(1).func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.ItemPatern) || !(func_70301_a(2).func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.ItemPatern)) {
      (func_70301_a(1)).field_77994_a--;
      (func_70301_a(2)).field_77994_a--;
      if ((func_70301_a(1)).field_77994_a <= 0)
        func_70299_a(1, null); 
      if ((func_70301_a(2)).field_77994_a <= 0)
        func_70299_a(2, null); 
    } 
    if (func_70301_a(0) == null || (func_70301_a(0).func_77973_b() != ItemsRegister.PALADIUM_INGOT && func_70301_a(0).func_77973_b() != Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM))) {
      func_70299_a(0, itemstack.func_77946_l());
    } else {
      (func_70301_a(0)).field_77994_a++;
    } 
    if (this.player instanceof net.minecraft.entity.player.EntityPlayerMP) {
      try {
        GrinderCraftQuest.trigger(this.player, itemstack, 1);
        MinecraftForge.EVENT_BUS.post((Event)new GrinderCraftEvent(this.player, itemstack));
      } catch (Exception e) {
        e.printStackTrace();
      } 
      GrinderCraftAchievement.performCheck(this.player, itemstack);
      if (PFactions.instance != null && PFactions.instance.getImpl() != null)
        PFactions.instance.getImpl().onGrinderCraft(this.player, itemstack); 
    } 
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_70296_d();
  }
  
  public float getScaledPaladium(int i) {
    return (this.paladiumAmount * i / this.maxPaladium);
  }
  
  public double getNonScaledPaladium() {
    return this.paladiumAmount / this.maxPaladium;
  }
  
  public int getScaledTool(int i) {
    return this.workedTimeCreation * i / this.timeNeededCreation;
  }
  
  public double getNonScaledTool() {
    return this.workedTimeCreation / this.timeNeededCreation;
  }
  
  public int getScaledUpgrade(int i) {
    return this.workedTimeUpgrade * i / this.timeNeededUpgrade;
  }
  
  public double getNonScaledUpgrade() {
    return this.workedTimeUpgrade / this.timeNeededUpgrade;
  }
  
  public int getScaledProgress(int i) {
    return this.workedTimePaladium * i / this.timeNeededPaladium;
  }
  
  public double getNonScaledProgress() {
    return this.workedTimePaladium / this.timeNeededPaladium;
  }
  
  public int getPaladium() {
    return this.paladiumAmount;
  }
  
  public int getMaxPaladium() {
    return this.maxPaladium;
  }
  
  public void consumePaladium() {
    ItemStack stack = func_70301_a(5).func_77946_l();
    int size = stack.field_77994_a;
    func_70296_d();
    int diff = this.maxPaladium - this.paladiumAmount;
    int ammount = 0;
    if (stack.func_77973_b() == ItemsRegister.PALADIUM_INGOT)
      ammount = 1; 
    if (stack.func_77973_b() == Item.func_150898_a(BlocksRegister.BLOCK_PALADIUM))
      ammount = 9; 
    if (stack.func_77973_b() instanceof ItemRepairableArmor && ((ItemRepairableArmor)stack
      .func_77973_b()).func_82812_d().equals(PArmorMaterial.paladium))
      ammount = (stack.func_77958_k() - stack.func_77960_j()) * ((ItemRepairableArmor)stack.func_77973_b()).getCost() / stack.func_77958_k(); 
    if (stack.func_77973_b() instanceof BaseItemSword && ((BaseItemSword)stack
      .func_77973_b()).getToolMaterial().equals(PToolMaterial.paladium))
      ammount = (stack.func_77958_k() - stack.func_77960_j()) * 2 / stack.func_77958_k(); 
    if (stack.func_77973_b() instanceof BaseItemPickaxe && ((BaseItemPickaxe)stack
      .func_77973_b()).getToolMaterial().equals(PToolMaterial.paladium))
      ammount = (stack.func_77958_k() - stack.func_77960_j()) * 3 / stack.func_77958_k(); 
    boolean removed = false;
    if (ammount != 0) {
      if (this.player instanceof net.minecraft.entity.player.EntityPlayerMP) {
        GrinderSmeltQuest.trigger(this.player, stack);
        if (PFactions.instance != null && PFactions.instance.getImpl() != null)
          PFactions.instance.getImpl().onGrinderSmelt(this.player, stack); 
      } 
      (func_70301_a(5)).field_77994_a = (int)((func_70301_a(5)).field_77994_a - Math.ceil(diff / ammount));
      if ((func_70301_a(5)).field_77994_a <= 0)
        func_70299_a(5, null); 
      removed = true;
    } 
    if (removed) {
      this.paladiumAmount += ammount * size;
      if (this.paladiumAmount > this.maxPaladium)
        this.paladiumAmount = this.maxPaladium; 
    } 
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74777_a("workedTimeCreation", (short)this.workedTimeCreation);
    compound.func_74777_a("workedTimeUpgrade", (short)this.workedTimeUpgrade);
    compound.func_74777_a("paladiumAmount", (short)this.paladiumAmount);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.workedTimeCreation = compound.func_74765_d("workedTimeCreation");
    this.workedTimeUpgrade = compound.func_74765_d("workedTimeUpgrade");
    this.paladiumAmount = compound.func_74765_d("paladiumAmount");
  }
  
  public int[] func_94128_d(int side) {
    return new int[0];
  }
  
  public boolean func_102007_a(int side, ItemStack stack, int slot) {
    return false;
  }
  
  public boolean func_102008_b(int side, ItemStack stack, int slot) {
    return false;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public void setPlayer(EntityPlayer player) {
    this.player = player;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\logics\GrinderLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */