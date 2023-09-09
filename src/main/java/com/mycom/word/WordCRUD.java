package com.mycom.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    /*
     * => 난이도 (1,2,3) & 새 단어 입력: 1 driveway
     * */

    ArrayList<Word> list;
    Scanner s;
    final String fname = "Dictionary.txt";

    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();
        // 1 driveway
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }

    /*
	 => 원하는 메뉴는? 1
	 ----------------------------------
	 1 ***		superintendent	관리자, 감독관
	 2 *			  electric  전기의, 전기를 생산하는
	 3 **			 equipment	장비, 용품
	 4 *				  pole  기둥, 장대
	 ----------------------------------
	 * */

    public void addItem() {
        Word one = (Word)add();
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다!!! ");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll() {
        System.out.println("-----------------------------");
        for(int i=0; i < list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("-----------------------------");
    }

    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;		// 화면에 출력할 포함한 단어 인덱스
        System.out.println("-----------------------------");
        for(int i=0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;	// keyword를 포함하지 않으면 아래 구문 무시,  아래는 포함하고 있을 때 조건
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);		// keyword 포함하는 arraylist에 추가
            j++;
        }

        if( (idlist.isEmpty()) ){
            System.out.println("검색 단어가 없습니다.");
        }
        System.out.println("-----------------------------");
        return idlist;
    }

    // searchLevel 위한 listAll
    public void listAll(int level) {
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;		// 화면에 출력할 포함한 단어 인덱스
        System.out.println("-----------------------------");

        for(int i=0; i < list.size(); i++) {
            int ilevel = list.get(i).getLevel();
            if(ilevel != level) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            j++;
        }

        if( (idlist.isEmpty()) ){
            System.out.println("해당 레벨의 단어는 없습니다.");
        }
        System.out.println("-----------------------------");
    }
    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();		// 엔터 소모

        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();		// 공백, 엔터 포함하여 입력 받기
        Word word = list.get(idlist.get(id-1));		// 실제 idlist에는 0번부터 값이 들어감
        word.setMeaning(meaning);
        System.out.println("\n단어가 수정되었습니다. \n");
    }

    public boolean hasMatchingItems(ArrayList<Integer> idlist){
        return !idlist.isEmpty(); // 아이템이 ArrayList안에 있으면 true 리턴
    }
    public void deleteItem() {
        // TODO 메뉴로 돌아가기 기능- 언제어디서든 입력을 할때 'q!'라는 문자열을 입력하면 메뉴로 돌아가기

        // Prompt for the word to be deleted
        System.out.print("=> 삭제할 단어 검색 : ");        // 없으면 취소, 메뉴로 돌아가기
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);

        // Keep asking until a valid word is found
        while( !(hasMatchingItems(idlist)) ){
            System.out.print("=> 삭제할 단어 검색 : ");        // 없으면 취소, 메뉴로 돌아가기
            keyword = s.next();
            idlist = this.listAll(keyword);
        }

        // Prompt for the selection of the word to delete
        System.out.print("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();		// 엔터 소모

        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String ans = s.next();		// 공백, 엔터 포함하여 입력 받기
        if(ans.equalsIgnoreCase("y")){
            int indexToRemove = idlist.get(id-1);
            list.remove( indexToRemove );		// ArrayList의 remove() 파라미터는 object 또는 삭제할 인덱스 정수, type-casting 필요 (int)
            System.out.println("선택한 단어 삭제 완료!!! \n");
        }else {
            System.out.println("취소되었습니다. \n");
        }
    }

    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));      // 데이터를 읽어올때 사용하는 객체
            String line;
            int count = 0;
            while(true) {
                line = br.readLine();           // BufferedReader는 텍스트 파일만 읽어오는 것이 아님, 통신할때 사용 가능
                if(line == null) break;
                String data[] = line.split("\\|");		// 문자 |
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("==> " + count + "개 로딩 완료!!!");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));
            for(Word one : list) {
                pr.write(one.toFileString() + "\n");
            }

            pr.close();
            System.out.println("===> 데이터 저장 완료!!!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void searchLevel() {
        System.out.print("=> 원하는 레벨은? (1~3) ");
        int level = s.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.print("=> 원하는 단어는? ");
        String keyword = s.next();

        listAll(keyword);

    }
}
