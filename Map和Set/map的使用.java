package map;

public class map的使用 {
    /**
     * put
     * get getOrDefault
     * remove
     * @param args
     */
    public static void main(String[] args) {
       // Map<String,Integer> map= new TreeMap<>();
        myTreeMap map = new myTreeMap();
        Integer v = map.put("chenpeixin",1234); //必须写成INteger
        System.out.println(v);
        //System.out.println(map);

        v = map.put("chenpeixin",79);
        System.out.println(v);//获取上一次的返回值
        //System.out.println(map);
        v= map.get("chenpeixin");
        System.out.println(v);
        System.out.println("====");
        v= map.get("gaobo");
        System.out.println(v);
        v = map.getOrDefault("goa",0);
        System.out.println(v);
        System.out.println(map);

//        System.out.println(map.remove("goabo"));
//        map.remove("chenpeixin");
//        System.out.println(map);

//        System.out.println(map.remove("goab", 89));
//        System.out.println(map);

    }
}
