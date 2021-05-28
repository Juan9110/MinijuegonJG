package com.example.minijuegonjg;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Juego  extends Activity {

    //VARIABLES PARA LOS COMPONENTES DE LA VISTA
    ImageButton imb00,imb01, imb02, imb03, imb04, imb05, imb06,imb07, imb08, imb09, imb10, imb11, imb12, imb13, imb14, imb15;
    ImageButton[] tablero = new ImageButton[16];
    Button botonReiniciar, botonSalir;
    TextView textoPuntuacion;
    TextView txt_aciertos;
    MediaPlayer sonidos_a, sonido_g, sonido_p, sonido_fallar;

    int puntuacion;
    int aciertos;

    //IMAGENES
    int[] imagenes;
    int fondo1;
    
    //VARIABLE DEL JUEGO

    ArrayList<Integer> arrayDesordenado;
    ImageButton PRIMERO;
    int numeroPrimero, numeroSegundo;

    boolean bloque = false;
    final Handler handler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        init();
    }

    private void cargarTablero(){
;       imb00 = findViewById(R.id.boton00);
        imb01 = findViewById(R.id.boton01);
        imb02 = findViewById(R.id.boton02);
        imb03 = findViewById(R.id.boton03);
        imb04 = findViewById(R.id.boton04);
        imb05 = findViewById(R.id.boton05);
        imb06 = findViewById(R.id.boton06);
        imb07 = findViewById(R.id.boton07);
        imb08 = findViewById(R.id.boton08);
        imb09 = findViewById(R.id.boton09);
        imb10 = findViewById(R.id.boton10);
        imb11 = findViewById(R.id.boton11);
        imb12 = findViewById(R.id.boton012);
        imb13 = findViewById(R.id.boton13);
        imb14 = findViewById(R.id.boton14);
        imb15 = findViewById(R.id.boton15);

        tablero[0] = imb00;
        tablero[1] = imb01;
        tablero[2] = imb02;
        tablero[3] = imb03;
        tablero[4] = imb04;
        tablero[5] = imb05;
        tablero[6] = imb06;
        tablero[7] = imb07;
        tablero[8] = imb08;
        tablero[9] = imb09;
        tablero[10] = imb10;
        tablero[11] = imb11;
        tablero[12] = imb12;
        tablero[13] = imb13;
        tablero[14] = imb14;
        tablero[15] = imb15;


    }

    private void cargarBotones(){
        botonReiniciar = findViewById(R.id.botonJuegoReiniciar);
        botonSalir = findViewById(R.id.botonJuego_Salir);
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void cargarTexto(){
        textoPuntuacion = findViewById(R.id.texto_puntuacion);
        puntuacion = 0;
        aciertos = 0;
        textoPuntuacion.setText("Puntuacion:  " + puntuacion);
        txt_aciertos = findViewById(R.id.txt_aciertos);
        txt_aciertos.setText("Aciertos: " + aciertos);

    }
    
    private void cargarImagenes(){
        imagenes = new int[]{
                R.drawable.la0,
                R.drawable.la1,
                R.drawable.la2,
                R.drawable.la3,
                R.drawable.la4,
                R.drawable.la5,
                R.drawable.la6,
                R.drawable.la7,
                
        };
     fondo1= R.drawable.fondo1;
    }
    
   private ArrayList<Integer> barajar(int Longitud){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i=0; i<Longitud*2; i++){
            result.add(i % Longitud);
        }
        Collections.shuffle(result);
       System.out.println(Arrays.toString(result.toArray()));
        return result;
   }

   private void comprobar(int i, final ImageButton imgb) {
       if (PRIMERO == null) {
           PRIMERO = imgb;
           PRIMERO.setScaleType(ImageView.ScaleType.CENTER_CROP);
           PRIMERO.setImageResource(imagenes[arrayDesordenado.get(i)]);
           PRIMERO.setEnabled(false);
           sonidos_a.seekTo(0);
           sonidos_a.start();
           numeroPrimero = arrayDesordenado.get(i);
       } else {
           bloque = true;
           imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
           imgb.setImageResource(imagenes[arrayDesordenado.get(i)]);
           imgb.setEnabled(false);
           numeroSegundo = arrayDesordenado.get(i);
           if (numeroPrimero == numeroSegundo) {
               sonido_p.seekTo(0);
               sonido_p.start();
               PRIMERO = null;
               bloque = false;
               aciertos++;
               puntuacion++;
               textoPuntuacion.setText("Puntuacion: " + puntuacion);
               txt_aciertos.setText("Aciertos: " + aciertos);
               if (aciertos == imagenes.length) {
                   Toast toast = Toast.makeText(getApplicationContext(), "Felicidades has ganado!! ", Toast.LENGTH_SHORT);
                   toast.show();
                   sonido_g.seekTo(0);
                   sonido_g.start();
               }
           } else {
               //no son iguales
             sonido_fallar.seekTo(0);
             sonido_fallar.start();
              handler.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      PRIMERO.setScaleType(ImageView.ScaleType.CENTER_CROP);
                      PRIMERO.setImageResource(fondo1);
                      PRIMERO.setEnabled(true);
                      imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                      imgb.setImageResource(fondo1);
                      imgb.setEnabled(true);
                      bloque = false;
                      PRIMERO = null;
                      puntuacion--;
                      textoPuntuacion.setText("Puntuacion: " + puntuacion);
                  }
              },1000);
           }

       }
   }
    

    private void init(){
        cargarTablero();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        arrayDesordenado = barajar(imagenes.length);
        for(int i=0; i<tablero.length; i++) {
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
            //tablero[i].setImageResource(rinegan);
            sonido_g = MediaPlayer.create(this, R.raw.aleluya_ganar);
            sonido_p = MediaPlayer.create(this, R.raw.un_par);
            sonidos_a = MediaPlayer.create(this, R.raw.abrir_carta);
            sonido_fallar = MediaPlayer.create(this,R.raw.fallar_carta);
        }

       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               for(int i=0; i<tablero.length; i++) {
                   tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                   //tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
                   tablero[i].setImageResource(fondo1);
               }
           }
       }, 1000);

        for(int i=0; i<tablero.length; i++){
            final int j = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!bloque)
                        comprobar(j, tablero[j]);

                }
            });
        }

    }

}

