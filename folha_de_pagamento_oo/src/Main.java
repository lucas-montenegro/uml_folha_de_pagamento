import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        Excecao excecao = new Excecao();
        ArrayList<Employee> employees = new ArrayList<>();
        Calendario calendario = new Calendario();
        Employee employeeAux = new Employee();
        Hourly hourlyAux = new Hourly();
        Comissioned comissionedAux = new Comissioned();
        Syndicate syndicateAux = employeeAux.getSindycate();
        PaymentSchedule paymentScheduleAux = new PaymentSchedule();
        ArrayList<PaymentSchedule> paymentSchedule = new ArrayList<>();
        UndoRedo undoRedo = new UndoRedo();
        Payroll payroll = new Payroll();

        // initialize the payment schedule
        paymentScheduleAux.setDayWeekly(5);
        paymentScheduleAux.setDayBiWeekly(5);
        paymentScheduleAux.setDayMonth(-1);
        paymentSchedule.add(paymentScheduleAux);

        int intAux = 0;
        double doubleAux = 0;

        // initialize the calendary
        System.out.println("Digite o primeiro dia do ano:");
        System.out.printf("(1) - Segunda\n(2) - Terça\n(3) - Quarta\n(4) - Quinta\n(5) - Sexta\n(6) - Sábado\n(7) - Domingo\n");

        while(true) {
            intAux = excecao.loadInt();
            if(intAux > 0 && intAux < 8) {
                break;
            }
            System.out.println("Digite um valor válido para o primeiro dia do ano!");
        }

        int initialDay = intAux;
        calendario.setInitialDay(initialDay);

        System.out.println("Digite o dia da semana atual:");
        System.out.printf("(1) - Segunda\n(2) - Terça\n(3) - Quarta\n(4) - Quinta\n(5) - Sexta\n(6) - Sábado\n(7) - Domingo\n");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux > 0 && intAux < 8) {
                break;
            }
            System.out.println("Digite um valor válido para o dia da semana!");
        }
        int dayOfWeek = intAux;
        calendario.setDayOfWeek(dayOfWeek);

        System.out.println("Digite o dia atual:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux > 0 && intAux < 32) {
                break;
            }
            System.out.println("Digite um valor válido para o dia!");
        }
        int day = intAux;
        calendario.setDay(day);

        System.out.println("Digite o mês atual:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux > 0 && intAux < 13) {
                break;
            }
            System.out.println("Digite um valor válido para o mês!");
        }
        int month = intAux;
        calendario.setMonth(month);

        System.out.println("Digite o ano atual:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux > -1) {
                break;
            }
            System.out.println("Digite um valor válido para o ano!");
        }
        int year = intAux;
        calendario.setYear(year);

        calendario.initializeCalendary(initialDay);

        // initialize undo stack
        undoRedo.addUndo(employees, calendario);

        System.out.printf("Escolha uma opção:\n(0) - Sair\n(1) - Adicionar um empregado\n(2) - Remover um empregado\n(3) - Lançar cartão de ponto\n(4) - Lancar uma venda\n(5) - Adicionar taxa de serviço\n(6) - Alterar dados\n(7) - Rodar a folha de pagamento\n(8) - Undo/Redo\n(9) - Aderir à uma agenda de pagamento\n(10) - Criar uma nova agenda de pagamento\n");
        int option = excecao.loadInt();

        while(option != 0) {
            if(option == 1) {
                System.out.println("Qual o tipo de empregado a ser adicionado?");
                System.out.printf("(1) - Assalariado\n(2) - Comissionado\n(3) - Horista\n");
                while(true) {
                    intAux = excecao.loadInt();
                    if(intAux > 0 && intAux < 4) {
                        break;
                    }
                    System.out.println("Digite um valor válido para o tipo de empregado!");
                }
                int type = intAux;
                if(type == 1) {
                    Assalaried employee = new Assalaried();
                    employee.addEmployee(employees, paymentSchedule.get(0));
                    employee.setActualPayment(employee.getSalary());
                    employee.setScheduleOption(1);
                    employee.calculateNextPayment(employee.getPaymentSchedule(), calendario);
                    employees.add(employee);
                }
                else if(type == 2) {
                    Comissioned employee = new Comissioned();
                    employee.addEmployee(employees, paymentSchedule.get(0));
                    employee.setActualPayment(employee.getSalary() / 2);
                    employee.setScheduleOption(2);
                    System.out.println("Digite a comissão do funcionário adicionado:");
                    while(true) {
                        doubleAux = excecao.loadDouble();
                        if(doubleAux >= 0) {
                            break;
                        }
                        System.out.println("Digite um valor válido para a comissão!");
                    }
                    double commission = doubleAux;
                    employee.setCommission(commission);
                    employee.calculateNextPayment(employee.getPaymentSchedule(), calendario);
                    employees.add(employee);
                }
                else if(type == 3) {
                    Hourly employee = new Hourly();
                    employee.addEmployee(employees, paymentSchedule.get(0));
                    employee.setActualPayment(0);
                    employee.setScheduleOption(3);
                    employee.calculateNextPayment(employee.getPaymentSchedule(), calendario);
                    employees.add(employee);
                }
                undoRedo.clearRedo();
                undoRedo.addUndo(employees, calendario);
                System.out.println("Empregado adicionado com sucesso!");
            }
            else if(option == 2) {
                employeeAux.removeEmployee(employees);
                undoRedo.clearRedo();
                undoRedo.addUndo(employees, calendario);
            }
            else if(option == 3) {
                hourlyAux.timecard(employees);
                undoRedo.clearRedo();
                undoRedo.addUndo(employees, calendario);
            }
            else if(option == 4) {
                comissionedAux.addSale(employees);
                undoRedo.clearRedo();
                undoRedo.addUndo(employees, calendario);
            }
            else if(option == 5) {
                employeeAux.allEmployees(employees);
                syndicateAux.addServiceTax(employees);
                undoRedo.clearRedo();
                undoRedo.addUndo(employees, calendario);
            }
            else if(option == 6) {
                employeeAux.changeEmployeeData(employees, calendario);
                undoRedo.clearRedo();
                undoRedo.addUndo(employees, calendario);
            }
            else if(option == 7) {
                payroll.payroll(employees, calendario);
                calendario.nextDayOfWeek(calendario.getDayOfWeek());
                calendario.nextDay(calendario.getDay(), calendario.getMonth());
                calendario.nextMonth(calendario.getDay(), calendario.getMonth());
                calendario.nextYear(calendario.getDay(), calendario.getMonth(), calendario.getYear());
                undoRedo.clearRedo();
                undoRedo.addUndo(employees, calendario);
            }
            else if(option == 8) {
                undoRedo.doUndoRedo(employees, calendario);
            }
            else if(option == 9) {
                employeeAux.allEmployees(employees);
                paymentScheduleAux.joinPaymentSchedule(employees, paymentSchedule);
            }
            else if(option == 10) {
                paymentScheduleAux.addPaymentSchedule();
                paymentSchedule.add(paymentScheduleAux);
                System.out.println("Agenda adicionada com sucesso!");
            }
            else if(option < 0 || option > 10) {
                System.out.println("Digite uma das opções válidas!");
            }

            System.out.printf("Escolha uma opção:\n(0) - Sair\n(1) - Adicionar um empregado\n(2) - Remover um empregado\n(3) - Lançar cartão de ponto\n(4) - Lancar uma venda\n(5) - Adicionar taxa de serviço\n(6) - Alterar dados\n(7) - Rodar a folha de pagamento\n(8) - Undo/Redo\n(9) - Aderir à uma agenda de pagamento\n(10) - Criar uma nova agenda de pagamento\n");
            option = excecao.loadInt();
        }

        for(Employee e : employees) {
            if(e instanceof Assalaried) {
                System.out.println("Type: Assalaried");
            }
            else if(e instanceof Comissioned) {
                System.out.println("Type: Comissioned");
                System.out.printf("Commission: %.2f\n", ((Comissioned) e).getCommission());
            }
            else if(e instanceof Hourly) {
                System.out.println("Type: Hourly");
            }
            System.out.printf("Name: %s\n", e.getName());
            System.out.printf("Address: %s\n", e.getAddress());
            System.out.printf("ID: %d\n", e.getId());
            System.out.printf("Salary: %.2f\n", e.getSalary());
            System.out.printf("Type of payment: %d\n", e.getPaymentMethod());
            System.out.printf("Syndicate: %d\n", e.getSindycate().getOptionSyndicate());
            System.out.printf("Syndicate ID: %d\n", e.getSindycate().getIdSyndicate());
            System.out.printf("Syndicate tax: %.2f\n", e.getSindycate().getSyndicateTax());
            System.out.printf("Syndicate service tax: %.2f\n", e.getSindycate().getServiceTax());
            System.out.printf("-------------------------------\n\n");
        }

        /*for(int i = 0; i < 12; i++) {
            int [][] calendary = calendario.getCalendary();
            System.out.printf("Initial day:%d | Last useful day: %d | Days:%d\n", calendary[i][0], calendary[i][1], calendary[i][2]);
        }*/

        System.out.println("Obrigado por utilizar o sistema de folha de pagamento!");
    }
}