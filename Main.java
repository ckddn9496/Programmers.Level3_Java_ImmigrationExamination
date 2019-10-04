import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int n = 6;
		int[] times = {7,10}; // return 28
//		int n = 6;
//		int[] times = {4,5,6};
		System.out.println(new Solution().solution(n, times));
	}
}

class Solution {
    public long solution(int n, int[] times) {
    	int numOfExaminer = times.length;
    	long left = 0;
    	long right = (long) (Math.floor(n / numOfExaminer) * Arrays.stream(times).max().orElse(0));
//    	System.out.println("left : " + left + " right: " + right);
    	long mid = (long) 0;
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
//    	System.out.println(mid);
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
    }
}