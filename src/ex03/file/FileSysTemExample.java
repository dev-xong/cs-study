package ex03.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSysTemExample {
    public static void main(String[] args) {

        //내 디렉토리 확인
        System.out.println("현재 디렉토리 : " + System.getProperty("user.dir"));

        //생성 할 파일
        String filePath = "example.txt";
        Path path = Paths.get(filePath);

        //파일 생성
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("파일 생성 : " + path.toAbsolutePath());
            } else {
                System.out.println("파일 이미 존재함 : " + path.toAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("파일 생성 오류 :" + e.getMessage());
        }

        //파일 쓰기
        try(BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("Hello file system");
            writer.newLine();
            writer.write("자바로 작성된 텍스트");
            System.out.println("파일 내용 작성 완료");
        } catch (IOException e) {
            System.out.println("쓰기 오류 : " + e.getMessage());
        }

        //파일 읽기
        try(BufferedReader reader = Files.newBufferedReader(path)) {
            System.out.println(" 파일 내용 ");
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(">" + line);
            }
        }catch (IOException e){
            System.out.println("읽기 오류 : " + e.getMessage()) ;
        }

        //파일 삭제
        try {
            Path dir = Paths.get("sample_dir");
            if(!Files.exists(dir)) {
                Files.createDirectory(dir);
                System.out.println("디렉토리 생성 : " + dir.toAbsolutePath());
            }

            //임시 파일 생성
            Files.createFile(dir.resolve("temp1.txt"));
            Files.createFile(dir.resolve("temp2.txt"));

            //디렉토리 내용 출력
            System.out.println("디렉토리 내용:");
            try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
                for(Path entry : stream){
                    System.out.println("-" + entry.toAbsolutePath());
                }
            }

            //디렉토리 삭제(내용 먼저 삭제)
            Files.delete(dir.resolve("temp1.txt"));
            Files.delete(dir.resolve("temp2.txt"));
            Files.delete(dir);
            System.out.println("디렉토리까지 삭제 완료");
        }catch (IOException e){
            System.out.println("디렉토리 작업 오류 : " + e.getMessage());
        }
    }
}
