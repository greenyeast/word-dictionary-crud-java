package com.mycom.word;

import java.util.Scanner;

public class WordManager {
    Scanner s = new Scanner(System.in);
    WordCRUD wordCRUD;
    WordManager(){
        wordCRUD = new WordCRUD(s);
    }

    public void start(){
        // TODO 메뉴로 돌아가기 기능- 언제어디서든 입력을 할때 'q!'라는 문자열을 입력하면 메뉴로 돌아가기
        wordCRUD.loadFile();
        while(true){
            int menu = selectMenu();
            System.out.println();

            if (menu == 0) {
                System.out.println("영어 단어장이 종료되었습니다.");
                break;
            }

            if (menu == 1){
                wordCRUD.listAll();
            }
            else if (menu == 2) {
                wordCRUD.searchLevel();
            }
            else if (menu == 3) {
                wordCRUD.searchWord();
            }
            else if (menu == 4) {
                wordCRUD.addItem();
            }
            else if (menu == 5) {       // update
                wordCRUD.updateItem();
            }
            else if (menu == 6) {       // delete
                wordCRUD.deleteItem();
            }
            else if (menu == 7) {
                wordCRUD.saveFile();
            }
        } // end while loop
    }
    public int selectMenu(){
        System.out.print("*** 영단어 마스터 ***\n"
                        + "********************\n"
                        + "1. 모든 단어 보기\n"
                        + "2. 수준별 단어 보기\n"
                        + "3. 단어 검색\n"
                        + "4. 단어 추가\n"
                        + "5. 단어 수정\n"
                        + "6. 단어 삭제\n"
                        + "7. 파일 저장\n"
                        + "0. 나가기\n"
                        + "********************\n"
                        + "=> 원하는 메뉴는? ");
        return s.nextInt();
    }
}
