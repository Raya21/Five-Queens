/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FiveQueens;
import java.lang.Math;
import static java.lang.Math.exp;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Msys
 */
public class Simulated_Annealing_Algo {
     public static int maxT;
    
    public Simulated_Annealing_Algo()
    {
        maxT=1000;
    }
    
    
     public static float clac_temp(int i,int max_temp)
    {
        float Tc;
        Tc= (float) (max_temp/Math.log10(i));
        return Tc;
    }
    
   
    
   public static  ArrayList<state> find_Successors (state parent) throws CloneNotSupportedException
    {
     
       
        
        ArrayList<state> Successors =new ArrayList<state>();
        
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
              state child=new state();
              
              for(int r=0;r<5;r++)
              {
                  for(int c=0;c<5;c++)
                  {
                      child.m[r][c]=parent.m[r][c];
                  }
              }
              
              
       
              if(child.m[i][j]==0)
              {
                 
                      for(int r=0;r<5;r++)
                      {
                          if(child.m[r][j]==1)
                          {
                              child.m[r][j]=0;
                              break;
                              
                          }
                      }
                      
                     child.m[i][j]=1;
                     Successors.add(child);

                      
                 }
             }
            }
  
        
      
        
    
        return Successors;
    }
   
   
   public static  state chooseRanSucc (ArrayList<state> successors)
   {
       state succ=new state();
       Random x =new Random();
       int ranNum =x.nextInt(20);
       succ=successors.get(ranNum);
       
       for(int r=0;r<5;r++)
              {
                  for(int c=0;c<5;c++)
                  {
                      succ.m[r][c]=successors.get(ranNum).m[r][c];
                  }
              }
       return succ;
       
   }
   
   
 public static  int findrow(state s,int col) ///queen in which row in findHeuristic function
   {
       int i;
       state e=new state();
       
        for(int r=0;r<5;r++)
              {
                  for(int c=0;c<5;c++)
                  {
                      e.m[r][c]=s.m[r][c];
                  }
              }
              
       
       
       
       for ( i=0;i<5;i++)
       {
           if(e.m[i][col]==1)
           {
               break;
           }
       }
       return i;
   }
   
   
   
   public static  int findHeuristic (state s)
   {
       state e =new state();
       //e=s;
       
       
       for(int r=0;r<5;r++)
              {
                  for(int c=0;c<5;c++)
                  {
                      e.m[r][c]=s.m[r][c];
                  }
              }
       
       
       
       
       int numPairAttacking=0;
       for (int c=0;c<5;c++)
       {
           int r=findrow(s,c);
            
           //Attacking at the same row
           int row3=r;
           int clo3=c;
           
           while(true){
               clo3++;
               if(clo3>4)
               {
                   break;
               }
               
               else if (e.m[row3][clo3]==1)
               {
                   numPairAttacking++; 
               }
                   
           }
           
       
           
           //diagonally left up
            int row=r;
            int col=c;
           
           while(true)
           {
               row--;
               col++; 
               
               if(row<0||col>4)
               {
                   break;
               }
               
               
             else if(e.m[row][col]==1)
              {
                  numPairAttacking++; 
              }
               
              
           }
           
         
           //diagonally left down
           
            int row2=r;
            int col2=c;
           
           while(true)
           {
               row2++;
               col2++; 
               
               if(row2>4||col2>4)
               {
                   break;
               }
               
               
             else if(e.m[row2][col2]==1)
              {
                  numPairAttacking++; 
              }
               
               
           }
           
         
           
           
       }
       return -numPairAttacking;
   }
    
 public static   state  Sim_Anneal(state current_state) throws CloneNotSupportedException
    {
        
        state xcurr=new state();
        state xbest=new state();
        state xnext=new state();
        int error;
        Random m=new Random();
        int i=2;
        float cooling_temp;
                
       // xcurr=current_state;
         for(int r=0;r<5;r++)
              {
                  for(int c=0;c<5;c++)
                  {
                      xcurr.m[r][c]=current_state.m[r][c];
                  }
              }
         
        //xbest=xcurr;
        
        for(int r=0;r<5;r++)
              {
                  for(int c=0;c<5;c++)
                  {
                      xbest.m[r][c]=xcurr.m[r][c];
                  }
              }
        
        
        
        
        while(i<1000)
        {
        
         cooling_temp=clac_temp(i,maxT);
  
         
        ArrayList<state> successors_current=new ArrayList<state>();
        successors_current=find_Successors(xcurr);
        state b=new state();
        b=chooseRanSucc(successors_current);
       // xnext=chooseRanSucc(successors_current);
       
        for(int r=0;r<5;r++)
              {
                  for(int c=0;c<5;c++)
                  {
                      xnext.m[r][c]=b.m[r][c];
                  }
              }
             
        
        error=findHeuristic(xnext)-findHeuristic(xcurr);
        
        if(error>0)
        {
            //xcurr=xnext
             for(int r=0;r<5;r++)
              {
                  for(int c=0;c<5;c++)
                  {
                      xcurr.m[r][c]=xnext.m[r][c];
                  }
              }
             
             //if(xbest<xcurr) xbest<-- xcurr
             
             if(findHeuristic(xbest)<findHeuristic(xcurr))
             {
                 //xbest=xcurr
                  for(int r=0;r<5;r++)
              {
                  for(int c=0;c<5;c++)
                  {
                      xbest.m[r][c]=xcurr.m[r][c];
                  }
              }
             }
             
        }
            
       
        
        else if (exp(-error/cooling_temp) > m.nextDouble(0,1.0))
        {
             for(int r=0;r<5;r++)
              {
                  for(int c=0;c<5;c++)
                  {
                      xcurr.m[r][c]=xnext.m[r][c];
                  }
              }
        }
        
        
        i++;
       
        
        }
        
        
        return xbest;
    }
    
    
    
    public static void main(String args[]) throws CloneNotSupportedException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fiveQueensJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fiveQueensJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fiveQueensJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fiveQueensJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
            
      
    }
}
