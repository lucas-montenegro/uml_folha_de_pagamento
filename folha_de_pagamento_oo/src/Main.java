import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList();
        //private undoRedo = new UndoRedo();
        //private Arraylist<PaymentSchedule> paymentSchedule = new PaymentSchedule();
        //private payroll = new Payroll();

        System.out.printf("Escolha uma opção:\n(0) - Sair\n(1) - Adicionar um empregado\n(2) - Remover um empregado\n");
        int option = input.nextInt();

        while(option > 0 && option <= 10) {
            if(option == 1) {
                Employee employee = new Employee();
                employee.addEmployee();
                employees.add(employee);
            }

            System.out.printf("Escolha uma opção:\n(1) - Adicionar um empregado\n(2) - Remover um empregado\n");
            option = input.nextInt();
        }

        System.out.println("Obrigado por utilizar o sistema de folha de pagamento!");
    }
}