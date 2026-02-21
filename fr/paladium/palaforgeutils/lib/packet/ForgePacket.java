package fr.paladium.palaforgeutils.lib.packet;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.network.callback.AbstractPacketCallback;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketDataField;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSide;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.validator.Validators;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import io.netty.buffer.ByteBuf;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public abstract class ForgePacket implements IMessage, IBufSerializable {
  private static final Map<Class<? extends ForgePacket>, PacketDataField[]> CACHED_FIELDS = (Map)new HashMap<>();
  
  private static final Map<UUID, Consumer<Object>> CALLBACK_MAP = new HashMap<>();
  
  private SimpleNetworkWrapper network;
  
  private UUID callbackUUID;
  
  private ByteBuf contextBuffer;
  
  private Side effectiveSide;
  
  private String effectivePlayer;
  
  private final Map<Field, Object> cachedFieldValues = new HashMap<>();
  
  public void processServer(EntityPlayerMP player) {}
  
  public void processClient() {}
  
  public void toBytes(ByteBuf buf) {
    this.effectiveSide = FMLCommonHandler.instance().getSide();
    this.contextBuffer = buf;
    ByteBufUtils.writeUTF8String(buf, PacketUtils.getPacketName((Class)getClass()));
    PacketSerialUtils.write(buf, this.callbackUUID);
    write(buf);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.effectiveSide = FMLCommonHandler.instance().getSide();
    this.contextBuffer = buf;
    read(buf);
  }
  
  public void write(ByteBuf buf) {
    Class<? extends ForgePacket> clazz = (Class)getClass();
    if (!CACHED_FIELDS.containsKey(clazz)) {
      List<PacketDataField> fields = new ArrayList<>();
      for (Field field : clazz.getDeclaredFields()) {
        if (field.isAnnotationPresent((Class)PacketData.class)) {
          if (!field.isAccessible())
            field.setAccessible(true); 
          fields.add(new PacketDataField(field, field.<PacketData>getAnnotation(PacketData.class)));
        } 
      } 
      Collections.sort(fields);
      CACHED_FIELDS.put(clazz, fields.toArray(new PacketDataField[0]));
    } 
    try {
      for (PacketDataField field : (PacketDataField[])CACHED_FIELDS.get(clazz)) {
        if (field.getData().value() == PacketSide.BOTH || this.effectiveSide == field.getData().value().getSide()) {
          Object value = null;
          if (this.cachedFieldValues.containsKey(field.getField())) {
            value = this.cachedFieldValues.get(field.getField());
          } else {
            this.cachedFieldValues.put(field.getField(), value = field.getField().get(this));
          } 
          write(value);
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void read(ByteBuf buf) {
    Class<? extends ForgePacket> clazz = (Class)getClass();
    if (!CACHED_FIELDS.containsKey(clazz)) {
      List<PacketDataField> fields = new ArrayList<>();
      for (Field field : clazz.getDeclaredFields()) {
        if (field.isAnnotationPresent((Class)PacketData.class)) {
          if (!field.isAccessible())
            field.setAccessible(true); 
          fields.add(new PacketDataField(field, field.<PacketData>getAnnotation(PacketData.class)));
        } 
      } 
      Collections.sort(fields);
      CACHED_FIELDS.put(clazz, fields.toArray(new PacketDataField[0]));
    } 
    ValidatorException exception = null;
    try {
      for (PacketDataField field : (PacketDataField[])CACHED_FIELDS.get(clazz)) {
        if (field.getData().value() == PacketSide.BOTH || this.effectiveSide != field.getData().value().getSide()) {
          Class<?>[] typeArray = null;
          Type genericType = field.getField().getGenericType();
          if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)genericType;
            Type[] parameterizedTypeArguments = parameterizedType.getActualTypeArguments();
            typeArray = new Class[parameterizedTypeArguments.length];
            for (int i = 0; i < parameterizedTypeArguments.length; i++)
              typeArray[i] = (Class)parameterizedTypeArguments[i]; 
          } else {
            typeArray = new Class[] { field.getField().getType() };
          } 
          Object value = read(typeArray);
          field.getField().set(this, value);
          if (exception == null)
            exception = Validators.validate(field.getField(), value, false); 
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    if (exception != null)
      throw exception; 
  }
  
  @Deprecated
  public void writeBoolean(ByteBuf buf, boolean value) {
    PacketSerialUtils.writeBoolean(buf, value);
  }
  
  @Deprecated
  public void writeByte(ByteBuf buf, byte value) {
    PacketSerialUtils.writeByte(buf, value);
  }
  
  @Deprecated
  public void writeChar(ByteBuf buf, char value) {
    PacketSerialUtils.writeChar(buf, value);
  }
  
  @Deprecated
  public void writeDouble(ByteBuf buf, double value) {
    PacketSerialUtils.writeDouble(buf, value);
  }
  
  @Deprecated
  public void writeFloat(ByteBuf buf, float value) {
    PacketSerialUtils.writeFloat(buf, value);
  }
  
  @Deprecated
  public void writeLong(ByteBuf buf, long value) {
    PacketSerialUtils.writeLong(buf, value);
  }
  
  @Deprecated
  public void writeInt(ByteBuf buf, int value) {
    PacketSerialUtils.writeInt(buf, value);
  }
  
  @Deprecated
  public void writeString(ByteBuf buf, String value) {
    PacketSerialUtils.writeString(buf, value);
  }
  
  @Deprecated
  public void writeNbt(ByteBuf buf, NBTTagCompound nbt) {
    PacketSerialUtils.writeNbt(buf, nbt);
  }
  
  @Deprecated
  public void writeItemStack(ByteBuf buf, ItemStack item) {
    PacketSerialUtils.writeItemStack(buf, item);
  }
  
  @Deprecated
  public void writeObject(ByteBuf buf, Object object) {
    PacketSerialUtils.writeObject(buf, object);
  }
  
  public void write(Object object) {
    PacketSerialUtils.write(this.contextBuffer, object);
  }
  
  @Deprecated
  public boolean readBoolean(ByteBuf buf) {
    return PacketSerialUtils.readBoolean(buf);
  }
  
  @Deprecated
  public byte readByte(ByteBuf buf) {
    return PacketSerialUtils.readByte(buf);
  }
  
  @Deprecated
  public char readChar(ByteBuf buf) {
    return PacketSerialUtils.readChar(buf);
  }
  
  @Deprecated
  public double readDouble(ByteBuf buf) {
    return PacketSerialUtils.readDouble(buf);
  }
  
  @Deprecated
  public float readFloat(ByteBuf buf) {
    return PacketSerialUtils.readFloat(buf);
  }
  
  @Deprecated
  public long readLong(ByteBuf buf) {
    return PacketSerialUtils.readLong(buf);
  }
  
  @Deprecated
  public int readInt(ByteBuf buf) {
    return PacketSerialUtils.readInt(buf);
  }
  
  @Deprecated
  public String readString(ByteBuf buf) {
    return PacketSerialUtils.readString(buf);
  }
  
  @Deprecated
  public NBTTagCompound readNbt(ByteBuf buf) {
    return PacketSerialUtils.readNbt(buf);
  }
  
  @Deprecated
  public ItemStack readItemStack(ByteBuf buf) {
    return PacketSerialUtils.readItemStack(buf);
  }
  
  @Deprecated
  public <T> T readObject(ByteBuf buf, Type type) {
    return (T)PacketSerialUtils.readObject(buf, new Type[] { type });
  }
  
  public Object read(Class<?>... type) {
    try {
      return PacketSerialUtils.read(this.contextBuffer, type);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public void setNetwork(SimpleNetworkWrapper network) {
    this.network = network;
  }
  
  public SimpleNetworkWrapper getNetwork() {
    if (this.network == null) {
      String name = PacketUtils.getPacketName((Class)getClass());
      this.network = PacketUtils.getNetwork(name);
    } 
    return this.network;
  }
  
  public void send(EntityPlayerMP player) {
    getNetwork().sendTo(this, player);
  }
  
  public void send(PlayerSelector selector) {
    selector.apply(this::send);
  }
  
  public void send() {
    getNetwork().sendToServer(this);
  }
  
  public void sendToAll() {
    getNetwork().sendToAll(this);
  }
  
  public <T> ForgePacket subscribe(Consumer<T> callback) {
    CALLBACK_MAP.put(this.callbackUUID = UUID.randomUUID(), callback);
    return this;
  }
  
  public void reply(Object reply) {
    if (reply instanceof ForgePacket) {
      ForgePacket packet = (ForgePacket)reply;
      packet.setCallbackUUID(this.callbackUUID);
      if (this.effectiveSide == Side.CLIENT) {
        packet.send();
      } else if (this.effectiveSide == Side.SERVER) {
        EntityPlayerMP player = getEffectivePlayer();
        if (player == null)
          return; 
        packet.send(player);
      } 
      return;
    } 
    if (this.callbackUUID == null)
      return; 
    AbstractPacketCallback abstractPacketCallback = new AbstractPacketCallback(this.callbackUUID, reply);
    SimpleNetworkWrapper network = getNetwork();
    if (this.effectiveSide == Side.CLIENT) {
      network.sendToServer((IMessage)abstractPacketCallback);
    } else if (this.effectiveSide == Side.SERVER) {
      EntityPlayerMP player = getEffectivePlayer();
      if (player == null)
        return; 
      network.sendTo((IMessage)abstractPacketCallback, player);
    } 
  }
  
  public Consumer<Object> pollCallback() {
    return pollCallback(this.callbackUUID);
  }
  
  public static Consumer<Object> pollCallback(UUID uuid) {
    if (uuid == null)
      return null; 
    return CALLBACK_MAP.remove(uuid);
  }
  
  public void setCallbackUUID(UUID callbackUUID) {
    this.callbackUUID = callbackUUID;
  }
  
  public ByteBuf getContextBuffer() {
    return this.contextBuffer;
  }
  
  public Side getSide() {
    return this.effectiveSide;
  }
  
  public boolean isRemote() {
    return this.effectiveSide.isClient();
  }
  
  @SideOnly(Side.SERVER)
  public EntityPlayerMP getEffectivePlayer() {
    if (this.effectivePlayer == null)
      return null; 
    return MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(this.effectivePlayer);
  }
  
  public void setSide(Side effectiveSide) {
    this.effectiveSide = effectiveSide;
  }
  
  public void setEffectivePlayer(EntityPlayerMP player) {
    this.effectivePlayer = player.func_70005_c_();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\ForgePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */