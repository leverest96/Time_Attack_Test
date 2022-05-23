package SijunVer;

class PlayerBTeam extends Player
{
    public PlayerBTeam(String name, String number) {
        super(name, number);
    }
    @Override
    public void play(String number) throws Exception {
        int strike = 0;
        int ball = 0;
        int out = 0;
        if(number.length()!=4){
            throw new IllegalArgumentException("Invalid input(length 4). The opportunity passes to the next team.");
        }
        boolean chkNumber[] = new boolean[10];
        for(int i=0;i<4;i++){
            char c = number.charAt(i);
            if(!('0'<=c&&c<='9')){
                throw new IllegalArgumentException("Invalid input(Only Numeric). The opportunity passes to the next team.");
            }
            if(chkNumber[c-'0']){
                throw new IllegalArgumentException("Invalid input(same number). The opportunity passes to the next team.");
            }
            chkNumber[c-'0'] = true;
        }
        // TODO
        String nowNumber  =  super.getInitNumber();
        int aCnt[] = new int[10];
        int bCnt[] = new int[10];
        boolean chk[] = new boolean[4];
        for(int i=0;i<4;i++){
            if(nowNumber.charAt(i) == number.charAt(i)){
                strike++;
                chk[i] = true;
            }
        }
        for(int i=0;i<4;i++){
            if(chk[i] ) continue;
            aCnt[nowNumber.charAt(i)-'0']++;
            bCnt[number.charAt(i)-'0']++;
        }
        for(int i=0;i<10;i++){
            if(aCnt[i]>0&&bCnt[i]>0){
                ball+=Math.min(aCnt[i],bCnt[i]);
            }
        }
        out = 4-strike-ball;
        this.addFightList(new Baseball(this.getName(),number, ball, strike, out));
        if(strike == 4) {
            System.out.println("Congratulation!");
            super.setIsEnd(true);
        } else {
            System.out.println("Strike : "+ball+", Ball :"+strike+", Out: "+out);
        }
    }
}