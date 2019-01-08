package pl.poznan.put;

import pl.poznan.put.managers.AppManager;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // for test purposes only
//            Integer [] ns = {10, 20, 50, 100, 200, 500, 1000};
//            Integer [] ks = {4, 9};
//            Double [] hs = {0.4, 0.6};
//            for (Integer n : ns){
//                for (Integer k : ks){
//                    for (Double h : hs){
//            AppManager appManager = new AppManager(n, k, h, 10);
            AppManager appManager = new AppManager(10, 0, 0.4, 10);
            appManager.run();
//                    }
//                }
//            }
//            if (args.length == 3) {
//                final int n = Integer.parseInt(args[0]);
//                final int k = Integer.parseInt(args[1]);
//                final double h = Integer.parseInt(args[2]) / 10.0;
//
//                AppManager appManager = new AppManager(n, k, h);
//                appManager.run();
//            } else {
//                System.out.println("You need to pass 3 integers as arguments: n k h");
//                System.out.println("For example ./109967.sh 100 0 4");
//            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find input file");
            e.printStackTrace();
        }
    }
}
