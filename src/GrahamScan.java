import java.util.Arrays;
import java.util.Stack;

class GrahamScan {
    public static int[][] convexHull(int[][] points){

        int count = 0;

        if(points.length < 3){
            throw new RuntimeException("Array has less than 3 points");
        }
        Stack<int[]> stack = new Stack<>();
        //Ищем наименьшее точку
        int l = 0;
        for (int i = 1; i < points.length; i++)
            if (points[i][0] < points[l][0] || (points[i][0] == points[l][0] && points[i][1] < points[l][1]))
                l = i;

        int[] temp = points[0];
        points[0] = points[l];
        points[l] = temp;
        //Пока верно

        //сортируем массив в порядке увеличения Y координаты(кроме первой точки)
        int j;
        for (int i = 2; i < points.length; i++) {
            j = i;
            while (j > 1 && orientation(points[0],points[j-1],points[j]) == -1) {
                count++;

                temp = points[j];
                points[j] = points[j - 1];
                points[j - 1] = temp;
                j -= 1;
            }
        }
        //Быстрая сортировка
//        Arrays.sort(points, 1, points.length, (p, q) -> orientation(points[0], p, q));

        stack.push(points[0]);
        stack.push(points[1]);



        for (int i = 2; i < points.length; i++) {
            int[] top = stack.pop();
            count++;
            while ((orientation(stack.peek(), top, points[i]) == -1) && stack.size()>1)
                top = stack.pop();
            stack.push(top);
            stack.push(points[i]);
        }

//        System.out.print(count + " ");
        return stack.toArray(new int[0][]);

    }

    //По часовой стрелки
    private static int orientation(int[] p, int[] q, int[] r) {
        int val = (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
        if (val == 0){
            return 0;
        }
        return (val > 0) ? 1 : -1;
    }

}


