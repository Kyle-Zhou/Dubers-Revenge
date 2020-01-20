/**
* [Counter.java]
* Controller for whenever a timer is needed in the program
* Stores values of reloadTime and gracePeriodTime to be compared with in gamePanel and hud
* @author Kyle Zhou
**/

class Counter {
  
  private static long reloadTime;
  private static long gracePeriodTime;
  
  /**
  * getReloadTime
  * @return long value of reloadTime
  */ 
  public static long getReloadTime(){
    return reloadTime;
  }
  
  /**
  * setReloadTime
  * @param reloadTime, long value of ReloadTime
  */ 
  public void setReloadTime(long reloadTime){
    this.reloadTime = reloadTime;
  }
  
  /**
  * getGracePeriodTime
  * @return long value of gracePeriodTime
  */ 
  public static long getGracePeriodTime(){
    return gracePeriodTime;
  }
  
  /**
  * setGracePeriodTime
  * @param gracePeriodTime, long value of gracePeriodTime
  */ 
  public void setGracePeriodTime(long gracePeriodTime){
    this.gracePeriodTime = gracePeriodTime;
  }
  
}