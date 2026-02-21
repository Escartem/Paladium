package fr.paladium.palamod.modules.paladium.common.items.weapons;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.common.Registry;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.MathHelper;

public class ItemInfernalKnocker extends ItemSword implements ITooltipWiki {
  public ItemInfernalKnocker() {
    super(Item.ToolMaterial.WOOD);
    func_77655_b("infernalknocker");
    func_111206_d("palamod:InfernalKnocker");
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  private float applyPetSkill(EntityPlayerMP player, float knockback) {
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.STRONG_KNOCKBACK.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return knockback; 
    double percentage = response.getValueAsPercent(value);
    float increased = knockback * (float)percentage;
    return knockback + increased;
  }
  
  public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
    if (player.field_70170_p.field_72995_K)
      return true; 
    if (WorldGuardUtils.isItemEffectBlocked(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, Item.func_150891_b(stack.func_77973_b())))
      return false; 
    float knockback = 3.0F;
    knockback = applyPetSkill((EntityPlayerMP)player, knockback);
    target.func_70024_g((-MathHelper.func_76126_a(player.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (MathHelper.func_76134_b(player.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F));
    if (target instanceof EntityPlayerMP)
      ((EntityPlayerMP)target).field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)target)); 
    return super.func_77644_a(stack, target, player);
  }
  
  public boolean func_77636_d(ItemStack stack) {
    return false;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#7.-linfernal-knocker";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\weapons\ItemInfernalKnocker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */