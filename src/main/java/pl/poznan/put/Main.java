package pl.poznan.put;

import pl.poznan.put.managers.AppManager;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            AppManager appManager = new AppManager(100, 0, 0.4, 10);
            appManager.run();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find input file");
            e.printStackTrace();
        }
    }
}
