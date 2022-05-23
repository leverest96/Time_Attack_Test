package DahyeVer;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

abstract class Player {
    private String name;
    private String initNumber;
    private List<String> fightList = new LinkedList<String>();
    private Boolean isEnd;

    public Player(String name, String initNumber) {
        this.name = name;
        this.initNumber = initNumber;
        this.isEnd = false;
    }

    public abstract void play(String number) throws Exception;

    public void addFightList(String number) {
        fightList.add(number);
    }

    public Boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Boolean isEnd) {
        this.isEnd = isEnd;
    }

    public String getInitNumber() {
        return initNumber;
    }

    public String getName() {
        return name;
    }

    public void printFightList() {
        // TODO
        System.out.println(name);
        System.out.println("------------------------------------------");
        for (int i = 0; i < fightList.size(); i++) {
            System.out.println((i + 1) + " : " + fightList.get(i));
        }
        System.out.println("------------------------------------------");
    }

    public boolean check(String number) {
        if (number.length() != 4) {
            System.out.println("Invalid input(length 4). The opportunity passes to the next team");
            return false;
        }
        if (!number.matches("-?\\d+(\\.\\d+)?")) {
            System.out.println("Invalid input(Only Numeric). The opportunity passes to the next team");
            return false;
        }

        int[] num = new int[10];

        for (int i = 0; i < number.length(); i++) {
            int index = number.charAt(i) - '0';
            if (num[index] == 1) {
                System.out.println("Invalid input(same number). The opportunity passes to the next team");
                return false;
            } else {
                num[index] = 1;
            }
        }
        return true;
    }

}

class PlayerATeam extends Player {
    public PlayerATeam(String name, String number) {
        super(name, number);
    }

    @Override
    public void play(String number) throws Exception {
        int strike = 0;
        int ball = 0;
        int out = 0;

        // TODO
        String correct = getInitNumber();

        if (!check(number)) {
            return;
        }

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == correct.charAt(i)) {
                strike++;
            } else {
                if (!correct.contains(String.valueOf(number.charAt(i)))) {
                    out++;
                }
            }
        }

        ball = 4 - strike - out;

        String fight = number + ": Strike : " + strike + ", Ball : " + ball + ", Out : " + out;
        addFightList(fight);

        if (strike == 4) {
            System.out.println("Congratulation!");
            super.setIsEnd(true);
        } else {
            System.out.println("Strike : " + strike + ", Ball :" + ball + ", Out: " + out);
        }
    }
}

class PlayerBTeam extends Player {
    public PlayerBTeam(String name, String number) {
        super(name, number);
    }

    @Override
    public void play(String number) throws Exception {
        int strike = 0;
        int ball = 0;
        int out = 0;

        // TODO
        String correct = getInitNumber();

        if (!check(number)) {
            return;
        }

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == correct.charAt(i)) {
                ball++;
            } else {
                if (!correct.contains(String.valueOf(number.charAt(i)))) {
                    out++;
                }
            }
        }

        strike = 4 - ball - out;

        String fight = number + ": Strike : " + strike + ", Ball : " + ball + ", Out:" + out;
        addFightList(fight);

        if (ball == 4) {
            System.out.println("Congratulation!");
            super.setIsEnd(true);
        } else {
            System.out.println("Strike : " + strike + ", Ball :" + ball + ", Out: " + out);
        }
    }
}

class PlayerCTeam extends Player {
    public PlayerCTeam(String name, String number) {
        super(name, number);
    }

    @Override
    public void play(String number) throws Exception {
        int strike = 0;
        int ball = 0;
        int out = 0;

        // TODO
        String correct = getInitNumber();

        if (!check(number)) {
            return;
        }

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == correct.charAt(i)) {
                strike++;
            } else {
                if (!correct.contains(String.valueOf(number.charAt(i)))) {
                    out++;
                }
            }
        }

        ball = 4 - strike - out;

        String fight = number + ": Strike : " + strike + ", Ball : " + ball + ", Out : " + out;
        addFightList(fight);

        if (strike >= 3) {
            System.out.println("Congratulation!");
            super.setIsEnd(true);
        } else {
            System.out.println("Strike : " + strike + ", Ball :" + ball + ", Out: " + out);
        }
    }
}

public class DahyeVer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player playerArray[] = {new PlayerATeam("A Team", "1234"), new PlayerBTeam("B Team", "5678"), new PlayerCTeam("C Team", "7381")};
        int checkPlayer = 0;

        while (true) {
            checkPlayer = 0;
            for (Player player : playerArray) {
                if (player.getIsEnd() == false) {
                    System.out.print("Please enter a 4 digit number (" + player.getName() + ") defence : ");
                    String number = scanner.nextLine();
                    try {
                        player.play(number);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    checkPlayer++;
                }
            }
            if (checkPlayer == playerArray.length) break;
        }

        for (Player player : playerArray) {
            player.printFightList();
        }
    }
}