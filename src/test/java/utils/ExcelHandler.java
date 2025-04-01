package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.List;
import java.util.function.Function;

public class ExcelHandler {

    public static <T> void writeDataToExcel(List<T> data, List<String> headers, Function<T,
            List<Object>> rowMapper, String filePath) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("data");

        //header row
        Row headerRow = sheet.createRow(0);//very first row
        for (int i = 0; i < headers.size(); i++) {
            headerRow.createCell(i).setCellValue(headers.get(i));
        }

        int rowIndex = 1;
        for (T record : data) {
            Row row = sheet.createRow(rowIndex++); //creates a unique row
            List<Object> cellValues = rowMapper.apply(record);
            for (int j = 0; j < cellValues.size(); j++) {
                Object value = cellValues.get(j);
                if (value instanceof Number) {
                    row.createCell(j).setCellValue(((Number) value).doubleValue());
                } else {
                    row.createCell(j).setCellValue(value != null ? value.toString() : "");
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
            Base.logger.info("Data successfully written to " + filePath);
        } catch (Exception e) {
            Base.logger.error("Error while writing data to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (Exception ex) {
                Base.logger.error("Error closing workbook: " + ex.getMessage());
            }
        }
    }
}
