package Utils;

public class CompareUtil {

    //public void extractLine(String responseBody, String line){
//        System.out.println("######################################################");
//        Arrays.stream(responseBody.toString().split(","))
//                .map(e -> e.startsWith("\"") && e.endsWith("\"") ? e.substring(1, e.length()-1) : e).collect(Collectors.toList()).forEach(System.out::println);;
//        System.out.println("######################################################");

//        List<String> splitMsgWithoutQuotation = splitMessage.stream()
//                .map(e -> e.startsWith("\"") && e.endsWith("\"") ? e.substring(1, e.length()-1) : e).collect(Collectors.toList());
   // }


    public boolean compareData(Object obj1, String sign, Object obj2){
        switch (sign){
            case "=="  :
            case  "равно"  :
                return obj1.equals(obj2);
            case "!="   :
            case "не равно"   :
                return !obj1.equals(obj2);
            case "<" :
            case "меньше" :
                return Integer.parseInt((String) obj1) < Integer.parseInt((String) obj2);
            case ">" :
            case "больше" :
                return Integer.parseInt((String) obj1) > Integer.parseInt((String) obj2);
        }
        return false;
    }
}
