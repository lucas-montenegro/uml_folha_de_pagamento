import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class UndoRedo {
    private Stack<ArrayList<Employee>> undo = new Stack<>();
    private Stack<ArrayList<Employee>> redo = new Stack<>();
    Scanner input = new Scanner(System.in);

    public Stack<ArrayList<Employee>> getUndo() {
        return undo;
    }

    public void setUndo(Stack<ArrayList<Employee>> undo) {
        this.undo = undo;
    }

    public Stack<ArrayList<Employee>> getRedo() {
        return redo;
    }

    public void setRedo(Stack<ArrayList<Employee>> redo) {
        this.redo = redo;
    }

    public void copyArrayList(ArrayList<Employee> oldEmployee, ArrayList<Employee> newEmployee) {
        oldEmployee.clear();

        for(Employee e: newEmployee) {
            if(e instanceof Assalaried) {
                Assalaried employee = new Assalaried();
                employee.copy(e, employee);
                oldEmployee.add(employee);
            }
            else if(e instanceof Comissioned) {
                Comissioned employee = new Comissioned();
                employee.copy(e, employee);
                employee.setCommission(((Comissioned) e).getCommission());
                oldEmployee.add(employee);
            }
            else if(e instanceof Hourly) {
                Hourly employee = new Hourly();
                employee.copy(e, employee);
                oldEmployee.add(employee);
            }
        }
    }

    public void clearRedo() {
        if(redo.size() > 0) {
            addUndo(redo.peek());
        }
        while(!redo.empty()) {
            redo.pop();
        }
    }


    public void addUndo(ArrayList<Employee> employees) {
        ArrayList<Employee> auxiliar = new ArrayList<>();
        copyArrayList(auxiliar, employees);
        undo.push(auxiliar);
    }

    public void addRedo() {
        ArrayList<Employee> auxiliar = new ArrayList<>();
        copyArrayList(auxiliar, undo.peek());
        redo.push(auxiliar);
    }

    public void removeUndo() {
        undo.pop();
    }

    public void removeRedo() {
        redo.pop();
    }

    public void doUndoRedo(ArrayList<Employee> employees) {
        int option, lastOption = 0;
        System.out.printf("(0) - Encerrar\n(1) - Undo\n(2) - Redo\n");
        option = input.nextInt();
        while(option != 0) {
            if(option == 1) {
                if (undo.size() > 1) {
                    addRedo();
                    removeUndo();
                    lastOption = 1;
                    System.out.println("Undo efetuado com sucesso!");
                } else {
                    System.out.println("Não foi possível realizar o Undo!");
                }
            }
            else if(option == 2) {
                if (redo.size() > 1) {
                    addUndo(redo.peek());
                    removeRedo();
                    lastOption = 2;
                    System.out.println("Redo efetuado com sucesso!");
                } else {
                    System.out.println("Não foi possível realizar o Redo!");
                }
            }

            System.out.printf("(0) - Encerrar\n(1) - Undo\n(2) - Redo\n");
            option = input.nextInt();
        }

        /*for(Employee e : undo.peek()) {
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

        System.out.println("Terminou o UNDO!");
        System.out.println("REDO SIZE: " + redo.size());
        if(redo.size() > 0) {
            for (Employee e : redo.peek()) {
                if (e instanceof Assalaried) {
                    System.out.println("Type: Assalaried");
                } else if (e instanceof Comissioned) {
                    System.out.println("Type: Comissioned");
                    System.out.printf("Commission: %.2f\n", ((Comissioned) e).getCommission());
                } else if (e instanceof Hourly) {
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
        }

        System.out.println("Terminou o REDO!");*/

        System.out.println("Undo/Redo encerrado!");
        if(lastOption == 1) copyArrayList(employees, undo.peek());
        else if(lastOption == 2) copyArrayList(employees, redo.peek());
    }
}
