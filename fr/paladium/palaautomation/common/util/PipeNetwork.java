package fr.paladium.palaautomation.common.util;

import fr.paladium.palaautomation.common.block.pipe.PipeType;
import fr.paladium.palaautomation.common.tile.EnumPipeFacing;
import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.pipe.ATileEntityPipe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PipeNetwork {
  public static final int MAX_NETWORK_SIZE = 30;
  
  private final IPipeMachine entry;
  
  private final List<ATileEntityPipe> pipes;
  
  private final ATileEntityPipe firstPipe;
  
  private PipeType speed;
  
  private PipeError error;
  
  public IPipeMachine getEntry() {
    return this.entry;
  }
  
  public List<ATileEntityPipe> getPipes() {
    return this.pipes;
  }
  
  public ATileEntityPipe getFirstPipe() {
    return this.firstPipe;
  }
  
  public PipeType getSpeed() {
    return this.speed;
  }
  
  public PipeError getError() {
    return this.error;
  }
  
  protected PipeNetwork(IPipeMachine entry, ATileEntityPipe firstPipe) {
    this.entry = entry;
    this.firstPipe = firstPipe;
    this.pipes = new ArrayList<>();
    this.speed = firstPipe.getType();
    initConnectedPipes(firstPipe.func_145831_w());
  }
  
  public static PipeNetwork of(IPipeMachine entry, ATileEntityPipe firstPipe) {
    if (entry == null || firstPipe == null)
      return null; 
    long currentMs = System.currentTimeMillis();
    if (!firstPipe.canTransfer(firstPipe.getType(), currentMs))
      return null; 
    return new PipeNetwork(entry, firstPipe);
  }
  
  public int size() {
    return this.pipes.size();
  }
  
  public ATileEntityPipe getLastPipe() {
    if (this.pipes.isEmpty())
      return null; 
    return this.pipes.get(this.pipes.size() - 1);
  }
  
  public IPipeMachine getReceiver() {
    ATileEntityPipe lastPipe = getLastPipe();
    if (lastPipe == null)
      return null; 
    EnumPipeFacing facing = lastPipe.getFacing();
    TileEntity tile = lastPipe.func_145831_w().func_147438_o(lastPipe.field_145851_c + facing
        .getFrontOffsetX(), lastPipe.field_145848_d + facing
        .getFrontOffsetY(), lastPipe.field_145849_e + facing
        .getFrontOffsetZ());
    if (tile == null || !(tile instanceof IPipeMachine))
      return null; 
    TileEntity tileEntry = this.entry.getTileEntity();
    if (tile.field_145851_c == tileEntry.field_145851_c && tile.field_145848_d == tileEntry.field_145848_d && tile.field_145849_e == tileEntry.field_145849_e)
      return null; 
    return (IPipeMachine)tile;
  }
  
  public boolean isOnlySinglePipe() {
    return (this.pipes.size() <= 1);
  }
  
  public void initConnectedPipes(World world) {
    long currentMs = System.currentTimeMillis();
    ATileEntityPipe currentPipe = this.firstPipe;
    this.pipes.add(currentPipe);
    int size = 0;
    for (; size < 30; size++) {
      EnumPipeFacing currentDirection = currentPipe.getFacing();
      TileEntity tile = world.func_147438_o(currentPipe.field_145851_c + currentDirection
          .getFrontOffsetX(), currentPipe.field_145848_d + currentDirection
          .getFrontOffsetY(), currentPipe.field_145849_e + currentDirection
          .getFrontOffsetZ());
      if (!(tile instanceof ATileEntityPipe))
        break; 
      ATileEntityPipe pipe = (ATileEntityPipe)tile;
      if (contains(pipe))
        break; 
      if (!pipe.canTransfer(pipe.getType(), currentMs))
        break; 
      if (pipe.getType() != this.speed)
        this.speed = PipeType.getMinSpeedFrom(this.speed, pipe.getType()); 
      currentPipe = pipe;
      this.pipes.add(pipe);
    } 
    if (size >= 30) {
      error(PipeError.REACHED_MAX_NETWORK_SIZE);
      return;
    } 
    error(PipeError.NO_ERROR);
    for (int i = 0; i < this.pipes.size(); i++) {
      ATileEntityPipe pipe = this.pipes.get(i);
      boolean changed = false;
      if (i == 0) {
        changed = pipe.changeParent(null);
      } else {
        changed = pipe.changeParent(this.pipes.get(i - 1));
      } 
      if (changed)
        pipe.updateOnRadius(); 
    } 
  }
  
  public void error(PipeError error) {
    this.error = error;
    this.pipes.forEach(pipe -> {
          if (pipe.error(error))
            pipe.updateOnRadius(); 
        });
  }
  
  public boolean contains(ATileEntityPipe pipe) {
    return this.pipes.contains(pipe);
  }
  
  public boolean canTransfer() {
    if (this.speed == null)
      this.speed = PipeType.PALADIUM; 
    long currentMs = System.currentTimeMillis();
    for (ATileEntityPipe pipe : this.pipes) {
      if (!pipe.canTransfer(this.speed, currentMs))
        return false; 
      pipe.onTransfer(currentMs);
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\commo\\util\PipeNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */