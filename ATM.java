import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        JFrame frame = new JFrame("My ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null); // Center the frame

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JButton withdrawButton = new JButton("Withdraw Money");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        panel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit Money");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        panel.add(depositButton);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        panel.add(checkBalanceButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank you for using My ATM. Have a great day!");
                System.exit(0);
            }
        });
        panel.add(exitButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void withdraw() {
        String input = JOptionPane.showInputDialog("Enter the amount to withdraw:");
        if (input != null) {
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0 && amount <= account.getBalance()) {
                    account.withdraw(amount);
                    JOptionPane.showMessageDialog(null, "Withdrawal successful.\nRemaining balance: " + account.getBalance() +"\u20B9");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid amount or insufficient funds.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid amount.");
            }
        }
    }

    private void deposit() {
        String input = JOptionPane.showInputDialog("Enter the amount to deposit:");
        if (input != null) {
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) {
                    account.deposit(amount);
                    JOptionPane.showMessageDialog(null, "Deposit successful.\nNew balance: " + account.getBalance() +"\u20B9");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid amount.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid amount.");
            }
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(null, "Your current balance is: " + account.getBalance() + "\u20B9");
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1500);
        ATM atm = new ATM(account);
        atm.start();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
