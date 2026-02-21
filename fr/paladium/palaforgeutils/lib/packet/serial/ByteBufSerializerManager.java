package fr.paladium.palaforgeutils.lib.packet.serial;

import fr.paladium.palaforgeutils.lib.packet.serial.dto.ArrayListBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.BooleanBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.ByteBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.CharacterBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.DoubleBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.EnumBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.FloatBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.HashMapBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.HashSetBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.IntegerBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.ItemStackBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.LongBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.NBTTagCompoundBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.OfflinePlayerBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.SerializableBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.StringBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.serial.dto.UUIDBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ByteBufSerializerManager {
  private static Map<Class<?>, IBufSerializer<?>> SERIALIZERS = new HashMap<>();
  
  private static Map<Integer, IBufSerializer<?>> SERIALIZERS_ID = new HashMap<>();
  
  private static Map<IBufSerializer<?>, Integer> SERIALIZERS_ID_REVERSE = new HashMap<>();
  
  static {
    addSerializer(Boolean.class, (IBufSerializer<?>)new BooleanBufSerializer());
    addSerializer(Byte.class, (IBufSerializer<?>)new ByteBufSerializer());
    addSerializer(Character.class, (IBufSerializer<?>)new CharacterBufSerializer());
    addSerializer(Double.class, (IBufSerializer<?>)new DoubleBufSerializer());
    addSerializer(Float.class, (IBufSerializer<?>)new FloatBufSerializer());
    addSerializer(Long.class, (IBufSerializer<?>)new LongBufSerializer());
    addSerializer(Integer.class, (IBufSerializer<?>)new IntegerBufSerializer());
    addSerializer(String.class, (IBufSerializer<?>)new StringBufSerializer());
    addSerializer(UUID.class, (IBufSerializer<?>)new UUIDBufSerializer());
    addSerializer(NBTTagCompound.class, (IBufSerializer<?>)new NBTTagCompoundBufSerializer());
    addSerializer(ItemStack.class, (IBufSerializer<?>)new ItemStackBufSerializer());
    addSerializer(ArrayList.class, (IBufSerializer<?>)new ArrayListBufSerializer());
    addSerializer(HashSet.class, (IBufSerializer<?>)new HashSetBufSerializer());
    addSerializer(HashMap.class, (IBufSerializer<?>)new HashMapBufSerializer());
    addSerializer(Enum.class, (IBufSerializer<?>)new EnumBufSerializer());
    addSerializer(OfflinePlayer.class, (IBufSerializer<?>)new OfflinePlayerBufSerializer());
    addSerializer(IBufSerializable.class, (IBufSerializer<?>)new SerializableBufSerializer());
  }
  
  public static void addSerializer(Class<?> clazz, IBufSerializer<?> serializer) {
    int id = SERIALIZERS_ID.size();
    SERIALIZERS.put(clazz, serializer);
    SERIALIZERS_ID.put(Integer.valueOf(id), serializer);
    SERIALIZERS_ID_REVERSE.put(serializer, Integer.valueOf(id));
  }
  
  public static IBufSerializer<?> getSerializer(Class<?> clazz) {
    IBufSerializer<?> serializer = SERIALIZERS.get(clazz);
    if (serializer != null)
      return serializer; 
    for (Map.Entry<Class<?>, IBufSerializer<?>> entry : SERIALIZERS.entrySet()) {
      if (((Class)entry.getKey()).isAssignableFrom(clazz))
        return entry.getValue(); 
    } 
    return null;
  }
  
  public static IBufSerializer<?> getSerializer(int id) {
    return SERIALIZERS_ID.get(Integer.valueOf(id));
  }
  
  public static int getSerializerId(IBufSerializer<?> serializer) {
    return ((Integer)SERIALIZERS_ID_REVERSE.get(serializer)).intValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\ByteBufSerializerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */