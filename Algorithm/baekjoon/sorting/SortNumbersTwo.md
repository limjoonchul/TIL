# [수 정렬하기2](https://www.acmicpc.net/problem/2751)
## 문제
* N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.

## 입력
* 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 숫자가 주어진다. 이 수는 절댓값이 1,000,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.

## 출력
* 첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.

### 예제 입력 1 
```text
5
5
4
3
2
1
```
### 예제 출력 1 
```text
1
2
3
4
5
```

### 풀이
> 처음에 Collections.sort를 사용하여 제출했는데 몇번이고 시간초과가 났다...
그래서 이걸 사용하면 안되는구나하고 처음 이 문제를 들어가기전에 설명에 내장정렬함수를 사용하라고 해서
찾아봤을 때 Arrays.sort()도 나와있어서 이거구나! 하고 썼지만 역시나 이것도 시간초과...
찾아봤는데 아직 모르는 개념들이 많이나와서 이해하지 못했지만 Arrays.sort는 O(nlogn)이지만
최악의 경우 O(n2)의 시간 복잡도를 갖게된다고 한다 이런 테스트케이스를 해두었겠지??
그래서 결국 블로그를 찾아보니 Collection.sort를 사용하는 방법이 맞았는데
내가 틀린 이유는 마지막에 출력문을 System.out.println() 이걸 사용해서 안되었다..
이런 부분에 대해서도 신경쓰면서 구현을 해야하는 것 같다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class NumberSorting2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arrayList.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(arrayList);

        for (Integer integer : arrayList) {
            sb.append(integer + "\n");
        }
        System.out.println(sb.toString());
    }
}
```

