import org.json.JSONArray;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Proxy;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(20);
        list2.add(18);
        list2.add(30);
        list2.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (Integer num : list2) {
            System.out.println(num);
        }


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
