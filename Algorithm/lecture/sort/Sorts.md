## 1. 정렬 이란

- 정렬 : 어떤 데이터들이 주어졌을 때 이를 정해진 순서대로 나열하는 것
- 정렬은 프로그램 작성시 빈번하게 필요로 함
- 다양한 알고리즘이 고안되었으며, 알고리즘 학습의 필수
- 다양한 정렬 알고리즘 이해를 통해, 동일한 문제에 대해 다양한 알고리즘이 고안될 수 있음을 이해하고, 각 알고리즘 간 성능 비교를 통해, 알고리즘 성능 분석에 대해서도 이해할 수 있다.

## 2. 버블 정렬이란

- 두 인접한 데이터를 비교해서, 앞에 있는 데이터가 뒤에 있는 데이터보다 크면, 자리를 바꾸는 정렬 알고리즘

```java
import java.util.Arrays;

public class BubbleSortTest {
    public static int[] bubbleSort(int[] data) {
        int temp = 0;
        boolean swap = false;
        for (int i = 0; i < data.length-1; i++) {
            // 턴이 돌 때마다 젤 큰 수가 맨 뒤로가서 더이상 비교할 필요가 없어지니깐
            // i를 빼준 횟수만큼만 반복하도록 설정
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) { // O(n)
                break;
            }
        }

        return data;
    }

    public static void main(String[] args) {
        int[] data = new int[10];
        for (int i = 0; i < 10; i++) {
//            int rand = (int)(Math.random() * 100) + 1;
//            data[i] = rand;
            data[i] = i;
        }

        System.out.println(Arrays.toString(bubbleSort(data)));

    }
}
```

## 3. 선택 정렬이란

- 다음과 같은 순서를 반복하여 정렬
    1. 주어진 데이터 중, 최소값을 찾는다.
    2. 해당 최소값을 데이터 맨 앞에 위치한 값과 교체한다.
    3. 맨 앞의 위치를 뺀 나머지 데이터부터 시작해서 위의 과정을 동일한 방법으로 반복한다.
- 기준데이터를 가지고 나머지 데이터들을 비교해서 교체하는데 맨 마지막 데이터는 어차피 가장 큰 데이터가 들어갈 수 밖에없어서 비교를 할 필요가 없다.

```java
import java.util.Arrays;

public class SelectSortTest {

    public static int[] selectSort(int[] data) {
        int temp;
        int lowest = 0;

        for (int i = 0; i < data.length - 1; i++) {
            lowest = i;
            // 이걸 해줘야 기준 값이 되는 데이터와 뒤에 남은 데이터들간에 비교가 이루어진다.
            // 핵심이 기준이 되는 값과 뒤의 값들을 비교해서 교체를 하는 것이니깐!
            for (int j = i + 1; j < data.length; j++) {
                if (data[lowest] > data[j]) {
                    lowest = j;
                }
            }

            temp = data[lowest];
            data[lowest] = data[i];
            data[i] = temp;
        }
        return data;
    }

    public static void main(String[] args) {
        int[] data = new int[10];
        for (int i = 0; i < data.length; i++) {
            int input = (int) (Math.random() * 100) + 1;
            System.out.print(input + " ");
            data[i] = input;
        }
        System.out.println();

        System.out.println(Arrays.toString(selectSort(data)));
    }
}
```

## 4. 삽입 정렬이란

- 두번째 인덱스부터 시작해서 앞에 인덱스의 값들과 비교해서 해당인덱스가 작으면 앞에 데이터와 교체해나가면서 자기보다 작은 데이터 뒤까지 반복한다.

```java
import java.util.Arrays;

public class InsertionSortTest {
    public static int[] insertionSort(int[] data) {
        int temp;
        for (int i = 0; i < data.length - 1; i++) { // 두번째 인덱스부터 시작하니 반복횟수는 -1을 해줌
            for (int j = i + 1; j > 0; j--) { // 해당 인덱스의 앞에 데이터들과 비교하니 인덱스는 작아짐
                if (data[j] < data[j - 1]) {
                    temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }
        return data;
    }

    public static void main(String[] args) {
        int[] data = new int[10];
        for (int i = 0; i < data.length; i++) {
            int input = (int) (Math.random() * 100) + 1;
            System.out.print(input + " ");
            data[i] = input;
        }
        System.out.println();

        System.out.println(Arrays.toString(insertionSort(data)));
    }

}
```