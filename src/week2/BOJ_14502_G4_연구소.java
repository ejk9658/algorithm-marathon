package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14502_G4_연구소 {

	static int N,M, map[][];
	static int r, R;
	static int[][] select;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		} //read
		
		// 1. 벽 3개 세우기
		int cnt = 0;
		select = new int[3][2];	// 새로 세울 벽 3개의 행,열 저장
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0)
					if(cnt=3) {
						virus();
						return;
					}
					wall(i,j,r++);
			}
		}
		
		
	}

	private static void wall(int r, int c, int cnt) {
		if(cnt==3) {
			virus();
		}
			
		int[][] select = new int[3][2];
		select[0][0] = -1;
		select[1][0] = -1;
		select[2][0] = -1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					if(cnt==3)
					cnt++;
					map[i][j] = 3;
					for(int k=0; k<3; k++) {
						if(select[k][0]!=-1) continue;
						select[k][0] = i;
						select[k][1] = j;
						
						
					}
				}
			}
		}
	}

}
