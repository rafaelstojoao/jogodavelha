package com.fai.jogodavelha;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.sql.Date;


/**
 *
 * @author Rafael
 */
public class Principal {
    
    public static void main(String[] args) {
        int linhajogada = 0, colunajogada = 0;
        boolean primeirajogada = true;
        Jogador j1 =  new Jogador();
        JogoDaVelha tab = new JogoDaVelha();
        
        Scanner leitor  = new Scanner(System.in);
        System.out.println("Por favor, informe o nome do jogador");
        j1.nome = leitor.next();
 
        System.out.println("Qual time é o do jogador? X ou O");
        tab.timeJogador = leitor.next();
 
        if(tab.timeJogador.equals("X") || tab.timeJogador.equals("x")){
            tab.timeComputador = "O";
        }
        else if(tab.timeJogador.equals("O") || tab.timeJogador.equals("o")){
            tab.timeComputador = "X";
        }
        
        tab.inicializaTabuleiro();
        
        System.out.println(" O jogador"+j1.nome+" joga com "+tab.timeJogador);
        
        
        int quemjoga; //1 quando humano, 0 quando pc
        
        Random rand = new Random();
        
        quemjoga = rand.nextInt(2);
        System.out.println("Quem inicia o jogo é o:");
        if(quemjoga ==1){
            System.out.println(" - Humano");
        }
        else
            System.out.println("- Computador");
        
        
        int jogadas = 0;
        while(jogadas < 9 && !tab.verificaVitoria() ){
            
            if(quemjoga == 0){ //PC
                
                while(tab.tabulero[linhajogada][colunajogada] != ""  || primeirajogada == true){
                    linhajogada = rand.nextInt(3);
                    colunajogada = rand.nextInt(3);
                    primeirajogada = false;
                }
                tab.fazJogada(linhajogada, colunajogada, tab.timeComputador);
                quemjoga = 1;
            }
            else if(quemjoga == 1){
                 while(tab.tabulero[linhajogada][colunajogada] != ""  || primeirajogada ==true){
                    System.out.println("Informe a linha que quer jogar");
                    linhajogada = leitor.nextInt();
                    System.out.println("Informe a coluna que quer jogar");
                    colunajogada = leitor.nextInt();
                    primeirajogada = false;

                }
                tab.fazJogada(linhajogada, colunajogada, tab.timeJogador);
                quemjoga = 0;
            }

            tab.imprimeTabuleiro();
            jogadas++;
        }
        
        String resultadoPartida = tab.verificaVitoria() ? (quemjoga == 1 ? "Computador" : "Jogador") : "Empate";
        
        // Conectar ao banco de dados e inserir dados da partida
        salvarDadosPartida(j1.nome, tab.timeJogador, resultadoPartida, jogadas);

        leitor.close();
    }   

    public static void salvarDadosPartida(String nomeJogador, String timeJogador, String resultadoPartida, int quantJogadas) {
        String url = "jdbc:mysql://localhost:3306/jogo_da_velha"; // Substitua pelo URL do seu banco de dados
        String usuario = "root"; // Substitua pelo seu usuário do banco de dados
        String senha = ""; // Substitua pela sua senha do banco de dados

        String sql = "INSERT INTO partida (nome_Jogador, time_Jogador, resultado_Partida, quant_Jogadas, data_Partida) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nomeJogador);
            pstmt.setString(2, timeJogador);
            pstmt.setString(3, resultadoPartida);
            pstmt.setInt(4, quantJogadas);
            pstmt.setDate(5, new Date(System.currentTimeMillis()));

            pstmt.executeUpdate();
            System.out.println("Dados da partida salvos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar dados da partida: " + e.getMessage());
        }
    }
}


