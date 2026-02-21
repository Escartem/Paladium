package fr.paladium.palaspawner.common.network.packet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palaspawner.client.ui.UISpawnerController;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import fr.paladium.palaspawner.common.spawner.ControllerState;
import fr.paladium.palaspawner.common.spawner.blueprint.ASpawnerBluePrint;
import fr.paladium.palaspawner.common.tile.Tier;
import fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
import fr.paladium.zephyrui.internal.mod.bridge.gui.client.GuiScreenBridge;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BBRequestController extends ForgePacket {
  @PacketData
  private int x;
  
  @PacketData
  private int y;
  
  @PacketData
  private int z;
  
  @PacketData
  private Map<JobType, Integer> jobDataMap;
  
  @PacketData
  private ControllerState state;
  
  @PacketData
  private Tier tier;
  
  public BBRequestController() {}
  
  public BBRequestController(int x, int y, int z, Map<JobType, Integer> jobDataMap, ControllerState state, Tier tier) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.jobDataMap = jobDataMap;
    this.state = state;
    this.tier = tier;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public Map<JobType, Integer> getJobDataMap() {
    return this.jobDataMap;
  }
  
  public ControllerState getState() {
    return this.state;
  }
  
  public Tier getTier() {
    return this.tier;
  }
  
  public BBRequestController(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void processServer(EntityPlayerMP player) {
    World world = player.field_70170_p;
    if (!world.func_72899_e(this.x, this.y, this.z) || getDistanceBetween(player.field_70165_t, player.field_70163_u, player.field_70161_v, this.x, this.y, this.z) > 12.0D)
      return; 
    TileEntity tileEntity = world.func_147438_o(this.x, this.y, this.z);
    if (!(tileEntity instanceof TileEntitySpawnerController))
      return; 
    TileEntitySpawnerController controller = (TileEntitySpawnerController)tileEntity;
    String id = controller.getLinkedBluePrintId();
    ASpawnerBluePrint bluePrint = SpawnerManager.getInstance().getBluePrint(id);
    Tier tier = (bluePrint == null) ? Tier.ZERO : bluePrint.getStructureTier();
    ForgePacket packet = new BBRequestController(this.x, this.y, this.z, controller.getJobDataMap(), controller.getControllerState(), tier);
    packet.send(player);
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    UI ui = (GuiScreenBridge.get() != null) ? (UI)GuiScreenBridge.get().getUiList().getFirst() : null;
    if (!(ui instanceof UISpawnerController))
      return; 
    UISpawnerController controllerUI = (UISpawnerController)ui;
    controllerUI.getTile().setControllerState(this.state);
    controllerUI.getTile().setJobDataMap(this.jobDataMap);
    controllerUI.getSwitchNode().index(this.state.name());
    controllerUI.setOpenedState(this.state);
    Tier currentTier = (Tier)controllerUI.getTierSignal().getOrDefault();
    if (currentTier != this.tier)
      controllerUI.getTierSignal().set(this.tier); 
  }
  
  private double getDistanceBetween(double x1, double y1, double z1, double x2, double y2, double z2) {
    double d0 = x1 - x2;
    double d1 = y1 - y2;
    double d2 = z1 - z2;
    return MathHelper.func_76133_a(d0 * d0 + d1 * d1 + d2 * d2);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\network\packet\BBRequestController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */