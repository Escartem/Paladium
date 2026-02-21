package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class Creepesque extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = new ItemStack(ItemsRegister.CAPTURE_STONE, 1);
    stack.func_77964_b(1);
    stack.func_151001_c("§ePierre de capture §bCreeper");
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74757_a("used", true);
    EntityCreeper creeper = new EntityCreeper(player.field_70170_p);
    compound.func_74778_a("entity", creeper.getClass().getSimpleName());
    compound.func_74778_a("entityClass", creeper.getClass().getCanonicalName());
    NBTTagCompound nbt = new NBTTagCompound();
    creeper.func_70109_d(nbt);
    compound.func_74782_a("nbt", (NBTBase)nbt);
    NBTTagCompound entityNbt = new NBTTagCompound();
    creeper.func_70014_b(entityNbt);
    compound.func_74782_a("entityNbt", (NBTBase)entityNbt);
    stack.func_77982_d(compound);
    creeper.func_70106_y();
    PlayerUtils.dropItemStack((Entity)player, x, y, z, stack);
  }
  
  public String getName() {
    return "Creepesque";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "creepesque";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Creepesque.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */