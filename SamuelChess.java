import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

public class SamuelChess{
    public static class chessPiece {
        private String PieceName;
        private String PieceColor;
        private String ColumnPosition;
        private int RowPosition;

        // Used to print the Piece object
        public String toString() {
            return "Name: " + PieceName + "\nColor: " + PieceColor + "\nColumn: " + ColumnPosition
                    + "\nRow: " + RowPosition;
        }
    }

    public static int ConvertCharToInt(char c) {
        return c - 'a' + 1;
    }

    public static boolean IsMoveInBoard(int NewColumn, int NewRow) {
        if (((NewColumn < 9) && (NewColumn > 0)) && ((NewRow < 9) && (NewRow > 0))) {
            return true;
        }
        return false;
    }

    public static boolean CanRookMove(int OldColumn, int OldRow, int NewColumn, int NewRow) {
        if (IsMoveInBoard(NewColumn, NewRow)) {
            if (((NewColumn != OldColumn) && (NewRow == OldRow)) || ((NewRow != OldRow)) && (NewColumn == OldColumn)) {
                return true;
            }
        }
        return false;
    }

    public static boolean CanBishopMove(int OldColumn, int OldRow, int NewColumn, int NewRow) {
        if (IsMoveInBoard(NewColumn, NewRow)) {
            if (Math.abs(NewColumn - OldColumn) == Math.abs(NewRow - OldRow) && (NewColumn != OldColumn)
                    && (NewRow != OldRow)) {
                return true;
            }
        }
        return false;
    }

    public static boolean CanQueenMove(int OldColumn, int OldRow, int NewColumn, int NewRow) {
        if ((CanRookMove(OldColumn, OldRow, NewColumn, NewRow))
                || (CanBishopMove(OldColumn, OldRow, NewColumn, NewRow))) {
            return true;
        }
        return false;
    }

    // finish coding the king functionality
    public static boolean CanKingMove(int OldColumn, int OldRow, int NewColumn, int NewRow) {
        if (CanQueenMove(OldColumn, OldRow, NewColumn, NewRow)) {
            if ((Math.abs(NewColumn - OldColumn) < 3) && (Math.abs(NewRow - OldRow) < 3)) {
                return true;
            }
        }
        return false;
    }
    public static boolean canPawnMove(int OldColumn, int OldRow, int NewColumn, int NewRow, chessPiece p){
        if (IsMoveInBoard(NewColumn, NewRow)) {
            if (p.PieceColor.equalsIgnoreCase("white")){
                if((NewColumn == OldColumn) && (NewRow != OldRow)){
                    OldRow++;
                    return true;
                }          
            }
        else if(p.PieceColor.equalsIgnoreCase("black")){
            if((NewColumn == OldColumn) && (NewRow != OldRow)){
                OldRow--;
                return true;
            }
        }
    }
        return false;
    }
    /*public static boolean CanPawnMove(chessPiece currentPiece, int OldColumn, int OldRow, int NewColumn, int NewRow){
        if(IsMoveInBoard(NewColumn, NewRow)){
            if(currentPiece.PieceColor.equals("white")){
                if((NewColumn == OldColumn) && (NewRow != OldRow)){
                    OldRow++;
                    return true;
                }
            }
            else if(currentPiece.PieceColor.equals("black")){
                if((NewColumn == OldColumn) && (NewRow != OldRow)){
                    OldRow--;
                    return true;
                }
            }
        }
        return false;
    }
    */
    // Main method takes in file to create chess pieces then takes in user input and
    // determines any legal moves
    public static void main(String[] args) {
        try {
            Scanner scnr = new Scanner(new File("/Users/gabyjayme/Desktop/Java/JavaPrograms/src/lab1_input_file.txt"));
            chessPiece[] arr = new chessPiece[6];
            int x = 0;
            // Takes in file and splits it into parts, creates chess pieces with values of
            // parts, populates an array with said peices
            while (scnr.hasNextLine()) {
                String data = scnr.nextLine();
                String[] parts = data.split(", ");
                chessPiece chess_piece = new chessPiece();
                chess_piece.PieceName = parts[0];
                chess_piece.PieceColor = parts[1];
                chess_piece.ColumnPosition = parts[2];
                chess_piece.RowPosition = Integer.parseInt(parts[3].trim());
                arr[x] = chess_piece;
                x++;
            }
            scnr.close();
            // prints out information of chess peices
            for (int i = 0; i < 6; i++) {
                System.out.println(arr[i].toString() + "\n");
            }
            // takes in user input
            System.out.print(
                    "While keeping the format of [X, Y] (X being a lowercase letter from a-h, Y being a number 1-8), please choose a Target Position: ");
            Scanner UserInput = new Scanner(System.in);
            String[] values = UserInput.nextLine().split(", ");
            System.out.println("Values you just gave: " + values[0] + ", " + values[1]);
            // need to finish this section
            UserInput.close();
            if (CanRookMove(8, 1, ConvertCharToInt(values[0].charAt(0)), Integer.parseInt(values[1]))) {
                System.out.println("Yes, rook can move");
            } else {
                System.out.println("No, rook can't move");
            }
            if (CanBishopMove(6, 1, ConvertCharToInt(values[0].charAt(0)), Integer.parseInt(values[1]))) {
                System.out.println("Yes, bishop can move");
            } else {
                System.out.println("No, bishop can't move");
            }
            if (CanQueenMove(4, 2, ConvertCharToInt(values[0].charAt(0)), Integer.parseInt(values[1]))) {
                System.out.println("Yes, queen can move");
            } else {
                System.out.println("No, queen can't move");
            }
            if (CanKingMove(6, 3, ConvertCharToInt(values[0].charAt(0)), Integer.parseInt(values[1]))) {
                System.out.println("Yes, king can move");
            } else {
                System.out.println("No, king can't move");
            }
            if (canPawnMove(8,2, ConvertCharToInt(values[0].charAt(0)), Integer.parseInt(values[1]) , arr[1])){
                System.out.println("Yes, pawn can move");
            } else {
                System.out.println("No, pawn can't move");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception");
        }
    }
}