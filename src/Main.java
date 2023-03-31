import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        int[][] arrPoint = {{0,0},{3,-1},{5,5},{3,1},
//                {5,1}};

        int[][][] arrayPoint = parserTxtFileInInt("numbers.txt");


        for (int i = 0; i < arrayPoint.length; i++) {
            System.out.print(arrayPoint[i].length + " ");
            long start = System.nanoTime();

            int[][] points = GrahamScan.convexHull(arrayPoint[i]);

            long end = System.nanoTime();
//            System.out.print(points.length + " ");
            System.out.println((int)(end-start)/1000);
        }

    }

    public static int[][][] parserTxtFileInInt(String name) throws FileNotFoundException {
        File file = new File(name);
        Scanner line = new Scanner(file);
        Scanner check = new Scanner(file);

        int count = 0;
        while (check.hasNext()){
            if(check.nextLine() != null){
                count++;
            }
        }

        int[][][] retusrnArray = new int[count][][];
        for (int i = 0; i < count; i++) {
            String[] t = line.nextLine().split(" ");
            List<int[]> circle = new ArrayList<>();


//            int[][] circle = new int[t.length / 2][];

            for (int j = 0; j < t.length; j+=2) {
                if(circle.contains(new int[]{Integer.parseInt(t[j]),Integer.parseInt(t[j+1])})){
                    continue;
                }
                circle.add(new int[]{Integer.parseInt(t[j]),Integer.parseInt(t[j+1])});
            }
            int[][] r = new int[circle.size()][];
            for (int j = 0; j < circle.size(); j++) {
                r[j] = circle.get(j);
            }

            retusrnArray[i] = r;
        }
        return retusrnArray;
    }
}