package com.example.tetrisclient;

import javafx.scene.shape.*;

import java.util.Random;

public class Controller {
    // Getting the numbers and the MESH from Tetris
    public static final int MOVE = TetrisClient.MOVE;
    public static final int SIZE = TetrisClient.SIZE;
    public static int XMAX = TetrisClient.XMAX;
    public static int YMAX = TetrisClient.YMAX;
    public static int[][] MESH = TetrisClient.MESH;

    public static void MoveRight(Form form) {
        if (form.a.getX() + MOVE <= XMAX - SIZE && form.b.getX() + MOVE <= XMAX - SIZE
                && form.c.getX() + MOVE <= XMAX - SIZE && form.d.getX() + MOVE <= XMAX - SIZE) {
            int movea = MESH[((int) form.a.getX() / SIZE) + 1][((int) form.a.getY() / SIZE)];
            int moveb = MESH[((int) form.b.getX() / SIZE) + 1][((int) form.b.getY() / SIZE)];
            int movec = MESH[((int) form.c.getX() / SIZE) + 1][((int) form.c.getY() / SIZE)];
            int moved = MESH[((int) form.d.getX() / SIZE) + 1][((int) form.d.getY() / SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() + MOVE);
                form.b.setX(form.b.getX() + MOVE);
                form.c.setX(form.c.getX() + MOVE);
                form.d.setX(form.d.getX() + MOVE);
            }
        }
    }

    public static void MoveLeft(Form form) {
        if (form.a.getX() - MOVE >= 0 && form.b.getX() - MOVE >= 0 && form.c.getX() - MOVE >= 0
                && form.d.getX() - MOVE >= 0) {
            int movea = MESH[((int) form.a.getX() / SIZE) - 1][((int) form.a.getY() / SIZE)];
            int moveb = MESH[((int) form.b.getX() / SIZE) - 1][((int) form.b.getY() / SIZE)];
            int movec = MESH[((int) form.c.getX() / SIZE) - 1][((int) form.c.getY() / SIZE)];
            int moved = MESH[((int) form.d.getX() / SIZE) - 1][((int) form.d.getY() / SIZE)];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() - MOVE);
                form.b.setX(form.b.getX() - MOVE);
                form.c.setX(form.c.getX() - MOVE);
                form.d.setX(form.d.getX() - MOVE);
            }
        }
    }

//    public static char historyRandomizer() {
//        char[] pieces = {'j', 'l', 'o', 's', 't', 'z', 'i'};
//        char history;
//        while (true) {
//            // First "roll"
//            char piece = pieces[(int) (Math.random() * pieces.length)];
//            // Roll is checked against the history
//            if (piece == history) {
//                piece = pieces[Math.floor(Math.random() * pieces.length)];
//            }
//            history = piece;
//            yield piece;
//        }
//    }

    public static Form makeRect(char history, Random random) {
        char[] pieces = {'j', 'l', 'o', 's', 't', 'z', 'i'};
        char piece;

        while (true) {
            piece = pieces[random.nextInt(pieces.length)];

            if (piece != history) {
                TetrisClient.history = piece;
                break;
            }
        }

        int block = (int) (Math.random() * 100);
        String name;
        Rectangle a = new Rectangle(SIZE - 1, SIZE - 1), b = new Rectangle(SIZE - 1, SIZE - 1), c = new Rectangle(SIZE - 1, SIZE - 1),
                d = new Rectangle(SIZE - 1, SIZE - 1);

        switch (piece){
            case 'j':
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2 - SIZE);
                b.setY(SIZE);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                d.setY(SIZE);
                name = "j";
                break;

            case 'l':
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2 - SIZE);
                b.setY(SIZE);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                d.setY(SIZE);
                name = "l";
                break;

            case 'o':
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 - SIZE);
                c.setY(SIZE);
                d.setX(XMAX / 2);
                d.setY(SIZE);
                name = "o";
                break;

            case 's':
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 - SIZE);
                d.setY(SIZE);
                name = "s";
                break;

            case 't':
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                name = "t";
                break;

            case 'z':
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 + SIZE);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE + SIZE);
                d.setY(SIZE);
                name = "z";
                break;

            case 'i':
                a.setX(XMAX / 2 - SIZE - SIZE);
                b.setX(XMAX / 2 - SIZE);
                c.setX(XMAX / 2);
                d.setX(XMAX / 2 + SIZE);
                name = "i";
                break;
            default:
                name = "error";
                break;
        }


        return new Form(a, b, c, d, name);
    }
}