# [멀쩡한 사각형](https://programmers.co.kr/learn/courses/30/lessons/62048) 문제풀이

* 유클리드 호제법을 이용한 GCD(최대 공약수)를 이용하여 푸는 문제이다
* w*h의 최대공약수를 구해서 해결한다.
* 변을 가를 때마다 하나씩 더 찢는다
* 아래나 오른쪽으로 이동하게되어있는데 예외는 꼭지점에서 만나는 부분 이때는 대각선으로 이동하게된다.
그래서 w+h - gcd(h,w) ex)3+2 - 1
* 최대공약수에 해당되는 부분마다 하나씩 덜 찢는다라고 생각하면 된다.
* 한가지 주의사항 w나 h는 1억까지 올라갈 수 있는데 두수를 곱하면 엄청난 숫자가 나는데, 
미리 long으로 바꿔놓고 연산하지 않으면 문제가 발생함 (안전하게 long으로 바꿔놓고해라) 
* gcd를 구할 때 x가 y보다 더 커야 제대로 동작을 하게 된다
* 근데 내부적으로 스왑이 일어남

```groovy

public class Squares {

    long gcd(int x, int y){
        if(y==0){
            return x;

        }else{
            return gcd(y,x%y);
        }

    }
    public long solution(int w, int h) {
        long answer = 1;

        answer = (long)w * (long)h - ((long)w + (long)h - gcd(w,h));
        return answer;
    }

    public static void main(String[] args) {
        Squares num62048 = new Squares();
        System.out.println(num62048.solution(8,12));

    }

}
```