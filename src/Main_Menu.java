import java.util.*;


public class Main_Menu {




    public static void main(String[] args) {
        map myObj = new map();
        int Coord_x = 10000;
        int Coord_y = 10000;
        int verif = 0;
        int lock = 0;
        int count = 0;
        int difficulty = 0;


           //Diffculty mode//
        Scanner s = new Scanner(System.in);
        while (difficulty<= 0 || difficulty>=4) {
            System.out.println("Welcome to MineSweeper");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("To play easy Mode ---->  press 1");
            System.out.println("To play medium Mode ---->  press 2");
            System.out.println("To play hard Mode ---->  press 3");

            difficulty = s.nextInt();

        }


        myObj.creatingMap(difficulty);
        myObj.printPlayerMap();
                //loop of play until win or lose mode//
        while (verif == 0){
            while (Coord_x<=0 || Coord_x >=myObj.getLenght1()+1) {
                System.out.println("");
                System.out.println("Choose Coord line:");
                //Player looking for case x//
                Coord_x = s.nextInt();
            }
            while (Coord_y<=0 || Coord_y >=myObj.getLenght2()+1) {
                //Player looking for case y//
                System.out.println("Choose Coord Column:");
                Coord_y = s.nextInt();
            }
            if (lock == 0){
                //All the map generation//
                myObj.bombGenerating(Coord_x,Coord_y);
                myObj.foungingCaseValue();
                myObj.printMap();
                lock=1;
            }
            //Player playing//
            int k = myObj.playerPlaying(Coord_x,Coord_y,count);
            if (k == 1){
                verif =1;
                myObj.printMap();
                System.out.println("You lose");
            }
            else if (k==2){
                myObj.printPlayerMap();
            }
            else{
                //Discovring case section//
                myObj.emptyCaseVerif(Coord_x,Coord_y);
                myObj.foundWhatNextToZero();
                myObj.printPlayerMap();
                if (myObj.DoesPlayerWon() == 1){
                    System.out.println("You won");
                    verif =1;
                }
            }
        Coord_x = 10000;Coord_y =10000;
        count++;
        }

    }
}