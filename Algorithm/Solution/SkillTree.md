# 프로그래머스 문제 49993번 스킬트리 문제풀이

* 제한 조건에 대해서 잘 읽어보고 파악하는게 중요하다.
* 우선 skill에 해당하는 스킬만 확인하고 다른 알파벳을 무시한다.
* for문을 돌면서 skill_tress의 String들의 각 char들 중 skill의 char와 일치하는 인덱스를 찾아서 해당 인덱스의 값을 index 변수에 넣어준다.
아니면 -1을 반환
* 해당인덱스가 값이 있으면 index에 값이 들어갈 테니 list.add해서 값을 list에 넣어준다.
* 
* skill_trees에 속하는게 없을 수도 있다. 그래서 list.size() > 0 를 체크해줘서 에러를 방지하고, 
스킬 찍은 것 중에 가장 첫번째가 C여야 한다 그래서 list에 값이 들어 간 것에서 list.get(0) != 0 을 체크하므로써
list의 0번째 인덱스의 값이 "CBD" 중에서 C의 인덱스가 0이니깐  0이 아니라면 
즉, C가 아니라면 `continue`를 써서 다시 위로 보낸다.

* 그 다음 위의 조건(C부터 시작한다는 조건)을 만족한 후 스킬 순서가 C->B->D여야 하는데
C->D->B일 수도 있으니깐 그 순서를 확인해서 CBD순서가 아니라면 그 스트링값이 우리가 찾던 값과 다르니
처음 반복문으로 돌아가서 그 다음 스트링을 확인하는 과정을 진행한다.
 
```groovy
import java.util.ArrayList;
import java.util.List;

public class SkilTree {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        //제한 조건이 중요하다 잘 읽어봐야한다. 이러한 제한사항이 있기 때문에 사용할 수 있는
        //알고리즘들이 갈린다. 조건에 나오는 숫자들이 굉장히 중요하다.
        //이후에 나오는 다른 조건들은 제한조건이 빡센것들이 많다 숫자들을 중요하게 봐야한다.

        //다른 알파벳은 무시하고 skill에 해당하는 스킬만 확인
        loop:
        for(String tree : skill_trees){
            List<Integer> list = new ArrayList<>(); //List는 인터페이스여서 직접만들지 못하고 arraylist로 만듬
            //list의 구현체이기때문에
            for (char s: tree.toCharArray()){
                int index = skill.indexOf(s); //찾아서있으면 해당인덱스 없으면 -1
                if(index >=0){
                    list.add(index);
                }
            }

            if(list.size() > 0 && list.get(0) !=0){ //
                continue; //틀리면 카운트하지 않고 넘어감
            }

            for (int i = 1; i< list.size(); i++){
                if(list.get(i-1) + 1 != list.get(i)){
                    continue loop;
                }
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {

        SkilTree skilTree = new SkilTree();
        String skill = "cbd";
        String[] skill_trees = new String[]{"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(skilTree.solution(skill,skill_trees));


        
    }
}
```