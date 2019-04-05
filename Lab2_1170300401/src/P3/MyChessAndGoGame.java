package P3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyChessAndGoGame {

    private final JFrame game = new JFrame("My Chess And Go Game");
    private final JPanel mainPanel = new JPanel();// Ö÷Ãæ°å
    private String Player1 = "player1";
    private String Player2 = "player2";
    private int ChessorGo = 0;// -1:chess 1:go
    private Player player1 = new Player(Player1);
    private Player player2 = new Player(Player2);
    private int count = 0;// ²Ù×÷Êý

    public void mainboardInit() {
        mainPanel.setLayout(new BorderLayout());
        game.add(mainPanel);
        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        Box vBox = Box.createHorizontalBox();
        Box eBox = Box.createVerticalBox();
        Box aBox = Box.createHorizontalBox();
        mainPanel.setLayout(new FlowLayout());
        Box text1 = Box.createVerticalBox();
        Box text2 = Box.createVerticalBox();
        text1.add(new JLabel("Input the game you want to play:(Please input chess or go)"));
        text1.add(new JLabel("Input player1's name:"));
        text1.add(new JLabel("Input player2's name:"));
        vBox.add(text1);
        JTextField chose = new JTextField(4);
        JTextField player1Field = new JTextField(10);
        JTextField player2Field = new JTextField(10);
        text2.add(chose);
        text2.add(player1Field);
        text2.add(player2Field);
        eBox.add(text2);
        JButton inputButton = new JButton("Begin your game!");
        aBox.add(inputButton);
        mainPanel.add(vBox);
        mainPanel.add(eBox);
        mainPanel.add(aBox);
        game.setLocation(300, 0);
        game.setSize(720, 780);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        inputButton.addActionListener(new ActionListener() {

            @Override public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String gameString = chose.getText();
                String p1 = player1Field.getText();
                String p2 = player2Field.getText();
                if ((!(gameString.toLowerCase().equals("chess") || gameString.toLowerCase().equals("go")))
                        || (!p1.isEmpty()&&!p2.isEmpty()&&(p1.equals(p2)))) {
                    JOptionPane.showMessageDialog(game, "Please check your input format", "The input is illegal",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    Player1 = p1.length() == 0 ? "player1" : p1;
                    Player2 = p2.length() == 0 ? "player2" : p2;
                    if (gameString.toLowerCase().equals("go")) {
                        ChessorGo = -1;
                    } else {
                        ChessorGo = 1;
                    }
                    player1 = new Player(Player1);
                    player2 = new Player(Player2);
                    if (ChessorGo == -1) {
                        chessBoard chessPanel = new chessBoard();
                        game.add(chessPanel);
                        game.setTitle("Go");
                        mainPanel.setVisible(false);
                        Box bBox = Box.createVerticalBox();
                        JButton putPieceButton = new JButton("put Piece");
                        JButton removeButton = new JButton("remove Piece");
                        JButton endButton = new JButton("end");
                        JButton skipButton = new JButton("skip");
                        bBox.add(putPieceButton);
                        bBox.add(removeButton);
                        Box funcBox = Box.createHorizontalBox();
                        funcBox.add(endButton);
                        funcBox.add(skipButton);
                        chessPanel.setLayout(new FlowLayout());
                        chessPanel.add(bBox);
                        bBox.setLocation(40, 650);
                        Box textBox = Box.createVerticalBox();
                        JTextField putTextField = new JTextField(3);
                        JTextField removeTextField = new JTextField(7);
                        textBox.add(putTextField);
                        textBox.add(removeTextField);
                        JTextField turnTextField = new JTextField(Player1);
                        chessPanel.add(turnTextField);
                        chessPanel.add(textBox);
                        chessPanel.add(funcBox, BorderLayout.CENTER);
                        Game newGame = new Game(ChessorGo, player1, player2);
                        putPieceButton.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                String str[] = putTextField.getText().split(" ");
                                int a[] = new int[str.length];
                                a[0] = Integer.parseInt(str[0]);
                                a[1] = Integer.parseInt(str[1]);
                                boolean flag = false;
                                if (!(count % 2 == 0 ? player1 : player2).isContainPiece(new Piece(1,
                                        (count % 2 == 0 ? player1 : player2).getName(), a[0] - 1, a[1] - 1))
                                        && !(count % 2 == 1 ? player1 : player2).isContainPiece(new Piece(1,
                                                (count % 2 == 1 ? player1 : player2).getName(), a[0] - 1, a[1] - 1)))
                                    flag = true;
                                if (a[0] >= 1 && a[0] <= 19 && a[1] >= 1 && a[1] <= 19 && str.length == 2 && flag) {
                                    newGame.putDownPiece(count % 2 == 0 ? player1 : player2,
                                            new Piece(1, count % 2 == 0 ? Player1 : Player2, a[0] - 1, a[1] - 1),
                                            new Position(a[0] - 1, a[1] - 1));
                                    chessPanel.paintChess((a[0] + 1) * 30, 700 - (a[1] + 1) * 30);
                                    count++;
                                } else
                                    JOptionPane.showMessageDialog(game, "Please check your input format",
                                            "The input is illegal", JOptionPane.WARNING_MESSAGE);
                                if (count % 2 == 0) {
                                    turnTextField.setText(Player1);
                                } else
                                    turnTextField.setText(Player2);

                            }
                        });
                        removeButton.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                String str[] = removeTextField.getText().split(" ");
                                int a[] = new int[str.length];
                                for (int i = 0; i < str.length; i++) {
                                    a[i] = Integer.parseInt(str[i]);
                                }
                                boolean flag = false;
                                if (!(count % 2 == 0 ? player1 : player2).isContainPiece(new Piece(1,
                                        (count % 2 == 0 ? player1 : player2).getName(), a[0] - 1, a[1] - 1))
                                        && (count % 2 == 1 ? player1 : player2).isContainPiece(new Piece(1,
                                                (count % 2 == 1 ? player1 : player2).getName(), a[0] - 1, a[1] - 1)))
                                    flag = true;
                                if (!(a[0] < 1 && a[0] >= 19 && a[1] < 1 && a[1] >= 19) && str.length == 2 && flag) {
                                    newGame.removePiece(count % 2 == 0 ? player1 : player2,
                                            new Position(a[0] - 1, a[1] - 1));
                                    chessPanel.paintRemove((a[0] + 1) * 30, 700 - (a[1] + 1) * 30);
                                    count++;
                                } else
                                    JOptionPane.showMessageDialog(game, "Please check your input format",
                                            "The input is illegal", JOptionPane.WARNING_MESSAGE);
                                if (count % 2 == 0) {
                                    turnTextField.setText(Player1);
                                } else
                                    turnTextField.setText(Player2);
                            }
                        });
                        endButton.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                JOptionPane.showMessageDialog(game,
                                        Player1 + " : " + player1.getGameHistoryBuilder() + "\n" + "have "
                                                + newGame.getNumOfPlayerPiecesInBoard(player1) + " pieces in board\n"
                                                + Player2 + " : " + player2.getGameHistoryBuilder() + "\n" + "have "
                                                + newGame.getNumOfPlayerPiecesInBoard(player2) + " pieces in board\n",
                                        "The information about the game", JOptionPane.PLAIN_MESSAGE);
                                System.exit(0);
                            }
                        });
                        skipButton.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                count++;
                                if (count % 2 == 0) {
                                    turnTextField.setText(Player1 + " :");
                                } else
                                    turnTextField.setText(Player2 + " : ");
                            }
                        });
                    } else {
                        goBoard goPanel = new goBoard();
                        goPanel.goboardInit();
                        mainPanel.setVisible(false);
                        game.setVisible(false);
                        goPanel.setVisible(true);
                    }
                }
            }
        });

    }

    class chessBoard extends JPanel {

        public void chessBoardInit() {
            // TODO Auto-generated constructor stub
            this.setVisible(true);
            this.setBorder(BorderFactory.createEtchedBorder());
            this.setSize(720, 700);
            this.setLocation(0, 0);
            this.setLayout(null);
        }

        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.ORANGE);
            g.fillRect(40, 80, 600, 600);
            g.setColor(Color.BLACK);
            for (int i = 80; i <= 680; i += 30) {
                g.drawLine(40, i, 640, i);
            }
            for (int j = 40; j <= 640; j += 30) {
                g.drawLine(j, 80, j, 680);
            }
        }

        public void paintChess(int x, int y) {
            Graphics g = this.getGraphics();
            if (count % 2 == 0) {
                g.setColor(Color.BLACK);
            } else
                g.setColor(Color.WHITE);
            g.fillOval(x, y, 20, 20);
        }

        public void paintRemove(int x, int y) {
            Graphics g = this.getGraphics();
            g.setColor(Color.ORANGE);
            g.fillOval(x, y, 20, 20);
            g.setColor(Color.BLACK);
            g.drawLine(x - 2, y + 10, x + 20, y + 10);
            g.drawLine(x + 10, y, x + 10, y + 20);
        }
    }

    class goBoard extends JFrame {

        private ImageIcon hei1 = new ImageIcon("src/P3/ºÚËþ1.jpg");
        private ImageIcon hei2 = new ImageIcon("src/P3/ºÚËþ2.jpg");
        private ImageIcon hei3 = new ImageIcon("src/P3/ºÚÂíÍ·1.jpg");
        private ImageIcon hei4 = new ImageIcon("src/P3/ºÚÂíÍ·2.jpg");
        private ImageIcon hei5 = new ImageIcon("src/P3/ºÚ¶ÜÅÆ1.jpg");
        private ImageIcon hei6 = new ImageIcon("src/P3/ºÚ¶ÜÅÆ2.jpg");
        private ImageIcon hei8 = new ImageIcon("src/P3/ºÚÍ·¹Ú.jpg");
        private ImageIcon hei7 = new ImageIcon("src/P3/ºÚÍõ¹Ú.jpg");
        private ImageIcon he1 = new ImageIcon("src/P3/ºÚÉ«ÆÕÍ¨Æå1.jpg");
        private ImageIcon he2 = new ImageIcon("src/P3/ºÚÉ«ÆÕÍ¨Æå2.jpg");
        private ImageIcon he3 = new ImageIcon("src/P3/ºÚÉ«ÆÕÍ¨Æå3.jpg");
        private ImageIcon he4 = new ImageIcon("src/P3/ºÚÉ«ÆÕÍ¨Æå4.jpg");
        private ImageIcon he5 = new ImageIcon("src/P3/ºÚÉ«ÆÕÍ¨Æå5.jpg");
        private ImageIcon he6 = new ImageIcon("src/P3/ºÚÉ«ÆÕÍ¨Æå6.jpg");
        private ImageIcon he7 = new ImageIcon("src/P3/ºÚÉ«ÆÕÍ¨Æå7.jpg");
        private ImageIcon he8 = new ImageIcon("src/P3/ºÚÉ«ÆÕÍ¨Æå8.jpg");
        private ImageIcon bai1 = new ImageIcon("src/P3/°×Ëþ1.jpg");
        private ImageIcon bai2 = new ImageIcon("src/P3/°×Ëþ1.jpg");
        private ImageIcon bai3 = new ImageIcon("src/P3/°×ÂíÍ·1.jpg");
        private ImageIcon bai4 = new ImageIcon("src/P3/°×ÂíÍ·2.jpg");
        private ImageIcon bai5 = new ImageIcon("src/P3/°×¶ÜÅÆ1.jpg");
        private ImageIcon bai6 = new ImageIcon("src/P3/°×¶ÜÅÆ2.jpg");
        private ImageIcon bai8 = new ImageIcon("src/P3/°×Í·¹Ú.jpg");
        private ImageIcon bai7 = new ImageIcon("src/P3/°×Íõ¹Ú.jpg");
        private ImageIcon ba1 = new ImageIcon("src/P3/°×É«ÆÕÍ¨Æå1.jpg");
        private ImageIcon ba2 = new ImageIcon("src/P3/°×É«ÆÕÍ¨Æå2.jpg");
        private ImageIcon ba3 = new ImageIcon("src/P3/°×É«ÆÕÍ¨Æå3.jpg");
        private ImageIcon ba4 = new ImageIcon("src/P3/°×É«ÆÕÍ¨Æå4.jpg");
        private ImageIcon ba5 = new ImageIcon("src/P3/°×É«ÆÕÍ¨Æå5.jpg");
        private ImageIcon ba6 = new ImageIcon("src/P3/°×É«ÆÕÍ¨Æå6.jpg");
        private ImageIcon ba7 = new ImageIcon("src/P3/°×É«ÆÕÍ¨Æå7.jpg");
        private ImageIcon ba8 = new ImageIcon("src/P3/°×É«ÆÕÍ¨Æå8.jpg");
        private JButton[][] goButtons = new JButton[8][8];

        public void goboardInit() {
            this.setVisible(true);
            this.setSize(720, 780);
            this.setLocation(300, 0);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Chess");
            goButtons[0][0] = new JButton(bai1);
            goButtons[0][7] = new JButton(bai2);
            goButtons[0][1] = new JButton(bai3);
            goButtons[0][6] = new JButton(bai4);
            goButtons[0][2] = new JButton(bai5);
            goButtons[0][5] = new JButton(bai6);
            goButtons[0][3] = new JButton(bai7);
            goButtons[0][4] = new JButton(bai8);
            goButtons[1][0] = new JButton(ba1);
            goButtons[1][1] = new JButton(ba2);
            goButtons[1][2] = new JButton(ba3);
            goButtons[1][3] = new JButton(ba4);
            goButtons[1][4] = new JButton(ba5);
            goButtons[1][5] = new JButton(ba6);
            goButtons[1][6] = new JButton(ba7);
            goButtons[1][7] = new JButton(ba8);

            goButtons[7][0] = new JButton(hei1);
            goButtons[7][7] = new JButton(hei2);
            goButtons[7][1] = new JButton(hei3);
            goButtons[7][6] = new JButton(hei4);
            goButtons[7][2] = new JButton(hei5);
            goButtons[7][5] = new JButton(hei6);
            goButtons[7][3] = new JButton(hei7);
            goButtons[7][4] = new JButton(hei8);
            goButtons[6][0] = new JButton(he1);
            goButtons[6][1] = new JButton(he2);
            goButtons[6][2] = new JButton(he3);
            goButtons[6][3] = new JButton(he4);
            goButtons[6][4] = new JButton(he5);
            goButtons[6][5] = new JButton(he6);
            goButtons[6][6] = new JButton(he7);
            goButtons[6][7] = new JButton(he8);
            for (int i = 2; i < 6; i++)
                for (int j = 0; j < 8; j++)
                    goButtons[i][j] = new JButton(" ");
            setIcon(hei1, goButtons[7][0]);
            setIcon(hei2, goButtons[7][7]);
            setIcon(hei3, goButtons[7][1]);
            setIcon(hei4, goButtons[7][6]);
            setIcon(hei5, goButtons[7][2]);
            setIcon(hei6, goButtons[7][5]);
            setIcon(hei7, goButtons[7][3]);
            setIcon(hei8, goButtons[7][4]);
            setIcon(he1, goButtons[6][0]);
            setIcon(he2, goButtons[6][1]);
            setIcon(he3, goButtons[6][2]);
            setIcon(he4, goButtons[6][3]);
            setIcon(he5, goButtons[6][4]);
            setIcon(he6, goButtons[6][5]);
            setIcon(he7, goButtons[6][6]);
            setIcon(he8, goButtons[6][7]);

            setIcon(bai1, goButtons[0][0]);
            setIcon(bai2, goButtons[0][7]);
            setIcon(bai3, goButtons[0][1]);
            setIcon(bai4, goButtons[0][6]);
            setIcon(bai5, goButtons[0][2]);
            setIcon(bai6, goButtons[0][5]);
            setIcon(bai7, goButtons[0][3]);
            setIcon(bai8, goButtons[0][4]);
            setIcon(ba1, goButtons[1][0]);
            setIcon(ba2, goButtons[1][1]);
            setIcon(ba3, goButtons[1][2]);
            setIcon(ba4, goButtons[1][3]);
            setIcon(ba5, goButtons[1][4]);
            setIcon(ba6, goButtons[1][5]);
            setIcon(ba7, goButtons[1][6]);
            setIcon(ba8, goButtons[1][7]);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (!goButtons[i][j].getText().equals(" ")) {
                        goButtons[i][j].setBounds(33 + (j * 80), 625 - (80 * i), 80, 80);
                        this.getLayeredPane().add(goButtons[i][j], Integer.MAX_VALUE);
                        goButtons[i][j].setVisible(true);
                    }
                }
            }
            Box bBox = Box.createVerticalBox();
            JButton movePieceButton = new JButton("move Piece");
            JButton eatButton = new JButton("eat Piece");
            JButton endButton = new JButton("end");
            JButton skipButton = new JButton("skip");
            bBox.add(movePieceButton);
            bBox.add(eatButton);
            Box funcBox = Box.createHorizontalBox();
            funcBox.add(endButton);
            funcBox.add(skipButton);
            this.setLayout(new FlowLayout());
            this.add(bBox);
            bBox.setLocation(40, 650);
            Box textBox = Box.createVerticalBox();
            JTextField moveTextField = new JTextField(3);
            JTextField eatTextField = new JTextField(7);
            textBox.add(moveTextField);
            textBox.add(eatTextField);
            JTextField turnTextField = new JTextField(Player1);
            this.add(turnTextField);
            this.add(textBox);
            this.add(funcBox, BorderLayout.CENTER);
            player1.addPiece(new Piece(1, Player1, 0, 7));
            player1.addPiece(new Piece(1, Player1, 7, 7));
            player1.addPiece(new Piece(1, Player1, 1, 7));
            player1.addPiece(new Piece(1, Player1, 6, 7));
            player1.addPiece(new Piece(1, Player1, 2, 7));
            player1.addPiece(new Piece(1, Player1, 5, 7));
            player1.addPiece(new Piece(1, Player1, 3, 7));
            player1.addPiece(new Piece(1, Player1, 4, 7));
            player1.addPiece(new Piece(1, Player1, 0, 6));
            player1.addPiece(new Piece(1, Player1, 7, 6));
            player1.addPiece(new Piece(1, Player1, 1, 6));
            player1.addPiece(new Piece(1, Player1, 6, 6));
            player1.addPiece(new Piece(1, Player1, 2, 6));
            player1.addPiece(new Piece(1, Player1, 5, 6));
            player1.addPiece(new Piece(1, Player1, 3, 6));
            player1.addPiece(new Piece(1, Player1, 4, 6));

            player2.addPiece(new Piece(1, Player2, 0, 0));
            player2.addPiece(new Piece(1, Player2, 7, 0));
            player2.addPiece(new Piece(1, Player2, 1, 0));
            player2.addPiece(new Piece(1, Player2, 6, 0));
            player2.addPiece(new Piece(1, Player2, 2, 0));
            player2.addPiece(new Piece(1, Player2, 5, 0));
            player2.addPiece(new Piece(1, Player2, 3, 0));
            player2.addPiece(new Piece(1, Player2, 4, 0));
            player2.addPiece(new Piece(1, Player2, 0, 1));
            player2.addPiece(new Piece(1, Player2, 7, 1));
            player2.addPiece(new Piece(1, Player2, 1, 1));
            player2.addPiece(new Piece(1, Player2, 6, 1));
            player2.addPiece(new Piece(1, Player2, 2, 1));
            player2.addPiece(new Piece(1, Player2, 5, 1));
            player2.addPiece(new Piece(1, Player2, 3, 1));
            player2.addPiece(new Piece(1, Player2, 4, 1));
            Game newGame = new Game(ChessorGo, player1, player2);

            movePieceButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    String[] move = moveTextField.getText().split(" ");
                    int a[] = new int[move.length];

                    for (int i = 0; i < move.length; i++) {
                        a[i] = Integer.parseInt(move[i]);
                    }
                    boolean flag = false;
                    if (!(count % 2 == 0 ? player1 : player2).isContainPiece(
                            new Piece(1, (count % 2 == 0 ? player1 : player2).getName(), a[3] - 1, a[2] - 1))
                            && !(count % 2 == 1 ? player1 : player2).isContainPiece(
                                    new Piece(1, (count % 2 == 1 ? player1 : player2).getName(), a[3] - 1, a[2] - 1))
                            && (count % 2 == 0 ? player1 : player2).isContainPiece(
                                    new Piece(1, (count % 2 == 0 ? player1 : player2).getName(), a[1] - 1, a[0] - 1))
                            && !(count % 2 == 1 ? player1 : player2).isContainPiece(
                                    new Piece(1, (count % 2 == 1 ? player1 : player2).getName(), a[1] - 1, a[0] - 1)))
                        flag = true;
                    if (!(a[0] < 1 && a[0] > 8 && a[1] < 1 && a[1] > 8) && move.length == 4
                            && moveTextField.getText() != null && flag) {
                        newGame.movePiece(count % 2 == 0 ? player1 : player2, new Position(a[1] - 1, a[0] - 1),
                                new Position(a[3] - 1, a[2] - 1));
                        goButtons[a[1] - 1][a[0] - 1].setLocation(33 + 80 * a[2] - 80, 625 - 80 * a[3] + 80);
                        JButton tempButton = new JButton(" ");
                        tempButton = goButtons[a[3] - 1][a[2] - 1];
                        goButtons[a[3] - 1][a[2] - 1] = goButtons[a[1] - 1][a[0] - 1];
                        goButtons[a[1] - 1][a[0] - 1] = tempButton;
                        count++;
                        repaint();
                    } else
                        JOptionPane.showMessageDialog(game, "Please check your input format", "The input is illegal",
                                JOptionPane.WARNING_MESSAGE);
                    if (count % 2 == 0) {
                        turnTextField.setText(Player1);
                    } else
                        turnTextField.setText(Player2);
                }
            });
            eatButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    String[] eat = eatTextField.getText().split(" ");
                    int a[] = new int[eat.length];
                    for (int i = 0; i < eat.length; i++) {
                        a[i] = Integer.parseInt(eat[i]);
                    }
                    boolean flag = false;
                    if (!(count % 2 == 0 ? player1 : player2).isContainPiece(
                            new Piece(1, (count % 2 == 0 ? player1 : player2).getName(), a[3] - 1, a[2] - 1))
                            && (count % 2 == 1 ? player1 : player2).isContainPiece(
                                    new Piece(1, (count % 2 == 1 ? player1 : player2).getName(), a[3] - 1, a[2] - 1))
                            && (count % 2 == 0 ? player1 : player2).isContainPiece(
                                    new Piece(1, (count % 2 == 0 ? player1 : player2).getName(), a[1] - 1, a[0] - 1))
                            && !(count % 2 == 1 ? player1 : player2).isContainPiece(
                                    new Piece(1, (count % 2 == 1 ? player1 : player2).getName(), a[1] - 1, a[0] - 1)))
                        flag = true;
                    if (!(a[0] < 1 && a[0] > 8 && a[1] < 1 && a[1] > 8) && eat.length == 4 && flag) {
                        newGame.eatPiece(count % 2 == 0 ? player1 : player2, new Position(a[1] - 1, a[0] - 1),
                                new Position(a[3] - 1, a[2] - 1));
                        goButtons[a[3] - 1][a[2] - 1] = new JButton(" ");
                        goButtons[a[1] - 1][a[0] - 1].setLocation(33 + 80 * a[2] - 80, 625 - 80 * a[3] + 80);
                        JButton tempButton = new JButton(" ");
                        tempButton = goButtons[a[3] - 1][a[2] - 1];
                        goButtons[a[3] - 1][a[2] - 1] = goButtons[a[1] - 1][a[0] - 1];
                        goButtons[a[1] - 1][a[0] - 1] = tempButton;
                        count++;
                        repaint();
                    } else
                        JOptionPane.showMessageDialog(game, "Please check your input format", "The input is illegal",
                                JOptionPane.WARNING_MESSAGE);
                    if (count % 2 == 0) {
                        turnTextField.setText(Player1);
                    } else
                        turnTextField.setText(Player2);
                }
            });
            skipButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    count++;
                    if (count % 2 == 0) {
                        turnTextField.setText(Player1);
                    } else
                        turnTextField.setText(Player2);
                }
            });
            endButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    JOptionPane.showMessageDialog(game,
                            Player1 + " : " + player1.getGameHistoryBuilder() + "\n" + "\thave "
                                    + newGame.getNumOfPlayerPiecesInBoard(player1) + " pieces in board\n" + Player2
                                    + " : " + player2.getGameHistoryBuilder() + "\n" + "\thave "
                                    + newGame.getNumOfPlayerPiecesInBoard(player2) + " pieces in board\n",
                            "The information about the game", JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
                }
            });
        }

        public void setIcon(ImageIcon icon, JButton iconButton) {
            Image temp = icon.getImage().getScaledInstance(80, 80, icon.getImage().SCALE_DEFAULT);
            icon = new ImageIcon(temp);
            iconButton.setIcon(icon);
        }

        @Override public void paint(Graphics g) {
            // TODO Auto-generated method stub
            super.paint(g);
            g.setColor(Color.BLACK);
            g.fillRect(40, 95, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(120, 95, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(200, 95, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(280, 95, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(360, 95, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(440, 95, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(520, 95, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(600, 95, 80, 80);

            g.setColor(Color.WHITE);
            g.fillRect(40, 175, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(120, 175, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(200, 175, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(280, 175, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(360, 175, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(440, 175, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(520, 175, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(600, 175, 80, 80);

            g.setColor(Color.BLACK);
            g.fillRect(40, 255, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(120, 255, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(200, 255, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(280, 255, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(360, 255, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(440, 255, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(520, 255, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(600, 255, 80, 80);

            g.setColor(Color.WHITE);
            g.fillRect(40, 335, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(120, 335, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(200, 335, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(280, 335, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(360, 335, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(440, 335, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(520, 335, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(600, 335, 80, 80);

            g.setColor(Color.BLACK);
            g.fillRect(40, 415, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(120, 415, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(200, 415, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(280, 415, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(360, 415, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(440, 415, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(520, 415, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(600, 415, 80, 80);

            g.setColor(Color.WHITE);
            g.fillRect(40, 495, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(120, 495, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(200, 495, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(280, 495, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(360, 495, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(440, 495, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(520, 495, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(600, 495, 80, 80);

            g.setColor(Color.BLACK);
            g.fillRect(40, 575, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(120, 575, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(200, 575, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(280, 575, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(360, 575, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(440, 575, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(520, 575, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(600, 575, 80, 80);

            g.setColor(Color.WHITE);
            g.fillRect(40, 655, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(120, 655, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(200, 655, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(280, 655, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(360, 655, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(440, 655, 80, 80);
            g.setColor(Color.WHITE);
            g.fillRect(520, 655, 80, 80);
            g.setColor(Color.BLACK);
            g.fillRect(600, 655, 80, 80);

            g.setColor(Color.BLACK);
            g.drawRect(40, 95, 640, 640);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (!goButtons[i][j].getText().equals(" ")) {
                        goButtons[i][j].repaint();
                    } else
                        goButtons[i][j].setOpaque(false);
                }
            }
        }

    }

    public static void main(String[] args) {
        MyChessAndGoGame board = new MyChessAndGoGame();
        board.mainboardInit();

    }
}
