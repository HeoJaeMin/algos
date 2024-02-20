import java.util.Arrays;
import java.util.List;

public class GiftKakao {

    public int solution(String[] friends, String[] gifts) {
            int answer = 0;
            int length = friends.length;

            List<String> friendsList = Arrays.asList(friends);

            int [][] prevGiven = new int[length][length];
            int [][] score = new int[length][2];

            for(int i=0;i<length;i++){
                Arrays.fill(prevGiven[i], 0);
                Arrays.fill(score[i], 0);
            }

            Arrays.stream(gifts).forEach(gift->{
                String[] fromTo = gift.split(" ");

                int from = friendsList.indexOf(fromTo[0]);
                int to = friendsList.indexOf(fromTo[1]);

                prevGiven[from][to]+=1;
                score[from][0]+=1;
                score[to][1]-=1;
            });

            int[] scoreSum = Arrays.stream(score).mapToInt(a-> Arrays.stream(a).sum()).toArray();

            for(int i=0;i<length;i++){
                int tempAnswer = 0;
                for(int j=0;j<length;j++){
                    if(i==j){
                        continue;
                    }

                    int give = prevGiven[i][j];
                    int take = prevGiven[j][i];

                    if(give>take || (give==take && scoreSum[i]>scoreSum[j])){
                        tempAnswer++;
                    }
                }

                answer = Integer.max(answer, tempAnswer);
            }

            return answer;
    }
}
