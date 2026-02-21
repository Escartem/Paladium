package fr.paladium.palamod.modules.luckyblock.items.may;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemMagneticCompass extends Item implements ITooltipInformations {
  private IIcon[] icons = new IIcon[32];
  
  public ItemMagneticCompass() {
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77655_b("magnetic_compass");
    func_111206_d("palamod:magnetic_compass");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      String currentWorld = CommonModule.getInstance().getConfig().getServerName().toUpperCase();
      Vec3 pos = null;
      switch (currentWorld) {
        case "EGOPOLIS":
          pos = Vec3.func_72443_a(-448.0D, 47.0D, -1208.0D);
          break;
        case "AELORIA":
          pos = Vec3.func_72443_a(651.0D, 51.0D, 1030.0D);
          break;
        case "XANOTH":
          pos = Vec3.func_72443_a(-1340.0D, 37.0D, -745.0D);
          break;
        case "KILMORDRA":
          pos = Vec3.func_72443_a(-441.0D, 38.0D, 1331.0D);
          break;
        case "RUNEGARD":
          pos = Vec3.func_72443_a(-404.0D, 48.0D, -1215.0D);
          break;
      } 
      if (pos != null) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74780_a("x", pos.field_72450_a);
        compound.func_74780_a("y", pos.field_72448_b);
        compound.func_74780_a("z", pos.field_72449_c);
        item.func_77982_d(compound);
      } else {
        PlayerUtils.sendMessage(player, "Essaye dans un autre monde.");
      } 
    } 
    return item;
  }
  
  public void func_94581_a(IIconRegister register) {
    for (int i = 0; i < 32; i++) {
      String index = String.valueOf(i);
      if (index.length() < 2)
        index = "0" + index; 
      this.icons[i] = register.func_94245_a("palamod:compass/magnetic_compass_" + index);
    } 
    this.field_77791_bV = this.icons[0];
    super.func_94581_a(register);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(ItemStack stack, int renderPass) {
    if (stack != null)
      return this.icons[apply(stack)]; 
    return this.icons[0];
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77650_f(ItemStack stack) {
    if (stack != null)
      return this.icons[apply(stack)]; 
    return this.icons[0];
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
    if (stack.func_77942_o() && 
      stack.func_77978_p().func_74764_b("x"))
      pos = new ChunkCoordinates(stack.func_77978_p().func_74762_e("x"), stack.func_77978_p().func_74762_e("y"), stack.func_77978_p().func_74762_e("z")); 
    if (pos == null)
      pos = new ChunkCoordinates((int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v); 
    return Math.atan2(pos.field_71573_c - entity.field_70161_v, pos.field_71574_a - entity.field_70165_t);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Cette boussole pointe vers un objet.", "Suis là pour obtenir une récompense." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\may\ItemMagneticCompass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */