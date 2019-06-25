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
    private int scheduleOption;
    private Syndicate sindycate = new Syndicate();
    private PaymentSchedule paymentSchedule = new PaymentSchedule();
    private Excecao excecao = new Excecao();
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

    public int getScheduleOption() { return scheduleOption; }

    public void setScheduleOption(int scheduleOption) { this.scheduleOption = scheduleOption; }

    public PaymentSchedule getPaymentSchedule() { return paymentSchedule; }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) { this.paymentSchedule = paymentSchedule; }

    public Excecao getExcecao() { return excecao; }

    public void setExcecao(Excecao excecao) { this.excecao = excecao; }

    public void allEmployees(ArrayList<Employee> employees) {
        for (Employee e : employees) {
            System.out.printf("Nome: %s\n", e.getName());
            System.out.printf("ID: %d\n", e.getId());
            System.out.printf("ID Sindicato: %d\n", e.getSindycate().getIdSyndicate());
            System.out.printf("-------------\n\n");
        }
    }

    public void addEmployee(ArrayList<Employee> employees, PaymentSchedule paymentSchedule) {
        int intAux = 0;
        double doubleAux = 0;
        setPaymentSchedule(paymentSchedule);

        System.out.printf("Cadastro da empresa:\n\n");
        System.out.println("Digite o seu nome:");
        String name = input.nextLine();
        setName(name);

        System.out.println("Digite o seu endereço:");
        String address = input.nextLine();
        setAddress(address);

        int id = generator.nextInt(1000000000);
        while (true) {
            int code = 0;
            for (Employee e : employees) {
                if (e.getId() == id) {
                    code = -1;
                    break;
                }
            }
            if (code == 0) break;
            id = generator.nextInt(1000000000);
        }
        setId(id);

        System.out.println("Digite o seu salário:");
        while(true) {
            doubleAux = excecao.loadDouble();
            if(doubleAux >= 0) {
                break;
            }
            System.out.println("Digite um valor válido para o salário!");
        }
        double salary = doubleAux;
        setSalary(salary);

        System.out.println("Digite o método de pagamento:");
        System.out.printf("(1) - Cheque pelos correios\n(2) - Cheque em mãos\n(3) - Depósito na conta bancária\n");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux > 0 && intAux < 4) {
                break;
            }
            System.out.println("Digite um valor válido para o metódo de pagamento!");
        }
        int paymentMethod = intAux;
        setPaymentMethod(paymentMethod);

        Syndicate syndicateAux = new Syndicate();
        System.out.printf("Cadastro do Sindicato:\n\n");
        System.out.printf("(0) - Não pertence ao sindicato\n(1) - Pertence ao sindicato\n");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux >= 0 && intAux <= 1) {
                break;
            }
            System.out.println("Digite um valor válido para a opção de sindicato!");
        }
        int optionSyndicate = intAux;
        syndicateAux.setOptionSyndicate(optionSyndicate);

        int idSyndicate = generator.nextInt(1000000000);
        if (optionSyndicate == 1) {
            while (true) {
                int code = 0;
                for (Employee e : employees) {
                    if (e.getSindycate().getIdSyndicate() == idSyndicate) {
                        code = -1;
                        break;
                    }
                }
                if (code == 0) break;
                idSyndicate = generator.nextInt(1000000000);
            }
            syndicateAux.setIdSyndicate(idSyndicate);

            System.out.println("Digite a taxa do sindicato:");
            while(true) {
                doubleAux = excecao.loadDouble();
                if(doubleAux >= 0) {
                    break;
                }
                System.out.println("Digite um valor válido para a taxa de sindicato!");
            }
            double syndicateTax = doubleAux;
            syndicateAux.setSyndicateTax(syndicateTax);
        } else {
            syndicateAux.setSyndicateTax(0);
            syndicateAux.setIdSyndicate(-1);
        }
        syndicateAux.setServiceTax(0);
        setSindycate(syndicateAux);
    }

    public void removeEmployee(ArrayList<Employee> employees) {
        allEmployees(employees);
        System.out.println("Digite o id do funcionário que deseja remover:");
        int idToRemove = excecao.loadInt();
        int code = 0;
        for (Employee e : employees) {
            if (e.getId() == idToRemove) {
                employees.remove(e);
                code = 1;
                break;
            }
        }
        if (code == 0) System.out.println("Funcionário não pôde ser removido!");
        else System.out.println("Funcionário removido com sucesso!");
    }

    public void copy(Employee oldEmployee, Employee newEmployee) {
        newEmployee.setName(oldEmployee.getName());
        newEmployee.setAddress(oldEmployee.getAddress());
        newEmployee.setId(oldEmployee.getId());
        newEmployee.setSalary(oldEmployee.getSalary());
        newEmployee.setActualPayment(oldEmployee.getActualPayment());
        newEmployee.setPaymentMethod(oldEmployee.getPaymentMethod());
        newEmployee.setSindycate(oldEmployee.getSindycate());
        newEmployee.setDaysToPayment(oldEmployee.getDaysToPayment());
        newEmployee.setPaymentSchedule(oldEmployee.getPaymentSchedule());
    }


    public void changeData(ArrayList<Employee> employees, Employee oldEmployee, Calendario calendario) {
        int intAux = 0;
        double doubleAux = 0;
        System.out.println("Digite 1 para modificar o nome e 0 para não modificar:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux >= 0 && intAux <= 1) {
                break;
            }
            System.out.println("Digite um valor válido!");
        }
        int option = intAux;
        input.nextLine();
        if (option == 1) {
            System.out.println("Digite o nome:");
            String name = input.nextLine();
            oldEmployee.setName(name);
        }

        System.out.println("Digite 1 para modificar o endereço e 0 para não modificar:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux >= 0 && intAux <= 1) {
                break;
            }
            System.out.println("Digite um valor válido!");
        }
        option = intAux;
        input.nextLine();
        if (option == 1) {
            System.out.println("Digite o endereço:");
            String address = input.nextLine();
            oldEmployee.setAddress(address);
        }

        System.out.println("Digite 1 para modificar o salário e 0 para não modificar:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux >= 0 && intAux <= 1) {
                break;
            }
            System.out.println("Digite um valor válido!");
        }
        option = intAux;
        if (option == 1) {
            System.out.println("Digite o novo salário:");
            while(true) {
                doubleAux = excecao.loadDouble();
                if(doubleAux >= 0) {
                    break;
                }
                System.out.println("Digite um valor válido para o salário!");
            }
            double salary = doubleAux;
            input.nextLine();
            oldEmployee.setSalary(salary);
        }

        System.out.println("Digite 1 para modificar o ID e 0 para não modificar:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux >= 0 && intAux <= 1) {
                break;
            }
            System.out.println("Digite um valor válido!");
        }
        option = intAux;
        input.nextLine();
        if (option == 1) {
            System.out.println("Digite o novo ID (número natural válido):");
            while(true) {
                intAux = excecao.loadInt();
                if(intAux > 0) {
                    break;
                }
                System.out.println("Digite um valor válido para o ID!");
            }
            int newId = intAux;
            while (true) {
                int code = 0;
                for (Employee e : employees) {
                    if (e.getId() == newId) {
                        code = -1;
                        break;
                    }
                }
                if (code == 0) break;
                System.out.println("ID repetido. Digite um novo ID:");
                while(true) {
                    intAux = excecao.loadInt();
                    if(intAux > 0) {
                        break;
                    }
                    System.out.println("Digite um valor válido para o ID!");
                }
                newId = intAux;
            }
            input.nextLine();
            oldEmployee.setId(newId);
        }

        System.out.println("Digite 1 para modificar a quantia do próximo contracheque e 0 para não modificar:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux >= 0 && intAux <= 1) {
                break;
            }
            System.out.println("Digite um valor válido!");
        }
        option = intAux;
        input.nextLine();
        if (option == 1) {
            System.out.println("Digite o valor do novo contracheque:");
            double atualPayment = excecao.loadDouble();
            input.nextLine();
            oldEmployee.setActualPayment(atualPayment);
        }

        System.out.println("Digite 1 para modificar a participação no sindicato e 0 para não modificar:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux >= 0 && intAux <= 1) {
                break;
            }
            System.out.println("Digite um valor válido!");
        }
        option = intAux;
        input.nextLine();
        if (option == 1) {
            System.out.println("Digite 1 para participar e 0 para não participar:");
            while(true) {
                intAux = excecao.loadInt();
                if(intAux >= 0 && intAux <= 1) {
                    break;
                }
                System.out.println("Digite um valor válido!");
            }
            int syndicateOption = intAux;
            input.nextLine();
            oldEmployee.getSindycate().setOptionSyndicate(syndicateOption);

            if (oldEmployee.getSindycate().getOptionSyndicate() == 0) {
                System.out.println("Como você saiu do sindicato o seu id e a sua taxa serão removidos automaticamente.");
                oldEmployee.getSindycate().setIdSyndicate(-1);
                oldEmployee.getSindycate().setSyndicateTax(0);
            }
        }

        if(oldEmployee.getSindycate().getOptionSyndicate() == 1) {
            System.out.println("Digite 1 para modificar a identificação no sindicato e 0 para não modificar:");
            while(true) {
                intAux = excecao.loadInt();
                if(intAux >= 0 && intAux <= 1) {
                    break;
                }
                System.out.println("Digite um valor válido!");
            }
            option = intAux;
            input.nextLine();
            if (option == 1) {
                System.out.println("Digite o ID de sindicato (número natural válido):");
                while(true) {
                    intAux = excecao.loadInt();
                    if(intAux > 0) {
                        break;
                    }
                    System.out.println("Digite um valor válido!");
                }
                int idSyndicate = intAux;
                while (true) {
                    int code = 0;
                    for (Employee e : employees) {
                        if (e.getSindycate().getIdSyndicate() == idSyndicate) {
                            code = -1;
                            break;
                        }
                    }
                    if (code == 0) break;
                    System.out.println("ID de sindicato já existente. Digite um novo ID de sindicato!");
                    while(true) {
                        intAux = excecao.loadInt();
                        if(intAux > 0) {
                            break;
                        }
                        System.out.println("Digite um valor válido!");
                    }
                    idSyndicate = intAux;
                }
                input.nextLine();
                oldEmployee.getSindycate().setIdSyndicate(idSyndicate);
            }

            System.out.println("Digite 1 para modificar a taxa do sindicato e 0 para não modificar:");
            while(true) {
                intAux = excecao.loadInt();
                if(intAux >= 0 && intAux <= 1) {
                    break;
                }
                System.out.println("Digite um valor válido!");
            }
            option = intAux;
            input.nextLine();
            if (option == 1) {
                System.out.println("Digite a taxa do sindicato:");
                while(true) {
                    doubleAux = excecao.loadDouble();
                    if(doubleAux >= 0) {
                        break;
                    }
                    System.out.println("Digite um valor válido!");
                }
                double syndicateTax = doubleAux;
                input.nextLine();
                oldEmployee.getSindycate().setSyndicateTax(syndicateTax);
            }
        }

        System.out.println("Digite 1 para modificar a taxa de serviço do sindicato e 0 para não modificar:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux >= 0 && intAux <= 1) {
                break;
            }
            System.out.println("Digite um valor válido!");
        }
        option = intAux;
        input.nextLine();
        if (option == 1) {
            System.out.println("Digite a taxa de serviço do sindicato:");
            while(true) {
                doubleAux = excecao.loadDouble();
                if(doubleAux >= 0) {
                    break;
                }
                System.out.println("Digite um valor válido!");
            }
            double serviceTax = doubleAux;
            input.nextLine();
            oldEmployee.getSindycate().setServiceTax(serviceTax);
        }

        System.out.println("Digite 1 para modificar o metódo de pagamento e 0 para não modificar:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux >= 0 && intAux <= 1) {
                break;
            }
            System.out.println("Digite um valor válido!");
        }
        option = intAux;
        input.nextLine();
        if (option == 1) {
            System.out.println("(1) Receber o pagamento em cheque pelos correios\n(2) Receber o pagamento em cheque em mãos\n(3) Receber o pagamento na conta bancária");
            while(true) {
                intAux = excecao.loadInt();
                if(intAux > 0 && intAux < 4) {
                    break;
                }
                System.out.println("Digite um valor válido!");
            }
            int paymentMethod = intAux;
            input.nextLine();
            oldEmployee.setPaymentMethod(paymentMethod);
        }

        System.out.println("Digite 1 para modificar o tipo de empregado e 0 para não modificar:");
        while(true) {
            intAux = excecao.loadInt();
            if(intAux >= 0 && intAux <= 1) {
                break;
            }
            System.out.println("Digite um valor válido!");
        }
        option = intAux;
        input.nextLine();
        if (option == 1) {
            System.out.printf("(1) - Assalariado\n(2) - Comissionado\n(3) - Horista\n");
            while(true) {
                intAux = excecao.loadInt();
                if(intAux > 0 && intAux < 4) {
                    break;
                }
                System.out.println("Digite um valor válido!");
            }
            int type = intAux;
            input.nextLine();
            if(type == 1) {
                Assalaried newEmployee = new Assalaried();
                copy(oldEmployee, newEmployee);
                newEmployee.setScheduleOption(1);
                newEmployee.calculateNextPayment(newEmployee.getPaymentSchedule(), calendario);
                employees.add(newEmployee);
            }
            else if(type == 2) {
                Comissioned newEmployee = new Comissioned();
                copy(oldEmployee, newEmployee);

                System.out.println("Digite 1 para modificar a comissão do empregado e 0 para não modificar:");
                option = input.nextInt();
                input.nextLine();
                if (option == 1) {
                    System.out.println("Digite o valor da comissão:");
                    while(true) {
                        doubleAux = excecao.loadDouble();
                        if(doubleAux >= 0) {
                            break;
                        }
                        System.out.println("Digite um valor válido!");
                    }
                    double commission = doubleAux;
                    input.nextLine();
                    newEmployee.setCommission(commission);
                }
                newEmployee.setScheduleOption(2);
                newEmployee.calculateNextPayment(newEmployee.getPaymentSchedule(), calendario);
                employees.add(newEmployee);
            }
            else if(type == 3) {
                Hourly newEmployee = new Hourly();
                copy(oldEmployee, newEmployee);
                newEmployee.setScheduleOption(3);
                newEmployee.calculateNextPayment(newEmployee.getPaymentSchedule(), calendario);
                employees.add(newEmployee);
            }
        } else {
            if (oldEmployee instanceof Comissioned) {
                System.out.println("Digite 1 para modificar a comissão do empregado e 0 para não modificar:");
                while(true) {
                    intAux = excecao.loadInt();
                    if(intAux >= 0 && intAux <= 1) {
                        break;
                    }
                    System.out.println("Digite um valor válido!");
                }
                option = intAux;
                input.nextLine();
                if (option == 1) {
                    System.out.println("Digite o valor da comissão:");
                    while(true) {
                        doubleAux = excecao.loadDouble();
                        if(doubleAux >= 0) {
                            break;
                        }
                        System.out.println("Digite um valor válido!");
                    }
                    double commission = doubleAux;
                    input.nextLine();
                    ((Comissioned) oldEmployee).setCommission(commission);
                }
            }

            employees.add(oldEmployee);
            System.out.println("Alterações completas!");
        }
    }

    public void changeEmployeeData(ArrayList<Employee> employees, Calendario calendario){
        allEmployees(employees);
        System.out.println("Digite o id do funcionário que deseja alterar os dados:");
        int idToChange = excecao.loadInt();
        int code = 0;
        Employee oldEmployee = new Employee();
        for(Employee e : employees) {
            if(e.getId() == idToChange) {
                oldEmployee = e;
                employees.remove(e);
                code = 1;
                break;
            }
        }

        if(code == 0) {
            System.out.println("Não pôde alterar os dados!");
        }
        else {
            changeData(employees, oldEmployee, calendario);
        }
    }
}
