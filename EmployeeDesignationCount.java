import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Employee{
    String name;
    String designation;

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                "-designation='" + designation + '\'';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
public class EmployeeDesignationCount {
    public static void main(String[] args) {
        String[] employeeDesignation = new String[2];
        employeeDesignation[0]="Software Engineer";
        employeeDesignation[1] = "Test Engineer";
        List<Employee> employeeList = new ArrayList<>();
        for (int i=0;i<5;i++)
        {
            String designation = new Random().nextBoolean()?employeeDesignation[0]:
                    employeeDesignation[1];
            Employee employee = new Employee();
            employee.setDesignation(designation);
            employee.setName("ABC"+(i+1));
            employeeList.add(employee);
        }
        employeeList.forEach(System.out::println);
        System.out.println(employeeList.stream()
                .collect(Collectors.groupingBy(employee -> employee.getDesignation(),Collectors.counting())));
    }
}
