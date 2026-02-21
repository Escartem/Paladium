package fr.paladium.palamod.modules.luckyblock.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLuckyPainting extends EntityHanging implements IEntityAdditionalSpawnData {
  public EnumArt art;
  
  public int y;
  
  public EntityLuckyPainting(World world) {
    super(world);
  }
  
  public EntityLuckyPainting(World world, int x, int y, int z, int side) {
    super(world, x, y, z, side);
    ArrayList<EnumArt> arraylist = new ArrayList();
    EnumArt[] aenumart = EnumArt.values();
    int i1 = aenumart.length;
    for (int j1 = 0; j1 < i1; j1++) {
      EnumArt enumart = aenumart[j1];
      this.art = enumart;
      func_82328_a(side);
      if (func_70518_d())
        arraylist.add(enumart); 
    } 
    if (!arraylist.isEmpty())
      this.art = arraylist.get(this.field_70146_Z.nextInt(arraylist.size())); 
    func_82328_a(side);
    this.y = y;
  }
  
  @SideOnly(Side.CLIENT)
  public EntityLuckyPainting(World world, int x, int y, int z, int side, String art) {
    this(world, x, y, z, side);
    EnumArt[] aenumart = EnumArt.values();
    int i1 = aenumart.length;
    for (int j1 = 0; j1 < i1; j1++) {
      EnumArt enumart = aenumart[j1];
      if (enumart.title.equals(art)) {
        this.art = enumart;
        break;
      } 
    } 
    func_82328_a(side);
    this.y = y;
  }
  
  public void func_70014_b(NBTTagCompound p_70014_1_) {
    p_70014_1_.func_74778_a("Motive", this.art.title);
    p_70014_1_.func_74768_a("cy", this.y);
    super.func_70014_b(p_70014_1_);
  }
  
  public void func_70037_a(NBTTagCompound p_70037_1_) {
    String s = p_70037_1_.func_74779_i("Motive");
    EnumArt[] aenumart = EnumArt.values();
    int i = aenumart.length;
    for (int j = 0; j < i; j++) {
      EnumArt enumart = aenumart[j];
      if (enumart.title.equals(s))
        this.art = enumart; 
    } 
    if (this.art == null)
      this.art = EnumArt.Kebab; 
    if (p_70037_1_.func_74764_b("cy"))
      this.y = p_70037_1_.func_74762_e("cy"); 
    super.func_70037_a(p_70037_1_);
  }
  
  public int func_82329_d() {
    return this.art.sizeX;
  }
  
  public int func_82330_g() {
    return this.art.sizeY;
  }
  
  public void func_110128_b(Entity entity) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer)entity;
      if (entityplayer.field_71075_bZ.field_75098_d)
        return; 
    } 
    func_70099_a(new ItemStack(ItemsRegister.LUCKY_PAINTING), 0.0F);
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    ByteBufUtils.writeUTF8String(buffer, this.art.title);
    buffer.writeInt(this.y);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    String s = ByteBufUtils.readUTF8String(additionalData);
    EnumArt[] aenumart = EnumArt.values();
    int i = aenumart.length;
    for (int j = 0; j < i; j++) {
      EnumArt enumart = aenumart[j];
      if (enumart.title.equals(s))
        this.art = enumart; 
    } 
    if (this.art == null)
      this.art = EnumArt.Kebab; 
    this.y = additionalData.readInt();
  }
  
  public enum EnumArt {
    Kebab("Paladium Small", 16, 16, 0, 0),
    Aztec("Snow", 16, 16, 16, 0),
    Alban("Alban", 16, 16, 32, 0),
    Aztec2("Aztec2", 16, 16, 48, 0),
    Bomb("Bomb", 16, 16, 64, 0),
    Plant("Plant", 16, 16, 80, 0),
    Wasteland("Wasteland", 16, 16, 96, 0),
    Pool("Pool", 32, 16, 0, 32),
    Courbet("Courbet", 32, 16, 32, 32),
    Sea("Sea", 32, 16, 64, 32),
    Sunset("Sunset", 32, 16, 96, 32),
    Creebet("Creebet", 32, 16, 128, 32),
    Wanderer("Wanderer", 16, 32, 0, 64),
    Match("Paladium", 32, 32, 0, 128),
    Bust("Bust", 32, 32, 32, 128),
    Stage("Stage", 32, 32, 64, 128),
    Void("Void", 32, 32, 96, 128),
    Fighters("Butterfly", 64, 32, 0, 96),
    Pointer("Pointer", 64, 64, 0, 192),
    Skeleton("Skeleton", 64, 48, 192, 64);
    
    public static final int maxArtTitleLength = "Paladium Small".length();
    
    public final String title;
    
    public final int sizeX;
    
    public final int sizeY;
    
    public final int offsetX;
    
    public final int offsetY;
    
    private static final String __OBFID = "CL_00001557";
    
    static {
    
    }
    
    EnumArt(String title, int width, int height, int x, int y) {
      this.title = title;
      this.sizeX = width;
      this.sizeY = height;
      this.offsetX = x;
      this.offsetY = y;
    }
  }
  
  public void func_70030_z() {
    super.func_70030_z();
  }
  
  public void func_70071_h_() {
    this.field_70163_u = this.y + 0.5D;
    this.field_70167_r = this.y + 0.5D;
    this.field_70137_T = this.y + 0.5D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityLuckyPainting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */