import java.util.Scanner;
import java.math.*;
public class MatrixMultiplication {
    public static void main(String[] args){
        //set up the matrix 
        //nxn where n is a power of 2
        //Scanner kb = new Scanner(System.in);
       
        Integer[][] matrixA = {{2,0,-1,6}, {3,7,8,0}, {-5,1,6,-2}, {8,0,2,7}};
        Integer[][] matrixB = {{0,1,6,3}, {-2,8,7,1}, {2,0,-1,0}, {9,1,6,-2}};
        bruteForce(matrixA,matrixB);
    }
    
    public Integer[][] loadMatrix(Integer[][] matrixes){
        
    }

    public static Integer[][] bruteForce(Integer[][] A, Integer[][] B){
        //first row times all collumns
        //2nd row times all collumns
        int size = A.length;
        
        Integer[][] answer = new Integer[size][size];
        for(int i = 0; i < size; i++){
            //controls A's rows
            System.out.print(i +"  :  ");
            for(int j = 0; j < size; j++){
                int sum = 0; 
                //B's columns
                for(int x = 0; x < size; x++){
                    //a's columns and b's rows
                    sum += A[i][x] * B[x][j];
                    //System.out.println("sum: "+ sum);
                    //System.out.print("\ti: "+i+" j: "+j+" x: "+x);
                }
                answer[i][j] = sum;
                System.out.print(answer[i][j]+" ");
            }  
            System.out.println("");
        }
        return answer;
    }
}