import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    int N = Sudoku.N;

    public ExcelReader() throws IOException {
    }

    public int[][] read(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filename));
        XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        int [][] myArray = new int[N][N];
        for( int i = 0; i < N; i++ ) {
            XSSFRow row = mySheet.getRow(i);
            for( int j = 0; j < N; j++ ) {
                myArray[i][j] = (int) row.getCell(j).getNumericCellValue();
            }
        }
        return myArray;
    }
}
