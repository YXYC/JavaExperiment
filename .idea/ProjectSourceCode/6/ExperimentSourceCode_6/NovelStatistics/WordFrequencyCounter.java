package ExperimentSourceCode_6.NovelStatistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        String filePath = "src/ExperimentSourceCode_6/data/text.txt";

        // 创建一个HashMap来存储单词和对应的出现频率
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        try {
            // 读取文件
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                // 使用正则表达式分割每一行的内容为单词数组
                String[] words = line.split("[^a-zA-Z]+");
                //遍历words数组中的每个元素，将每个元素赋值给变量word
                for (String word : words) {
                    if (!word.isEmpty()) {
                        // 将单词转换为小写字母
                        String lowercaseWord = word.toLowerCase();
                        // 更新单词的出现频率
                        //获取wordFrequencyMap中键为lowercaseWord的值，如果该键不存在，则返回默认值0。
                        wordFrequencyMap.put(lowercaseWord, wordFrequencyMap.getOrDefault(lowercaseWord, 0) + 1);
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 将单词和对应的出现频率转换为List
        List<Map.Entry<String, Integer>> wordFrequencyList = new ArrayList<>(wordFrequencyMap.entrySet());

        // 根据出现频率进行排序
        Collections.sort(wordFrequencyList, (a, b) -> b.getValue().compareTo(a.getValue()));

        // 打印排序后的结果
        for (Map.Entry<String, Integer> entry : wordFrequencyList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}