package thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author :wxd
 * @Description:
 * @Date: Created in 11:31 2019/4/28
 */
public class test01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
       /* for (Object o : list) {
            if("a".equals(o)){
                list.remove(o);
            }
        }*/
       list.removeIf(o->o.equals("a"));
        list.removeIf(s -> s.contains("c"));
        System.out.println(list.size());
       for(int i = 0; i<list.size();i++){
            if(list.get(i).equals("a")){
                    list.remove(i);
            }
       }
       String o;


        System.out.println(list.size());
    }
}
