package ExperimentSourceCode_2.vehicle.Common;

import ExperimentSourceCode_2.vehicle.all.Common;

public class ComputerTime {
    public static void main(String[] args) {
        System.out.println("交通工具："+args[0]);
        System.out.println("参数A："+args[1]);
        System.out.println("参数B："+args[2]);
        System.out.println("参数C："+args[3]);
        double A = Double.parseDouble(args[1]);
        double B = Double.parseDouble(args[2]);
        double C = Double.parseDouble(args[3]);
        double v,t;
        try{
            Common d = (Common) Class.forName("ExperimentSourceCode_2.vehicle."+args[0]).newInstance();
            v = d.runTime(A,B,C);
            t = 1000/v;
            System.out.println("平均速度："+v+"km/h");
            System.out.println("运行时间"+t+"小时");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
