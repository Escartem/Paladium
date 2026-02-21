package fr.paladium.palaforgeutils.lib.packet.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.network.ByteBufUtils;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.nbt.FastNBT;
import fr.paladium.palaforgeutils.lib.packet.serial.ByteBufSerializerManager;
import fr.paladium.palaforgeutils.lib.packet.serial.ByteBufSerializerType;
import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import lombok.NonNull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PacketSerialUtils {
  private static final GsonBuilder GSON_BUILDER = new GsonBuilder();
  
  private static final int COMPRESSION_THRESHOLD = 8192;
  
  private static final ThreadLocal<byte[]> TL_BUFFER = (ThreadLocal)ThreadLocal.withInitial(() -> new byte[8192]);
  
  private static final ThreadLocal<Deflater> TL_DEFLATER = ThreadLocal.withInitial(() -> new Deflater(1));
  
  private static final ThreadLocal<Inflater> TL_INFLATER = ThreadLocal.withInitial(Inflater::new);
  
  private static Gson gson;
  
  public static void writeBoolean(ByteBuf buf, boolean value) {
    buf.writeBoolean(value);
  }
  
  public static void writeByte(ByteBuf buf, byte value) {
    buf.writeByte(value);
  }
  
  public static void writeChar(ByteBuf buf, char value) {
    buf.writeChar(value);
  }
  
  public static void writeDouble(ByteBuf buf, double value) {
    buf.writeDouble(value);
  }
  
  public static void writeFloat(ByteBuf buf, float value) {
    buf.writeFloat(value);
  }
  
  public static void writeLong(ByteBuf buf, long value) {
    buf.writeLong(value);
  }
  
  public static void writeInt(ByteBuf buf, int value) {
    buf.writeInt(value);
  }
  
  public static void writeString(ByteBuf buf, String value) {
    if (value == null) {
      buf.writeBoolean(false);
      ByteBufUtils.writeVarInt(buf, 0, 5);
      return;
    } 
    byte[] utf8Bytes = value.getBytes(StandardCharsets.UTF_8);
    int len = utf8Bytes.length;
    if (len > 8192) {
      byte[] compressed = compress(utf8Bytes);
      if (compressed != null && compressed.length < len) {
        buf.writeBoolean(true);
        ByteBufUtils.writeVarInt(buf, compressed.length, 5);
        buf.writeBytes(compressed);
      } else {
        buf.writeBoolean(false);
        ByteBufUtils.writeVarInt(buf, len, 5);
        buf.writeBytes(utf8Bytes);
      } 
      return;
    } 
    buf.writeBoolean(false);
    ByteBufUtils.writeVarInt(buf, len, 5);
    buf.writeBytes(utf8Bytes);
  }
  
  public static void writeNbt(ByteBuf buf, NBTTagCompound nbt) {
    try {
      if (nbt == null) {
        ByteBufUtils.writeVarInt(buf, 0, 5);
        return;
      } 
      byte[] data = FastNBT.compress(nbt);
      if (data == null) {
        ByteBufUtils.writeVarInt(buf, 0, 5);
        return;
      } 
      int length = data.length;
      if (length <= 0) {
        ByteBufUtils.writeVarInt(buf, 0, 5);
        return;
      } 
      ByteBufUtils.writeVarInt(buf, length, 5);
      buf.writeBytes(data);
    } catch (IOException e) {
      throw new RuntimeException("Failed to write NBT data", e);
    } 
  }
  
  public static void writeItemStack(ByteBuf buf, ItemStack item) {
    if (item == null) {
      buf.writeShort(-1);
      return;
    } 
    buf.writeShort(Item.func_150891_b(item.func_77973_b()));
    buf.writeByte(item.field_77994_a);
    buf.writeShort(item.func_77960_j());
    if (item.func_77973_b().func_77645_m() || item.func_77973_b().func_77651_p())
      writeNbt(buf, item.func_77978_p()); 
  }
  
  public static void writeObject(ByteBuf buf, Object object) {
    writeString(buf, getGson().toJson(object));
  }
  
  public static void writeEnum(ByteBuf buf, Enum<?> element) {
    buf.writeInt(element.ordinal());
  }
  
  public static void writeList(ByteBuf buf, Collection<?> collection) {
    writeInt(buf, collection.size());
    for (Object object : collection)
      write(buf, object); 
  }
  
  public static void writeSet(ByteBuf buf, Set<?> set) {
    writeInt(buf, set.size());
    for (Object object : set)
      write(buf, object); 
  }
  
  public static void writeMap(ByteBuf buf, Map<?, ?> map) {
    if (map == null) {
      writeInt(buf, 0);
      return;
    } 
    writeInt(buf, map.size());
    for (Map.Entry<?, ?> entry : map.entrySet()) {
      write(buf, entry.getKey());
      write(buf, entry.getValue());
    } 
  }
  
  public static void write(ByteBuf buf, Object object) {
    if (object == null) {
      writeEnum(buf, (Enum<?>)ByteBufSerializerType.NULL);
      return;
    } 
    IBufSerializer<?> serializer = ByteBufSerializerManager.getSerializer(object.getClass());
    if (serializer != null) {
      writeEnum(buf, (Enum<?>)ByteBufSerializerType.CUSTOM);
      writeInt(buf, ByteBufSerializerManager.getSerializerId(serializer));
      serializer.write(buf, object);
      return;
    } 
    if (object instanceof Collection && ForgeEnv.isIDE()) {
      System.err.println("[PalaForgeUtils][Network] You use an unmapped type of Collection.");
      System.err.println("[PalaForgeUtils][Network] " + object + " is now serialized as JsonObject.");
    } 
    writeEnum(buf, (Enum<?>)ByteBufSerializerType.OBJECT);
    writeObject(buf, object);
  }
  
  public static boolean readBoolean(ByteBuf buf) {
    return buf.readBoolean();
  }
  
  public static byte readByte(ByteBuf buf) {
    return buf.readByte();
  }
  
  public static char readChar(ByteBuf buf) {
    return buf.readChar();
  }
  
  public static double readDouble(ByteBuf buf) {
    return buf.readDouble();
  }
  
  public static float readFloat(ByteBuf buf) {
    return buf.readFloat();
  }
  
  public static long readLong(ByteBuf buf) {
    return buf.readLong();
  }
  
  public static int readInt(ByteBuf buf) {
    return buf.readInt();
  }
  
  public static String readString(ByteBuf buf) {
    boolean compressed = buf.readBoolean();
    int length = ByteBufUtils.readVarInt(buf, 5);
    if (length == 0)
      return ""; 
    byte[] bytes = new byte[length];
    buf.readBytes(bytes);
    if (!compressed)
      return new String(bytes, StandardCharsets.UTF_8); 
    byte[] decompressed = decompress(bytes);
    if (decompressed != null)
      return new String(decompressed, StandardCharsets.UTF_8); 
    throw new RuntimeException("Failed to decompress string data");
  }
  
  public static NBTTagCompound readNbt(ByteBuf buf) {
    int length = ByteBufUtils.readVarInt(buf, 5);
    if (length <= 0)
      return null; 
    byte[] data = new byte[length];
    buf.readBytes(data);
    try {
      return FastNBT.decompress(data);
    } catch (IOException e) {
      throw new RuntimeException("Failed to read NBT data", e);
    } 
  }
  
  public static ItemStack readItemStack(ByteBuf buf) {
    int itemId = buf.readShort();
    if (itemId < 0)
      return null; 
    int stackSize = buf.readByte();
    int itemDamage = buf.readShort();
    Item item = Item.func_150899_d(itemId);
    if (item == null)
      return null; 
    ItemStack itemStack = new ItemStack(item, stackSize, itemDamage);
    if (item.func_77645_m() || item.func_77651_p())
      itemStack.func_77982_d(readNbt(buf)); 
    return itemStack;
  }
  
  public static <T> T readObject(ByteBuf buf, Type... type) {
    if (type == null || type.length == 0) {
      readString(buf);
      return null;
    } 
    String value = readString(buf);
    return (T)getGson().fromJson(value, type[0]);
  }
  
  public static <T> T readEnum(ByteBuf buf, Class<T> type) {
    if (type == null || !type.isEnum() || type.getEnumConstants() == null || (type.getEnumConstants()).length == 0) {
      buf.readInt();
      return null;
    } 
    return type.getEnumConstants()[buf.readInt()];
  }
  
  public static <T> Collection<T> readList(ByteBuf buf, Class<T> type) {
    int size = readInt(buf);
    List<T> list = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      list.add((T)read(buf, new Class[] { type }));
    } 
    return list;
  }
  
  public static <T> Set<T> readSet(ByteBuf buf, Class<T> type) {
    int size = readInt(buf);
    Set<T> set = new HashSet<>(size);
    for (int i = 0; i < size; i++) {
      set.add((T)read(buf, new Class[] { type }));
    } 
    return set;
  }
  
  public static <K, V> Map<K, V> readMap(ByteBuf buf, Class<K> keyClass, Class<V> valueClass) {
    int size = readInt(buf);
    Map<K, V> map = new HashMap<>(size);
    for (int i = 0; i < size; i++) {
      K key = (K)read(buf, new Class[] { keyClass });
      V value = (V)read(buf, new Class[] { valueClass });
      map.put(key, value);
    } 
    return map;
  }
  
  public static Object read(ByteBuf buf, Class<?>... clazz) {
    ByteBufSerializerType serialType = readEnum(buf, ByteBufSerializerType.class);
    if (serialType == ByteBufSerializerType.NULL)
      return null; 
    if (serialType == ByteBufSerializerType.OBJECT)
      return readObject(buf, (Type[])clazz); 
    if (serialType == ByteBufSerializerType.CUSTOM) {
      int serialId = readInt(buf);
      IBufSerializer<?> serializer = ByteBufSerializerManager.getSerializer(serialId);
      if (serializer != null)
        return serializer.read(buf, clazz); 
    } 
    return null;
  }
  
  @NonNull
  public static GsonBuilder getGsonBuilder() {
    return GSON_BUILDER;
  }
  
  @NonNull
  public static Gson getGson() {
    if (gson == null)
      gson = GSON_BUILDER.create(); 
    return gson;
  }
  
  private static byte[] compress(byte[] data) {
    try {
      Deflater deflater = TL_DEFLATER.get();
      deflater.reset();
      deflater.setInput(data);
      deflater.finish();
      ByteArrayOutputStream baos = new ByteArrayOutputStream(Math.max(data.length / 2, 256));
      byte[] buffer = TL_BUFFER.get();
      while (!deflater.finished()) {
        int read = deflater.deflate(buffer);
        if (read > 0)
          baos.write(buffer, 0, read); 
      } 
      return baos.toByteArray();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  private static byte[] decompress(byte[] data) {
    try {
      Inflater inflater = TL_INFLATER.get();
      inflater.reset();
      inflater.setInput(data);
      ByteArrayOutputStream baos = new ByteArrayOutputStream(Math.max(data.length * 2, 512));
      byte[] buffer = TL_BUFFER.get();
      while (!inflater.finished()) {
        int count = inflater.inflate(buffer);
        if (count > 0) {
          baos.write(buffer, 0, count);
          continue;
        } 
        if (inflater.needsInput() || inflater.needsDictionary())
          break; 
      } 
      return baos.toByteArray();
    } catch (DataFormatException e) {
      e.printStackTrace();
      return null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packe\\utils\PacketSerialUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */