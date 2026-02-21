package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.dialog;

import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.IceCreamSellerData;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.IceCreamType;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class IceCreamSellerDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Marchand de glace";
  
  private static final String WELCOME_TEXT = "Bonjour, qu'est-ce que je vous sers ?";
  
  private static final String CONTINUE_TEXT = "Continuer";
  
  private static final String CLOSE_TEXT = "Au revoir !";
  
  private static final String ONE_BALL_TEXT = "Une boule";
  
  private static final String TWO_BALL_TEXT = "Deux boules";
  
  private static final String CRESUS_WITHDRAW_REASON = "Ice Cream Seller";
  
  private static final String CRESUS_FAIL_MESSAGE = "&cVous n'avez pas assez d'argent pour acheter une glace !";
  
  private static final double ONE_BALL_PRICE = 250.0D;
  
  private static final double TWO_BALL_PRICE = 500.0D;
  
  public static final int MAX_SELL_AMOUNT = 5;
  
  private final List<IceCreamSellerData> datas;
  
  public IceCreamSellerDialogManager() {
    super("Marchand de glace", Collections.singletonList("Marchand de glace"));
    this.datas = new ArrayList<>();
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    IceCreamSellerData data = getData(entity, player);
    if (!data.isOwner(player) || data.getSellAmount() >= 5)
      return null; 
    return firstDialog(player, entity, data);
  }
  
  private CustomDialog firstDialog(EntityPlayerMP player, Entity entity, IceCreamSellerData data) {
    CustomDialog dialog = new CustomDialog(this.title, "Bonjour, qu'est-ce que je vous sers ?");
    dialog.setCloseGui();
    dialog.addOption("Continuer");
    dialog.addOption("Au revoir !");
    dialog.setCallback(ret -> {
          if (ret.intValue() == 1)
            sendOtherDialog(player, secondDialog(player, entity, data)); 
        });
    return dialog;
  }
  
  private CustomDialog secondDialog(EntityPlayerMP player, Entity entity, IceCreamSellerData data) {
    CustomDialog dialog = new CustomDialog(this.title, "Vous souhaitez prendre une boule de glace ou deux ?");
    dialog.setCloseGui();
    dialog.addOption("Une boule");
    dialog.addOption("Deux boules");
    dialog.setCallback(ret -> {
          if (ret.intValue() == 1) {
            sendOtherDialog(player, oneBallDialog(player, entity, data));
          } else if (ret.intValue() == 2) {
            sendOtherDialog(player, twoBallDialog(player, entity, data));
          } 
        });
    return dialog;
  }
  
  private CustomDialog oneBallDialog(EntityPlayerMP player, Entity entity, IceCreamSellerData data) {
    CustomDialog dialog = new CustomDialog(this.title, "");
    dialog.setCloseGui();
    for (IceCreamType value : IceCreamType.values())
      dialog.addOption(value.getName()); 
    dialog.setCallback(ret -> {
          for (IceCreamType value : IceCreamType.values()) {
            if (ret.intValue() == value.ordinal() + 1) {
              data.setFirstIceCreamType(value);
              giveReward(player, entity, data);
            } 
          } 
        });
    return dialog;
  }
  
  private CustomDialog twoBallDialog(EntityPlayerMP player, Entity entity, IceCreamSellerData data) {
    CustomDialog dialog = new CustomDialog(this.title, "");
    dialog.setCloseGui();
    for (IceCreamType value : IceCreamType.values())
      dialog.addOption(value.getName()); 
    dialog.setCallback(ret -> {
          for (IceCreamType value : IceCreamType.values()) {
            if (ret.intValue() == value.ordinal() + 1) {
              data.setFirstIceCreamType(value);
              sendOtherDialog(player, twoBallSecondDialog(player, entity, data));
            } 
          } 
        });
    return dialog;
  }
  
  private CustomDialog twoBallSecondDialog(EntityPlayerMP player, Entity entity, IceCreamSellerData data) {
    CustomDialog dialog = new CustomDialog(this.title, "");
    dialog.setCloseGui();
    for (IceCreamType value : IceCreamType.values())
      dialog.addOption(value.getName()); 
    dialog.setCallback(ret -> {
          for (IceCreamType value : IceCreamType.values()) {
            if (ret.intValue() == value.ordinal() + 1) {
              data.setSecondIceCreamType(value);
              giveReward(player, entity, data);
            } 
          } 
        });
    return dialog;
  }
  
  public boolean sendDialog(EntityPlayerMP player, Entity entity) {
    boolean ret = super.sendDialog(player, entity);
    return ret;
  }
  
  private IceCreamSellerData getData(Entity entity, EntityPlayerMP player) {
    IceCreamSellerData existingData = this.datas.stream().filter(data -> data.getEntityUniqueId().equals(entity.func_110124_au())).findFirst().orElse(null);
    if (existingData == null) {
      existingData = new IceCreamSellerData(entity, player);
      this.datas.add(existingData);
    } 
    return existingData;
  }
  
  private ItemStack buildStack(IceCreamSellerData data) {
    ItemStack stack;
    NBTTagCompound compound = new NBTTagCompound();
    if (data.getSecondIceCreamType() == null) {
      stack = new ItemStack(ItemsRegister.ICE_CREAM, 1);
    } else {
      stack = new ItemStack(ItemsRegister.DOUBLE_ICE_CREAM, 1);
    } 
    if (data.getFirstIceCreamType() != null) {
      compound.func_74778_a("firstFlavor", data.getFirstIceCreamType().getName());
      stack.func_77982_d(compound);
      stack.func_151001_c("Glace " + data.getFirstIceCreamType().getName());
    } 
    if (data.getSecondIceCreamType() != null) {
      compound.func_74778_a("secondFlavor", data.getSecondIceCreamType().getName());
      stack.func_77982_d(compound);
      stack.func_151001_c("Glace " + data.getFirstIceCreamType().getName() + " - " + data.getSecondIceCreamType().getName());
    } 
    return stack;
  }
  
  private double getPrice(IceCreamSellerData data) {
    if (data.getSecondIceCreamType() != null)
      return 500.0D; 
    return 250.0D;
  }
  
  private void giveReward(final EntityPlayerMP player, final Entity entity, final IceCreamSellerData data) {
    if (!(entity instanceof EntityNPC) || entity.field_70128_L || data.getSellAmount() >= 5)
      return; 
    CresusCallback<CresusResponse> callback = new CresusCallback<CresusResponse>() {
        public void onSuccess(CresusResponse cresusResponse) {
          if (data.getSellAmount() >= 5)
            return; 
          World world = player.field_70170_p;
          EntityNPC npc = (EntityNPC)entity;
          ItemUtils.spawnItemAtEntity((Entity)npc, IceCreamSellerDialogManager.this.buildStack(data));
          data.sell();
          if (data.getSellAmount() >= 5)
            npc.func_70106_y(); 
        }
        
        public void onFail(CresusResponse cresusResponse, Throwable throwable) {
          MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas assez d'argent pour acheter une glace !" });
        }
      };
    CresusManager.getInstance().withdrawPlayerAsync(player
        .func_110124_au(), getPrice(data), "Ice Cream Seller", callback);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\dialog\IceCreamSellerDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */