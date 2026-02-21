package fr.paladium.palamod.modules.luckyblock.profile.fetcher;

import fr.paladium.palamod.modules.luckyblock.profile.api.LuckyBlockApi;
import fr.paladium.palamod.modules.luckyblock.profile.dto.LuckyBlock;
import fr.paladium.profile.server.fetcher.DataFetcher;
import fr.paladium.profile.server.requests.model.Paging;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;

public class LuckyBlockFetcher extends DataFetcher<LuckyBlockApi, List<LuckyBlock>> {
  private static final int LIMIT = 100;
  
  public LuckyBlockFetcher(Class<LuckyBlockApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public List<LuckyBlock> fetch(String uuid) {
    List<LuckyBlock> luckyblocks = new ArrayList<>();
    int offset = 0;
    int totalCount = -1;
    while (totalCount == -1 || luckyblocks.size() < totalCount) {
      Call<Paging<LuckyBlock>> call = ((LuckyBlockApi)getApi()).getLuckyBlocks(uuid, offset, 100);
      try {
        Response<Paging<LuckyBlock>> response = call.execute();
        if (response.isSuccessful()) {
          Paging<LuckyBlock> paging = (Paging<LuckyBlock>)response.body();
          if (paging == null || paging.getData() == null || paging.getData().isEmpty())
            break; 
          luckyblocks.addAll(paging.getData());
          totalCount = paging.getTotalCount();
          offset += 100;
        } 
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    return luckyblocks;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\profile\fetcher\LuckyBlockFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */