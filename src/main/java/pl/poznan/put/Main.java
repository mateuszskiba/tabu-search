package pl.poznan.put;

import pl.poznan.put.managers.AppManager;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length == 3) {
                final int n = Integer.parseInt(args[0]);
                final int k = Integer.parseInt(args[1]);
                final double h = Integer.parseInt(args[2]) / 10.0;

                AppManager appManager = new AppManager(n, k, h);
                appManager.run();
            } else {
                System.out.println("You need to pass 3 integers as arguments: n k h");
                System.out.println("For example ./109967.sh 100 0 4");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find input file");
            e.printStackTrace();
        }
    }
}
