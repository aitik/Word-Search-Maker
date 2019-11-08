package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
//timer https://www.youtube.com/watch?v=t2Bv6hwELsU
//reader https://www.codespeedy.com/read-a-specific-line-from-a-text-file-in-java/




public class Controller {
    Timeline clock = new Timeline();

    Button[][] board = new Button[6][6];
    char[][] charBoard = new char[6][6];
    int[][] data = new int[6][6];
    int cl, rw, randcl, randrw, randdirect, randdirect2, lr, lc, wlf, r0,r1,r2,r3,r4, seconds, secondsmax;
    int bresult=1000;
    int timeworks=0;
    String[] words = new String[5];
    String randStringChar;
    String word;
    String letter;
    @FXML
    private Label currentWord;
    @FXML
    private Label wleft;
    @FXML
    private Label time;
    @FXML
    private Label bestresult;
    @FXML
    private Label log;
    @FXML
    private Label word1;
    @FXML
    private Label word2;
    @FXML
    private Label word3;
    @FXML
    private Label word4;
    @FXML
    private Label word5;
    @FXML
    private Button ch00,ch01,ch02,ch03,ch04,ch05,ch10,ch11,ch12,ch13,ch14,ch15,ch20,ch21,ch22,ch23,ch24,ch25,ch30,ch31,ch32,ch33,ch34,ch35,ch40,ch41,ch42,ch43,ch44,ch45,ch50,ch51,ch52,ch53,ch54,ch55;
    @FXML
    private void start(){//start a new game
        WordsReader wr = new WordsReader();
        words[0]=wr.WordsReader(3);
        words[1]=wr.WordsReader(4);
        words[2]=wr.WordsReader(4);
        words[3]=wr.WordsReader(5);
        words[4]=wr.WordsReader(5);
        timeworks++;
        clock.stop();
        seconds=31;
        secondsmax=seconds;
        r0=0;
        r1=0;
        r2=0;
        r3=0;
        r4=0;
        word="";
        wlf=5;
        log.setText("");
        wleft.setText("Words left: "+wlf);
        for(int i=0;i<data.length;i++){//filling data array
            for(int j=0;j<data.length;j++){
                data[i][j]=2;
            }
        }
        String tr="A";
        for(int i=0;i<data.length;i++){//filling char array
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
        wordlist();
        initialize();
    }
    @FXML
    private void WordMake(ActionEvent t){//buttons functions
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
        for(int i=0; i<board.length;i++){//setting on action buttons
            for(int j=0; j< board.length;j++){
                board[i][j].setOnAction(z);
            }
        }

    }
    @FXML
    private void clearCurrentWord(){//clearing current word
        word="";
        letter="";
        currentWord.setText("");
        unsetDisable();
    }
    @FXML
    public void initialize() {//setting a timer
        clock.setCycleCount(Timeline.INDEFINITE);
            if(clock!=null){
                clock.stop();
            }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {//setting a KeyFrame
            @Override
            public void handle(ActionEvent event) {
                seconds--;
                time.setText("Time left "+String.valueOf(seconds)+" seconds");
                if(seconds<=0){
                    clock.stop();
                    timeout();
                }
            }
        });
            if(timeworks==1){clock.getKeyFrames().add(frame);}
        clock.playFromStart();
    }

    public void setDisable(int r, int c){//setting disable and turning on specific buttons
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
    public void unsetDisable(){//turning on all buttons
        for(int i=0;i<board.length;i++){
            for(int j=0; j< board.length; j++){
                board[i][j].setDisable(false);
            }
        }
    }
    public void check(String w){//checking word for finding
        for (int i=0; i<5;i++){
            if(w.equals(words[i])){
                word="";
                letter="";
                currentWord.setText("");
                unsetDisable();
                if(i==0){
                    word1.setText("");
                    r0++;
                    if (r0>1) {
                        log.setText("You already have found word " +w);
                        return;
                    }
                }
                if(i==1){
                    word2.setText("");
                    r1++;
                    if (r1>1) {
                        log.setText("You already have found word " +w);
                        return;
                    }
                }
                if(i==2){
                    word3.setText("");
                    r2++;
                    if (r2>1) {
                        log.setText("You already have found word " +w);
                        return;
                    }
                }
                if(i==3){
                    word4.setText("");
                    r3++;
                    if (r3>1) {
                        log.setText("You already have found word " +w);
                        return;
                    }
                }
                if(i==4){
                    word5.setText("");
                    r4++;
                    if (r4>1) {
                        log.setText("You already have found word " +w);
                        return;
                    }
                }
                log.setText("You found a word: "+w);
                wlf-=1;
                wleft.setText("Words left: "+wlf);
                if(wlf==0){
                    log.setText("You won! Your result is "+(secondsmax-1-seconds)+" seconds");
                    if((secondsmax-1-seconds)<bresult){
                        bresult=secondsmax-1-seconds;
                        bestresult.setText("Best result: "+bresult+" seconds");
                    }
                    clock.stop();
                }
            }
        }
    }
    public void timeout(){//when time out
        for(int i=0;i<board.length;i++){
            for(int j=0; j< board.length; j++){
                board[i][j].setDisable(true);
            }
        }
        log.setText("Time is out!");
    }
    public void fill(){//filing words in a GridPane
        for(String w : words ){
            fillword(w);
        }
    }
    public void fillword(String w){//randomly filling words in a GridPane
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
    public void fillrandomchars(){//filling with a random letters other fields
        for(int i=0; i<6; i++){
            for(int j=0;j<6;j++){
                if(data[i][j]==2){
                    int randIntChar=(int)(Math.random()*26+1)+64;
                    randch(randIntChar,i,j);
                }
            }
        }
    }
    public void wordlist(){//setting up a word list
        word1.setText(words[0]);
        word2.setText(words[1]);
        word3.setText(words[2]);
        word4.setText(words[3]);
        word5.setText(words[4]);
    }
    public void randch(int n, int i1, int j1){//generating a random letter
        randStringChar=String.valueOf((char)n);
        board[i1][j1].setText(String.valueOf((randStringChar.charAt(0))));
        charBoard[i1][j1]=randStringChar.charAt(0);
        data[i1][j1]=0;
    }
}
