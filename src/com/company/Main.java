package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
                Scanner input = new Scanner(System.in);
                String T [] = new String [9];
                boolean gameisover = false ;
                boolean done = false;
                boolean turnX = true;
                int num ;
                int move = 1;
                int mode = 0;

                System.out.println("Welcome to Narnia's Tic Tac Toe");
                System.out.println("Instructions: Enter a valid number that corresponds to the cell you desire. The cells are numbered as followed: " );
                System.out.println(" 0 " + " 1 " + " 2 ");
                           System.out.println(" 3 " + " 4 " + " 5 ");
                System.out.println(" 6 " + " 7 " + " 8 ");
                System.out.println();

                for (int i=0; i < 9; i++ )
                {
                    T[i] = "_";
                }

                while (!done)
                {
                    System.out.println("What mode would you like to play? Player vs Player (PvP) or Player vs Computer (PvC) or Computer v Computer (CvC)");
                    System.out.println("Please enter 1 for PvP, please enter 2 for PvC, please enter 3 for CvC");
                    mode = input.nextInt();
                    input.nextLine();
                    if (mode==1 || mode==2 || mode==3 )
                        done = true;
                    else
                        System.out.println("Enter a valid mode");
                }

                while (!gameisover)
                {
                    if (mode==1 && turnX)
                        num = getuserinput(T, "X");
                    else if (mode==1 && !turnX)
                        num = getuserinput(T, "O");
                    else if (mode==2 && turnX )
                        num = getuserinput(T, "X");
                    else if (mode==2 && !turnX)
                        num = getcompinput(T,"O", move);
                    else
                    {
                        if (turnX)
                            num = getcompinput(T,"X", move);
                        else
                            num = getcompinput(T,"O", move);
                    }


                    if (turnX)
                        T[num] = "X";
                    else
                        T[num] = "O";

                    System.out.println("Player " + T[num] + " has played at cell " + num);
                    System.out.println();
                    System.out.println(T[0] + " " + T[1] + " " + T[2]);
                    System.out.println(T[3] + " " + T[4] + " " + T[5]);
                    System.out.println(T[6] + " " + T[7] + " " + T[8]);
                    System.out.println();

                    if (checkboard(T, T[num]))
                    {
                        System.out.println("Player " + T[num] + " has won the game");
                        gameisover = true;
                    }
                    else if (move==9)
                    {
                        System.out.println("The game is a tie!");
                        gameisover = true;
                    }
                    else
                    {
                        move++;

                        if (turnX)
                            turnX = false;
                        else
                            turnX = true;
                    }


                }//while loop

            } //main

            public static boolean checkline(String A, String B, String C, String check)
            {
                //System.out.println(A + B + C + check);
                if(A.equals(check) && B.equals(check) && C.equals(check))
                    return true;
                else
                    return false;

            }

            public static boolean checkboard(String G[], String check)
            {
                if (checkline(G[0],G[1],G[2], check))
                    return true;
                else if (checkline(G[3],G[4],G[5], check))
                    return true;
                else if (checkline(G[6],G[7],G[8], check))
                    return true;
                else if (checkline(G[0],G[3],G[6], check))
                    return true;
                else if (checkline(G[1],G[4],G[7], check))
                    return true;
                else if (checkline(G[2],G[5],G[8], check))
                    return true;
                else if (checkline(G[0],G[4],G[8], check))
                    return true;
                else if (checkline(G[2],G[4],G[6], check))
                    return true;
                else
                    return false;
            }

            public static int getuserinput (String G[], String check)
            {
                Scanner input = new Scanner(System.in);
                boolean valid = false;
                int uservalue = 0 ;
                while (!valid)
                {
                    System.out.println("Please enter a value from 0-8 for an empty cell, Player " + check );
                    uservalue = input.nextInt();
                    input.nextLine();
                    if ((uservalue < 0) || (uservalue > 8))
                    {
                        System.out.println("Invalid Input - Please enter a valid value");
                    }
                    else if(G[uservalue].equals("X") || G[uservalue].equals("O"))
                        System.out.println("Hey, cheater, you can't override an existing cell");
                    else
                        valid = true;
                }
                return uservalue;
            }

            public static int getcompinput (String G[], String check, int move)
            {
                int i;
                String checkblock;

                if (check.equals("X"))
                    checkblock = "O";
                else
                    checkblock = "X";

                i = possiblewin(G,check);
                //System.out.println(i + check);
                if (i != -1)
                    return i;

                i = possiblewin(G, checkblock);
                //System.out.println(i + checkblock);
                if (i != -1)
                    return i;

                if (G[4].equals("_"))
                    return 4;

                if (move == 2)
                    return 0;

                if (move == 3 && G[8].equals("_"))
                    return 8;

                if (move == 4 && G[4].equals(checkblock))
                    return 2;

                if (move == 4 && G[4].equals(check))
                {
                    if (G[3].equals("_") && G[5].equals("_"))
                        return 3;
                    else
                        return 1;

                }

                for (i=0;i<9;i++)
                {
                    if (G[i].equals("_"))
                        return i;
                }

                return i ;
            }

            public static int possiblewin (String G[], String check)
            {
                if (G[0].equals("_"))
                {
                    if ((G[1].equals(check) && G[2].equals(check)) || (G[3].equals(check) && G[6].equals(check)) || (G[4].equals(check) && G[8].equals(check))  )
                        return 0;
                }

                if (G[1].equals("_"))
                {
                    if ((G[0].equals(check) && G[2].equals(check)) || (G[4].equals(check) && G[7].equals(check)) )
                        return 1;
                }

                if (G[2].equals("_"))
                {
                    if ((G[0].equals(check) && G[1].equals(check)) || (G[5].equals(check) && G[8].equals(check)) || (G[4].equals(check) && G[6].equals(check))  )
                        return 2;
                }

                if (G[3].equals("_"))
                {
                    if ((G[0].equals(check) && G[6].equals(check)) || (G[4].equals(check) && G[5].equals(check))  )
                        return 3;
                }

                if (G[4].equals("_"))
                {
                    if ((G[1].equals(check) && G[7].equals(check)) || (G[3].equals(check) && G[5].equals(check)) || (G[0].equals(check) && G[8].equals(check)) || (G[2].equals(check) && G[6].equals(check))  )
                        return 4;
                }

                if (G[5].equals("_"))
                {
                    if ((G[2].equals(check) && G[8].equals(check)) || (G[3].equals(check) && G[4].equals(check)) )
                        return 5;
                }

                if (G[6].equals("_"))
                {
                    if ((G[0].equals(check) && G[3].equals(check)) || (G[4].equals(check) && G[2].equals(check)) || (G[7].equals(check) && G[8].equals(check))  )
                        return 6;
                }

                if (G[7].equals("_"))
                {
                    if ((G[6].equals(check) && G[8].equals(check)) || (G[1].equals(check) && G[4].equals(check)) )
                        return 7;
                }

                if (G[8].equals("_"))
                {
                    if ((G[0].equals(check) && G[4].equals(check)) || (G[5].equals(check) && G[2].equals(check)) || (G[6].equals(check) && G[7].equals(check))  )
                        return 8;
                }
                return -1;

            }


        } //class

