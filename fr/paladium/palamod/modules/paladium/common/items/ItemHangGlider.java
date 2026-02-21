package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.combattag.CombatTag;
import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.common.helios.ModuleEggHunt;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.paladium.common.events.PlayerHandler;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Potions;
import fr.paladium.palamod.modules.stats.eep.StatsEep;
import fr.paladium.palapass.common.quest.misc.ItemUseQuest;
import fr.paladium.pet.client.PetClientProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.bukkit.Bukkit;

public class ItemHangGlider extends Item implements ITooltipWiki {
  public ItemHangGlider() {
    func_77625_d(1);
    func_77655_b("hangglider");
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_111206_d("palamod:hangglider");
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    NBTTagCompound nbt;
    if (stack.func_77942_o()) {
      nbt = stack.func_77978_p();
    } else {
      nbt = new NBTTagCompound();
    } 
    if (nbt.func_74764_b("Uses")) {
      nbt.func_82580_o("Uses");
    } else {
      nbt.func_74768_a("Uses", 1);
    } 
    nbt.func_74772_a("lastUse", System.currentTimeMillis());
    stack.func_77982_d(nbt);
    player.field_70143_R = 0.0F;
    return stack;
  }
  
  public void func_77663_a(ItemStack stack, World world, Entity entity, int slot, boolean hand) {
    super.func_77663_a(stack, world, entity, slot, hand);
    if (!(entity instanceof EntityPlayer) || stack.func_77973_b() != this)
      return; 
    EntityPlayer player = (EntityPlayer)entity;
    if (!world.field_72995_K && hand && PEggHunt.status != null && PEggHunt.status.getEggOwner() != null && PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_())) {
      if (player.field_70173_aa % 60 == 0)
        (new Notification(Notification.NotificationType.WARNING, "L'item " + func_77653_i(stack) + " est désactivé en possession de l'oeuf", PEggHunt.data.isEndEvent() ? "end" : "egghunt")).send((EntityPlayerMP)player); 
      return;
    } 
    if (world.field_72995_K && ModuleEggHunt.getInstance().isActive())
      return; 
    boolean onGround = player.field_70122_E;
    if (hand && player.field_70163_u > 0.0D && player.field_70163_u < 256.0D) {
      if (!stack.func_77942_o())
        return; 
      NBTTagCompound nbt = stack.func_77978_p();
      if (nbt.func_74764_b("Uses") && !WorldGuardUtils.isItemEffectBlocked(entity, stack)) {
        if (player.field_70181_x < 0.0D && !onGround) {
          double horizontalSpeed, verticalSpeed;
          boolean sneaking = false;
          if (player instanceof EntityPlayerMP) {
            int uses = ((Integer)PlayerHandler.getInstance().getPendingHangGliderUses().getOrDefault(player.func_110124_au(), Integer.valueOf(0))).intValue() + 1;
            if (uses > 20) {
              PlayerHandler.getInstance().getPendingHangGliderUses().put(player.func_110124_au(), Integer.valueOf(0));
              ItemUseQuest.trigger(player, stack, 1);
              if (PFactions.instance != null && PFactions.instance.getImpl() != null)
                PFactions.instance.getImpl().onItemUse(player, stack, 1); 
              StatsEep statsEep = StatsEep.get((Entity)player);
              statsEep.increaseHangGliderDistance(1);
            } else {
              PlayerHandler.getInstance().getPendingHangGliderUses().put(player.func_110124_au(), Integer.valueOf(uses));
            } 
          } 
          if (player.func_70093_af()) {
            horizontalSpeed = 0.1D;
            verticalSpeed = 0.8D;
            sneaking = true;
          } else {
            horizontalSpeed = 0.03D;
            verticalSpeed = 0.6D;
          } 
          if (player.func_70644_a((Potion)PRegister_Potions.potionGravity)) {
            PotionEffect pEffect = player.func_70660_b((Potion)PRegister_Potions.potionGravity);
            int ampli = pEffect.func_76458_c();
            float reduced = (float)((ampli == 1) ? 1.3D : ((ampli == 2) ? 1.35D : 1.45D));
            verticalSpeed *= reduced;
          } 
          if (!processPetSkill(player, horizontalSpeed, verticalSpeed, sneaking)) {
            double x = Math.cos(Math.toRadians((player.field_70759_as + 90.0F))) * horizontalSpeed;
            double z = Math.sin(Math.toRadians((player.field_70759_as + 90.0F))) * horizontalSpeed;
            player.field_70181_x *= verticalSpeed;
            player.field_70159_w += x;
            player.field_70179_y += z;
          } 
        } else if (onGround) {
          nbt.func_82580_o("Uses");
        } 
        nbt.func_74772_a("lastUse", System.currentTimeMillis());
        stack.func_77982_d(nbt);
        player.field_70143_R = 0.0F;
      } 
    } else if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("Uses") && onGround) {
      NBTTagCompound nbt = stack.func_77978_p();
      nbt.func_82580_o("Uses");
      nbt.func_74772_a("lastUse", System.currentTimeMillis());
      stack.func_77982_d(nbt);
      player.field_70143_R = 0.0F;
    } 
  }
  
  @SideOnly(Side.CLIENT)
  private double getSkillValue(PassiveSkillEnum skillEnum) {
    return PetClientProxy.getInstance().getSkillValue(skillEnum);
  }
  
  @SideOnly(Side.SERVER)
  private double getPersonalValue(PassiveSkillEnum skillEnum, PetPlayer pet) {
    return skillEnum.getResponse(pet).getPersonalValue(pet);
  }
  
  private boolean processPetSkill(EntityPlayer player, double horizontalSpeed, double verticalSpeed, boolean sneaking) {
    if (!player.field_70170_p.field_72995_K && player instanceof EntityPlayerMP) {
      try {
        if (CombatTag.getInstance().getManager().isInCombat(Bukkit.getPlayer(player.func_110124_au())))
          return false; 
      } catch (Exception|NoClassDefFoundError exception) {}
    } else if (player.field_70170_p.field_72995_K && CommonModule.getInstance().getCombatTag().inFight()) {
      return false;
    } 
    World world = player.field_70170_p;
    PassiveSkillEnum horizontalEnum = PassiveSkillEnum.AERIAL_EXPERT;
    PassiveSkillEnum verticalEnum = PassiveSkillEnum.AVIATOR;
    double horizontalValue = 0.0D;
    double verticalValue = 0.0D;
    if (world.field_72995_K && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      horizontalValue = getSkillValue(horizontalEnum);
      verticalValue = getSkillValue(verticalEnum);
    } else {
      PetPlayer pet = PetPlayer.get(player);
      horizontalValue = getPersonalValue(horizontalEnum, pet);
      verticalValue = getPersonalValue(verticalEnum, pet);
    } 
    if (horizontalValue <= 0.0D && verticalValue <= 0.0D)
      return false; 
    if (horizontalValue > 0.0D) {
      horizontalValue = PetUtils.getValueAsPercent(horizontalValue);
      horizontalSpeed *= 1.0D + horizontalValue;
    } 
    if (verticalValue > 0.0D) {
      double defaultValue = 0.6D;
      double maxValue = 0.57D;
      double reverserPercentage = verticalValue;
      double currentValue = 0.6D - reverserPercentage / 100.0D * 0.030000000000000027D;
      if (sneaking)
        currentValue += 0.2D; 
      verticalSpeed = currentValue;
    } 
    player.field_70181_x *= verticalSpeed;
    double x = Math.cos(Math.toRadians((player.field_70759_as + 90.0F))) * horizontalSpeed;
    double z = Math.sin(Math.toRadians((player.field_70759_as + 90.0F))) * horizontalSpeed;
    player.field_70159_w += x;
    player.field_70179_y += z;
    player.field_70143_R = 0.0F;
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pvp#4.-autre";
  }
  
  public static boolean isActive(ItemStack item) {
    return (item.func_77942_o() && item.func_77978_p().func_74764_b("Uses"));
  }
  
  public static long getLastUse(ItemStack item) {
    if (item.func_77942_o() && item.func_77978_p().func_74764_b("lastUse"))
      return item.func_77978_p().func_74763_f("lastUse"); 
    return 0L;
  }
  
  public boolean hasEffect(ItemStack par1ItemStack, int pass) {
    return (isActive(par1ItemStack) || super.hasEffect(par1ItemStack, pass));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemHangGlider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */