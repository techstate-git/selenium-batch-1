package class5;

import org.junit.jupiter.api.Test;

import static utilities.ExcelUtils.addRow;
import static utilities.ExcelUtils.create3HeadersTable;

public class Excel {
    @Test
    public void tableTest() {
        create3HeadersTable("Table", "Employee ID", "Employee Name", "Salary");
        addRow("Table", "EMP003", "Angelina Jolie", "150,000.00");
    }
}
