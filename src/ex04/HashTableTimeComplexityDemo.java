package ex04;

import java.util.HashMap;
import java.util.Random;

public class HashTableTimeComplexityDemo {

    //무작위 문자열 키 생성 함수
    public static String generateRandomKey(int length){
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random(); //난수 생성기

        //length 만큼 반복하여 알파벳 중 무작위로 하나씩 선택해 문자열 생성
        for(int i = 0; i < length; i++){
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString(); //무작위 키 문자열 반환
    }

    public static void main(String[] args) {

        int[] sizes = {10_000, 100_000, 1_000_000}; // 1천, 10만, 100만 데이터의 크기 배열 선언

        //배열의 각 값(데이터의 개수)에 대해 반복 수행
        for(int size : sizes){

            //HashMap 생성
            HashMap<String, Integer> map = new HashMap<>();
            //생성된 키 저장 배열
            String[] keys = new String[size];

            //삽입 시작 시점의 시간 기록
            long startPut = System.nanoTime();

            //size 만큼 반복
            for(int i  = 0; i < size; i++) {
                String key = generateRandomKey(10);
                keys[i] = key;
                map.put(key, i); // (키, 값) 쌍으로 저장
            }
            long endPut = System.nanoTime(); // 삽입 종료 시간 측정
            long putTime = (endPut - startPut) / 1_000_000; //밀리초 단위로 변환

            //조회 시간 측정
            Random rand = new Random();
            long startGet = System.nanoTime();

            //keys[] 배열에서 무작위 인덱스 키 선택
            //조회 연산 10000번 수행
            for(int i  = 0; i < 10_000; i++) {
                map.get(keys[rand.nextInt(size)]);
            }

            //종료 시간 측정 후 밀리초 반환
            long endGet = System.nanoTime();
            long getTime = (endGet - startGet) / 1_000_000;

            System.out.println("데이터 개수 : " + size);
            System.out.println("삽입 시간 : " + putTime + "ms" );
            System.out.println("조회 시간 (10,000회) : " + getTime + "ms" );
            System.out.println("---------------------");
        }

    }
}
