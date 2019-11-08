package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordsReader {

    public String WordsReader(int letter){//reading files
        int randomlinenumber, linenumber;
        String getword;
        if(letter==3){//return random word 3 letter
            try{
                File file = new File("src/3LetterWords.txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader readerbuffer = new BufferedReader(fileReader);
                randomlinenumber=(int)(Math.random()*1012+1);
                for(linenumber=1;linenumber<1012;linenumber++){
                    if(linenumber==randomlinenumber){
                        getword=readerbuffer.readLine();
                        fileReader.close();
                        return getword;
                    }else {
                        readerbuffer.readLine();
                    }
                }
            }
            catch (IOException e){
                e.printStackTrace();
                getword="COW";
                return getword;
            }
        }
        if(letter==4){//return random 4 letter word
            try{
                File file2 = new File("src/4LetterWords.txt");
                FileReader fileReader2 = new FileReader(file2);
                BufferedReader readerbuffer2 = new BufferedReader(fileReader2);
                randomlinenumber=(int)(Math.random()*3902+1);
                for(linenumber=1;linenumber<3902;linenumber++){
                    if(linenumber==randomlinenumber){
                        getword=readerbuffer2.readLine();
                        fileReader2.close();
                        return getword;
                    }else {
                        readerbuffer2.readLine();
                    }
                }
            }
            catch (IOException e){
                e.printStackTrace();
                getword = "MATH";
                return getword;
            }
        }
        if(letter==5){//return random 5 letter word
            try{
                File file4 = new File("src/5LetterWords.txt");
                FileReader fileReader4 = new FileReader(file4);
                BufferedReader readerbuffer4 = new BufferedReader(fileReader4);
                randomlinenumber=(int)(Math.random()*8636+1);
                for(linenumber=1;linenumber<8636;linenumber++){
                    if(linenumber==randomlinenumber){
                        getword=readerbuffer4.readLine();
                        fileReader4.close();
                        return getword;
                    }else {
                        readerbuffer4.readLine();
                    }
                }
            }
            catch (IOException e){
                e.printStackTrace();
                getword="LABEL";
                return getword;
            }
        }
        else{
            getword="ERROR";
            return getword;
        }
        getword="ERROR";
    return getword;}
}
