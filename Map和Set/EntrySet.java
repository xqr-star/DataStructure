package map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class EntrySet {
    public static void main(String[] args) {
        Map<String,Integer> map = new TreeMap<>();
        map.put("daob",124);
        map.put("fgreb",13454);
        map.put("greb",125);
        map.put("agtrob",564);


        Set<String> keySet = map.keySet();
        Collection<Integer> values = map.values();
        System.out.println(keySet);
        System.out.println(values);

        for(Map.Entry<String,Integer> mapSet:map.entrySet()){
            System.out.println(mapSet.getKey()+ mapSet.getValue());
        }



    }
}
