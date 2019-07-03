import java.util.Scanner;

public class Excecao {
    Scanner input = new Scanner(System.in);

    public void tryString(String test) throws NullStringException {
        if(test.equals(null) || test.equals("")) {
            throw new NullStringException();
        }
    }

    public String loadString() {
        String auxiliar = "";
        boolean correctInput = true;

        while(correctInput) {
            try {
                auxiliar = input.nextLine();
                tryString(auxiliar);
                correctInput = false;
            } catch (NullStringException e) {
                System.out.println("Digite uma string válida!");
            }
        }

        return auxiliar;
    }


    public int loadInt() {
        int auxiliar = 0;
        boolean correctInput = true;

        while(correctInput) {
            try {
                auxiliar = Integer.parseInt(input.next());
                correctInput = false;
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido!");
            }
        }

        return auxiliar;
    }

    public double loadDouble() {
        double auxiliar = 0;
        boolean correctInput = true;

        while(correctInput) {
            try {
                auxiliar = Double.parseDouble(input.next());
                correctInput = false;
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido!");
            }
        }

        return auxiliar;
    }

}
