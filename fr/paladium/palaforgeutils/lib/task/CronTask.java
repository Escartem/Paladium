package fr.paladium.palaforgeutils.lib.task;

import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import java.time.Duration;
import java.time.ZonedDateTime;

public abstract class CronTask extends ATask {
  private final ExecutionTime cron;
  
  public CronTask(String name, String cron) {
    super(name);
    CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX);
    CronParser cronParser = new CronParser(cronDefinition);
    this.cron = ExecutionTime.forCron(cronParser.parse(cron));
  }
  
  public boolean start() {
    setDelayInfo(getNextExecution());
    return super.start();
  }
  
  public long getRepeatTime(int iter) {
    return getNextExecution();
  }
  
  private long getNextExecution() {
    ZonedDateTime now = ZonedDateTime.now();
    return ((Duration)this.cron.timeToNextExecution(now).get()).toMillis();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\task\CronTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */