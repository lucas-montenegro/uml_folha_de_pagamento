import java.util.ArrayList;

public class Hourly extends Employee implements Payment {
    public void updateActualPayment(double money){
        setActualPayment(getActualPayment() + money);
    }

    public void timecard(ArrayList<Employee> employees) {
        allEmployees(employees);
        System.out.println("Digite o id do funcionário que deseja lançar o cartão de ponto(apenas horistas):");
        int idToVerify = input.nextInt();
        int code = 0;
        for(Employee e : employees) {
            if(e.getId() == idToVerify && e instanceof Hourly) {
                code = 1;
                break;
            }
        }
        if(code == 0) System.out.println("Não é possível lançar o cartão de ponto!");
        else {
            double money = 0;
            double salary = getSalary();
            System.out.println("Digite a hora de entrada do funcionário:");
            int entry_hour = input.nextInt();
            System.out.println("Digite o minuto de entrada do funcionário:\n");
            int entry_minute = input.nextInt();
            System.out.println("Digite a hora de saída do funcionário:");
            int exit_hour = input.nextInt();
            System.out.println("Digite o minuto de entrada do funcionário:");
            int exit_minute = input.nextInt();

            int total_hours;

            if (exit_hour == entry_hour) {
                total_hours = 24;
            } else if (entry_hour > exit_hour) {
                total_hours = 24 - entry_hour + exit_hour;
            } else {
                total_hours = exit_hour - entry_hour;
            }
            if (entry_minute > exit_minute) {
                total_hours--;
            }
            if (total_hours > 8) {
                money += salary * 8 + (salary * 1.5 * (total_hours - 8));
            } else {
                money += salary * total_hours;
            }

            updateActualPayment(money);
            System.out.println("Cartão de ponto adicionado com sucesso!");
        }
    }

}
