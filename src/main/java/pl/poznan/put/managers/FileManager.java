package pl.poznan.put.managers;

import pl.poznan.put.structures.Job;
import pl.poznan.put.structures.Problem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private FileManager() { }

    public static List<Problem> readFile(String fileName) throws FileNotFoundException {
        File file = new File("input/" + fileName);
        Scanner scanner = new Scanner(file);

        int numberOfProblems = Integer.parseInt(scanner.nextLine().trim());
        final List<Problem> problems = new ArrayList<>();

        for (int i=0; i<numberOfProblems; i++) {
            int numberOfJobs = Integer.parseInt(scanner.nextLine().trim());
            final Problem problem = new Problem();

            for (int j=0; j<numberOfJobs; j++) {
                String[] splitted = scanner
                        .nextLine()
                        .trim()
                        .replaceAll(" +", " ")
                        .split(" ");

                problem.addJob(new Job(
                        j,
                        Integer.parseInt(splitted[0]),
                        Integer.parseInt(splitted[1]),
                        Integer.parseInt(splitted[2])
                ));
            }
            problems.add(problem);
        }
        scanner.close();
        return problems;
    }

    public static void saveResult(String fileName, int costFunctionValue, Problem solved) throws IOException {
        Files.createDirectories(Paths.get("./output/109967"));
        PrintWriter writer = new PrintWriter("output/109967/" + fileName);
        writer.write(Integer.toString(costFunctionValue));
        writer.write(System.getProperty("line.separator"));
        final List<Job> jobs = solved.getJobs();
        for (int i = 0; i < jobs.size(); i++) {
            writer.write(Integer.toString(jobs.get(i).getId()));
            if (i != jobs.size() - 1) {
                writer.write(" ");
            }
        }
        writer.close();
    }
}
