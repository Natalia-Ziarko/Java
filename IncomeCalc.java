import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IncomeCalc {
    private static final int ADD_INCOMES = 1;
    private static final int SHOW_INCOMES = 2;
    private static final int AVG_INCOME = 3;
    private static final int MAX_MIN_INCOMES = 4;
    private static final int SORT_INCOMES = 5;
    private static final int LESS_MORE_AVG_INCOMES = 6;
    private static final int EXIT = 0;

    private static final int MONTHS_NO = 12;
    private static final int DATA_ROWS = 2;
    private static final int MONTH_ROW_NO = 0;
    private static final int INCOME_ROW_NO = 1;

    static double[][] incomeMonth = new double[MONTHS_NO][DATA_ROWS];
    static double[][] clonedIncomeMonth = new double[MONTHS_NO][DATA_ROWS];

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Displays the main menu options.
     */
    public static void main(String[] args) {
        int option;

        do {
            showMenu();

            option = sc.nextInt();

            switch(option){
                case ADD_INCOMES: addIncomes();
                    break;
                case SHOW_INCOMES: showIncomes();
                    break;
                case AVG_INCOME: avgIncome();
                    break;
                case MAX_MIN_INCOMES: maxMinIncomes();
                    break;
                case SORT_INCOMES: sortIncomes();
                    break;
                case LESS_MORE_AVG_INCOMES: lessMoreAvgIncomes();
                    break;
                case EXIT: System.out.println("\n >>> Good bye! <<<");
                    break;
                default : System.out.println("\n >>> Wrong option <<<");
            }
        } while(option != 0);
    }

    /**
     * Shows menu.
     */
    public static void showMenu(){
        System.out.println("\n----------------------------------------------------------");
        System.out.println("-------------Welcome to the Income Calculator-------------");
        System.out.println("----------------------------------------------------------");
        System.out.println("\nType option number or 0 if you want to end the program\n");
        System.out.println("1 - Add incomes");
        System.out.println("2 - Show all incomes");
        System.out.println("3 - Show average income");
        System.out.println("4 - Show max and min incomes");
        System.out.println("5 - Show sorted incomes");
        System.out.println("6 - Show incomes less than and more than average income");
    }

    /**
     * Adds to the array [0] - month number, [1] - input income.
     */
    public static void addIncomes() {
        for (int i = 0; i < MONTHS_NO; i++) {
            System.out.println("Income for month: " + (i + 1));

            try {
                incomeMonth[i][0] = i + 1;
                incomeMonth[i][1] = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(">>> Invalid input! Please enter a valid number. <<<\n");
                sc.next();  // Consume the invalid input
                i--;
            }
        }

        System.out.println("\n>>> Incomes added correctly! <<<");

    }

    /**
     * Shows incomes.
     */
    public static void showIncomes() {
        System.out.println();

        for(int i = 0; i < MONTHS_NO; i++) {
            System.out.println((int) incomeMonth[i][0] + " month, income: " + incomeMonth[i][1]);
        }
    }

    /**
     * Counts and shows average income.
     */
    public static void avgIncome() {
        double sum = 0, avg;

        for(int i = 0; i < MONTHS_NO; i++) sum += incomeMonth[i][1];

        avg = (double) Math.round(sum / MONTHS_NO * 100) / 100;

        System.out.println("\nAverage income: " + avg);
    }

    /**
     * Counts and shows minimum and maximum income.
     */
    public static void maxMinIncomes() {
        double max, min;
        max = min = incomeMonth[0][1];

        for(int i = 1; i < MONTHS_NO; i++) {
            if(max < incomeMonth[i][1]) max = incomeMonth[i][1];
            if(min > incomeMonth[i][1]) min = incomeMonth[i][1];
        }

        System.out.println("\nMinimum income: " + min);
        System.out.println("Maximum income: " + max);
    }

    /**
     * Creates and returns cloned incomes array.
     */
    public static double[][] clonedArray() {
        for (int i = 0; i < MONTHS_NO; i++) {
            //clonedIncomeMonth[i] = new double[MONTHS_NO];

            System.arraycopy(incomeMonth[i], 0, clonedIncomeMonth[i], 0, DATA_ROWS);
        }

        return clonedIncomeMonth;
    }

    /**
     * Sorts array by incomes and shows result.
     */
    public static void sortIncomes() {
        double[][] sortedArray = clonedArray();

        for(int i = 0; i < MONTHS_NO - 1; i++) {
            for(int j = 0; j < MONTHS_NO - i - 1; j++) {
                if(sortedArray[j][INCOME_ROW_NO] > sortedArray[j+1][INCOME_ROW_NO]) {
                    ArrayList<Double> temp = new ArrayList<>();

                    Collections.addAll(temp, sortedArray[j+1][MONTH_ROW_NO], sortedArray[j+1][INCOME_ROW_NO]);

                    sortedArray[j+1][MONTH_ROW_NO] = sortedArray[j][MONTH_ROW_NO];
                    sortedArray[j+1][INCOME_ROW_NO] = sortedArray[j][INCOME_ROW_NO];
                    sortedArray[j][MONTH_ROW_NO] = temp.get(MONTH_ROW_NO);
                    sortedArray[j][INCOME_ROW_NO] = temp.get(INCOME_ROW_NO);

                    temp.clear();
                }
            }
        }

        for (int i = 0; i < MONTHS_NO; i++) {
            System.out.println((int) sortedArray[i][0] + " month, income: " + sortedArray[i][1]);
        }
    }

    /**
     * Counts and shows less than avg incomes and more than avg incomes.
     */
    public static void lessMoreAvgIncomes() {
        double sum = 0, avg;
        for(int i = 0; i < MONTHS_NO; i++) sum += incomeMonth[i][1];

        avg = sum / MONTHS_NO;

        System.out.println("\nIncomes less than average income: ");
        for(int i = 0; i < MONTHS_NO; i++) {
            if(incomeMonth[i][1] < avg) {
                System.out.println("\t" + (int) incomeMonth[i][0] + " month, income: " + incomeMonth[i][1]);
            }
        }

        System.out.println("\nIncomes more than average income: ");
        for(int i = 0; i < MONTHS_NO; i++) {
            if(incomeMonth[i][1] > avg) {
                System.out.println("\t" + (int) incomeMonth[i][0] + " month, income: " + incomeMonth[i][1]);
            }
        }
    }

}