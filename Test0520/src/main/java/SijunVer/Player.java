package SijunVer;

import java.util.LinkedList;
import java.util.List;

abstract class Player{
    private String name;
    private String initNumber;
    private List<Baseball> fightList = new LinkedList<>();
    private Boolean isEnd;
    public Player(String name, String initNumber) {
        this.name = name;
        this.initNumber = initNumber;
        this.isEnd = false;
    }
    public abstract void play(String number) throws Exception;
    public void addFightList(Baseball baseball) {
        fightList.add(baseball);
    }
    public Boolean getIsEnd() { return isEnd; }
    public void setIsEnd(Boolean isEnd) { this.isEnd = isEnd; }
    public String getInitNumber() { return initNumber; }
    public String getName() { return name; }
    public void printFightList() {
        System.out.println(this.getName()+" Team");
        System.out.println("-------------------------------");
        int idx = 1;
        for(Baseball baseball : this.fightList){
            System.out.print(idx++);
            if(baseball.getTeam().equals(this.getName())){
                System.out.println(baseball);
            }
        }
        System.out.println("-------------------------------");
    }

}