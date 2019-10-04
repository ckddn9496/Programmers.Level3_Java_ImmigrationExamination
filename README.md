# Programmers.Level3_Java_ImmigrationExamination

## 프로그래머스 > 코딩테스트 연습 > 이분탐색 > 입국심사 

### 1. 문제설명

문제: https://programmers.co.kr/learn/courses/30/lessons/43238

input으로 입국심사를 기다리는 사람의 수 n, 입국심사대에 있는 심사관 마다 심사하는데 걸리는 시간에 대한 배열 times가 들어온다. 한 심사대에서는 동시에 한 명만 심사를 할 수 있고, 대기하는 사람중 가장 앞에 서 있는 사람은 비어있는 심사대로 가서 심사를 받을 수 있다. 모든 사람이 심사를 받는데 걸릴 수 있는 최소 시간을 return하는 문제

### 2. 풀이

BF방식으로 심사가 가장 오래걸리는 심사관의 시간이 혼자 모든 사람을 심사할 때의 시간을 max로 두고 새로운 심사를 시작할 수 있는 시간에 대한 HashMap<>을 만들어서 문제를 해결 하려고 하였으나 시간 초과가 나서 문제 종류대로 이분탐색을 사용하여 해결하였다.

정렬된 배열에 대해 값을 이분 탐색하듯이, 연속된 시간에 이분탐색을 적용하였다. 최소 소요시간 `long left = 0` 부터 최대 소요시간 `long right = (long) (Math.floor(n / numOfExaminer) * Arrays.stream(times).max().orElse(0))` 를 지정하였다. 시간의 중간지점을 `mid = left + right` 로 두어 해당 시간까지 처리할 수 있는 고객수를 count하였다.

`count == n`일 경우 탐색을 중단한다
`count > n`일 경우 더 많은 사람이 심사 받을 수 있으므로 right값을 조정하여 다시 탐색을 진행한다.
`count < n`일 경우 모든 사람이 심사 받지 못하므로 left값을 조정하여 다시 탐색을 진행한다.

```java
while (left < right) {
  mid = (left + right)/2;
  long count = 0;
  for (int time : times)
    count += (mid/time);

  if (count == n) { // 알맞는 인원 심사
    break;
  } else if (count > n) { // 더 심사받음...
    right = mid - 1;
  } else { // 덜 심사받음
    left = mid + 1;
  }
}
```

탐색이 종료되면 n명의 사람이 모두 검사받을 수 있는 시간을 얻을 수 있다. 하지만 그 시간이 최소라고는 할 수 없다. n 번째 사람이 검사를 막 마쳤을 때가 최소시간인 것이지 n번째 사람이 검사를 마치고 n+1 번째 사람이 검사를 진행중인 시간일 수 있다. 그렇기 때문에 얻어낸 `mid`값을 줄여가며 `count < n`가 되는 시간을 찾아 그 시간에 1초후 시간을 return하면 최소시간을 찾아 return해준 결과와 동일 하여 이렇게 해결하였다.

```java
while (true) {
  long count = 0;
  for (int time : times)
    count += (mid/time);
  if (count < n)
    break;
  else
    mid--;
}


return mid+1;
```

### 3. 문제의 문제점

C++ 에서는 정확한 답임에도 불구하고 input의 크기 때문에 에러가 발생할 수 있다고 한다.

참고: https://programmers.co.kr/learn/questions/4505
