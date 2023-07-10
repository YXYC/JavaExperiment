package ExperimentSourceCode_2.fill;


interface Computable {
    int M = 10;

    int f(int x);

    public abstract int g(int x, int y);
}

class A implements Computable {
    public int f(int x) {
        return 1 + M + 2 * x;
    }

    public int g(int x, int y) {
        return M * (x + y);
    }
}

class B implements Computable {
    public int f(int x) {
        return x * x;
    }

    public int g(int x, int y) {
        return x * y * M;
    }
}

public class interfacedemo {
    public static void main(String[] args) {
        Computable a = new A();
        Computable b = new B();
        System.out.println(a.M);
        System.out.println("" + a.f(20) + ", " + b.g(12, 2));
        System.out.println(b.M);
        System.out.println("" + b.f(20) + ", " + b.g(12, 2));
    }
}


