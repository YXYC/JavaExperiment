import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
public class CountWords {
    public static void main(String[] args) {
		TreeMap<String,Integer> treemap=new TreeMap<String,Integer>();
		String text="java c java c  c pathon java pathon c ";     
		String[] words=text.split("\\s+");
		for(int i=0;i<words.length;i++){
          String key=words[i].toLowerCase();
          if(key.length()>0){
              if(!treemap.containsKey(key)){
              treemap.put(key, 1);//如果map无元素，就放入
          }
          else{
             int value=treemap.get(key).intValue();
             value++;
             treemap.put(key, value); // 如果有此单词，值加1          
          } 
          }      
		}     
		Set<Map.Entry<String, Integer>> set=treemap.entrySet();//返回treemap里的条目规则集
		for(Map.Entry<String, Integer> e:set){
			System.out.println(e.getKey()+"\t"+e.getValue());
		}
	}
}
//原文链接：https://blog.csdn.net/tuke_tuke/article/details/47397999