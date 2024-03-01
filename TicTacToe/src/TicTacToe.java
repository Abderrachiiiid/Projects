import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    TicTacToe() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 800);
        this.frame.getContentPane().setBackground(new Color(50, 50, 50));
        this.frame.setLayout(new BorderLayout());
        this.frame.setVisible(true);

        this.textField.setBackground(new Color(25, 25, 25));
        this.textField.setForeground(new Color(25, 255, 0));
        this.textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        this.textField.setHorizontalAlignment(JLabel.CENTER);
        this.textField.setText("Tic-Tac-Toe");
        this.textField.setOpaque(true);

        this.title_panel.setLayout(new BorderLayout());
        this.title_panel.setBounds(0, 0, 800, 100);

        this.button_panel.setLayout(new GridLayout(3, 3));
        this.button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            this.buttons[i] = new JButton();
            this.button_panel.add(this.buttons[i]);
            this.buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            this.buttons[i].addActionListener(this);
        }

        this.title_panel.add(this.textField);
        this.frame.add(this.title_panel, BorderLayout.NORTH);
        this.frame.add(this.button_panel);

        this.firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == this.buttons[i]) {
                if (this.player1_turn) {
                    if (this.buttons[i].getText() == "") {
                        this.buttons[i].setForeground(new Color(255, 0, 0));
                        this.buttons[i].setText("X");
                        this.player1_turn = false;
                        this.textField.setText("O turn");
                        this.check();
                    }
                } else {
                    if (this.buttons[i].getText() == "") {
                        this.buttons[i].setForeground(new Color(0, 0, 255));
                        this.buttons[i].setText("O");
                        this.player1_turn = true;
                        this.textField.setText("X turn");
                        this.check();
                    }
                }
            }
        }

    }

    public void firstTurn() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.random.nextInt(2) == 0) {
            this.player1_turn = true;
            this.textField.setText("X turn");
        } else {
            this.player1_turn = false;
            this.textField.setText("O turn");
        }
    }

    public void check() {

        if ((this.buttons[0].getText() == "X")
                && (this.buttons[1].getText() == "X")
                && (this.buttons[2].getText() == "X")) {
            this.xWins(0, 1, 2);
        }
        if ((this.buttons[3].getText() == "X")
                && (this.buttons[4].getText() == "X")
                && (this.buttons[5].getText() == "X")) {
            this.xWins(3, 4, 5);
        }
        if ((this.buttons[6].getText() == "X")
                && (this.buttons[7].getText() == "X")
                && (this.buttons[8].getText() == "X")) {
            this.xWins(6, 7, 8);
        }
        if ((this.buttons[0].getText() == "X")
                && (this.buttons[4].getText() == "X")
                && (this.buttons[8].getText() == "X")) {
            this.xWins(0, 4, 8);
        }
        if ((this.buttons[2].getText() == "X")
                && (this.buttons[4].getText() == "X")
                && (this.buttons[6].getText() == "X")) {
            this.xWins(2, 4, 6);
        }
        if ((this.buttons[0].getText() == "X")
                && (this.buttons[3].getText() == "X")
                && (this.buttons[6].getText() == "X")) {
            this.xWins(0, 3, 6);
        }
        if ((this.buttons[1].getText() == "X")
                && (this.buttons[4].getText() == "X")
                && (this.buttons[7].getText() == "X")) {
            this.xWins(6, 7, 8);
        }
        if ((this.buttons[2].getText() == "X")
                && (this.buttons[5].getText() == "X")
                && (this.buttons[8].getText() == "X")) {
            this.xWins(2, 5, 8);
        }

        if ((this.buttons[0].getText() == "O")
                && (this.buttons[1].getText() == "O")
                && (this.buttons[2].getText() == "O")) {
            this.oWins(0, 1, 2);
        }
        if ((this.buttons[3].getText() == "O")
                && (this.buttons[4].getText() == "O")
                && (this.buttons[5].getText() == "O")) {
            this.oWins(3, 4, 5);
        }
        if ((this.buttons[6].getText() == "O")
                && (this.buttons[7].getText() == "O")
                && (this.buttons[8].getText() == "O")) {
            this.oWins(6, 7, 8);
        }
        if ((this.buttons[0].getText() == "O")
                && (this.buttons[4].getText() == "O")
                && (this.buttons[8].getText() == "O")) {
            this.oWins(0, 4, 8);
        }
        if ((this.buttons[2].getText() == "O")
                && (this.buttons[4].getText() == "O")
                && (this.buttons[6].getText() == "O")) {
            this.oWins(2, 4, 6);
        }
        if ((this.buttons[0].getText() == "O")
                && (this.buttons[3].getText() == "O")
                && (this.buttons[6].getText() == "O")) {
            this.oWins(0, 3, 6);
        }
        if ((this.buttons[1].getText() == "O")
                && (this.buttons[4].getText() == "O")
                && (this.buttons[7].getText() == "O")) {
            this.oWins(6, 7, 8);
        }
        if ((this.buttons[2].getText() == "O")
                && (this.buttons[5].getText() == "O")
                && (this.buttons[8].getText() == "O")) {
            this.oWins(2, 5, 8);
        }

    }

    public void xWins(int a, int b, int c) {
        this.buttons[a].setBackground(Color.GREEN);
        this.buttons[b].setBackground(Color.GREEN);
        this.buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            this.buttons[i].setEnabled(false);
        }
        this.textField.setText("X Wins");
    }

    public void oWins(int a, int b, int c) {
        this.buttons[a].setBackground(Color.GREEN);
        this.buttons[b].setBackground(Color.GREEN);
        this.buttons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            this.buttons[i].setEnabled(false);
        }
        this.textField.setText("O Wins");
    }

}
