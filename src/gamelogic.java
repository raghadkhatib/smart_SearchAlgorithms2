import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;
import java.util.PriorityQueue;
public class gamelogic {
    Date date;
    int    rp,cp,rf,cf,labelwid,labellen,fx,fy,fcost=0,fatherindex,s=0,wa,ww,stop=0,nm;
    boolean win,los,move =true;
    SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss s");
    static int con=0,bfs=1,stat=0 ;
    Scanner input=new Scanner(System.in);
    String mov;
    gamestucture[] qeu=new gamestucture[10000000];
    int result[]=new int[102];
    PriorityQueue<gamestucture> priorityq=new PriorityQueue<gamestucture>();

    public gamelogic() {
    }
    public gamelogic(gamestucture label) {
        labelwid = label.wide;
        labellen = label.leng;
        rf = label.flagx;
        cf = label.flagy;
        rp = label.playerx;
        cp= label.playery;
    }
    //////////////////////////////////////////////////////////////
    public boolean canmove(int r,int c,gamestucture node) {
        if (node.ground[r][c] ==1 || node.ground[r][c] ==2 ) {
            return true;
        }
        return false;

    }
    //////////////////////////////////////////////////////////
    public void printlabel(gamestucture node) {
        for (int i = 0; i < labelwid; i++) {
            for (int ii = 0; ii < labellen; ii++) {
                if(node.playerx==i && node.playery==ii){
                    System.out.print("p  ");
                }
                else if(rf==i && cf==ii){
                    System.out.print("f  ");
                }
                else{
                    System.out.print(node.ground[i][ii]+"  ");
                }
            }
            System.out.println();
        }
    }
    ////////////////////////////////////////////////
    public boolean the_end(gamestucture node) {
        if (node.playerx == node.flagx && node.playery == node.flagy) {
            for (int i = 0; i < labelwid; i++) {
                for (int ii = 0; ii < labellen; ii++) {
                    if (node.ground[i][ii] != 0) {
                            return false;
                    }
                }
            }
            System.out.println("THE END , YOU WIN");
            move=true;
            return true;
        }
        return false;
    }
    ////////////////////////////////////////////////////
    public boolean lose(gamestucture node) {
        if(node.playerx >0){
            if(node.ground[node.playerx-1][node.playery] != 0) {
                return false;
            }
        }
        if (node.playerx < labelwid-1) {
            if (node.ground[node.playerx + 1][node.playery] != 0) {
                return false;
            }
        }
        if(node.playery >0) {
            if(node.ground[node.playerx][(node.playery-1)] != 0 ) {
                return false;
            }
        }
        if(node.playery< labellen-1) {
            if (node.ground[node.playerx][node.playery + 1] != 0) {
                return false;
            }
        }
        move=true;
        return true;
    }
    ////////////////////////////////////////////////////////////////////////////////////
    public gamestucture nextl(int xx, int yy,gamestucture nod,gamestucture old) {
        nod.playerx = xx;
        nod.playery = yy;
        for (int i = 0; i < labelwid; i++) {
            for (int ii = 0; ii < labellen; ii++) {
                nod.ground[i][ii] = old.ground[i][ii];
            }
        }
        nod.ground[nod.playerx][nod.playery] =  nod.ground[nod.playerx][nod.playery]- 1;
        return nod;
    }
    /////////////////////////////////////////////////////////////////////////////////
    public  boolean equalfun(gamestucture son){
        for(int rr=0;rr<con;rr++){
        if(son.playerx==qeu[rr].playerx){
            if(son.playery==qeu[rr].playery){
                for (int i = 0; i < labelwid; i++) {
                    for (int ii = 0; ii < labellen; ii++) {
                        if(son.ground[i][ii]!=qeu[rr].ground[i][ii]){
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        }}
        return false;
    }
    ///////////////////////////////////////////////////////////////////////////////////
    public boolean bfs (gamestucture parant){
        Date datestart =new Date();
            qeu[con] = new gamestucture(labelwid,labellen,parant.playerx,parant.playery,rf,cf);
        qeu[con].id=con;
      do{  fx=parant.playerx;
        fy= parant.playery;
          fatherindex=parant.id;
        /////////////////////////////////////////////////////////up
        if(parant.playerx>0){
            if(canmove(fx-1, fy ,parant)) {
                System.out.println(" uppppppppp");
                con+=1;
                qeu[con] = new gamestucture(labelwid,labellen,fx-1,fy,rf,cf);
                qeu[con] =nextl(fx-1, fy ,qeu[con],parant);
                if(!equalfun(qeu[con])){
                win=the_end( qeu[con] );
                los=lose( qeu[con] );
                    qeu[con].father=fatherindex;
                    qeu[con].id=con;
                if(win){
                    stop=con;
                }
                if(los){
                    con-=1;}
            }else{
                    con-=1;
                }
            }}
        ////////////////////////////////////////////////////////////right
        if(parant.playery<labellen-1){
            if(canmove(fx, fy+1 ,parant)) {
                System.out.println(" rigاt");
                con+=1;
                qeu[con] = new gamestucture(labelwid,labellen,fx,fy+1,rf,cf);
                qeu[con] =nextl(fx, fy+1 ,qeu[con],parant);
                if(!equalfun(qeu[con])){
                win=the_end( qeu[con] );
                los=lose( qeu[con] );
                    qeu[con].father=fatherindex;
                    qeu[con].id=con;
                if(win){
                    stop=con;
                }
                if(los){
                    con-=1;}
            }
            else{con-=1;
            }
            }}
        ////////////////////////////////////////////////////////////down
        if(parant.playerx<labelwid-1){
            if(canmove(fx+1, fy ,parant)) {
                System.out.println("  down");
                con+=1;
                qeu[con] = new gamestucture(labelwid,labellen,fx+1,fy,rf,cf);
                qeu[con] =nextl(fx+1, fy ,qeu[con],parant);
                if(!equalfun(qeu[con])){
                win=the_end( qeu[con] );
                los=lose( qeu[con] );
                    qeu[con].father=fatherindex;
                    qeu[con].id=con;
                if(win){
                    stop=con;
                }
                if(los){
                    con-=1;}

            }else{
                    con-=1;
                }
            }}
        if(parant.playery>0){
            if(canmove(fx, fy - 1,parant)) {
                System.out.println("  ,A=left");
                con+=1;
                qeu[con] = new gamestucture(labelwid,labellen,fx,fy-1,rf,cf);
                qeu[con] =nextl(fx, fy - 1,qeu[con],parant);
                if(!equalfun(qeu[con])){
                win=the_end( qeu[con] );
                los=lose( qeu[con] );
                    qeu[con].father=fatherindex;
                    qeu[con].id=con;
                if( win){
                    stop=con;
                }
                if(los){
                    con-=1;}
            }else{
                    con-=1;
                }
            }}

              parant=qeu[bfs];
              printlabel(parant);
              bfs+=1;}
              while(!the_end(parant) && (stop ==0));
              ////////////////////////////////////////
        if(win){
            Date date2 =new Date();

            result[s]=stop;
            wa=stop;
            while(qeu[wa].id!=0){
                s+=1;
                result[s]=qeu[wa].father;
                ww=qeu[wa].father;
                wa=ww;}
            System.out.println("  the solution is //////////////the solution///////////the solution///  ");

            for(int i=s;i>=0;i--){
                printlabel(qeu[result[i]]);
                System.out.println("  ");
            }
            System.out.println( "start time:"+sdf.format(datestart));
            System.out.println( "Current time"+sdf.format(date2));
            System.out.println("  the number of solution node"+s);
            System.out.println("  the number of visited node"+con);
            return true;
        }
        return false;
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    public  boolean dfs(gamestucture raghad){
        if(stat==0){
         date =new Date();
      }
        int count=1;
        win=false;
        los=true;
        fx=raghad.playerx;
        fy= raghad.playery;
        //////////////////////////////////////////
        if(raghad.playery>0){
            if(canmove(fx, fy - 1,raghad)) {
                System.out.println("  ,A=left");
                qeu[stat+count] = new gamestucture(labelwid,labellen,fx,fy-1,rf,cf);
                qeu[stat+count]=nextl(fx, fy - 1,qeu[stat+count],raghad);
                if(!equalfun(qeu[stat+count])){
                win=the_end(qeu[stat+count]);
                los=lose(qeu[stat+count]);
                if(win ){
                    printlabel(qeu[stat+count]);
                    Date date2 =new Date();
                    System.out.println( "start time:"+sdf.format(date));
                    System.out.println( "Current time"+sdf.format(date2));
                    System.out.println(" the number of solution node "+s);
                    System.out.println("  the number of visited node"+stat);
                    System.out.println("  the solution is //////////////the solution///////////the solution///  ");
                    System.out.println(" ");
                    for(int i=1;i<=s;i++){
                        ww=result[i];
                        printlabel(qeu[ww]);
                        System.out.println(" ");
                    }
                    return true;
                }
                if(!los){
                    count+=1;}
            }
                else{
                    count-=1;
                }
            }
        }
        ///////////////////////////////////////////////////  //down
        if(raghad.playerx< raghad.wide-1){
            if (canmove(fx+ 1, fy,raghad)) {
                System.out.println(" dowwwwwn ");
                qeu[stat+count]=new gamestucture(labelwid,labellen,fx+1,fy,rf,cf);
                qeu[stat+count]= nextl(fx + 1, fy,qeu[stat+count],raghad);
                if(!equalfun(qeu[stat+count])){
                win=the_end(qeu[stat+count]);
                los=lose(qeu[stat+count]);
                if(win ){
                    printlabel(qeu[stat+count]);
                    Date date2 =new Date();
                    System.out.println( "start time:"+sdf.format(date));
                    System.out.println( "Current time"+sdf.format(date2));
                    System.out.println(" the number of solution node "+s);
                    System.out.println("  the number of visited node"+stat);
                    System.out.println("  the solution is //////////////the solution///////////the solution///  ");
                    for(int i=1;i<=s;i++){
                        ww=result[i];
                        printlabel(qeu[ww]);
                        System.out.println(" ");
                    }
                    return true;
                }
                if(!los){
                    count+=1;}
            }
                else{
                    count-=1;
                }
            }
            }
        ///////////////////////////////////////////////////////
        if(raghad.playery< labellen-1){
            if (canmove(fx, fy + 1,raghad)) {    //right
                System.out.println(" right");
                qeu[stat+count]=new gamestucture(labelwid,labellen,fx+1,fy,rf,cf);
                qeu[stat+count]=nextl(fx, fy + 1,qeu[stat+count],raghad);
                if(!equalfun(qeu[stat+count])){
                win=the_end(qeu[stat+count]);
                los=lose(qeu[stat+count]);
                if(win){
                    printlabel(qeu[stat+count]);
                    Date date2 =new Date();
                    System.out.println( "start time:"+sdf.format(date));
                    System.out.println( "Current time"+sdf.format(date2));
                    System.out.println(" the number of solution node "+s);
                    System.out.println("  the number of visited node"+stat);
                    System.out.println("  the solution is //////////////the solution///////////the solution///  ");
                    for(int i=1;i<=s;i++){
                        ww=result[i];
                        printlabel(qeu[ww]);
                        System.out.println(" ");
                    }
                    return true;
                }if(!los){
                    count+=1;}
            }else{
                    count-=1;
                }
            }}
        //////////////////////////////////////////////
        ///////////////////////////////   //up
        if(raghad.playerx>0){
            if (canmove(fx- 1,fy,raghad)) {
                System.out.println(" S=upppppp ");
                qeu[stat+count]=new gamestucture(labelwid,labellen,fx-1,fy,rf,cf);
                qeu[stat+count]=nextl(fx - 1, fy,qeu[stat+count],raghad);
                if(!equalfun(qeu[stat+count])){
                win=the_end(qeu[stat+count]);
                los=lose(qeu[stat+count]);
                if(win ){
                    printlabel(qeu[stat+count]);
                    Date date2 =new Date();
                    System.out.println( "start time:"+sdf.format(date));
                    System.out.println( "Current time"+sdf.format(date2));
                    System.out.println(" the number of solution node "+s);
                    System.out.println("  the number of visited node"+stat);
                    System.out.println("  the solution is //////////////the solution///////////the solution///  ");
                    for(int i=1;i<=s;i++){
                        ww=result[i];
                        printlabel(qeu[ww]);
                        System.out.println(" ");
                    }
                    return true;
                }if(!los){
                    count+=1;}
            }else{
                    count-=1;
                }
            }}


        stat+=(count-1);
        for(int i=0;i<count-1;i++){
            printlabel(qeu[stat-i]);
            s+=1;
            result[s]=stat-i;
            win=dfs(qeu[stat-i]);
            if(win){
                return  true;
            }
        }
        stat-=(count-1);
        s-=1;
        if(!win){
            return false;
        }

        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void usermode(gamestucture start){
        stat +=1;
        do {
            System.out.println("D=right , S= back ,W=front ,A=left");
            mov = input.next();
            if (mov.equalsIgnoreCase("a")) {
                if(start.playery>0){
                    if (canmove(start.playerx, start.playery- 1,start)) {
                        start=nextl(start.playerx, (start.playery) - 1,start,start);
                    }
                }} else if (mov.equalsIgnoreCase("d")) {
                if(start.playery< start.leng-1){
                    if (canmove(start.playerx, (start.playery) + 1,start)) {
                        start=nextl(start.playerx, (start.playery) + 1,start,start);
                    }}
            } else if (mov.equalsIgnoreCase("w")) {
                if(start.playerx>0){
                    if (canmove((start.playerx) - 1, start.playery,start)) {
                        start=nextl((start.playerx) - 1, start.playery,start,start);
                    }}
            } else if (mov.equalsIgnoreCase("s")) {
                if(start.playerx< start.wide-1){
                    if (canmove((start.playerx) + 1, start.playery,start)) {
                        start=nextl((start.playerx) + 1, start.playery,start,start);
                    }
                }} else {
                move = false;
            }
            printlabel(start);
            win=the_end(start);
            los=lose(start);
            if(los){
                System.out.println("GAME OVER");
            }
        }
        while (!move);
        if (!win && !los) {
            usermode(start);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean ucs(gamestucture parant){
        Date datesta =new Date();
        priorityq.add(parant);
        qeu[con] = new gamestucture(labelwid,labellen,fx,fy,rf,cf);
       do { parant= priorityq.poll();
        fx=parant.playerx;
        fy= parant.playery;
        fcost=parant.cost;
        win=the_end( parant );
       fatherindex=parant.id;

    if(win){
        Date date2 =new Date();
            result[s]=fatherindex;
            wa=fatherindex;
            while(qeu[wa].id!=0){
                s+=1;
                result[s]=qeu[wa].father;
                ww=qeu[wa].father;
                wa=ww;}
            System.out.println("  the solution is //////////////the solution///////////the solution/// ");

            for(int i=s-1;i>=0;i--){
                printlabel(qeu[result[i]]);
                System.out.println("  ");
            }
        System.out.println( "start time:"+sdf.format(datesta));
        System.out.println( "Current time"+sdf.format(date2));
        System.out.println("  the cost //solution nodes"+fcost);
        System.out.println("  the number of visited node"+con);
            return true;
        }
        /////////////////////////////////////////////////////////up
        if(parant.playerx>0){
            if(canmove(fx-1, fy ,parant)) {
                con+=1;
                qeu[con] = new gamestucture(labelwid,labellen,fx-1,fy,rf,cf);
                qeu[con] =nextl(fx-1, fy ,qeu[con],parant);
                if(!equalfun(qeu[con])   && (!lose(qeu[con]) || the_end(qeu[con]))) {
                    System.out.println(" uppppppppp");
                    qeu[con].setcost(fcost + 1);
                    qeu[con].father = fatherindex;
                   qeu[con].id = con;
                    printlabel(qeu[con]);
                    priorityq.add(qeu[con]);
                }
                else{
                    con-=1;
                }
            }}
        ////////////////////////////////////////////////////////////right
        if(parant.playery<labellen-1){
            if(canmove(fx, fy+1 ,parant)) {
                con+=1;
                qeu[con] = new gamestucture(labelwid,labellen,fx,fy+1,rf,cf);
                qeu[con] =nextl(fx, fy+1 ,qeu[con],parant);
                if(!equalfun(qeu[con])   &&(!lose(qeu[con]) || the_end(qeu[con]))) {
                    qeu[con].setcost(fcost + 1);
                   qeu[con].father = fatherindex;
                    priorityq.add(qeu[con]);
                    qeu[con].id = con;
                    System.out.println(" rigاt");
                    printlabel(qeu[con]);
                }
                else{
                    con-=1;
                }
            }}
        ////////////////////////////////////////////////////////////down
        if(parant.playerx<labelwid-1){
            if(canmove(fx+1, fy ,parant)) {
                System.out.println("  down");
                con+=1;
                qeu[con] = new gamestucture(labelwid,labellen,fx+1,fy,rf,cf);
                qeu[con] =nextl(fx+1, fy ,qeu[con],parant);
                if(!equalfun(qeu[con])   && (!lose(qeu[con]) || the_end(qeu[con]))) {
                    qeu[con].setcost(fcost + 1);
                    qeu[con].father = fatherindex;
                  qeu[con].id = con;
                    printlabel(qeu[con]);
                    priorityq.add(qeu[con]);
                }
                else{
                    con-=1;
                }
            }}
        ////////////////////////////////////////////////////////////////
        if(parant.playery>0){
            if(canmove(fx, fy - 1,parant)) {
                con+=1;
                qeu[con] = new gamestucture(labelwid,labellen,fx,fy-1,rf,cf);
                qeu[con] =nextl(fx, fy - 1,qeu[con],parant);
                if(!equalfun(qeu[con])   &&(!lose(qeu[con]) || the_end(qeu[con]))){                     //
                    System.out.println("  ,A=left");
                    qeu[con].setcost(fcost+1);
                   qeu[con].father=fatherindex;
                   qeu[con].id=con;
                    printlabel(qeu[con]);
                    priorityq.add(qeu[con]);}
                else{
                    con-=1;
                }
            }}

       }
        while (!priorityq.isEmpty());

        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean Astar(gamestucture parant ,int num){
        Date datesta =new Date();
        priorityq.add(parant);
        qeu[con] = new gamestucture(labelwid,labellen,fx,fy,rf,cf);
        do { parant= priorityq.poll();
            fx=parant.playerx;
            fy= parant.playery;
            fcost=parant.cost;
            win=the_end( parant );
            fatherindex=parant.id;

            if(win){
                Date date2 =new Date();
                result[s]=fatherindex;
                wa=fatherindex;
                while(qeu[wa].id!=0){
                    s+=1;
                    result[s]=qeu[wa].father;
                    ww=qeu[wa].father;
                    wa=ww;}
                System.out.println("  the solution is //////////////the solution///////////the solution/// ");

                for(int i=s-1;i>=0;i--){
                    printlabel(qeu[result[i]]);
                    System.out.println("  ");
                }
                System.out.println( "start time:"+sdf.format(datesta));
                System.out.println( "Current time"+sdf.format(date2));
                System.out.println("  the cost//solution nods"+fcost);
                System.out.println("  the number of visited node"+con);
                return true;
            }
            /////////////////////////////////////////////////////////up
            if(parant.playerx>0){
                if(canmove(fx-1, fy ,parant)) {
                    con+=1;
                    qeu[con] = new gamestucture(labelwid,labellen,fx-1,fy,rf,cf);
                    qeu[con] =nextl(fx-1, fy ,qeu[con],parant);
                    if(!equalfun(qeu[con])   && (!lose(qeu[con]) || the_end(qeu[con]))) {
                        System.out.println(" uppppppppp");
                        qeu[con].setcost(fcost + 1);
                        qeu[con].setheuris(heuristic(qeu[con],num));
                        qeu[con].father = fatherindex;
                        qeu[con].id = con;
                        printlabel(qeu[con]);
                        priorityq.add(qeu[con]);
                    }
                    else{
                        con-=1;
                    }
                }}
            ////////////////////////////////////////////////////////////right
            if(parant.playery<labellen-1){
                if(canmove(fx, fy+1 ,parant)) {
                    con+=1;
                    qeu[con] = new gamestucture(labelwid,labellen,fx,fy+1,rf,cf);
                    qeu[con] =nextl(fx, fy+1 ,qeu[con],parant);
                    if(!equalfun(qeu[con])   &&(!lose(qeu[con]) || the_end(qeu[con]))) {
                        qeu[con].setcost(fcost + 1);
                        qeu[con].setheuris(heuristic(qeu[con],num));
                        qeu[con].father = fatherindex;
                        priorityq.add(qeu[con]);
                        qeu[con].id = con;
                        System.out.println(" rigاt");
                        printlabel(qeu[con]);
                    }
                    else{
                        con-=1;
                    }
                }}
            ////////////////////////////////////////////////////////////down
            if(parant.playerx<labelwid-1){
                if(canmove(fx+1, fy ,parant)) {
                    System.out.println("  down");
                    con+=1;
                    qeu[con] = new gamestucture(labelwid,labellen,fx+1,fy,rf,cf);
                    qeu[con] =nextl(fx+1, fy ,qeu[con],parant);
                    if(!equalfun(qeu[con])   && (!lose(qeu[con]) || the_end(qeu[con]))) {
                        qeu[con].setcost(fcost + 1);
                        qeu[con].setheuris(heuristic(qeu[con],num));
                        qeu[con].father = fatherindex;
                        qeu[con].id = con;
                        printlabel(qeu[con]);
                        priorityq.add(qeu[con]);
                    }
                    else{
                        con-=1;
                    }
                }}
            ////////////////////////////////////////////////////////////////
            if(parant.playery>0){
                if(canmove(fx, fy - 1,parant)) {
                    con+=1;
                    qeu[con] = new gamestucture(labelwid,labellen,fx,fy-1,rf,cf);
                    qeu[con] =nextl(fx, fy - 1,qeu[con],parant);
                    if(!equalfun(qeu[con])   &&(!lose(qeu[con]) || the_end(qeu[con]))){                     //
                        System.out.println("  ,A=left");
                        qeu[con].setcost(fcost+1);
                        qeu[con].setheuris(heuristic(qeu[con],num));
                        qeu[con].father=fatherindex;
                        qeu[con].id=con;
                        printlabel(qeu[con]);
                        priorityq.add(qeu[con]);}
                    else{
                        con-=1;
                    }
                }}

        }
        while (!priorityq.isEmpty());

        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public  int heuristic(gamestucture label,int num){
        nm=0;
        int coun=1;int cou=1;int c=1;int co=1;
        rp=label.playerx;
        cp= label.playery;
        if(num==1){
            while(rp<labelwid-c){
                 if(canmove(rp+c,cp,label)){
                      nm+=1;
                      c+=1;}
                 else break;
            }
            while(rp>=co){
                if(canmove(rp-co,cp,label)){
                   nm+=1;
                   co+=1;}
                 else break;
            }
            while(cp<labellen-cou){
                if(canmove(rp,cp+cou,label)){
                   nm+=1;
                   cou+=1;}
                 else break;
            }
            while(cp>=coun){
                if(canmove(rp,cp-coun,label)){
                    nm+=1;
                    coun+=1;}
                 else break;
            }
        }
        else if(num==2){
          nm=(Math.abs(rf-rp)+Math.abs(cf-cp));
        }
        else if(num==3){
            if(rp<labelwid-1){
            if(canmove(rp+1,cp,label)){
                nm+=1;
            }}
            if(rp>0){
            if(canmove(rp-1,cp,label)){
                nm+=1;
            }}
            if(cp<labellen-1){
            if(canmove(rp,cp+1,label)){
                nm+=1;
            }}
            if(cp>0){
            if(canmove(rp,cp-1,label)){
                nm+=1;
            }}
        }
        else if(num==4){
            nm=(Math.abs(rf-rp));
        }
        else if(num==5){
            nm=(Math.abs(cf-cp));
        }
        return nm;
    }
}

