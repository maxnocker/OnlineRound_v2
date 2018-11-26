import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static int rows;
    public static int columns;
    public static int minIngridients;
    public static int maxSize;

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(System.getProperty("user.dir"));

        int[][] matrix = reader();
        printMatrix(matrix);

        for(int r=0;r<rows;r++){
            for (int c = 0; c<columns; c++) {


            }
        }


    }

    public static void printMatrix(int[][] pizzaGrid){
        for(int i=0; i<pizzaGrid.length; i++){
            for(int j=0; j<pizzaGrid[i].length; j++){
                System.out.print(pizzaGrid[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] reader(){

        BufferedReader br = null;

        try{

            br = new BufferedReader(new FileReader("in/small.in"));

            String line = br.readLine();

            String info[] = line.split(" ");
            rows = Integer.parseInt(info[0]);
            columns = Integer.parseInt(info[1]);
            minIngridients = Integer.parseInt(info[2]);
            maxSize = Integer.parseInt(info[3]);

            int tmpPizzaArray[][] = new int[rows][columns];

            line = br.readLine();
            int c = 0;

            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    tmpPizzaArray[c][i] = (line.charAt(i) == 'T')? 1 : 0;
                }
                c++;

                line = br.readLine();
            }

            br.close();

            return tmpPizzaArray;

        } catch (Exception e){

        }

        return null;

    }
}
