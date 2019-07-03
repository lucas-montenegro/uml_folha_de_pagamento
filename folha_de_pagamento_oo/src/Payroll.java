import java.util.ArrayList;

public class Payroll {
    public void payEmployee(Employee employee, Calendario calendario) {
        double total;
        double actualPayment = employee.getActualPayment();
        System.out.printf("Sálario atual: %.2f\n", actualPayment);

        if (employee.getSindycate().getServiceTax() != 0 && actualPayment >= 0.0) {
            double taxes = employee.getSindycate().getServiceTax();
            total = (actualPayment * taxes) / 100.0;
            actualPayment -= total;
            System.out.printf("-%.2f de serviços do sindicato\n", total);
            employee.getSindycate().setServiceTax(0);
        }

        if(employee instanceof Assalaried) {
            employee.setActualPayment(employee.getSalary());
            ((Assalaried) employee).getGenericPayment().calculateNextPayment(employee.getPaymentSchedule(), calendario, employee);
        }
        else if(employee instanceof Comissioned) {
            employee.setActualPayment(employee.getSalary() / 2);
            ((Comissioned) employee).getGenericPayment().calculateNextPayment(employee.getPaymentSchedule(), calendario, employee);
        }
        else if(employee instanceof Hourly) {
            employee.setActualPayment(0);
            ((Hourly) employee).getGenericPayment().calculateNextPayment(employee.getPaymentSchedule(), calendario, employee);
        }
        System.out.println("----------------------------------");
        System.out.printf("Foi pago %.2f\n", actualPayment);
    }

    public void payroll(ArrayList<Employee> employees, Calendario calendario) {
        int day = calendario.getDay();
        int month = calendario.getMonth();
        int year = calendario.getYear();
        for (Employee e: employees) {
            int daysToPayment = e.getDaysToPayment();
            double salary = e.getSalary();
            double actualPayment = e.getActualPayment();
            double taxes, total;

            if (day == 1) { // syndicate´s day of payment
                if (e.getSindycate().getOptionSyndicate() == 1) {
                    taxes = e.getSindycate().getSyndicateTax();
                    total = (salary * taxes) / 100.0;
                    actualPayment -= total;
                    System.out.printf("O funcionário %s, de ID de sindicato %s, teve descontado %.2f de seu salário pela sua participação no sindicato!\n", e.getName(), e.getSindycate().getIdSyndicate(), total);
                    e.setActualPayment(actualPayment);
                }
            }

            if (daysToPayment == 0) {
                System.out.printf("Nome: %s | ID : %s\n", e.getName(), e.getId());
                payEmployee(e, calendario);

                if (e.getPaymentMethod() == 1) {
                    System.out.printf("Recebeu o pagamento através de um cheque para %s\n\n", e.getAddress());
                } else if (e.getPaymentMethod() == 2) {
                    System.out.println("Recebeu o pagamento através de um cheque em mãos\n\n");
                } else if (e.getPaymentMethod() == 3) {
                    System.out.println("Recebeu o pagamento através de um depósito na conta bancária\n\n");
                }
            } else {
                System.out.printf("O funcionário %s não recebeu nenhum pagamento hoje\n", e.getName());
            }
            e.setDaysToPayment(e.getDaysToPayment() - 1);
        }
        System.out.printf("Folha de pagamento realizada na data %d/%d/%d !\n", day, month, year);
    }
}
