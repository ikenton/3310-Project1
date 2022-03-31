

//import java.util.Scanner;
//import java.math.*;
public class MatrixMultiplication {
    public static void main(String[] args){
       
        Integer[][] matrixA = {{2,0,-1,6}, {3,7,8,0}, {-5,1,6,-2}, {8,0,2,7}};
        Integer[][] matrixB = {{0,1,6,3}, {-2,8,7,1}, {2,0,-1,0}, {9,1,6,-2}};

        Integer[][] test1 = {{1,3}, {4,5}};
        Integer[][] test2 = {{4, 6}, {20,1}};
          

        Integer[][] matrixC= naive(matrixA, matrixB);
        Integer[][] strass = strassens(matrixA, matrixB); 
        Integer[][] brute = bruteForce(matrixA, matrixB);       
        
        System.out.println("Strassens: ");
        for(int i = 0; i < matrixA.length; i++){
            for(int j = 0; j< matrixA.length; j++){
                System.out.print(" "+strass[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Naive: ");
        for(int i = 0; i < matrixC.length; i++){
            for(int j = 0; j< matrixC.length; j++){
                System.out.print(" "+matrixC[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Brute force: ");
        for(int i = 0; i < brute.length; i++){
            for(int j = 0; j< brute.length; j++){
                System.out.print(" "+brute[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /*
    public static Integer[][] loadMatrix(Integer[][] matrixes){
        /*for extra credit add 0's to the ends.
            check if its' a power of 2 if not, load 0's
        
        Scanner kb = new Scanner(System.in);
        int n = matrixes.length;

        System.out.println("Enter integer: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.println(": ");
                matrixes[i][j] = kb.nextInt();
            }
        }
    }
    */

    public static Integer[][] strassens(Integer[][] A, Integer[][] B){
        Integer[][] answer = new Integer[A.length][A.length];
        if(A.length <= 1){
            //System.out.println(A[0][0]+" "+B[0][0]);
            answer[0][0] = A[0][0]*B[0][0];
            //System.out.println(answer[0][0]);
            return answer;
        } 
        Integer[][][] aThree = divide(A);
        Integer[][][] bThree = divide(B);
        
        //SUB ARRAYS
        Integer[][] a = aThree[0]; 
        Integer[][] b = aThree[1];
        Integer[][] c = aThree[2];
        Integer[][] d = aThree[3];

        Integer[][] e = bThree[0];
        Integer[][] f = bThree[1];
        Integer[][] g = bThree[2];
        Integer[][] h = bThree[3];
        
        Integer[][] p1 = strassens(arrayAdd(a, d), arrayAdd(e, h));
        Integer[][] p2 = strassens(d, arraySub(g, e));
        Integer[][] p3 = strassens(arrayAdd(a, b), h);
        Integer[][] p4 = strassens(arraySub(b, d), arrayAdd(g, h));
        Integer[][] p5 = strassens(a, arraySub(f, h));
        Integer[][] p6 = strassens(arrayAdd(c, d), e);
        Integer[][] p7 = strassens(arraySub(a, c), arrayAdd(e, f));
        
        //stitch it all back together
        /* c11 = p1 + p2 - p3 + p4
         * c12 = p5 + p3
         * c21 = p6 + p2
         * c22 = p5 + p1 - p6 - p7 
        */
        //System.out.println("The c's");
        Integer[][] c11 = arraySub(arrayAdd(arrayAdd(p1, p2), p4), p3);
        Integer[][] c12 = arrayAdd(p5, p3);
        Integer[][] c21 = arrayAdd(p6, p2);
        Integer[][] c22 = arraySub(arraySub(arrayAdd(p5, p1), p6), p7);

        answer = stitch(c11, c12, c21, c22);

        
        return answer;
        
    }
    
    public static Integer[][] stitch(Integer[][] c11,Integer[][] c12, Integer[][] c21, Integer[][] c22){
        int size = c11.length;
        int n = size*2;
        Integer[][] answer = new Integer[n][n];
        for(int i = 0; i < n; i++){
            if(i < size){
                for(int j = 0; j < n; j++){
                    if(j < size){
                        answer[i][j] = c11[i][j];
                    }else{
                        answer[i][j] = c12[i][j - size];
                    }
                }
            }else{
                for(int j = 0; j < n; j++){
                    if(j < size){
                        answer[i][j] = c21[i-size][j];
                    }else{
                        answer[i][j] = c22[i-size][j-size];
                    }
                }
            }
        }
        return answer;  
    }

    public static Integer[][] arrayAdd(Integer[][] A, Integer[][] B){
        
        int n = A.length;
        Integer[][] C = new Integer[n][n];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                //System.out.println(A[i][j] +" + "+ B[i][j]);
                C[i][j] = A[i][j] + B[i][j];
                //System.out.println(" Add "+C[i][j]);
            }
        }
        return C;
    }
    

    public static Integer[][] arraySub(Integer[][] A, Integer[][] B){
        int n = A.length;
        Integer[][] C = new Integer[n][n];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                //System.out.println(A[i][j] +" - "+ B[i][j]);
                C[i][j] = A[i][j] - B[i][j];
                //System.out.println("Sub "+C[i][j]);
            }
        }
        return C;
    }
    

    public static Integer[][] naive(Integer[][] matrixA, Integer[][] matrixB){
        //base case
        Integer[][] answer = new Integer[matrixA.length][matrixA.length];
        if(matrixA.length <= 1){
            //System.out.println(A[0][0]+" "+B[0][0]);
            answer[0][0] = matrixA[0][0]*matrixB[0][0];
            //System.out.println(answer[0][0]);
            return answer;
        } 
        Integer[][][] aThree = divide(matrixA);
        Integer[][][] bThree = divide(matrixB);
        //SUB ARRAYS
        Integer[][] a = aThree[0];
        Integer[][] b = aThree[1];
        Integer[][] c = aThree[2];
        Integer[][] d = aThree[3];

        Integer[][] e = bThree[0];
        Integer[][] f = bThree[1];
        Integer[][] g = bThree[2];
        Integer[][] h = bThree[3];
        
        Integer[][] p1 = naive(a, e);
        Integer[][] p2 = naive(b, g);
        Integer[][] p3 = naive(a, f);
        Integer[][] p4 = naive(b, h);
        Integer[][] p5 = naive(c, e);
        Integer[][] p6 = naive(d, g);
        Integer[][] p7 = naive(c, f);
        Integer[][] p8 = naive(d, h);
        //stitch it all back together
        /* c11 = p1 + p2
         * c12 = p3 + p4
         * c21 = p5 + p6
         * c22 = p7 + p8 
        */
        //System.out.println("The c's");
        Integer[][] c11 = arrayAdd(p1, p2);
        Integer[][] c12 = arrayAdd(p3, p4);
        Integer[][] c21 = arrayAdd(p5, p6);
        Integer[][] c22 = arrayAdd(p7, p8);

        answer = stitch(c11, c12, c21, c22);
         
        return answer;
    }

    public static Integer[][][] divide(Integer[][] matrixes){
        //4 new matrixes
        //System.out.println("DIVIDE");
        int size = matrixes.length;

        int div = size/2;

        Integer[][] a = new Integer[div][div];
        Integer[][] b = new Integer[div][div];
        Integer[][] c = new Integer[div][div];
        Integer[][] d = new Integer[div][div];
        for(int i = 0; i < div; i++){
            for(int j = 0; j< div;j++){
                a[i][j] = matrixes[i][j];
                b[i][j] = matrixes[i][j+div];
                c[i][j] = matrixes[i + div][j];
                d[i][j] = matrixes[i+div][j+div];
               /* System.out.println("a: "+a[i][j]);
                System.out.println("b: "+b[i][j]);
                System.out.println("c: "+c[i][j]);
                System.out.println("d: "+d[i][j]);*/
                
            }
        }

        Integer[][][] temp = {a,b,c,d};
        //System.out.println(temp[0][0][0]);
        return temp;
    }

    
    public static Integer[][] bruteForce(Integer[][] A, Integer[][] B){
        //first row times all collumns
        //2nd row times all collumns
        int size = A.length;
        
        Integer[][] answer = new Integer[size][size];
        for(int i = 0; i < size; i++){
            //controls A's rows
            //System.out.print(i +"  :  ");
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
                //System.out.print(answer[i][j]+" ");
            }  
            //System.out.println("");
        }
        return answer;
    }
}