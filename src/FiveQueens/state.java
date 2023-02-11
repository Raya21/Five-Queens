/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FiveQueens;

/**
 *
 * @author Msys
 */
public class state implements Cloneable {
    public int [][]m=new int [5][5];
    
    public state ()
    {
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                m[i][j]=0;
            }
        }
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
}
