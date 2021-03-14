import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    private List<org.PayrollService.EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService(List<org.PayrollService.EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    public EmployeePayrollService() {
    }

    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee Name ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary");
        Double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new org.PayrollService.EmployeePayrollData(id, name, salary));
    }

    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("Employee Payroll Data " + employeePayrollList);
        else if (ioService.equals(IOService.FILE_IO)) {
            new org.PayrollService.EmployeePayrollFileIOService().writeData(employeePayrollList);
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll Service");
        ArrayList<org.PayrollService.EmployeePayrollData> employeePayrollList = new ArrayList<>();
        org.PayrollService.EmployeePayrollService employeePayroll = new org.PayrollService.EmployeePayrollService(employeePayrollList);
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayroll.readEmployeePayrollData(consoleInputReader);
        employeePayroll.writeEmployeePayrollData(IOService.CONSOLE_IO);
    }

    public void printData(IOService fileIo) {
        if (fileIo.equals(IOService.FILE_IO)) {
            new org.PayrollService.EmployeePayrollFileIOService().printData();
        }
    }

    public long countEntries(IOService fileIo) {
        if (fileIo.equals(IOService.FILE_IO)) {
            return new org.PayrollService.EmployeePayrollFileIOService().countEntries();
        }
        return 0;
    }

    public List<org.PayrollService.EmployeePayrollData> readPayrollData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            this.employeePayrollList = new org.PayrollService.EmployeePayrollFileIOService().readData();
        return employeePayrollList;
    }
}