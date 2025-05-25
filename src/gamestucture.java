public class gamestucture implements Comparable <gamestucture>{
    int playerx , playery,flagx,flagy;
    int wide,leng,cost=0,father=0,id=0,heuris=0;
    int ground[][]=new int[10][10];
    public gamestucture(){
    }
    public gamestucture(int wid,int len ,int px,int py,int fx,int fy){
        wide=wid;
        leng=len;
        for(int i =0;i<wide;i++){
            for(int ii =0;ii<leng;ii++) {
                ground[i][ii]=1;
            }
        }
        playerx=px;
        playery=py;
        flagx=fx;
        flagy=fy;
    }
    public void setvacuum(int x,int y){
        ground[x][y]=0;
    }
    public void setblock(int x,int y){
        ground[x][y]=2;
    }
    public void setcost(int x){
        cost=x;
    }
    public void setheuris(int x){
        heuris=x;
    }
    public int getcost(){return cost+heuris;}
    public boolean equals(gamestucture other){
        return this.getcost()==other.getcost();
    }
    public int compareTo  (gamestucture other){
        if(this.equals(other)){
            return 0;
        }
        else if(getcost()> other.getcost()){
            return 1;
        }
        else
            return -1;
    }
}
