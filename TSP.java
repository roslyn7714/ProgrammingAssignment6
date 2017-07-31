package tsp;

import java.io.*;
import java.util.*;

/**
 *
 * @author rosly
 */
public class TSP {

    int CITI;
    static int[][] adjacency;
    int bestCost = Integer.MAX_VALUE;
    ArrayList<Integer> bestpath;

    private TSP(int N) {
        CITI = N;
        adjacency = new int[CITI][CITI];
        bestpath = new ArrayList<>();

    }

    private void populateMatrix(String fname) {

        try {
            File f = new File(fname);
            Scanner input = new Scanner(f);
            int value, i, j;
            for (i = 0; i < CITI && input.hasNext(); i++) {
                for (j = i; j < CITI && input.hasNext(); j++) {
                    if (i == j) {
                        adjacency[i][j] = 0;
                    } else {
                        value = input.nextInt();
                        adjacency[i][j] = value;
                        adjacency[j][i] = value;
                    }
                }
            }
            input.close();
        } catch (IOException e) {
            System.out.println("File reading failed");

        }
    }

    public int cost(ArrayList<Integer> path) {
        int totalcost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            totalcost += adjacency[path.get(i)][path.get(i + 1)];
        }
        if (path.size() == CITI) {
            totalcost += adjacency[path.get(path.size() - 1)][0];
        }

        return totalcost;
    }

    public class TravSP {

        private int nNode;
        private Stack<Integer> pathStack;

        public TravSP() {
            pathStack = new Stack<Integer>();
        }

        public void tsp(int adj_Matrix[][]) {
            nNode = adj_Matrix[1].length - 1;
            int[] visitedArr = new int[nNode + 1];
            visitedArr[0] = 1;
            pathStack.push(0);
            int element, dest = 0, i;
            int minNum = Integer.MAX_VALUE;
            boolean minFlag = false;
            System.out.print(0 + "\t");
            while (!pathStack.isEmpty()) {
                element = pathStack.peek();
                i = 0;
                minNum = Integer.MAX_VALUE;
                while (i <= nNode) {
                    if (adj_Matrix[element][i] > 1 && visitedArr[i] == 0) {
                        if (minNum > adj_Matrix[element][i]) {
                            minNum = adj_Matrix[element][i];
                            dest = i;
                            minFlag = true;
                        }
                    }
                    i++;
                }
                if (minFlag) {
                    visitedArr[dest] = 1;
                    pathStack.push(dest);
                    System.out.print(dest + "\t");
                    minFlag = false;
                    continue;
                }
                pathStack.pop();
            }
        }
    }

    public static void main(String... arg) {
        TSP test = new TSP(12);
        test.populateMatrix("tsp12.txt");
        System.out.println("The cities are visited are as follows: ");
        TravSP tspNearestNeighbour = test.new TravSP();
        tspNearestNeighbour.tsp(adjacency);

    }
}
