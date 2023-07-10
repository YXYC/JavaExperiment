package ExperimentSourceCode_2.fill;

class Static_final {
    static int i = 10;
    static final int k = 20;
    public static void main(String[] args){
        System.out.println("i = "+i);
        System.out.println("k = "+k);
        //k = 30; //此句编译出错，必须去掉
    }
    static{ i=i/2; }

}
