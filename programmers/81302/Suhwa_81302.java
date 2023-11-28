import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {

    class Node{
        int row;
        int col;

        public Node(int row, int col){
            this.row =row;
            this.col=col;
        }
    }


    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for(int i =0 ; i<places.length ; i++){
            String[] place = places[i];
            List<char[]> placeList = new ArrayList<>();
            for(String placelist : place){
                placeList.add(placelist.toCharArray());
            }

            //P의 위치들의 배열생성
            List<Node> pPlace = pPlaceList(placeList);
            answer[i]=(checkDistance(pPlace, placeList));
        }
        return answer;
    }


    public List<Node> pPlaceList(List<char[]> placeArray){
        List<Node> pPlace = new ArrayList<>();

        for(int i =0 ; i<placeArray.size(); i++){
            for(int j =0; j<placeArray.get(i).length;j++){
                if(placeArray.get(i)[j]=='P'){
                    pPlace.add(new Node(i,j));
                }
            }
        }

        return pPlace;
    }



    public int checkDistance(List<Node> pPlace, List<char[]> placeArray){
        // [[p-1위치개수],...]p위치개수에 맨해튼거리계산
        // 위중 맨해튼 거리가 2이하인경우
        //가로 세로 대각선에 가림막있는지확인
        for(int i =0 ; i <pPlace.size()-1;i++){
            for(int j =i+1 ; j<pPlace.size();j++){
                int distance = Math.abs(pPlace.get(i).row - pPlace.get(j).row)
                        + Math.abs(pPlace.get(i).col-pPlace.get(j).col);

                if(distance <= 2){
                    if(!checkPartition(pPlace.get(i), pPlace.get(j) , placeArray)){
                        return 0;
                    }
                }
            }
        }
        return 1;

    }

    public boolean checkPartition(Node a, Node b, List<char[]> placeArray){
        if(a.row == b.row){
            int col = a.col>b.col ? b.col+1 : a.col+1;
            return placeArray.get(a.row)[col]=='X';
        }
        else if(a.col == b.col){
            int row = a.row>b.row ? b.row+1: a.row+1;
            return placeArray.get(row)[a.col]=='X';
        }
        else{
            if(b.col < a.col){
                return (placeArray.get(a.row+1)[a.col]=='X'
                        && placeArray.get(a.row)[a.col-1]=='X');
            }
            else{
                return placeArray.get(a.row+1)[a.col]=='X'
                        && placeArray.get(a.row)[a.col+1]=='X';
            }
        }
    }



}