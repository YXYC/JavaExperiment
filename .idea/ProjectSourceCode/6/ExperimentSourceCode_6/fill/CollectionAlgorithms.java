package ExperimentSourceCode_6.fill;

import java.util.*;
public class CollectionAlgorithms {
    public static void main(String[] args){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<100;i++){

            list.add((int)(Math.random()*100));//添加元素
        }
        Collections.sort(list);  //对集合进行排序
        System.out.println("Sorted Array: "+list);
        int testNumber=10;
        int index=Collections.binarySearch(list,testNumber);
        if(index>=0){
            System.out.println("Number "+testNumber+" found at index: "+index);
        }else{
            System.out.println("Number "+testNumber+" not found");
        }
        System.out.println("Max number: "+Collections.max(list));
        System.out.println("Min number: "+Collections.min(list));
        System.out.println("Frequency of "+testNumber+": "+Collections.frequency(list,testNumber));//testNumber出现的频率
        Set<Integer> sortedList=new HashSet<>();
        sortedList.addAll(list);
        System.out.println("Number of distinct elements: "+sortedList.size());
        list.clear();
        list.addAll(sortedList);
        Collections.shuffle(list);  //搅乱集合元素顺序
        List<Integer> topTenList=list.subList(0,10);
        Collections.sort(topTenList);
        System.out.println("Top 10: "+topTenList);
    }
}