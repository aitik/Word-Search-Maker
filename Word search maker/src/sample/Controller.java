package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;


public class Controller {
    Button[][] board = new Button[6][6];
    char[][] charBoard = new char[6][6];
    int[][] data = new int[6][6];
    int cl, rw, randcl, randrw, randdirect, randdirect2, lr, lc, wlf;
    String[] words = new String[]{"ROW", "MATH", "CHAR","LABEL", "CLASS"};
    String randStringChar;
    String word;
    String letter;
    @FXML
    private Label currentWord;
    @FXML
    private Label wleft;
    @FXML
    private Label log;
    @FXML
    private Button ch00,ch01,ch02,ch03,ch04,ch05,ch10,ch11,ch12,ch13,ch14,ch15,ch20,ch21,ch22,ch23,ch24,ch25,ch30,ch31,ch32,ch33,ch34,ch35,ch40,ch41,ch42,ch43,ch44,ch45,ch50,ch51,ch52,ch53,ch54,ch55;
    @FXML
    private void start(){
        word="";
        wlf=5;
        log.setText("");
        wleft.setText("Words left: "+wlf);
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data.length;j++){
                data[i][j]=2;
            }
        }
        String tr="A";
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data.length;j++){
                charBoard[i][j]=tr.charAt(0);
            }
        }
        board[0][0]=ch00;
        board[0][1]=ch01;
        board[0][2]=ch02;
        board[0][3]=ch03;
        board[0][4]=ch04;
        board[0][5]=ch05;
        board[1][0]=ch10;
        board[1][1]=ch11;
        board[1][2]=ch12;
        board[1][3]=ch13;
        board[1][4]=ch14;
        board[1][5]=ch15;
        board[2][0]=ch20;
        board[2][1]=ch21;
        board[2][2]=ch22;
        board[2][3]=ch23;
        board[2][4]=ch24;
        board[2][5]=ch25;
        board[3][0]=ch30;
        board[3][1]=ch31;
        board[3][2]=ch32;
        board[3][3]=ch33;
        board[3][4]=ch34;
        board[3][5]=ch35;
        board[4][0]=ch40;
        board[4][1]=ch41;
        board[4][2]=ch42;
        board[4][3]=ch43;
        board[4][4]=ch44;
        board[4][5]=ch45;
        board[5][0]=ch50;
        board[5][1]=ch51;
        board[5][2]=ch52;
        board[5][3]=ch53;
        board[5][4]=ch54;
        board[5][5]=ch55;
        currentWord.setText("");
        fill();
        fillrandomchars();
        unsetDisable();
    }
    @FXML
    private void WordMake(ActionEvent t){
        EventHandler z = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                rw = GridPane.getRowIndex((Button) t.getSource());
                cl = GridPane.getColumnIndex((Button) t.getSource());
                letter=String.valueOf(charBoard[rw][cl]);
                word+=letter;
                currentWord.setText(word);
                setDisable(rw, cl);
                lr=rw;
                lc=cl;
                check(word);
            }
        };
        for(int i=0; i<board.length;i++){
            for(int j=0; j< board.length;j++){
                board[i][j].setOnAction(z);
            }
        }

    }
    @FXML
    private void clearCurrentWord(){
        word="";
        letter="";
        currentWord.setText("");
        unsetDisable();
    }
    public void setDisable(int r, int c){
        for(int i=0;i<board.length;i++){
            for(int j=0; j< board.length; j++){
                board[i][j].setDisable(true);
            }
        }
        if(word.length()==1){
            if(r!=0 && r!=5 && c!=0 && c!=5){
                board[r][c+1].setDisable(false);
                board[r+1][c].setDisable(false);
                board[r+1][c+1].setDisable(false);
                board[r][c-1].setDisable(false);
                board[r-1][c].setDisable(false);
                board[r-1][c-1].setDisable(false);
                board[r-1][c+1].setDisable(false);
                board[r+1][c-1].setDisable(false);
            }
            if(r==0 && c==0){
                board[r][c+1].setDisable(false);
                board[r+1][c].setDisable(false);
                board[r+1][c+1].setDisable(false);
            }
            if(r==0 && c==5){
                board[r][c-1].setDisable(false);
                board[r+1][c].setDisable(false);
                board[r+1][c-1].setDisable(false);
            }
            if(r==5 && c==0){
                board[r-1][c].setDisable(false);
                board[r][c+1].setDisable(false);
                board[r-1][c+1].setDisable(false);
            }
            if(r==5 && c==5){
                board[r-1][c].setDisable(false);
                board[r][c-1].setDisable(false);
                board[r-1][c-1].setDisable(false);
            }
            if(c==0 && r>0 && r<5){
                board[r-1][c].setDisable(false);
                board[r+1][c].setDisable(false);
                board[r-1][c+1].setDisable(false);
                board[r+1][c+1].setDisable(false);
                board[r][c+1].setDisable(false);
            }
            if(r==0 && c>0 && c<5){
                board[r][c-1].setDisable(false);
                board[r][c+1].setDisable(false);
                board[r+1][c-1].setDisable(false);
                board[r+1][c+1].setDisable(false);
                board[r+1][c].setDisable(false);
            }
            if(r==5 && c>0 && c<5){
                board[r][c-1].setDisable(false);
                board[r][c+1].setDisable(false);
                board[r-1][c-1].setDisable(false);
                board[r-1][c+1].setDisable(false);
                board[r-1][c].setDisable(false);
            }
            if(c==5 && r>0 && r<5){
                board[r-1][c].setDisable(false);
                board[r+1][c].setDisable(false);
                board[r-1][c-1].setDisable(false);
                board[r+1][c-1].setDisable(false);
                board[r][c-1].setDisable(false);
            }
        }
        else{
            if(r-lr==1 && lc==c){
                if(r!=5){
                    board[r+1][c].setDisable(false);
                }
            }
            if(lr-r==1 && lc==c){
                if(r!=0){
                    board[r-1][c].setDisable(false);
                }
            }
            if(lr==r && c-lc==1){
                if(c!=5){
                    board[r][c+1].setDisable(false);
                }
            }
            if(lr==r && lc-c==1){
                if(c!=0){
                    board[r][c-1].setDisable(false);
                }
            }
            if(r-lr==1 && c-lc==1){
                if(r!=5 && c!=5){
                    board[r+1][c+1].setDisable(false);
                }
            }
            if(lr-r==1 && lc-c==1){
                if(r!=0 && c!=0){
                    board[r-1][c-1].setDisable(false);
                }
            }
            if(r-lr==1 && lc-c==1){
                if(r!=5 && c!=0){
                    board[r+1][c-1].setDisable(false);
                }
            }
            if(lr-r==1 && c-lc==1){
                if(r!=0 && c!=5){
                    board[r-1][c+1].setDisable(false);
                }
            }
        }

    }
    public void unsetDisable(){
        for(int i=0;i<board.length;i++){
            for(int j=0; j< board.length; j++){
                board[i][j].setDisable(false);
            }
        }
    }
    public void check(String w){
        for (int i=0; i<5;i++){
            if(w.trim()==words[i].trim()){
                log.setText("You found a word "+w);
                wlf-=1;
                wleft.setText("Words left: "+wlf);
                unsetDisable();
                if(wlf==0){
                    log.setText("You won!");
                }
            }
        }
    }
    public void fill(){
        for(String w : words ){
            fillword(w);
        }
    }
    public void fillword(String w){
        if(w.length()==3){//3 letters
            randrw=(int)(Math.random()*3);
            randcl=(int)(Math.random()*3);
            randdirect2=(int)(Math.random()*2+1);
            if(randdirect2==1){
                for(int i=0;i<3;i++){
                    board[randrw+i][randcl+i].setText(String.valueOf((w.charAt(i))));
                    charBoard[randrw+i][randcl+i]=w.charAt(i);
                    data[randrw+i][randcl+i]=0;
                }
                return;
            }
            else{
                for(int i=0;i<3;i++){
                    board[randrw+i][randcl+i].setText(String.valueOf((w.charAt(2-i))));
                    charBoard[randrw+i][randcl+i]=w.charAt(2-i);
                    data[randrw+i][randcl+i]=0;
                }
                return;
            }
        }
        if(w.length()==4){//4 letters
            int k=0;
            int empty=0;
            randdirect=(int)(Math.random()*2+1);
            while(k<1000) {
                empty=0;
                randdirect = (int) (Math.random() * 2 + 1);
                if (randdirect == 1) {
                    randrw = (int) (Math.random() * 6);
                    randcl = (int) (Math.random() * 3);
                    for (int j = 0; j < w.length(); j++) {
                        if (data[randrw][randcl + j] == 2) {
                            empty++;
                        }
                    }
                    if (empty == w.length()) {
                        if(randdirect2==1){
                            for(int i=0;i<4;i++){
                                board[randrw][randcl+i].setText(String.valueOf((w.charAt(i))));
                                charBoard[randrw][randcl+i]=w.charAt(i);
                                data[randrw][randcl+i]=0;
                            }
                            return;
                        }
                        else{
                            for(int i=0;i<4;i++){
                                board[randrw][randcl+i].setText(String.valueOf((w.charAt(3-i))));
                                charBoard[randrw][randcl+i]=w.charAt(3-i);
                                data[randrw][randcl+i]=0;
                            }
                            return;
                        }
                    }
                }
                else{
                    empty=0;
                    randrw=(int)(Math.random()*3);
                    randcl=(int)(Math.random()*6);
                    for (int j = 0; j < w.length(); j++) {
                        if (data[randrw+j][randcl] == 2) {
                            empty++;
                        }
                    }
                    if (empty == w.length()) {
                        randdirect2=(int)(Math.random()*2+1);
                        if(randdirect2==1){
                            for(int i=0;i<4;i++){
                                board[randrw+i][randcl].setText(String.valueOf((w.charAt(i))));
                                charBoard[randrw+i][randcl]=w.charAt(i);
                                data[randrw+i][randcl]=0;
                            }
                            return;
                        }
                        else{
                            for(int i=0;i<4;i++){
                                board[randrw+i][randcl].setText(String.valueOf((w.charAt(3-i))));
                                charBoard[randrw+i][randcl]=w.charAt(3-i);
                                data[randrw+i][randcl]=0;
                            }
                            return;
                        }

                    }
                }
                k++;
            }
        }
        if(w.length()==5){//5 letters
            int k=0;
            int empty=0;
            randdirect2=(int)(Math.random()*2+1);
            while(k<1000) {
                empty=0;
                randdirect = (int) (Math.random() * 2 + 1);
                if (randdirect == 1) {
                    randrw = (int) (Math.random() * 6);
                    randcl = (int) (Math.random() * 2);
                    for (int j = 0; j < w.length(); j++) {
                        if (data[randrw][randcl + j] == 2) {
                            empty++;
                        }
                    }
                    if (empty == w.length()) {
                        if(randdirect2==1){
                            for(int i=0;i<5;i++){
                                board[randrw][randcl+i].setText(String.valueOf((w.charAt(i))));
                                charBoard[randrw][randcl+i]=w.charAt(i);
                                data[randrw][randcl+i]=0;
                            }
                            return;
                        }
                        else{
                            for(int i=0;i<5;i++){
                                board[randrw][randcl+i].setText(String.valueOf((w.charAt(4-i))));
                                charBoard[randrw][randcl+i]=w.charAt(4-i);
                                data[randrw][randcl+i]=0;
                            }
                            return;
                        }
                    }
                }
                else{
                    empty=0;
                    randrw=(int)(Math.random()*2);
                    randcl=(int)(Math.random()*6);
                    for (int j = 0; j < w.length(); j++) {
                        if (data[randrw+j][randcl] == 2) {
                            empty++;
                        }
                    }
                    if (empty == w.length()) {
                        if(randdirect2==1){
                            for(int i=0;i<w.length();i++){
                                board[randrw+i][randcl].setText(String.valueOf((w.charAt(i))));
                                charBoard[randrw+i][randcl]=w.charAt(i);
                                data[randrw+i][randcl]=0;
                            }
                            return;
                        }
                        else{
                            for(int i=0;i<w.length();i++){
                                board[randrw+i][randcl].setText(String.valueOf((w.charAt(4-i))));
                                charBoard[randrw+i][randcl]=w.charAt(4-i);
                                data[randrw+i][randcl]=0;
                            }
                            return;
                        }
                    }
                }
                k++;
            }

        }

    }
    public void fillrandomchars(){
        for(int i=0; i<6; i++){
            for(int j=0;j<6;j++){
                if(data[i][j]==2){
                    int randIntChar=(int)(Math.random()*26+1);
                    randch(randIntChar,i,j);
                }
            }
        }
    }
    public void randch(int n, int i1, int j1){
        randStringChar="B";
        if(n==1){
            randStringChar="A";
        }
        if(n==2){
            randStringChar="B";
        }
        if(n==3){
            randStringChar="C";
        }
        if(n==4){
            randStringChar="D";
        }
        if(n==5){
            randStringChar="E";
        }
        if(n==6){
            randStringChar="F";
        }
        if(n==7){
            randStringChar="G";
        }
        if(n==8){
            randStringChar="H";
        }
        if(n==9){
            randStringChar="I";
        }
        if(n==10){
            randStringChar="J";
        }
        if(n==11){
            randStringChar="K";
        }
        if(n==12){
            randStringChar="L";
        }
        if(n==13){
            randStringChar="M";
        }
        if(n==14){
            randStringChar="N";
        }
        if(n==15){
            randStringChar="O";
        }
        if(n==16){
            randStringChar="P";
        }
        if(n==17){
            randStringChar="Q";
        }
        if(n==18){
            randStringChar="R";
        }
        if(n==19){
            randStringChar="S";
        }
        if(n==20){
            randStringChar="T";
        }
        if(n==21){
            randStringChar="U";
        }
        if(n==22){
            randStringChar="V";
        }
        if(n==23){
            randStringChar="W";
        }
        if(n==24){
            randStringChar="X";
        }
        if(n==25){
            randStringChar="Y";
        }
        if(n==26){
            randStringChar="Z";
        }
        board[i1][j1].setText(String.valueOf((randStringChar.charAt(0))));
        charBoard[i1][j1]=randStringChar.charAt(0);
        data[i1][j1]=0;
    }
}
