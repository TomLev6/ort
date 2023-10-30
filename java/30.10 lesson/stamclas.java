import java.util.HashMap;
import java.util.Map;

public class stamclas {
    public Map<String, Integer> wordCount(String[] strings) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String ch: strings)
        {
            if(map.containsKey(ch))
            {
                map.put(ch,map.get(ch)+1);
            }
            else {map.put(ch,1);}
        }
        return map;
    }

}
