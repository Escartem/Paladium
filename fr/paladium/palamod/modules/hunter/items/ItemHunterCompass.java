package fr.paladium.palamod.modules.hunter.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemHunterCompass extends Item implements ITooltipWiki {
  private IIcon[][] icons = new IIcon[5][32];
  
  public ItemHunterCompass() {
    func_77637_a((CreativeTabs)Registry.HUNTER_TAB);
    func_77655_b("hunter_compass");
    func_111206_d("palamod:hunter_compass");
    func_77625_d(1);
  }
  
  public String func_77667_c(ItemStack item) {
    String type = "";
    switch (item.func_77960_j()) {
      case 0:
        type = "amethyste";
        break;
      case 1:
        type = "titane";
        break;
      case 2:
        type = "paladium";
        break;
      case 3:
        type = "endium";
        break;
      case 4:
        type = "legendaire";
        break;
    } 
    return "item.hunter_compass_" + type;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!world.field_72995_K);
    return stack;
  }
  
  public void func_150895_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < (HunterCompassType.values()).length; i++)
      list.add(new ItemStack(item, 1, i)); 
  }
  
  public String func_77653_i(ItemStack item) {
    String name = "";
    switch (item.func_77960_j()) {
      case 0:
        name = "§eBoussole en §dAmethyste";
        break;
      case 1:
        name = "§eBoussole en §7Titane";
        break;
      case 2:
        name = "§eBoussole en §cPaladium";
        break;
      case 3:
        name = "§eBoussole en §9Endium";
        break;
      case 4:
        name = "§eBoussole §6Légendaire";
        break;
    } 
    return name;
  }
  
  public HunterCompassType getHunterCompassType(ItemStack item) {
    return HunterCompassType.values()[item.func_77960_j()];
  }
  
  public enum HunterCompassType {
    AMETHYST, TITANE, PALADIUM, ENDIUM, LEGENDAIRE;
  }
  
  public void func_94581_a(IIconRegister register) {
    for (int k = 0; k < 5; k++) {
      String type = (k == 0) ? "amethyst" : ((k == 1) ? "titane" : ((k == 2) ? "paladium" : ((k == 3) ? "endium" : "legend")));
      for (int i = 0; i < 32; i++) {
        String index = String.valueOf(i);
        if (index.length() < 2)
          index = "0" + index; 
        this.icons[k][i] = register.func_94245_a("palamod:compass/compass_" + type + index);
      } 
      this.field_77791_bV = this.icons[k][0];
    } 
    super.func_94581_a(register);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(ItemStack stack, int renderPass) {
    if (stack != null)
      return this.icons[stack.func_77960_j()][apply(stack)]; 
    return this.icons[stack.func_77960_j()][0];
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77650_f(ItemStack stack) {
    if (stack != null)
      return this.icons[stack.func_77960_j()][apply(stack)]; 
    return this.icons[stack.func_77960_j()][0];
  }
  
  @SideOnly(Side.CLIENT)
  public int apply(ItemStack stack) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    if (entityClientPlayerMP == null && !stack.func_82839_y())
      return 0; 
    boolean entityExists = (entityClientPlayerMP != null);
    Entity entity = entityExists ? (Entity)entityClientPlayerMP : (Entity)stack.func_82836_z();
    double rotation = entityExists ? entity.field_70177_z : getFrameRotation((EntityItemFrame)entity);
    rotation %= 360.0D;
    double adjusted = Math.PI - (rotation - 90.0D) * 0.01745329238474369D - getAngle(entity, stack);
    float f = (float)(adjusted / 6.283185307179586D) % 1.0F + 0.0F;
    return MathHelper.func_76125_a(Math.round((f * 32.0F + 16.0F) % 32.0F), 0, 31);
  }
  
  @SideOnly(Side.CLIENT)
  private double getFrameRotation(EntityItemFrame itemFrame) {
    return clampAngle(180 + itemFrame.field_82332_a * 90);
  }
  
  @SideOnly(Side.CLIENT)
  private int clampAngle(int angle) {
    angle %= 360;
    if (angle >= 180)
      angle -= 360; 
    if (angle < -180)
      angle += 360; 
    return angle;
  }
  
  @SideOnly(Side.CLIENT)
  private double getAngle(Entity entity, ItemStack stack) {
    ChunkCoordinates pos = null;
    if (entity instanceof EntityPlayer) {
      EntityPlayer p = (EntityPlayer)entity;
      if (!JobsBridge.canUseCraft(p, stack, false)) {
        pos = new ChunkCoordinates((int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v);
        return Math.atan2(pos.field_71573_c - entity.field_70161_v, pos.field_71574_a - entity.field_70165_t);
      } 
    } 
    if (stack.func_77942_o() && 
      stack.func_77978_p().func_74764_b("x"))
      pos = new ChunkCoordinates(stack.func_77978_p().func_74762_e("x"), stack.func_77978_p().func_74762_e("y"), stack.func_77978_p().func_74762_e("z")); 
    if (pos == null)
      pos = new ChunkCoordinates((int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v); 
    return Math.atan2(pos.field_71573_c - entity.field_70161_v, pos.field_71574_a - entity.field_70165_t);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> lore, boolean flag) {
    boolean binded = false;
    if (stack.func_77942_o() && 
      stack.func_77978_p().func_74764_b("x"))
      binded = true; 
    if (binded) {
      lore.add("§aUn pillage à été trouvé");
    } else {
      lore.add("§cAucun pillage trouvé sur ce serveur");
    } 
    super.func_77624_a(stack, player, lore, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pillage#5.-les-boussoles-a-pillage";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\items\ItemHunterCompass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */