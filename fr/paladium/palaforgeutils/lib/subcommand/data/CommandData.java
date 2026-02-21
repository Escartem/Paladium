package fr.paladium.palaforgeutils.lib.subcommand.data;

import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandData {
  private static final String EMPTY_STRING = "";
  
  private String[] travelArgs;
  
  private String[] fullArgs;
  
  private List<String> freeArgs;
  
  private List<String> playerNames;
  
  private List<Number> numbers;
  
  private List<String> enumValues;
  
  public void setTravelArgs(String[] travelArgs) {
    this.travelArgs = travelArgs;
  }
  
  public void setFullArgs(String[] fullArgs) {
    this.fullArgs = fullArgs;
  }
  
  public void setFreeArgs(List<String> freeArgs) {
    this.freeArgs = freeArgs;
  }
  
  public void setPlayerNames(List<String> playerNames) {
    this.playerNames = playerNames;
  }
  
  public void setNumbers(List<Number> numbers) {
    this.numbers = numbers;
  }
  
  public void setEnumValues(List<String> enumValues) {
    this.enumValues = enumValues;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof CommandData))
      return false; 
    CommandData other = (CommandData)o;
    if (!other.canEqual(this))
      return false; 
    if (!Arrays.deepEquals((Object[])getTravelArgs(), (Object[])other.getTravelArgs()))
      return false; 
    if (!Arrays.deepEquals((Object[])getFullArgs(), (Object[])other.getFullArgs()))
      return false; 
    Object<String> this$freeArgs = (Object<String>)getFreeArgs(), other$freeArgs = (Object<String>)other.getFreeArgs();
    if ((this$freeArgs == null) ? (other$freeArgs != null) : !this$freeArgs.equals(other$freeArgs))
      return false; 
    Object<String> this$playerNames = (Object<String>)getPlayerNames(), other$playerNames = (Object<String>)other.getPlayerNames();
    if ((this$playerNames == null) ? (other$playerNames != null) : !this$playerNames.equals(other$playerNames))
      return false; 
    Object<Number> this$numbers = (Object<Number>)getNumbers(), other$numbers = (Object<Number>)other.getNumbers();
    if ((this$numbers == null) ? (other$numbers != null) : !this$numbers.equals(other$numbers))
      return false; 
    Object<String> this$enumValues = (Object<String>)getEnumValues(), other$enumValues = (Object<String>)other.getEnumValues();
    return !((this$enumValues == null) ? (other$enumValues != null) : !this$enumValues.equals(other$enumValues));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof CommandData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + Arrays.deepHashCode((Object[])getTravelArgs());
    result = result * 59 + Arrays.deepHashCode((Object[])getFullArgs());
    Object<String> $freeArgs = (Object<String>)getFreeArgs();
    result = result * 59 + (($freeArgs == null) ? 43 : $freeArgs.hashCode());
    Object<String> $playerNames = (Object<String>)getPlayerNames();
    result = result * 59 + (($playerNames == null) ? 43 : $playerNames.hashCode());
    Object<Number> $numbers = (Object<Number>)getNumbers();
    result = result * 59 + (($numbers == null) ? 43 : $numbers.hashCode());
    Object<String> $enumValues = (Object<String>)getEnumValues();
    return result * 59 + (($enumValues == null) ? 43 : $enumValues.hashCode());
  }
  
  public String toString() {
    return "CommandData(travelArgs=" + Arrays.deepToString((Object[])getTravelArgs()) + ", fullArgs=" + Arrays.deepToString((Object[])getFullArgs()) + ", freeArgs=" + getFreeArgs() + ", playerNames=" + getPlayerNames() + ", numbers=" + getNumbers() + ", enumValues=" + getEnumValues() + ")";
  }
  
  public String[] getTravelArgs() {
    return this.travelArgs;
  }
  
  public String[] getFullArgs() {
    return this.fullArgs;
  }
  
  public List<String> getFreeArgs() {
    return this.freeArgs;
  }
  
  public List<String> getPlayerNames() {
    return this.playerNames;
  }
  
  public List<Number> getNumbers() {
    return this.numbers;
  }
  
  public List<String> getEnumValues() {
    return this.enumValues;
  }
  
  public CommandData(String[] args) {
    this.travelArgs = args;
    if (args.length == 0) {
      this.fullArgs = new String[0];
    } else {
      this.fullArgs = new String[args.length - 1];
      System.arraycopy(args, 1, this.fullArgs, 0, args.length - 1);
    } 
    this.playerNames = new ArrayList<>();
    this.numbers = new ArrayList<>();
    this.enumValues = new ArrayList<>();
    this.freeArgs = new ArrayList<>();
  }
  
  public void addPlayerName(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    this.playerNames.add(name);
  }
  
  public void addEnumValue(@NonNull String value) {
    if (value == null)
      throw new NullPointerException("value is marked non-null but is null"); 
    this.enumValues.add(value);
  }
  
  public void addNumber(Number number) {
    this.numbers.add(number);
  }
  
  public void addFreeArg(String s) {
    this.freeArgs.add(s);
  }
  
  public int getInteger() {
    return getNumber().intValue();
  }
  
  public double getDouble() {
    return getNumber().doubleValue();
  }
  
  public float getFloat() {
    return getNumber().floatValue();
  }
  
  public long getLong() {
    return getNumber().longValue();
  }
  
  public Number getNumber() {
    return getNumber(0);
  }
  
  public Number getNumber(int index) {
    if (this.numbers.size() <= index)
      return Integer.valueOf(0); 
    return this.numbers.get(index);
  }
  
  public String getFreeArg() {
    return getFreeArg(0);
  }
  
  public String getFreeArg(int index) {
    if (this.freeArgs.size() <= index)
      return null; 
    return this.freeArgs.get(index);
  }
  
  public <T extends Enum<T>> T getEnum(Class<T> clazz) {
    return getEnum(0, clazz);
  }
  
  public <T extends Enum<T>> T getEnum(int index, Class<T> clazz) {
    if (!Enum.class.isAssignableFrom(clazz))
      return null; 
    String enumValue = getEnumValue(index);
    if (enumValue == null)
      return null; 
    try {
      return Enum.valueOf(clazz, enumValue);
    } catch (IllegalArgumentException e) {
      return null;
    } 
  }
  
  public String getEnumValue() {
    return getEnumValue(0);
  }
  
  public String getEnumValue(int index) {
    if (this.enumValues.size() <= index)
      return null; 
    return this.enumValues.get(index);
  }
  
  public String getLastArg() {
    if (this.fullArgs.length == 0)
      return ""; 
    return this.fullArgs[this.fullArgs.length - 1];
  }
  
  public Optional<EntityPlayerMP> getTargetedPlayer() {
    String name = getTargetedPlayerName();
    if (name == null)
      return Optional.empty(); 
    return Optional.ofNullable(PlayerUtils.getPlayer(name));
  }
  
  public String getTargetedPlayerName() {
    return getTargetedPlayerName(0);
  }
  
  public String getTargetedPlayerName(int index) {
    if (this.playerNames.size() <= index)
      return null; 
    return this.playerNames.get(index);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\data\CommandData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */