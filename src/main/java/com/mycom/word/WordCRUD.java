package com.mycom.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    /*
     * => 난이도 (1,2,3) & 새 단어 입력: 1 driveway
     * */

    ArrayList<Word> list;
    Scanner s;

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
        System.out.println("새 단어가 단어장에 추가되었습니다. ");
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
        System.out.println("-----------------------------");
        return idlist;
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
        System.out.println("단어가 수정되었습니다. ");
    }

    public void deleteItem() {
        System.out.print("=> 삭제할 단어 검색 : ");		// 검색 결과가 없으면 메뉴로 돌아가게 처리
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();		// 엔터 소모

        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String ans = s.next();		// 공백, 엔터 포함하여 입력 받기
        if(ans.equalsIgnoreCase("y")){
            list.remove((int)idlist.get(id-1));		// ArrayList의 remove() 파라미터는 object 또는 삭제할 인덱스 정수
            System.out.println("단어가 삭제되었습니다. ");
        }else {
            System.out.println("취소되었습니다. ");
        }

    }
}
