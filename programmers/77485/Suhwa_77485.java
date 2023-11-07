

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        // 1. 행, 열로 회전당하는 표 초기화
        int[][] matrix = new int[rows][columns];
        for(int i =0 ;i < rows ; i++){
            for(int j = 0 ; j<columns;j++){
                matrix[i][j]=i*columns + (j+1);
            }
        }


        // 2. 쿼리별로 회전하게함
        for(int i=0;i<queries.length;i++){
            answer[i]=rotation(queries[i], matrix, rows, columns);
        }


        // 3. 회전할때 최소 숫자를 기록
        return answer;
    }

    public int rotation(int [] query, int[][] matrix, int rows, int columns){
        int x1Index = query[0]-1;
        int y1Index = query[1]-1;
        int x2Index = query[2]-1;
        int y2Index = query[3]-1;

        int minValue = 10000;

        //오른쪽
        int store;
        int change =  matrix[x1Index][y1Index];
        for(int i =y1Index ; i<y2Index;i++){
            if(minValue > change){ minValue = change;}
            store = matrix[x1Index][i+1];
            matrix[x1Index][i+1] = change;
            change = store;
        }

        //아래
        for(int i = x1Index ; i < x2Index ; i++ ){
            if(minValue > change){ minValue = change;}
            store = matrix[i+1][y2Index];
            matrix[i+1][y2Index] = change;
            change = store;

        }

        //왼쪽
        for(int i =y2Index ; i>y1Index;i--){
            if(minValue > change){ minValue = change;}
            store = matrix[x2Index][i-1];
            matrix[x2Index][i-1] = change;
            change = store;
        }

        //위
        for(int i = x2Index ; i > x1Index ; i-- ){
            if(minValue > change){ minValue = change;}
            store = matrix[i-1][y1Index];
            matrix[i-1][y1Index] = change;
            change = store;

        }

        // System.out.println();
        // for(int i =0 ;i < rows ; i++){
        //     for(int j = 0 ; j<columns;j++){
        //         System.out.printf(" /%02d",matrix[i][j]);
        //     }
        //     System.out.println();
        // }


        return minValue;
    }
}