import java.util.Scanner;

public class map {
    String[][] m_map;
    String[][] player_map;
    void printMap(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        for (int i=0; i < m_map.length; i++) {
            for (int k=0; k < m_map[0].length; k++) {
                System.out.print(m_map[i][k]);
            }
            System.out.println("");
        }
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    void printPlayerMap(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        for (int i=0; i < player_map.length; i++) {
            for (int k=0; k < player_map[0].length; k++) {
                System.out.print(" ");
                if (i == 0 && k<10){
                    System.out.print(" ");
                }
                if (i>= 1){
                    if (k==1 && i>=10){}
                    else {
                        System.out.print(" ");
                    }
                }
                if (i==0 || k==0){
                    System.out.print(ANSI_CYAN+player_map[i][k]+ANSI_RESET);
                }
                else if (player_map[i][k] == "X"){
                    System.out.print(ANSI_YELLOW+player_map[i][k]+ANSI_RESET);
                }
                else if (player_map[i][k] == "B"){
                    System.out.print(ANSI_RED+player_map[i][k]+ANSI_RESET);
                }
                else if (Integer.parseInt(player_map[i][k]) == 0){
                    System.out.print(ANSI_WHITE+player_map[i][k]+ANSI_RESET);
                }
                else if (Integer.parseInt(player_map[i][k]) == 1){
                    System.out.print(ANSI_GREEN+player_map[i][k]+ANSI_RESET);
                }
                else if (Integer.parseInt(player_map[i][k]) == 2){
                    System.out.print(ANSI_BLUE+player_map[i][k]+ANSI_RESET);
                }
                else if (Integer.parseInt(player_map[i][k]) == 3){
                    System.out.print(ANSI_PURPLE+player_map[i][k]+ANSI_RESET);
                }
                else {
                    System.out.print(ANSI_PURPLE+player_map[i][k]+ANSI_RESET);
                }
            }
            System.out.println("");
            System.out.println("");
        }
    }
    void creatingMap(int sizing){
        if (sizing == 1){
            m_map = new String[8][8];
            player_map = new String[9][9];

        }
        else if (sizing == 2){
            m_map = new String[15][15];
            player_map = new String[16][16];
        }
        else if (sizing == 3){
            m_map = new String[15][29];
            player_map = new String[16][30];
        }
        for (int i=0; i < m_map.length; i++) {
            for (int k=0; k < m_map[0].length; k++) {
                m_map[i][k] = "X";

            }
        }

        for (int i=0; i < player_map.length; i++) {
            for (int k=0; k < player_map[0].length; k++) {
                player_map[i][k]="X";
            }
        }

        for (int k=0; k < player_map[0].length; k++) {
            player_map[0][k]=String.valueOf(k);

        }
        for (int i=0; i < player_map.length; i++) {
            player_map[i][0]=String.valueOf(i);
        }
        player_map[0][0]="X";
    }

    void bombGenerating(int i, int k){
        int bombe = 0;
        if (m_map.length == 8){
            bombe = 10;
        }
        else if(m_map.length == 15){
            bombe = 40;
        }
        else if(m_map[0].length == 29){
            bombe = 100;
        }


        for (int c=0; c < bombe; c++) {
            int x;
            int y;
            x = (int) (Math.random() * m_map.length);
            y = (int) (Math.random() * m_map[0].length);
            if (m_map[x][y] == "B" && (x==i && y == k)){
                c--;
            }
            else {
                m_map[x][y] = "B";
            }

        }
    }

    int playerPlaying(int x,int y,int count){
        Scanner s = new Scanner(System.in);
        int Q= 3;
        if (count != 0) {
            System.out.println("Do you suspect a bomb here?");
            System.out.println("if yes press 1");
            System.out.println("if no press 0");
        }
        else{Q=0;}
        while (Q<= -1 || Q>=2){
            Q = s.nextInt();
        }
        if (Q==1){
            player_map[x][y] ="B";
            return 2;
        }
        if (m_map[x-1][y-1] != "B" && Q ==0){
            player_map[x][y] = m_map[x-1][y-1];
            return 0;
        }
        else {
            return 1;
        }
    }

    void foungingCaseValue(){
        int countBombeAround=0;
        for (int i=0; i < m_map.length; i++) {
            for (int k=0; k < m_map[0].length; k++) {
                if (m_map[i][k] != "B"){
                    //TOP line
                    if (i-1 >= 0 && k-1 >= 0){ //top left
                        if (m_map[i-1][k-1]=="B"){
                            countBombeAround++;
                        }
                    }

                    if(i-1 >= 0){ // top mid
                        if (m_map[i-1][k]=="B"){
                            countBombeAround++;
                        }
                    }
                    if(i-1 >= 0 && k+1 < m_map[0].length){ // top right
                        if (m_map[i-1][k+1]=="B"){
                            countBombeAround++;
                        }
                    }
                    //MID line
                    if (k-1 >= 0){ //mid left
                        if (m_map[i][k-1]=="B"){
                            countBombeAround++;
                        }
                    }

                    if(k+1 < m_map[0].length){ // mid right
                        if (m_map[i][k+1]=="B"){
                            countBombeAround++;
                        }
                    }
                    //BOT line
                    if (k-1 >= 0 && i+1 < m_map.length){ //bot left
                        if (m_map[i+1][k-1]=="B"){
                            countBombeAround++;
                        }
                    }
                    if(i+1 < m_map.length){ // bot mid
                        if (m_map[i+1][k]=="B"){
                            countBombeAround++;
                        }
                    }
                    if(i+1< m_map.length && k+1 < m_map[0].length){ // bot right
                        if (m_map[i+1][k+1]=="B"){
                            countBombeAround++;
                        }
                    }
                    String str = String.valueOf(countBombeAround);
                    m_map[i][k] = str;
                    countBombeAround = 0;
                }
            }
        }
    }
    void emptyCaseVerif(int x,int y) {
        int isCaseZero = 0;
        int verif = 0;
        System.out.println(m_map[x-1][y-1]);
        if (Integer.parseInt(m_map[x-1][y-1])==0) {
            System.out.println(m_map[x-1][y-1]);
            while (verif == 0) {
                isCaseZero = 0;
                for (int i = 1; i < player_map.length; i++) {
                    for (int k = 1; k < player_map[0].length; k++) {
                        if (player_map[i][k] != "X" && player_map[i][k] != "B") {

                            if (Integer.parseInt(player_map[i][k]) == 0) {
                                //found all stick 0 on column
                                if (i - 1 >= 1) {
                                    if (player_map[i - 1][k] == "X") {
                                        if (Integer.parseInt(m_map[i - 2][k - 1]) == 0) {
                                            player_map[i - 1][k] = "0";
                                            isCaseZero++;
                                        }
                                    }
                                }
                                if (i + 1 < player_map.length) {
                                    if (player_map[i + 1][k] == "X") {
                                        if (Integer.parseInt(m_map[i][k - 1]) == 0) {
                                            player_map[i + 1][k] = "0";
                                            isCaseZero++;
                                        }
                                    }
                                }
                                //found all stick 0 on linee
                                if (k - 1 >= 1) {
                                    if (player_map[i][k - 1] == "X") {
                                        if (Integer.parseInt(m_map[i - 1][k - 2]) == 0) {
                                            player_map[i][k - 1] = "0";
                                            isCaseZero++;
                                        }
                                    }
                                }
                                if (k + 1 < player_map.length) {
                                    if (player_map[i][k + 1] == "X") {
                                        if (Integer.parseInt(m_map[i - 1][k]) == 0) {
                                            player_map[i][k + 1] = "0";
                                            isCaseZero++;
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
                if (isCaseZero != 0){
                    verif = 0;
                }
                else{
                    verif = 1;
                }
            }
        }
    }

    void foundWhatNextToZero(){
        for (int i = 1; i < player_map.length; i++) {
            for (int k = 1; k < player_map[0].length; k++) {
                if (player_map[i][k] != "X" && player_map[i][k] != "B") {
                    //TOP line
                    if (Integer.parseInt(player_map[i][k]) == 0) {
                        if (i - 1 >= 1 && k - 1 >= 1){
                            player_map[i-1][k-1]= m_map[i-2][k-2];
                        }
                    }
                    if (Integer.parseInt(player_map[i][k]) == 0) {
                        if (i - 1 >= 1 ){
                            player_map[i-1][k]= m_map[i-2][k-1];
                        }
                    }
                    if (Integer.parseInt(player_map[i][k]) == 0) {
                        if (i - 1 >= 1 && k + 1 < player_map[0].length){
                            player_map[i-1][k+1]= m_map[i-2][k];
                        }
                    }
                    //MID line
                    if (Integer.parseInt(player_map[i][k]) == 0) {
                        if ( k - 1 >= 1){
                            player_map[i][k-1]= m_map[i-1][k-2];
                        }
                    }
                    if (Integer.parseInt(player_map[i][k]) == 0) {
                        if (k + 1 < player_map[0].length){
                            player_map[i][k+1]= m_map[i-1][k];
                        }
                    }
                    //BOT line
                    if (Integer.parseInt(player_map[i][k]) == 0) {
                        if (i + 1 < player_map.length && k - 1 >= 1){
                            player_map[i+1][k-1]= m_map[i][k-2];
                        }
                    }
                    if (Integer.parseInt(player_map[i][k]) == 0) {
                        if (i + 1 < player_map.length ){
                            player_map[i+1][k]= m_map[i][k-1];
                        }
                    }
                    if (Integer.parseInt(player_map[i][k]) == 0) {
                        if (i + 1 < player_map.length && k + 1 < player_map[0].length){
                            player_map[i+1][k+1]= m_map[i][k];
                        }
                    }
                }
            }
        }
    }

int DoesPlayerWon(){
        int b =0;
        int unknown = 0;
    for (int i = 1; i < player_map.length; i++) {
        for (int k = 1; k < player_map[0].length; k++) {
            if (player_map[i][k]== "B"){
                b++;
            }
            if (player_map[i][k]== "X"){
                unknown++;
            }
        }
    }
    if (m_map.length == 8){
        if (b+unknown == 10){
            return 1;
        }
    }
    else if (m_map.length == 15){
        if (b+unknown == 40){
            return 1;
        }
    }
    else if(m_map[0].length == 29){
        if (b+unknown == 100){
            return 1;
        }
    }
    else{
        return 0;
    }
    return 0;
}

int getLenght1(){
        return m_map.length;
}
    int getLenght2(){
        return m_map[0].length;
    }
}




