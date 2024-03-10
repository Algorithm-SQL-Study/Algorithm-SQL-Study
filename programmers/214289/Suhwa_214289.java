//DP? backtracking?
//1. 승객탑승전에 최대한 실내온도 시간으로 옮겨야함
//2. 각 시간마다의 최소온도를 계산하여 이후 최소 전력

//3. 점화식,
// 최초값 : |실외온도-희망온도경계값| * a  : 승객첫탑승전의 최소전력사용량
// -> 승객첫탑승시 온도는 t1 or t2
// 승객이 첫 탑승으로부터의 n분일때의 최소전력 = n-1일때의 최소전력 + (a or b or 0)
//   -> n+1분에 승객이 탑승했으면, 무조건 실내온도가 t1~t2여야함
//   -> n+1분에 승객이 탑승안했으면, 0이다가 온도 못맞추면 다시 뒤로가서 다른 뱡향으로 가기

//1. 승객첫탑승전의 최소전력사용량


class Solution {
    public int t1;
    public int t2;
    public int a;
    public int b;
    public int[] onboard;

    public int solution(int temperature, //실외온도
                        int t1, //실내온도 시작
                        int t2, //실내온도 끝
                        int a, //희망온도!=실내온도일때 소비량
                        int b, //희망온도==실내온도일대 소비량
                        int[] onboard //승객탑승중
    ) {
        this.t1 = t1;
        this.t2 = t2;
        this.a = a;
        this.b = b;
        this.onboard=onboard;

        int initialMinElecPee= 0;
        int temperatureT1Gap = Math.abs(temperature - t1);
        int temperatureT2Gap = Math.abs(temperature - t2);
        int firstSeatTime = getFirstSeatTime();

        //2. 백트래킹으로 최소전력 사용량 구하기
        int minElecPee = 0;
        int[] options;
        if(a>b){
            options = {0,b,a}
        }else{
            options = {0,a,b}
        }

        if(temperatureT1Gap>temperatureT2Gap){
            initialMinElecPee = temperatureT2Gap*a;
            DFS(firstSeatTime, t1, onboard, options)
        }else{
            initialMinElecPee = temperatureT1Gap*a;
        }



        int answer = 0;
        return answer;
    }

    class Node{
        int index;
        int temps;
        int pee; // 0분~index분까지의 전력

        public Node(int index, int temps, int pee){
            this.index = index;
            this.temps = temps;
            this.pee=pee;
        }
    }

    public Node DFS(Node startNode){
        if(onboard[i]==1 && !isbetweenT1andT2(startNode.temps)){
            //n분에 승객이 탑승했는데 온도가 T1~t2이 아닐때
            return startNode;
        }

        for(int i=startNode.index ; i<onboard.length;i++){
            DFS(new Node(i,startNode.temps-1,startNode.pee ));

            DFS(new Node(i,startNode.temps???,startNode.pee+a ))

            DFS(new Node(i,startNode.temps???,startNode.pee+b ))


        }
    }

    public boolean isbetweenT1andT2(int now){
        int minT;
        int maxT;
        if(t1>t2){
            minT = t2;
            maxT=t1;
        }else{
            minT = t1;
            maxT=t2;
        }
        if(now<minT){
            return false;
        }

        if(now> maxT){
            return false;
        }

        return true;
    }

    public int getFirstSeatTime(){
        for(int i=0; i<onboard.length; i++){
            if(onboard[i]==1){
                return i
            }
        }
    }
}