import java.util.ArrayList;
import java.util.Scanner;

public class Employee {
    private String name;
    private String address;
    private int id;
    private double salary;
    private double actualPayment;
    private int paymentMethod;
    private int daysToPayment;
    //private paymentSchedule = new PaymentSchedule();
    //private sindycate = new Syndicate();
    private Scanner input = new Scanner(System.in);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(double actualPayment) {
        this.actualPayment = actualPayment;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getDaysToPayment() {
        return daysToPayment;
    }

    public void setDaysToPayment(int daysToPayment) {
        this.daysToPayment = daysToPayment;
    }

    public void addEmployee(){
        System.out.println("Digite o seu nome:");
        String name = input.nextLine();
        setName(name);

        System.out.println("Digite o seu endereço:");
        String address = input.nextLine();
        setName(address);





    }

}
