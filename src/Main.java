import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int SIZE = 3;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Давайте съиграем в крестики - нолики, снизу вы видите поле размером 3х3\n" +
                "Вам нужно ввести координаты точки, куда хотите сходить" +
                "\nX - это строка по горизонтали (слева на право)\nY по вертикали(Сверху вниз)");
        initMap();
        printMap();
        System.out.println();

        while (true) {
            humanTurn();
            printMap();
            if(checkWin(DOT_X)){
                System.out.println("Ты Победил! Поздравляю");
                break;
            }

            if(isFull()){
                System.out.println("Ничья...");
                break;
            }

            aiTurn();
            printMap();
            if(checkWin(DOT_O)){
                System.out.println("ИИ нынче очень развито, компьютер победил! Повезет в другой раз!");
                break;
            }
            if(isFull()){
                System.out.println("Ничья...");
                break;
            }
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%s ", map[i][j]);
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("Введите координаты X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = DOT_X;

    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x;
        int y;

        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;

    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /*public static boolean checkWin(char c){
        if(map[0][0] == c && map[0][1] == c && map[0][2] == c){
            return true;
        }
        if(map[1][0] == c && map[1][1] == c && map[1][2] == c){
            return true;
        }
        if(map[2][0] == c && map[2][1] == c && map[2][2] == c){
            return true;
        }
        if(map[0][0] == c && map[1][0] == c && map[2][0] == c){
            return true;
        }
        if(map[0][1] == c && map[1][1] == c && map[2][1] == c){
            return true;
        }
        if(map[0][2] == c && map[1][2] == c && map[2][2] == c){
            return true;
        }

        if(map[0][0] == c && map[1][1] == c && map[2][2] == c){
            return true;
        }

        if(map[0][2] == c && map[1][1] == c && map[2][0] == c){
            return true;
        }

        return false;
    }*/
    //Задание №2,
    // Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
    public static boolean checkWin(char dot) {

        boolean isCheckedDiagL, isCheckedDiagR;
        isCheckedDiagL = isCheckedDiagR = true;

        for (int diag = 0; diag < SIZE; diag++) {

            boolean isRow, isCol;
            isRow = isCol = true;

            for (int i = 0; i < SIZE; i++) {
                isRow = (map[diag][i] == dot) && isRow;
                isCol = (map[i][diag] == dot) && isCol;
                if (i == diag) {
                    isCheckedDiagL = (map[diag][diag] == dot) && isCheckedDiagL;
                    isCheckedDiagR = (map[diag][SIZE - 1 - diag] == dot) && isCheckedDiagR;
                }
            }

            if (isRow || isCol) return true;
        }

        if (isCheckedDiagL || isCheckedDiagR) return true;

        return false;
    }



}
