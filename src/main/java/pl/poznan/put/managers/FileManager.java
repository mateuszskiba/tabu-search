package pl.poznan.put.managers;

import pl.poznan.put.structures.Job;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private FileManager() { }

    public static List<List<Job>> readFile(String fileName) throws FileNotFoundException {
        File file = new File("input/" + fileName);
        Scanner scanner = new Scanner(file);

        int numberOfProblems = Integer.parseInt(scanner.nextLine().trim());
        final List<List<Job>> problems = new ArrayList<>();

        for (int i=0; i<numberOfProblems; i++) {
            int numberOfJobs = Integer.parseInt(scanner.nextLine().trim());
            final List<Job> jobs = new ArrayList<>();

            for (int j=0; j<numberOfJobs; j++) {
                String[] splitted = scanner
                        .nextLine()
                        .trim()
                        .replaceAll(" +", " ")
                        .split(" ");

                jobs.add(
                        new Job(
                                j,
                                Integer.parseInt(splitted[0]),
                                Integer.parseInt(splitted[1]),
                                Integer.parseInt(splitted[2])
                        )
                );
            }
            problems.add(jobs);
        }
        scanner.close();
        return problems;
    }

    public static void saveResult(String fileName, int costFunctionValue, List<Job> solved) throws IOException {
        Files.createDirectories(Paths.get("./output/109967"));
        PrintWriter writer = new PrintWriter("output/109967/" + fileName);
        writer.write(Integer.toString(costFunctionValue));
        writer.write(System.getProperty("line.separator"));
        for (int i = 0; i < solved.size(); i++) {
            writer.write(Integer.toString(solved.get(i).getId()));
            if (i != solved.size() - 1) {
                writer.write(" ");
            }
        }
        writer.close();
    }
}
