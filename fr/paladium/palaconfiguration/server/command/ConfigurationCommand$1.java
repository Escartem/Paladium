package fr.paladium.palaconfiguration.server.command;

import fr.paladium.palaconfiguration.server.dto.config.response.ConfigRefreshResponse;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import java.util.List;

class null implements IRetrofitCallback<List<ConfigRefreshResponse>> {
  public void onSuccess(List<ConfigRefreshResponse> result) {
    context.send("§aLes configurations ont été refresh avec succès");
  }
  
  public void onFail(List<ConfigRefreshResponse> refreshDTOS, Throwable throwable) {
    context.send("§cErreur lors du refresh des configurations");
    throwable.printStackTrace();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\command\ConfigurationCommand$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */