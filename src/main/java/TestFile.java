import com.sun.istack.internal.NotNull;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;

class TestFile {
    private final static String DEFAULT_PATH_TO_FILE = "C:\\Doku\\myConfig\\EmailTemplateParams\\";
    private final static String DEFAULT_FILE_EXTENSION = ".txt";
    private static final String DEFAULT_FILE_NAME = "example.txt";

//    private final static String[][] bookData = {
//            {"Head First Java", "Kathy Serria", "79"},
//            {"Effective Java", "Joshua Bloch", "6"},
//            {"Clean Code", "Robert martin", "42"},
//            {"Thinking in Java", "Bruce Eckel", "35"},
//    };
    private final static List<String> bookData = Arrays.asList("Head First Java,Kathy Serria,79",
            "Effective Java,Joshua Bloch,6","Clean Code,Robert martin,42","Thinking in Java,Bruce Eckel,35");
    private final static String CONS = null;

    public static void main(String[] args) {
        writeToFile(DEFAULT_FILE_NAME, "SOME DATA");
        writeToXLS("EmailTemplateParams.xlsx", bookData);
        String str = readFile(DEFAULT_PATH_TO_FILE + "test.html", StandardCharsets.UTF_8);
        System.out.println(BooleanUtils.toBoolean("Y"));
        List<String> params = calcEmailTemplateParams(str);
        System.out.println(String.valueOf(params));

        testMethod(CONS);

        List<String> tesList = Arrays.asList("fe", "ferf", "ERF");
        System.out.println(String.valueOf(tesList));

//        System.out.println("valOf" + Long.valueOf(""));

        System.out.println("feef3fde45 f44".replaceAll("\\D+",""));
    }

    public static String testMethod(@NotNull String name){

        return "";
    }

    private static List<String> calcEmailTemplateParams(String template) {
        List<String> params = new ArrayList<>();

        List<String> words = new ArrayList<>(Arrays.asList(template.split("\\$")));
        words.remove(0);

        for (String word : words) {
            final String param = word.split("\\s")[0];
            final String splitQuote = param.split("\"")[0];
            final String filtered = splitQuote.replaceAll("[.,]","");
            params.add(filtered);
        }
        return params;
    }


    private static void writeToFile(String fileName, String data) {
        try (BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(DEFAULT_PATH_TO_FILE + fileName), "utf-8")
        )
        ) {
            output.write(data);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
    private static void writeToXLS(String fileName, List<String> rows) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("EmailTemplateParamInfo");

        int rowCount = 1;
        Row row = createHeader(sheet, rowCount);
//        Row row = null;
        for (String aBook : rows) {
            row = sheet.createRow(++rowCount);

            int columnCount = 0;
            String[] columns = aBook.split(",");
            for (String field : columns) {
                row.createCell(++columnCount).setCellValue(field);
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(DEFAULT_PATH_TO_FILE + fileName)) {
            workbook.write(outputStream);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    private static Row createHeader(XSSFSheet sheet, int rowNum) {
        rowNum = rowNum < 1 ? 1 : rowNum;
        Row row = sheet.createRow(rowNum);
        row.createCell(1).setCellValue("Template Name");
        row.createCell(2).setCellValue("Template parameter");
        return row;
    }
    public static String readFile(String path, Charset encoding) {
        byte[] encoded = new byte[0];

        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(encoded, encoding);
    }
}