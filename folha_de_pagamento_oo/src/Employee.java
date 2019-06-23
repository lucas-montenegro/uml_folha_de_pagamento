import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Employee {
    private String name;
    private String address;
    private int id;
    private double salary;
    private double actualPayment;
    private int paymentMethod;
    private int daysToPayment;
    private Syndicate sindycate = new Syndicate();
    private PaymentSchedule paymentSchedule = new PaymentSchedule();
    Random generator = new Random();
    Scanner input = new Scanner(System.in);

    public Syndicate getSindycate() { return sindycate; }

    public void setSindycate(Syndicate sindycate) { this.sindycate = sindycate; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getActualPayment() { return actualPayment; }

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

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public void allEmployees(ArrayList<Employee> employees) {
        for(Employee e : employees) {
            System.out.printf("Nome: %s\n", e.getName());
            System.out.printf("ID: %d\n", e.getId());
            System.out.printf("ID Sindicato: %d\n", e.getSindycate().getIdSyndicate());
            System.out.printf("-------------\n\n");
        }
    }

    public void addEmployee(ArrayList<Employee> employees){
        System.out.printf("Cadastro da empresa:\n\n");
        System.out.println("Digite o seu nome:");
        String name = input.nextLine();
        setName(name);

        System.out.println("Digite o seu endereço:");
        String address = input.nextLine();
        setAddress(address);

        int id = generator.nextInt(1000000000);
        while(true) {
            int code = 0;
            for(Employee e : employees) {
                if(e.getId() == id) {
                    code = -1;
                    break;
                }
            }
            if(code == 0) break;
            id = generator.nextInt(1000000000);
        }
        setId(id);

        System.out.println("Digite o seu salário:");
        double salary = input.nextDouble();
        setSalary(salary);

        System.out.println("Digite o método de pagamento:");
        System.out.printf("(1) - Cheque pelos correios\n(2) - Cheque em mãos\n(3) - Depósito na conta bancária\n");
        int paymentMethod = input.nextInt();
        setPaymentMethod(paymentMethod);

        Syndicate syndicateAux = new Syndicate();
        System.out.printf("Cadastro do Sindicato:\n\n");
        System.out.printf("(0) - Não pertence ao sindicato\n(1) - Pertence ao sindicato\n");
        int optionSyndicate = input.nextInt();
        syndicateAux.setOptionSyndicate(optionSyndicate);

        int idSyndicate = generator.nextInt(1000000000);
        if(optionSyndicate == 1) {
            while(true) {
                int code = 0;
                for(Employee e : employees) {
                    if(e.getSindycate().getIdSyndicate() == idSyndicate) {
                        code = -1;
                        break;
                    }
                }
                if(code == 0) break;
                idSyndicate = generator.nextInt(1000000000);
            }
            syndicateAux.setIdSyndicate(idSyndicate);

            System.out.println("Digite a taxa do sindicato:");
            double syndicateTax = input.nextDouble();
            syndicateAux.setSyndicateTax(syndicateTax);
        }
        else {
            syndicateAux.setSyndicateTax(0);
            syndicateAux.setIdSyndicate(-1);
        }
        syndicateAux.setServiceTax(0);
        setSindycate(syndicateAux);
    }

    public void removeEmployee(ArrayList<Employee> employees){
        allEmployees(employees);
        System.out.println("Digite o id do funcionário que deseja remover:");
        int idToRemove = input.nextInt();
        int code = 0;
        for(Employee e : employees) {
            if(e.getId() == idToRemove) {
                employees.remove(e);
                code = 1;
                break;
            }
        }
        if(code == 0) System.out.println("Funcionário não pôde ser removido!");
        else System.out.println("Funcionário removido com sucesso!");
    }

    /*public void changeEmployeeData(ArrayList<Employee> employees){
        allEmployees(employees);
        System.out.println("Digite o id do funcionário que deseja alterar os dados:");
        int idToChange = input.nextInt();
        int code = 0;
        //Employee oldEmployee = new Employee();
        //Employee employee = new Employee();
        for(Employee e : employees) {
            if(e.getId() == idToChange) {
                if(e instanceof Assalaried) {
                    Assalaried oldEmployee = (Assalaried) e;
                    employees.remove(e);
                }
                else if(e instanceof Comissioned) {
                    Comissioned oldEmployee = (Comissioned) e;
                    //oldEmployee = e;
                    employees.remove(e);
                }
                else {
                    Hourly oldEmployee = (Hourly) e;
                    //oldEmployee = e;
                    employees.remove(e);
                }
                code = 1;
                break;
            }
        }

        if(code == 0) {
            System.out.println("Não pôde alterar os dados!");
        }
        else {
            System.out.println("Qual o tipo do empregado?");
            System.out.printf("(1) - Assalariado\n(2) - Comissionado\n(3) - Horista\n");
            int type = input.nextInt();
            if(type == 1 && !(oldEmployee instanceof Assalaried)) {
                Assalaried employee = (Assalaried) oldEmployee;
            }
            else if(type == 2 && !(oldEmployee instanceof Comissioned)) {
                oldEmployee = (Comissioned) oldEmployee;
            }
            else if(type == 3 && (oldEmployee instanceof Hourly)) {
                oldEmployee = (Hourly) oldEmployee;
            }

            System.out.println("Digite o seu nome:");
            String name = input.nextLine();
            oldEmployee.setName(name);

            System.out.println("Digite o seu endereço:");
            String address = input.nextLine();
            oldEmployee.setAddress(address);

            System.out.println("Digite o seu ID:");
            int id = input.nextInt();
            while(true) {
                code = 0;
                for(Employee e : employees) {
                    if(e.getId() == id) {
                        code = -1;
                        break;
                    }
                }
                if(code == 0) break;
                id = input.nextInt();
            }
            oldEmployee.setId(id);

            System.out.println("Digite o seu salário:");
            double salary = input.nextDouble();
            oldEmployee.setSalary(salary);

            System.out.println("Digite o método de pagamento:");
            System.out.printf("(1) - Cheque pelos correios\n(2) - Cheque em mãos\n(3) - Depósito na conta bancária\n");
            int paymentMethod = input.nextInt();
            oldEmployee.setPaymentMethod(paymentMethod);

            System.out.printf("Cadastro do Sindicato:\n\n");
            System.out.printf("(0) - Não pertence ao sindicato\n(1) - Pertence ao sindicato\n");
            int optionSyndicate = input.nextInt();
            oldEmployee.getSindycate().setOptionSyndicate(optionSyndicate);

            if(optionSyndicate == 1) {
                System.out.println("Digite o seu ID de Sindicato:");
                int idSyndicate = input.nextInt();
                while(true) {
                    code = 0;
                    for(Employee e : employees) {
                        if(e.getSindycate().getIdSyndicate() == idSyndicate) {
                            code = -1;
                            break;
                        }
                    }
                    if(code == 0) break;
                    idSyndicate = input.nextInt();
                }
                oldEmployee.getSindycate().setIdSyndicate(idSyndicate);

                System.out.println("Digite a taxa do sindicato:");
                double syndicateTax = input.nextDouble();
                oldEmployee.getSindycate().setSyndicateTax(syndicateTax);
            }
            else {
                oldEmployee.getSindycate().setSyndicateTax(0);
                oldEmployee.getSindycate().setIdSyndicate(-1);
            }
            oldEmployee.getSindycate().setServiceTax(0);
        }
    }*/
}
