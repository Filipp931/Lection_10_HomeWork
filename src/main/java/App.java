import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class App {
    public static void main(String[] args) {
    }

    /**
     * Получение списка чисел из файла
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    static Stack<Integer> getNums(Path file) throws FileNotFoundException {
        Stack<Integer> nums = new Stack<>();
        if(Files.exists(file) && Files.isRegularFile(file)) {
            try(BufferedReader br = Files.newBufferedReader(file)) {
                while(br.ready())
                    nums.add(br.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else throw new FileNotFoundException("File nof found");
        return nums;
    }

    /**
     * Вычисление файкториала числа
     * @param num - число
     * @return значение факториала
     */
    public Long factorial(int num){
        if(num == 1) return 1l;
        if(num == 0 ) return 0l;
        return num * factorial(num -1);
    }

    /**
     * Создание тестового файла с числами
     * @param file путь к файлу
     */
    private static void createFileNums(Path file){
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            nums.add(new Random().nextInt(20));
        }
        if(Files.exists(file)) {
            try {
                Files.delete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(BufferedWriter bw = Files.newBufferedWriter(file)) {
            nums.forEach(num -> {
                try {
                    bw.write(num);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * вычисление факториала и вывод на экран
     * @param path путь к файлу
     */
    public void factorialMultithreading(Path path){
        createFileNums(path);
        Stack<Integer> nums = null;
        try {
            nums = getNums(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Stack<Integer> finalNums = nums;
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
               // synchronized (finalNums){
                    while (!finalNums.isEmpty()) {
                        int num = finalNums.pop();
                        Long res = factorial(num);
                        System.out.printf("Factorial of %d = %d, %s \n", num, res, Thread.currentThread().getName());
                  //  }
                }
            });
            thread.start();
        }
    }

}
