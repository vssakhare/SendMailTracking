/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.emp.common;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Pooja Jadhav
 */


   
public class VendorMailScheduler extends TimerTask 
{
      private static Logger logger = Logger.getLogger(VendorMailScheduler.class);
   private static Timer timer;
    private static TimerTask timerTask;
    private final static long FREQUENCY = 1000*60*60*24;
    private static int MAHAONLINE_EXECUTION_HH = 01;
    private static int MAHAONLINE_EXECUTION_MM = 00;
   public static void startScheduler() throws Exception {
        try { 

            timerTask = new VendorMailScheduler();
            timer = new Timer("VendorMailScheduler");
            timer.schedule(timerTask, getFirstRunTime(), FREQUENCY);
           
        } catch (Exception ex) {
            throw ex;
        }
      
   }
  private static Date getFirstRunTime()
    {
        Calendar today = new GregorianCalendar();
        today.add(Calendar.DATE, 1);
        Calendar result = new GregorianCalendar(
          today.get(Calendar.YEAR),
          today.get(Calendar.MONTH),
          today.get(Calendar.DATE),
          MAHAONLINE_EXECUTION_HH,
          MAHAONLINE_EXECUTION_MM
        );
        System.out.println("Send Vendor MAIL Scheduler First Run Time ..... "+result.getTime());
        return result.getTime();
  }
  public static void resetScheduler() throws Exception {
        try {
            if (timerTask != null) {
                timerTask.cancel();
            }

            startScheduler();

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void stopScheduler() throws Exception {
        try {
            if (timerTask != null) {
                timerTask.cancel();
                System.out.println("VendorMailScheduler timerTask Stopped..... "+new Date());
            }

            if (timer != null) {
                timer.cancel();
                System.out.println("VendorMailScheduler timer Stopped..... "+new Date());
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
 public void run() {
        try {
            SimpleDateFormat formatter= new SimpleDateFormat();
          Date date=new Date();
         System.out.println("VendorMailScheduler Task executed"+formatter.format(date));
            System.out.println("VendorMailScheduler Scheduled ..... "+new Date());           
                 ReadMailVendor.readMailFile();
        } catch (Exception ex) {
              logger.log(Level.ERROR, "VendorMailScheduler :: run() :: Exception .. " + ex.getMessage());
            System.out.println("Exception VendorMailScheduler run : "+ex.getMessage());
        }
    }
   
}