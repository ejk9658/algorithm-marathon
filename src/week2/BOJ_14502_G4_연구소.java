package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14502_G4_연구소 {

	static int N,M, map[][], copy[][];
	static List<int[]> virus = new ArrayList<>();
	static int[][] dd = {{0,1},{1,0},{0,-1},{-1,0}};
	static int count, maxCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2)
					virus.add(new int[] {i,j});	// virus(2)인 경우를 리스트에 저장
				else if(map[i][j]==0)
					count++;	// 초기의 안전영역 수
			}
		} //read
		
		if(virus.size()==0) {	// 초기의 map에 virus가 없는 경우
			maxCount = count;
		} else {	// 1. 벽 3개 세우기
			combi(0,0,0);	// 1. 새로운 벽 3개 조합 찾기
		}
		
		System.out.println(maxCount);
	}

	private static void combi(int r, int c, int cnt) {
		if(cnt==3) {
			// map->copy 배열 복사
			for(int i=0; i<N; i++)
				System.arraycopy(map[i], 0, copy[i], 0, M);
			
			// 2. 바이러스 퍼트리기
			for(int i=0,n=virus.size(); i<n; i++) {
				count = 0;
				spread(virus.get(i)[0],virus.get(i)[1]);
			}

			// 3. 안전 영역 크기 구하기
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					if(copy[i][j]==0)
						count++;
			
			maxCount = Math.max(maxCount, count);
			return;
		}
		for(int i=r; i<N; i++) {
			for(int j=c; j<M; j++) {
				if(map[i][j]==0) {
					map[i][j]=3;	// 새로 새운 벽 = 3
					combi(i,j,cnt+1);
					map[i][j]=0;
				}
				c=0;
			}
		}
	}
	
	private static void spread(int r, int c) {
		for(int d=0; d<4; d++) {
			int nr = r+dd[d][0];
			int nc = c+dd[d][1];
			if(!check(nr,nc)) continue;
			if(copy[nr][nc]==0) {
				copy[nr][nc]=4;	// virus가 퍼진 곳 = 4
				spread(nr,nc);
			}
		}
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}
