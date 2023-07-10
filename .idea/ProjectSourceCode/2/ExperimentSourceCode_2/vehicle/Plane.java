package ExperimentSourceCode_2.vehicle;

import ExperimentSourceCode_2.vehicle.all.Common;

public class Plane implements Common {

    @Override
    public double runTime(double a, double b, double c) {
        return a+b+c;
    }
}
