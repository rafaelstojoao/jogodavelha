package com.fai.jogodavelha;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author Rafael
 */
public class JogoDaVelha {
    public String timeJogador = "";
    public String timeComputador = "";
    public String[][] tabulero = new String[3][3];
    
    
    public void fazJogada(int linha, int coluna, String time){
       this.tabulero[linha][coluna] = time;
    }
    
    public void imprimeTabuleiro(){
        for (int i = 0; i < 3; i++) {
            System.out.println(this.tabulero[i][0]+" | "+this.tabulero[i][1]+" | "+this.tabulero[i][2]);
            System.out.println("----------------------------");
        }
    }
    
    public boolean verificaVitoria(){
        for (int i = 0; i < 3; i++) { // verifica linhas
            if(this.tabulero[i][0]==this.tabulero[i][1] &&
               this.tabulero[i][1]==this.tabulero[i][2] && 
                    this.tabulero[i][0] != ""){
                System.out.println(this.tabulero[i][0]+" Venceu na linha!!!");
                return true;
        }
        }
        for (int j = 0; j < 3; j++) { // verifica colunas
            if(this.tabulero[0][j]==this.tabulero[1][j]&&
               this.tabulero[1][j]==this.tabulero[2][j] && 
                    this.tabulero[j][0] != ""){
                System.out.println(this.tabulero[0][j]+" Venceu na coluna!!!");
                return true;
            }
        }
        if(this.tabulero[0][0]==this.tabulero[1][1] &&
                this.tabulero[1][1] == this.tabulero[2][2] &&
                this.tabulero[0][0] != ""){
            System.out.println(this.tabulero[0][0]+" Venceu na diagonal");
            return true;
        }
        
        if(this.tabulero[0][2]==this.tabulero[1][1] &&
                this.tabulero[1][1] == this.tabulero[2][0] &&
                this.tabulero[0][2] != ""){
            System.out.println(this.tabulero[0][2]+" Venceu na diagonal");
            return true;
        }
    
        return false;
    }
    public void inicializaTabuleiro(){
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tabulero[i][j]="";
            }
        }
    }
    
    
}
