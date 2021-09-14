package com.example.hellojapan;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] list_of_id = {R.id.btnA,
                            R.id.btnI,
                            R.id.btnU,
                            R.id.btnE,
                            R.id.btnO,
                            R.id.btnKa,
                            R.id.btnKi,
                            R.id.btnKu,
                            R.id.btnKe,
                            R.id.btnKo,
                            R.id.btnSa,
                            R.id.btnShi,
                            R.id.btnSu,
                            R.id.btnSe,
                            R.id.btnSo,
                            R.id.btnTa,
                            R.id.btnChi,
                            R.id.btnTsu,
                            R.id.btnTe,
                            R.id.btnTo,
                            R.id.btnNa,
                            R.id.btnNi,
                            R.id.btnNu,
                            R.id.btnNe,
                            R.id.btnNo,
                            R.id.btnHa,
                            R.id.btnHi,
                            R.id.btnFu,
                            R.id.btnHe,
                            R.id.btnHo,
                            R.id.btnMa,
                            R.id.btnMi,
                            R.id.btnMu,
                            R.id.btnMe,
                            R.id.btnMo,
                            R.id.btnYa,
                            R.id.btnYu,
                            R.id.btnYo,
                            R.id.btnRa,
                            R.id.btnRi,
                            R.id.btnRu,
                            R.id.btnRe,
                            R.id.btnRo,
                            R.id.btnWa,
                            R.id.btnWo,
                            R.id.btnN};

        int[] list_of_sound = {R.raw.a,
                            R.raw.i,
                            R.raw.u,
                            R.raw.e,
                            R.raw.o,
                            R.raw.ka,
                            R.raw.ki,
                            R.raw.ku,
                            R.raw.ke,
                            R.raw.ko,
                            R.raw.sa,
                            R.raw.shi,
                            R.raw.su,
                            R.raw.se,
                            R.raw.so,
                            R.raw.ta,
                            R.raw.chi,
                            R.raw.tsu,
                            R.raw.te,
                            R.raw.to,
                            R.raw.na,
                            R.raw.ni,
                            R.raw.nu,
                            R.raw.ne,
                            R.raw.no,
                            R.raw.ha,
                            R.raw.hi,
                            R.raw.fu,
                            R.raw.he,
                            R.raw.ho,
                            R.raw.ma,
                            R.raw.mi,
                            R.raw.mu,
                            R.raw.me,
                            R.raw.mo,
                            R.raw.ya,
                            R.raw.yu,
                            R.raw.yo,
                            R.raw.ra,
                            R.raw.ri,
                            R.raw.ru,
                            R.raw.re,
                            R.raw.ro,
                            R.raw.wa,
                            R.raw.wo,
                            R.raw.n};

        int[] list_of_img_h = {R.drawable.h_a,
                            R.drawable.h_i,
                            R.drawable.h_u,
                            R.drawable.h_e,
                            R.drawable.h_o,
                            R.drawable.h_ka,
                            R.drawable.h_ki,
                            R.drawable.h_ku,
                            R.drawable.h_ke,
                            R.drawable.h_ko,
                            R.drawable.h_sa,
                            R.drawable.h_shi,
                            R.drawable.h_su,
                            R.drawable.h_se,
                            R.drawable.h_so,
                            R.drawable.h_ta,
                            R.drawable.h_chi,
                            R.drawable.h_tsu,
                            R.drawable.h_te,
                            R.drawable.h_to,
                            R.drawable.h_na,
                            R.drawable.h_ni,
                            R.drawable.h_nu,
                            R.drawable.h_ne,
                            R.drawable.h_no,
                            R.drawable.h_ha,
                            R.drawable.h_hi,
                            R.drawable.h_fu,
                            R.drawable.h_he,
                            R.drawable.h_ho,
                            R.drawable.h_ma,
                            R.drawable.h_mi,
                            R.drawable.h_mu,
                            R.drawable.h_me,
                            R.drawable.h_mo,
                            R.drawable.h_ya,
                            R.drawable.h_yu,
                            R.drawable.h_yo,
                            R.drawable.h_ra,
                            R.drawable.h_ri,
                            R.drawable.h_ru,
                            R.drawable.h_re,
                            R.drawable.h_ro,
                            R.drawable.h_wa,
                            R.drawable.h_wo,
                            R.drawable.h_n};

        int[] list_of_img_k = {R.drawable.k_a,
                            R.drawable.k_i,
                            R.drawable.k_u,
                            R.drawable.k_e,
                            R.drawable.k_o,
                            R.drawable.k_ka,
                            R.drawable.k_ki,
                            R.drawable.k_ku,
                            R.drawable.k_ke,
                            R.drawable.k_ko,
                            R.drawable.k_sa,
                            R.drawable.k_shi,
                            R.drawable.k_su,
                            R.drawable.k_se,
                            R.drawable.k_so,
                            R.drawable.k_ta,
                            R.drawable.k_chi,
                            R.drawable.k_tsu,
                            R.drawable.k_te,
                            R.drawable.k_to,
                            R.drawable.k_na,
                            R.drawable.k_ni,
                            R.drawable.k_nu,
                            R.drawable.k_ne,
                            R.drawable.k_no,
                            R.drawable.k_ha,
                            R.drawable.k_hi,
                            R.drawable.k_fu,
                            R.drawable.k_he,
                            R.drawable.k_ho,
                            R.drawable.k_ma,
                            R.drawable.k_mi,
                            R.drawable.k_mu,
                            R.drawable.k_me,
                            R.drawable.k_mo,
                            R.drawable.k_ya,
                            R.drawable.k_yu,
                            R.drawable.k_yo,
                            R.drawable.k_ra,
                            R.drawable.k_ri,
                            R.drawable.k_ru,
                            R.drawable.k_re,
                            R.drawable.k_ro,
                            R.drawable.k_wa,
                            R.drawable.k_wo,
                            R.drawable.k_n};


        ImageButton[] list_of_image_button= new ImageButton[list_of_id.length];
        for(int i=0; i<list_of_id.length; i++){
            list_of_image_button[i] = findViewById(list_of_id[i]);
        }

        //Switch between alphabets
        Button btnKata = findViewById(R.id.btnKata);
        Button btnHira = findViewById(R.id.btnHira);
        Button btnKataActive = findViewById(R.id.btnKataActive);
        Button btnHiraActive = findViewById(R.id.btnHiraActive);

        btnKataActive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                findViewById(R.id.hira).animate().alpha(0).setDuration(500);
                findViewById(R.id.kata).animate().alpha(1).setDuration(500);
                btnKata.animate().alpha(0).setDuration(500);
                btnHiraActive.animate().alpha(0).setDuration(500);
                btnKataActive.animate().alpha(1).setDuration(500);
                btnHira.animate().alpha(1).setDuration(500);
                for (int i = 0; i < list_of_id.length; i++) {
                    int finalI = i;
                    list_of_image_button[i].animate().withStartAction(new Runnable() {
                        @Override
                        public void run() {
                            list_of_image_button[finalI].setImageResource(list_of_img_k[finalI]);
                        }
                    }).rotationBy(360);
                }
            }
        });

        btnHiraActive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                findViewById(R.id.kata).animate().alpha(0).setDuration(500);
                findViewById(R.id.hira).animate().alpha(1).setDuration(500);
                btnKataActive.animate().alpha(0).setDuration(500);
                btnHira.animate().alpha(0).setDuration(500);
                btnHiraActive.animate().alpha(1).setDuration(500);
                btnKata.animate().alpha(1).setDuration(500);
                for (int i = 0; i < list_of_id.length; i++) {
                    int finalI = i;
                    list_of_image_button[i].animate().withStartAction(new Runnable() {
                        @Override
                        public void run() {
                            list_of_image_button[finalI].setImageResource(list_of_img_h[finalI]);
                        }
                    }).rotationBy(-360);
                }
            }
        });

        //Pronounce selected character
        for(int i=0;i<list_of_image_button.length;i++){
            int finalI = i;
            list_of_image_button[i].setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    MediaPlayer mp = MediaPlayer.create(MainActivity.this, list_of_sound[finalI]);
                    mp.start();
                }
            });
        }

    }

}