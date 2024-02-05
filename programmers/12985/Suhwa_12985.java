class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        int nextA = a;
        int nextB = b;

        while(!isMatch(nextA,nextB)){
            nextA = nextRound(nextA);
            nextB = nextRound(nextB);
            answer+=1;
        }

        return answer;
    }

    public boolean isMatch(int a, int b){
        if(a%2==0&&b%2!=0&&b+1==a){
            return true;
        }

        if(a%2!=0&&b%2==0&&a+1==b)  return true;
        return false;
    }

    public int nextRound(int u){
        if(u%2==0){//짝수일때
            return u/2;
        }
        else{//홀수일때
            return (u+1)/2;

        }
    }
}