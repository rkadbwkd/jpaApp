package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		SpringApplication.run(JpashopApplication.class, args);
//		int n = 8;
//		int k = 2;
//		String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
//
//		String answer = "";
//		for(int i = 0; i < n ; i ++)
//			answer += "O";
//
//		for(int i = 0; i < cmd.length; i++){
//			if(cmd[i] == "Z"){ // 복구인경우
//				//answer i = O
//				answer = answer.substring(0,k) + "O" + answer.substring(k+1, n);
//			} else if (cmd[i] == "C"){ // 현재행 삭제후 아래행인경우
//				// answer i = X
//				 answer = answer.substring(0,k) + "X" +  answer.substring(k+1,n-1);
//				k++;
//			} else {
//				if(cmd[i].substring(0,1) == "U"){ // 위로가는경우
//					  k -= Integer.parseInt(cmd[i].substring(2,3));
//				} else { // 아래로가는경우
//					   k += Integer.parseInt(cmd[i].substring(2,3));
//				}
//			}
//		}
//
//		System.out.println(answer);
	}

}
