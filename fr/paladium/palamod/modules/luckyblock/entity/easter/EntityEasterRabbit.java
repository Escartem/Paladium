package fr.paladium.palamod.modules.luckyblock.entity.easter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketRainbowParticle;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityEasterRabbit extends EntityRabbit {
  private int jumpTimer = 0;
  
  private int jumpNumber = 0;
  
  private EnumFacing[] horizontalDir = new EnumFacing[] { EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST };
  
  private Item itemToDropEachJump;
  
  private int jumpMaxNumber;
  
  public Item getItemToDropEachJump() {
    return this.itemToDropEachJump;
  }
  
  public void setItemToDropEachJump(Item itemToDropEachJump) {
    this.itemToDropEachJump = itemToDropEachJump;
  }
  
  public int getJumpMaxNumber() {
    return this.jumpMaxNumber;
  }
  
  public void setJumpMaxNumber(int jumpMaxNumber) {
    this.jumpMaxNumber = jumpMaxNumber;
  }
  
  public EntityEasterRabbit(World world) {
    super(world);
    this.jumpMaxNumber = 15;
    func_94058_c("§b");
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    if (!this.field_70170_p.field_72995_K) {
      this.jumpTimer++;
      if (this.jumpTimer % 10 == 0) {
        int radius = 20;
        for (Object o : this.field_70170_p.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(this.field_70165_t - radius, 0.0D, this.field_70161_v - radius, this.field_70165_t + radius, 255.0D, this.field_70161_v + radius))) {
          if (o instanceof EntityPlayerMP) {
            EntityPlayerMP p = (EntityPlayerMP)o;
            PalaMod.getNetwork().sendTo((IMessage)new PacketRainbowParticle((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v), p);
          } 
        } 
      } 
      if (this.jumpTimer >= 20) {
        this.jumpNumber++;
        func_70664_aZ();
        BlockPos currentPos = new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        EnumFacing direction = this.horizontalDir[this.field_70146_Z.nextInt(4)];
        BlockPos newPos = currentPos.offset(direction, 2);
        for (int yOffset = 0; yOffset < 2; yOffset++) {
          if (this.field_70170_p.func_147437_c(newPos.getX(), newPos.getY() + yOffset, newPos.getZ())) {
            switch (direction) {
              case EAST:
                func_70080_a(newPos.getX() + 0.5D, (newPos.getY() + yOffset), newPos.getZ() + 0.5D, 90.0F, 0.0F);
                break;
              case NORTH:
                func_70080_a(newPos.getX() + 0.5D, (newPos.getY() + yOffset), newPos.getZ() + 0.5D, 180.0F, 0.0F);
                break;
              case SOUTH:
                func_70080_a(newPos.getX() + 0.5D, (newPos.getY() + yOffset), newPos.getZ() + 0.5D, 0.0F, 0.0F);
                break;
              case WEST:
                func_70080_a(newPos.getX() + 0.5D, (newPos.getY() + yOffset), newPos.getZ() + 0.5D, -90.0F, 0.0F);
                break;
            } 
            break;
          } 
        } 
        ItemStack itemStack = new ItemStack(this.itemToDropEachJump, 1, 0);
        PlayerUtils.dropItemStack((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, itemStack);
        if (this.jumpNumber >= this.jumpMaxNumber)
          func_70106_y(); 
        this.jumpTimer = 0;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\easter\EntityEasterRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */