import java.util.ArrayList;

public class Hourly extends Employee {
    public void timecard(ArrayList<Employee> employees) {
        int intAux = 0;
        allEmployees(employees);
        Hourly employee = new Hourly();
        System.out.println("Digite o id do funcionário que deseja lançar o cartão de ponto(apenas horistas):");
        int idToVerify = getExcecao().loadInt();
        int code = 0;
        for(Employee e : employees) {
            if(e.getId() == idToVerify && e instanceof Hourly) {
                employee = (Hourly) e;
                code = 1;
                break;
            }
        }
        if(code == 0) System.out.println("Não é possível lançar o cartão de ponto!");
        else {
            double money = 0;
            double salary = employee.getSalary();
            System.out.println("Digite a hora de entrada do funcionário:");
            while(true) {
                intAux = getExcecao().loadInt();
                if(intAux >= 0 && intAux < 24) {
                    break;
                }
                System.out.println("Digite um valor válido para a hora!");
            }
            int entry_hour = intAux;
            System.out.println("Digite o minuto de entrada do funcionário:");
            while(true) {
                intAux = getExcecao().loadInt();
                if(intAux >= 0 && intAux < 60) {
                    break;
                }
                System.out.println("Digite um valor válido para os minutos!");
            }
            int entry_minute = intAux;
            System.out.println("Digite a hora de saída do funcionário:");
            while(true) {
                intAux = getExcecao().loadInt();
                if(intAux >= 0 && intAux < 24) {
                    break;
                }
                System.out.println("Digite um valor válido para a hora!");
            }
            int exit_hour = intAux;
            System.out.println("Digite o minuto de saída do funcionário:");
            while(true) {
                intAux = getExcecao().loadInt();
                if(intAux >= 0 && intAux < 60) {
                    break;
                }
                System.out.println("Digite um valor válido para os minutos!");
            }
            int exit_minute = intAux;

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

            employee.getGenericPayment().updateActualPayment(money, employee);
            System.out.println("Cartão de ponto adicionado com sucesso!");
        }
    }

}
