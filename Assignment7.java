import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Assignment7 {

    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(101); // Random integer between 0 and 100
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i : array) {
                writer.write(i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] readFileToArray(String filename) {
        ArrayList<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void bubbleSort(int[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice:");
        System.out.println("1: Generate a random array and save to a file");
        System.out.println("2: Read array from file, sort it, and save to another file");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter the length of the array:");
                int length = scanner.nextInt();
                int[] array = createRandomArray(length);
                System.out.println("Enter the filename to save:");
                String filename = scanner.next();
                writeArrayToFile(array, filename);
                System.out.println("Array saved to " + filename);
                break;
            case 2:
                System.out.println("Enter the filename to read from:");
                String inputFilename = scanner.next();
                int[] arrayFromFile = readFileToArray(inputFilename);
                bubbleSort(arrayFromFile);
                System.out.println("Enter the filename to save the sorted array:");
                String outputFilename = scanner.next();
                writeArrayToFile(arrayFromFile, outputFilename);
                System.out.println("Sorted array saved to " + outputFilename);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        scanner.close();
    }
}
