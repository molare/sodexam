package renting.com.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import static org.apache.http.HttpHeaders.USER_AGENT;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
/*import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;*/
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import sun.net.www.http.HttpClient;


public class Utility {
    
    public static final String ADMIN_LOGIN = "admin";
    public static final String[] daysTab = {"Dim","Lun","Mar","Mer","Jeu","Ven","Sam"};
    public static final String URL_CREATE_PURCHASE_INVOICE = "http://localhost:8080/erp/apps/ppv/deliveryPurchase/add";
    public static final String URL_CREATE_PURCHASE_INVOICE_ON_DISK = "purchaseInvoice/exportFileOnDisk";
    public static final String URL_IMPORT_WEIGHING = "http://localhost:8080/erp/apps/ppv/importWeighing";
    public static final int NUMBER_LENGTH = 10;
    public static final int NUMBER_TRANSACTIONID_LENGTH = 18;
    public static final int NUMBER_PIN_LENGTH = 6;
    public static final String NUMBER_CARACT = "0123456789ABCDEFGHIJKLMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String caract = "0123456789ABCDEFGHIJKLMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final int TOTAL_PLANT_FOR_ONE_HECTARE = 140;
    //IP Address PV Servers
    public static final String  IP_ADDRESS_ELOKA = "192.168.94.100";
    public static final String  IP_ADDRESS_ANGUEDEDOU ="192.168.89.100";
    public static final String  IP_ADDRESS_MONTEZO ="192.168.91.100";
    public static final String  IP_ADDRESS_DABOU ="192.168.92.100";
    public static final String  IP_ADDRESS_SIKENSI ="192.168.93.100";
    public static final String  IP_ADDRESS_SIEGE ="192.168.0.4";
    
    public static final int LEAP_YEAR = 366;
    public static final int NO_LEAP_YEAR = 365 ;

    /*Sert a la  génération d'un nombre aléatoire entre 1 et 100000 */
    public static final int RANDOM_MIN = 1;
    public static final int RANDOM_MAX = 100000 ;        

    //Export
    public static final String PARAMETER_ITEM_NAME = "itemName";
    public static final String EXPORT_TYPE = "fileType";
    public static final String DAILY_FLASH_FOLDERNAME = "flash_journalier";
    public static final String BUYING_FOLDERNAME = "buying";
    
    public static final String PDF_FILE_TYPE = "pdf";
    public static final String XLS_FILE_TYPE = "xls";

    public static final Map<String, String> CONTENT_FILE_TYPE = new HashMap<String, String>();
    static {
        CONTENT_FILE_TYPE.put(PDF_FILE_TYPE, "application/pdf");
        CONTENT_FILE_TYPE.put(XLS_FILE_TYPE, "application/vnd.ms-excel");
    }


    public static String getNowDate() {
            return (new Date()).toString();
    }

    public static String ucfirst(String chaine){
        return chaine.substring(0, 1).toUpperCase()+ chaine.substring(1).toLowerCase();
    }

