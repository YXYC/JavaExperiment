public class Shiyan2_1_1 {
    public static void main(String[] args){
        System.out.println("我们已经知道地球平均半径6370.856千米");
        System.out.println("也知道公认的地球质量为5.98×10^24 kg");
        System.out.println("我们就可以使用数学公式计算地球的平均密度为：");
        double radius=6.370856E6;  //定义存储半径的浮点型变量，用科学计数法
        double mass=5.98E24; //定义存储质量的浮点型变量
        double volume=4*Math.PI*Math.pow(radius,3)/3;  //球的体积公式，注意数学公式在程序中变换，用到了Math类中常量和方法。
        double density=mass/volume; //计算平均密度
        System.out.print(density+"(千克/立方米)"); //输出数据
    }
}