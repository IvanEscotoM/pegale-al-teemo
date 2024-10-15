
package Juego;
import Juego.BotonConBackground;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;




public class Juego {
   int anchuraTablero = 600;
   int altoTablero = 650;
   JFrame ventana = new JFrame("Pegale-Al-Teemo");
   int tiempo=1200;
   JLabel etiquetaTexto = new JLabel();
   JPanel panelDeTexto= new JPanel();
   JPanel panelTablero= new JPanel();
   JPanel panelGameOver= new JPanel();
   BotonConBackground[] botones = new BotonConBackground[9];
   JButton cuadroTeemo;
   JButton cuadroHongo;
   JButton cuadroHongo2;
   ImageIcon teemo;
   ImageIcon pasto;
   ImageIcon hongo;
   ImageIcon gameOver;
   ImageIcon burla;
   ImageIcon burla2;
   Image icono;
   JPanel panelDeTiempo=new JPanel();
   JLabel cronometro;
        Random azar = new Random();
   Timer tiempoTeemo;
   Timer tiempoHongo;
   Timer tiempoHongo2;
   Timer tiempoJuego;
   int puntuacion=0;
   
   int tiempoRestante=30;
   public Juego(){
        
       ventana.setSize(anchuraTablero,altoTablero);
        ventana.setLocationRelativeTo(null);
        icono=new ImageIcon(getClass().getResource("/icono.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        
        etiquetaTexto.setFont(new Font("Arial",Font.PLAIN,50));
        etiquetaTexto.setHorizontalAlignment(JLabel.CENTER);
        cronometro= new JLabel();
        cronometro.setFont(new Font("Arial",Font.ITALIC,30));
        cronometro.setHorizontalAlignment(JLabel.CENTER);
        cronometro.setOpaque(true);
        panelDeTiempo.setLayout(new BorderLayout());
        panelDeTiempo.add(cronometro);
        correrTiempo();
        etiquetaTexto.setOpaque(true);
        
   
        etiquetaTexto.setText("Puntuacion: "+puntuacion);
        etiquetaTexto.setOpaque(true);
        panelDeTexto.setLayout(new BorderLayout());
        panelDeTexto.add(etiquetaTexto);
        ventana.add(panelDeTexto, BorderLayout.NORTH);
        ventana.add(panelDeTiempo,BorderLayout.SOUTH);
        panelTablero.setLayout(new GridLayout(3,3)); 
        ventana.add(panelTablero);
        
        Image pastoImg = new ImageIcon(getClass().getResource("/pasto.png")).getImage();
        pasto= new ImageIcon(pastoImg);
        //Image teemoImg = new ImageIcon(getClass().getResource("/teemo.gif")).getImage();
        teemo = new ImageIcon(getClass().getResource("/teemo.gif"));
        Image hongoImg = new ImageIcon(getClass().getResource("/hongo.png")).getImage();
        hongo = new ImageIcon(hongoImg.getScaledInstance(150,150,java.awt.Image.SCALE_SMOOTH));
                    for (int i =0; i<9;i++){
                    BotonConBackground cuadro= new BotonConBackground(pastoImg);
                    botones[i]=cuadro;
                    panelTablero.add(cuadro);
                    cuadro.setFocusable(false);
                    cuadro.setIcon(pasto);
                    cuadro.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                           JButton cuadro =(JButton) e.getSource();
                           if(cuadro==cuadroTeemo){
                               puntuacion+=300;
                               if(puntuacion%3000==0&&tiempo>400){
                                   tiempo-=200;
                                   tiempoTeemo.stop();
                                   tiempoTeemo.start();
                               }
                               etiquetaTexto.setText("Puntuacion: "+puntuacion);
                           } else if(cuadro==cuadroHongo||cuadro==cuadroHongo2){
                               tiempoHongo.stop();
                               tiempoTeemo.stop();
                               tiempoHongo2.stop();
                               tiempoJuego.stop();
                               panelGameOver.setLayout(new GridLayout(1,1));
                               JButton botonGameOver= new JButton();
                               panelGameOver.add(botonGameOver);
                               gameOver = new ImageIcon(getClass().getResource("/gameOver.gif"));
                               botonGameOver.setIcon(gameOver);
                               ventana.add(panelGameOver);
                               etiquetaTexto.setText("GG EZZZZZZZ");
                               panelTablero.setVisible(false);
                               panelDeTiempo.setVisible(false);
                               panelGameOver.setVisible(true);
                               JButton nuevoJuego= new JButton();
                               nuevoJuego.setText("Jugar nuevamente");
                               ventana.add(nuevoJuego, BorderLayout.SOUTH);
                               nuevoJuego.addActionListener(new ActionListener(){
                                   @Override
                                   public void actionPerformed(ActionEvent e){
                                        panelGameOver.removeAll();
                                        nuevoJuego.setVisible(false);
                                        tiempoRestante=31;
                                        tiempoHongo2.start();
                                        tiempoTeemo.start();
                                        tiempoHongo.start();
                                        tiempoJuego.start();
                                        puntuacion=0;
                                        etiquetaTexto.setText("Puntuacion: "+puntuacion);
                                       try {
                                           Thread.sleep(1000);
                                       } catch (InterruptedException ex) {
                                           Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                        panelGameOver.setVisible(false);
                                        panelTablero.setVisible(true);
                                        panelDeTiempo.setVisible(true);
                                       
                                   }
                               });
                           } 
                        }
                    });
                }

                tiempoJuego= new Timer(1000,new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {

                       if(tiempoRestante>0){
                           tiempoRestante--;
                           correrTiempo();
                       } else{
                               tiempoHongo.stop();
                               tiempoHongo2.stop();
                               tiempoTeemo.stop();
                               tiempoJuego.stop();
                               panelGameOver.setLayout(new GridLayout(1,1));
                               JButton botonGameOver= new JButton();
                               panelGameOver.add(botonGameOver);
                               gameOver = new ImageIcon(getClass().getResource("/winGame.gif"));
                               botonGameOver.setIcon(gameOver);
                               ventana.add(panelGameOver);
                               etiquetaTexto.setText("GG, report JG");
                               panelTablero.setVisible(false);
                               panelDeTiempo.setVisible(false);
                               JButton nuevoJuego= new JButton();
                               nuevoJuego.setText("Jugar nuevamente");
                               nuevoJuego.setVisible(true);
                               ventana.add(nuevoJuego, BorderLayout.SOUTH);
                               nuevoJuego.addActionListener(new ActionListener(){
                                   @Override
                                   public void actionPerformed(ActionEvent e){
                                        panelGameOver.removeAll();
                                        nuevoJuego.setVisible(false);
                                        tiempoRestante=31;
                                        tiempoHongo2.start();
                                        tiempoTeemo.start();
                                        tiempoHongo.start();
                                        tiempoJuego.start();
                                        puntuacion=0;
                                        etiquetaTexto.setText("Puntuacion: "+puntuacion);
                                       try {
                                           
                                           Thread.sleep(1000);
                                       } catch (InterruptedException ex) {
                                           Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                        panelGameOver.setVisible(false);
                                        panelDeTiempo.setVisible(true);
                                        panelTablero.setVisible(true);
                                       
                                   }
                               });
                               panelGameOver.setVisible(true);
                               ;
                       }

                   }
                });

                tiempoTeemo= new Timer(tiempo, new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.out.println(tiempo);
                        if(cuadroTeemo!=null){
                           cuadroTeemo.setIcon(pasto);
                           cuadroTeemo=null;
                        }
                        int numeroAzar= azar.nextInt(9);
                        JButton cuadro= botones[numeroAzar];
                        if(cuadroHongo==cuadro){
                            return;
                        } 
                        cuadroTeemo=cuadro;
                        cuadroTeemo.setIcon(teemo);
                    }
                    });
                tiempoHongo= new Timer(900, new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       if(cuadroHongo!=null){
                           cuadroHongo.setIcon(pasto);
                           cuadroHongo=null;
                       }
                       int numeroAzar2=azar.nextInt(9);
                       JButton cuadro2= botones[numeroAzar2];
                       if(cuadroTeemo==cuadro2){
                           return;
                       }
                       cuadroHongo=cuadro2;
                       cuadroHongo.setIcon(hongo);
                   }
                });
                tiempoHongo2= new Timer(600, new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       if(cuadroHongo2!=null){
                           cuadroHongo2.setIcon(pasto);
                           cuadroHongo2=null;
                       }
                       int numeroAzar2=azar.nextInt(9);
                       JButton cuadro3= botones[numeroAzar2];
                       if(cuadroTeemo==cuadro3){
                           return;
                       }
                       cuadroHongo2=cuadro3;
                       cuadroHongo2.setIcon(hongo);
                   }
                });
                tiempoHongo2.start();
                tiempoTeemo.start();
                tiempoHongo.start();
                tiempoJuego.start();
                ventana.setVisible(true);
        
         }
   
   public void correrTiempo(){
       int minutos= tiempoRestante/60;
       int segundos =tiempoRestante%60;
       cronometro.setText(String.format("%02d:%02d",minutos,segundos));
   }
}