    public static String leftPad(String originalString, int length,char padCharacter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
           sb.append(padCharacter);
        }
        String padding = sb.toString();
        String paddedString = padding.substring(originalString.length())
              + originalString;
        return paddedString;
    }


    public static String getCode() {
            char[] number = new char[NUMBER_LENGTH];
            int index;
            for (int i = 0; i < NUMBER_LENGTH; i++) {
                    index = (int) (Math.random() * caract.length());
                    number[i] = caract.charAt(index);
            }
            return new String(number);
    }

    public static String getMerchentCode() {
            char[] number = new char[NUMBER_LENGTH];
            int index;
            for (int i = 0; i < NUMBER_LENGTH; i++) {
                    index = (int) (Math.random() * NUMBER_CARACT.length());
                    number[i] = NUMBER_CARACT.charAt(index);
            }
            return new String(number);
    }

    public static String getTransactionId() {
            char[] number = new char[NUMBER_TRANSACTIONID_LENGTH];
            int index;
            for (int i = 0; i < NUMBER_TRANSACTIONID_LENGTH; i++) {
                    index = (int) (Math.random() * NUMBER_CARACT.length());
                    number[i] = NUMBER_CARACT.charAt(index);
            }
            return new String(number);
    }

    public static String getPinCode() {
            char[] number = new char[NUMBER_PIN_LENGTH];
            int index;
            for (int i = 0; i < NUMBER_PIN_LENGTH; i++) {
                    index = (int) (Math.random() * caract.length());
                    number[i] = caract.charAt(index);
            }
            return new String(number);
    }

    public String getCodeByPrefix(String prefix){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String nowStrDate = sdf.format(cal.getTime());
        String nowCode = Utility.getCode();        
        return (prefix+"_"+nowStrDate+"_"+nowCode.substring(0,5).toUpperCase());
    }

    public static String getTimestampNumber() {
            return String.valueOf(new Date().getTime());
    }

    public static String getNewNumberCode() {
            return getCode().concat(getTimestampNumber());
    }



    public static String toLowerFirstLetter(String word) {

            if (word != null) {
                    char firstLetter = Character.toLowerCase(word.toCharArray()[0]);
                    return firstLetter + word.substring(1);
            }
            return "";
    }

    public static String[] splitStringWhiteSpace(String word) {
            if (word != null) {
                    word.split("\\s+");
            }
            return new String[2];
    }

    public static Date addorSubstractMinutToDate(Date date, int minute) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, minute);
            return calendar.getTime();

    }

    public static Date addorSubstractSecondToDate(Date date, int sec) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.SECOND, sec);
            return calendar.getTime();

    }

    public static Date addorSubstractDayToDate(Date date, int day) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, day);
            return calendar.getTime();

    }

    public static Date addorSubstractYearToDate(Date date, int yy) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, yy);
            return calendar.getTime();

    }

    public static Date addorSubstractMonthToDate(Date date, int mth) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, mth);
            return calendar.getTime();

    }


    // returns negative value if date1 is before date2
    // returns 0 if dates are even
    // returns positive value if date1 is after date2
    public static long compareTo(Date date1, Date date2) {
            return date1.getTime() - date2.getTime();
    }

    public static Date getDateOfDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        return calendar.getTime();
    }





    public static HashMap<String,String> getSortingParams(String sorter){
        String[] arrayString =  sorter.split("\\s+");            
        HashMap<String,String> res = new HashMap<String,String>();
        res.put("column", toLowerFirstLetter(arrayString[0]));
        res.put("direction", arrayString[1]);
        return res;
    }

    public static String  capitalize(String value){

        char[] caracters = value.toCharArray();
        caracters[0] = Character.toUpperCase(caracters[0]);
        return new String(caracters);

    }

    public static double arrondi100Pres (double valToArround){
       return Math.round(valToArround*100.0)/100.0;
    }
    
    public static float arrondi100Pres (float valToArround){
       return Math.round(valToArround*100.0)/100.0f;
       
    }
    
    public static double arrondiDecimal100Pres (double valToArround){
       return valToArround*100.0/100.0;
    }

    public static double arrondiMonetaire (double valToArround){
       return Math.round(valToArround/10)*10;
    }

    public static double arrondi100UniteSuperieur (double valToArround){          
       double res =  Math.ceil(valToArround/100);
       return res*100 ;

    }

    public static double arrondiWholePart (double valToArround){
        return Math.round(valToArround);
       /*int res= (int)valToArround;
       return (double)res;*/
    }
    
    public static float arrondiWholePart (float valToArround){
        return Math.round(valToArround);
    }


    public static String thousandSeparator (double amount){
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);         
        return decimalFormat.format(amount);
    }        
    
    public static String thousandSeparator(String value){        
        try {
            return thousandSeparator (Double.parseDouble(value));
        } catch (NumberFormatException nfe) {
            return value;
        }
    }
    
    

    public static long differenceDate(Date date1, Date date2){
          long ONE_DAY = 60 * 60 * 24 * 1000; // Total millisec
         return ((date2.getTime() - date1.getTime()) / ONE_DAY);
    }

    /*Fonction pour générer les codes de versement journalier*/  

    public static String getCodePayBack(){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String nowStrDate = sdf.format(cal.getTime());
        String nowCode = Utility.getCode();

        return (nowStrDate+"-"+nowCode.substring(0,3).toUpperCase());
    }

    // HTTP GET request
   /* public  static void sendGet(String url) throws Exception {


        HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);

            // add request header
            request.addHeader("User-Agent", USER_AGENT);

            HttpResponse response = client.execute(request);

            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                   new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                    result.append(line);
            }

            System.out.println(result.toString());

    }*/

    // HTTP POST request
    public static String sendPost( String url, HashMap<String,String> parameters) throws Exception {
            String res = "";
            try{
                CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(url);
                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                Set<String> keys = parameters.keySet();
                for(String key : keys){
                   urlParameters.add(new BasicNameValuePair(key, parameters.get(key)));
                }

                //System.out.println(urlParameters);
                httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
                CloseableHttpResponse response = httpclient.execute(httpPost);

                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + httpPost.getEntity());
                System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                        result.append(line);
                }

                System.out.println(result.toString());
                res =  result.toString();

            }catch(Exception ex){

                System.out.println(ex.getMessage());
                ex.printStackTrace();

            }

            return res;


    }

    /*Fonction pour générer un nombre aléatoire entre 1 et 100000*/    
    public static int getRandomCode() {
      int randomNum ;

      Random rn = new Random();
      randomNum = rn.nextInt((RANDOM_MAX - RANDOM_MIN) + 1) + RANDOM_MIN;

      return randomNum;
    } 

    public static Date firstMondayYear(int year) {

      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, year);
      cal.set(Calendar.DAY_OF_YEAR, 1);   

      int day_of_week = cal.get(Calendar.DAY_OF_WEEK);

      switch (day_of_week) {
          case 5: // jeudi
              cal.set(Calendar.YEAR, year-1);
              cal.set(Calendar.MONTH, Calendar.DECEMBER);
              cal.set(Calendar.DAY_OF_MONTH, 29);             
              break;
          case 4: // mercredi
              cal.set(Calendar.YEAR, year-1);
              cal.set(Calendar.MONTH, Calendar.DECEMBER);
              cal.set(Calendar.DAY_OF_MONTH, 30);           
              break;
          case 3: // mardi
              cal.set(Calendar.YEAR, year-1);
              cal.set(Calendar.MONTH, Calendar.DECEMBER);
              cal.set(Calendar.DAY_OF_MONTH, 31);           
              break;
          case 2: // lundi
              break;	
          case 1: // dimanche
              cal.set(Calendar.YEAR, year);
              cal.set(Calendar.MONTH, Calendar.JANUARY);
              cal.set(Calendar.DAY_OF_MONTH, 2); 
              break;
          case 7: // samedi
              cal.set(Calendar.YEAR, year);
              cal.set(Calendar.MONTH, Calendar.JANUARY);
              cal.set(Calendar.DAY_OF_MONTH, 3); 
              break;
          case 6: // vendredi
              cal.set(Calendar.YEAR, year);
              cal.set(Calendar.MONTH, Calendar.JANUARY);
              cal.set(Calendar.DAY_OF_MONTH, 4); 
              break;	
      }

      return cal.getTime();
  }

    public static  Date firstYearDay(String dateStr) {   
          Date res = new Date();
          SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
          try {
              res = formater.parse(dateStr);
          } catch (ParseException ex) {
              ex.printStackTrace();
              Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
          }
          Calendar cal = Calendar.getInstance();
          cal.setTime(res);
          cal.set(Calendar.MONTH,0);
          cal.set(Calendar.DAY_OF_MONTH,1);
          return cal.getTime();
   }

    public static  Date lastYearDay(String dateStr) {   
          Date res = new Date();
          SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
          try {
              res = formater.parse(dateStr);
          } catch (ParseException ex) {
              ex.printStackTrace();
              Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
          }
          Calendar cal = Calendar.getInstance();
          cal.setTime(res);
          cal.set(Calendar.MONTH,11);
          cal.set(Calendar.DAY_OF_MONTH,31);
          return cal.getTime();
   }

    /**
     * set Hour to date 
     * Ex Hour =  12:15:19
     */
     public static Date setHourDate(Date date, String hour) {
      SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat formater1 = new SimpleDateFormat("yyyy-MM-dd H:m:s");
      Date res = null;
      try {
          res = formater1.parse(formater.format(date)+" "+hour);
      } catch (ParseException ex) {
          ex.printStackTrace();
          Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
      }

      return res;
    }

   public static  Date firstMonthDay(String dateStr) {   
          Date res = new Date();
          SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
          try {
              res = formater.parse(dateStr);
          } catch (ParseException ex) {
              ex.printStackTrace();
              Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
          }
          Calendar cal = Calendar.getInstance();
          cal.setTime(res);
          cal.set(Calendar.DAY_OF_MONTH, 1);  
          return cal.getTime();
   }



   public static  Date lastMonthDay(String dateStr) {        
          Date res = new Date();
          SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
          try {
              res = formater.parse(dateStr);
          } catch (ParseException ex) {
              ex.printStackTrace();
              Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
          }

          Calendar cal = Calendar.getInstance();
          cal.setTime(res); 
          cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));           
          System.out.println("getTime :" +cal.getTime());   
          return cal.getTime();
   }

   /**
   * Determine l'adresse ip du noeud actuel
   * @return 
   */
   public static String getIpAdress(){        
      try {
          Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
          while( b.hasMoreElements()){
              for ( InterfaceAddress f : b.nextElement().getInterfaceAddresses()){
                  if ( f.getAddress().isSiteLocalAddress()){
                      return f.getAddress().getHostAddress();
                  }
              }
          }
      } catch (SocketException e) {
          e.printStackTrace();
      }
      return null;

   }

    public static boolean isSiege(){   
        return IP_ADDRESS_SIEGE.equals(getIpAdress());

   }

   public static double formatDecimal(double val, int digit){
        /*DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.FRANCE);
        dfs.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("#,###.00", dfs);*/
        DecimalFormat df = new DecimalFormat(); 
        df.setMaximumFractionDigits (2) ; //arrondi à 2 chiffres apres la virgules 
        return Double.parseDouble(df.format(val));

   }
   
   /*public static List<Integer> getAuthorizedNurseryId(HttpSession session) {
        List<Integer> ids = new ArrayList<Integer>();
        if(session != null){
            if( session.getAttribute("plantNurseryList") != null){
                List<PlantNursery> res = (List<PlantNursery>)session.getAttribute("plantNurseryList");
                for(PlantNursery pn : res){
                   ids.add(pn.getId());
                }
            }  
        }
        System.out.println("plantNurseryList ids:"+ ids);
        return ids;
    }*/

 

}
