import java.util.Scanner;

class Calculator {
    private Scanner scanner;

    public Calculator() {
        scanner = new Scanner(System.in);
    }

    public double add(double a, double b) {
        return a + b;
    }
	
    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }

    public double getNumberInput(String prompt) {
        double number = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                number = scanner.nextDouble();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
        return number;
    }

    public char getOperationInput() {
        char operation = ' ';
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter operation (+, -, *, /): ");
            String input = scanner.next();
            if (input.length() == 1) {
                operation = input.charAt(0);
                if (operation == '+' || operation == '-' || operation == '*' || operation == '/') {
                    validInput = true;
                } else {
                    System.out.println("Invalid operation. Please enter +, -, *, or /.");
                }
            } else {
                System.out.println("Please enter a single character.");
            }
        }
        return operation;
    }

    public double calculate(double a, double b, char operation) {
        switch (operation) {
            case '+':
                return add(a, b);
            case '-':
                return subtract(a, b);
            case '*':
                return multiply(a, b);
            case '/':
                return divide(a, b);
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
    }
    public void run() {
        System.out.println("Welcome to Java Calculator!");
        
        boolean continueCalculating = true;
        
        while (continueCalculating) {
            double firstNumber = getNumberInput("Enter first number: ");
            char operation = getOperationInput();
            double secondNumber = getNumberInput("Enter second number: ");
            
            try {
                double result = calculate(firstNumber, secondNumber, operation);
                System.out.printf("%.2f %c %.2f = %.2f%n", firstNumber, operation, secondNumber, result);
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.print("Do you want to perform another calculation? (y/n): ");
            char choice = scanner.next().charAt(0);
            continueCalculating = (choice == 'y' || choice == 'Y');
        }
        
        System.out.println("Thank you for using Java Calculator!");
        scanner.close();
    }
	
	
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }
}