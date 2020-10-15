import org.json.JSONArray;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * <p>Title: MainApp</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/13 0013 15:56
 */
public class MainApp {
    public static void main(String[] args) {

        ArrayList<Object> objects = new ArrayList<>();
        System.out.println(objects);
        Stream<Object> stream = objects.stream();
        System.out.println(stream == null);

//        double random = Math.random();
//        Double value = new BigDecimal(random).setScale(2, RoundingMode.HALF_UP).doubleValue();
//        System.out.println(value);
//        Double value1 = new BigDecimal(random).setScale(1, RoundingMode.HALF_UP).doubleValue();
//        System.out.println(new Random().nextInt(100));
//        System.out.println(value1.intValue()*10);

//   double a = 0.0;
//        System.out.println(a == 0);



//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
//        try {Date dateStart = formatter.parse("202009");
//
//            System.out.println(dateStart);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        System.out.println("sout".substring(0,2));
//        String year = "202009";
//        String calYear = year.substring(0, 4);
//        String calMonth = year.substring(4);
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, Integer.parseInt(calYear));
////        cal.set(Calendar.MONTH, Integer.parseInt(calMonth));
//        Date time = cal.getTime();
//        System.out.println(time);

//        List<Date> list = new ArrayList<Date>();
//        List<String> formatDateList = new ArrayList<String>();
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2020);
//        cal.set(Calendar.MONTH, 0);
//        cal.set(Calendar.DATE, 1);
//        int year = cal.get(Calendar.YEAR);
//        while (cal.get(Calendar.YEAR) == year) {
//            list.add(cal.getTime());
//            cal.add(Calendar.DATE, 1);
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        for (Date date: list) {
//            String format = sdf.format(date);
//            formatDateList.add(format);
//            System.out.println(format.replace("-","").substring(4));
//        }

    }
}
