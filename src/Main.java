import  java.util.Scanner;
public class Main {

    /////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        int  width ,leng,xplayer=-1 ,yplayer=-1,xflag=-1,yflag=-1;
        Scanner input=new Scanner(System.in);
        System.out.println("enter the width of the label");
        width=input.nextInt();
        System.out.println("enter the length of the label");
        leng=input.nextInt();
        while(xplayer==-1){
            System.out.println("enter player x position");
            int i=input.nextInt();
            if(i < width && i >=0){
                xplayer=i;
            }
            else{
                System.out.println("error position try again");
            }
        }
        while(yplayer==-1){
            System.out.println("enter player y position");
            int iii=input.nextInt();
            if(iii< leng   && iii >=0){
                yplayer=iii;
            }
            else{
                System.out.println("error position try again");
            }
        }
        while(xflag==-1){
            System.out.println("enter flag x position");
            int ii=input.nextInt();
            if(ii< width && ii >=0){
                xflag=ii;
            }
            else{
                System.out.println("error position try again");
            }
        }
        while(yflag==-1){
            System.out.println("enter flag y position");
            int ii=input.nextInt();
            if(ii< leng && ii >=0){
                yflag=ii;
            }
            else{
                System.out.println("error position try again");
            }
        }
        gamestucture label =new gamestucture(width,leng,xplayer,yplayer,xflag,yflag);
        for(int i=0;i<(width+leng);i++) {
            System.out.println("enter vacuum x label");
            int j = input.nextInt();
            System.out.println("enter vacuum y label");
            int jj = input.nextInt();
            if (j < 0 || j >= width || jj < 0 || jj >= leng || (j == label.playerx && jj == label.playery) || (j == label.flagx && jj == label.flagy)) {
                System.out.println("error position try again");
                continue;
            }
            label.setvacuum(j, jj);
            System.out.println("add another vacuum?  no = press 0 ,yes =press any num,");
            int jjj = input.nextInt();
            if (jjj == 0) {
                break;
            }
        }
        for (int i = 0; i < (width + leng); i++) {
            System.out.println("add  block?  no = press 0 ,yes =press any num,");
            int jjj = input.nextInt();
            if (jjj == 0) {
                break;
            }
            System.out.println("enter block x label");
            int j = input.nextInt();
            System.out.println("enter block y label");
            int jj = input.nextInt();
            if (j < 0 || j >= width || jj < 0 || jj >= leng) {
                System.out.println("error position try again");

                continue;
            }
            label.setblock(j, jj);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////
        label.ground[label.playerx][label.playery] = label.ground[label.playerx][label.playery] - 1;
        gamestucture label2 =new gamestucture(width,leng,xplayer,yplayer,xflag,yflag);
        gamestucture label3 =new gamestucture(width,leng,xplayer,yplayer,xflag,yflag);
        gamestucture label4 =new gamestucture(width,leng,xplayer,yplayer,xflag,yflag);
        gamestucture label5 =new gamestucture(width,leng,xplayer,yplayer,xflag,yflag);
        for (int i = 0; i < width; i++) {
            for (int ii = 0; ii < leng; ii++) {
                label2.ground[i][ii] = label.ground[i][ii];
                label3.ground[i][ii] = label.ground[i][ii];
                label4.ground[i][ii] = label.ground[i][ii];
                label5.ground[i][ii] = label.ground[i][ii];
            }
        }
        gamelogic start = new gamelogic(label);
        start.printlabel(label);
        int again=0;
        do{
            start.fcost=0;start.s=0;start.stop=0;start.move =true;start.con=0;start.bfs=1;start.stat=0 ;
            System.out.println("select   1:usermod  2:Dfs  3:Bfs   4 ucs  5 Astar");
            int player=input.nextInt();
            switch (player){
                case 1:
                    start.usermode(label);
                    System.out.println("press 1 to solve it again");
                    again=input.nextInt();
                    break;
                case 2:
                    start.dfs(label2);
                    System.out.println("press 1 to solve it again");
                    again=input.nextInt();
                    break;
                case 3:
                    start.bfs(label3);
                    System.out.println("press 1 to solve it again");
                    again=input.nextInt();
                    break;
                case 4:
                    start.ucs(label4);
                    System.out.println("press 1 to solve it again");
                    again=input.nextInt();
                    break;
                case 5:
                    System.out.println("enter heuristic num 1//2//3//4//5");
                    int nn=input.nextInt();
                    start.Astar(label5,nn);
                    break;
                default:
                    System.out.println("try again");
            }}
        while (again==1);
    }
}
