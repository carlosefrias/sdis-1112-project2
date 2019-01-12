package com.sdistp2.spycounterspy;

import java.util.ArrayList;
import java.util.Collections;

import entidades.Avatar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewAttackAct extends Activity implements OnClickListener{
	ImageView attackimage;
	TextView textpoints, avatarWinner;
	Button backButton, sair;
	Avatar avatar;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewattack);

        Bundle bundle = this.getIntent().getExtras();
        avatar = (Avatar) bundle.getSerializable("key");
        String ganhou = (String) bundle.getSerializable("ganhou");
        String pontos = (String) bundle.getSerializable("pontos");
        
        attackimage = (ImageView) findViewById(R.id.ViewAttackImage);    
        backButton = (Button) findViewById(R.id.ViewAttackBackButton);
        sair = (Button) findViewById(R.id.ViewAttackSair);
        textpoints = (TextView) findViewById(R.id.ViewAttackPoints);
        avatarWinner = (TextView) findViewById(R.id.ViewAttackWinner);

        textpoints.setTextColor(Color.CYAN);
        textpoints.setText(pontos);
        avatarWinner.setText("Vencedor do Duelo: " + ganhou);
        
        ArrayList<Integer> imagens = new ArrayList<Integer>();
        imagens.add(R.drawable.attack1);
        imagens.add(R.drawable.attack2);
        imagens.add(R.drawable.attack3);
        imagens.add(R.drawable.attack4);
        imagens.add(R.drawable.attack5);
        imagens.add(R.drawable.attack7);
        Collections.shuffle(imagens);
        
    	attackimage.setImageResource(imagens.get(0));
        
        backButton.setOnClickListener(this);
        sair.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.ViewAttackBackButton){			
			//recuar para uma activity anterior (Mostrar mapa) enviando o avatar...
			Bundle bundle = new Bundle();
			bundle.putSerializable("key", avatar);
			Intent newIntent = new Intent(this.getApplicationContext(), ViewMap.class);
			newIntent.putExtras(bundle);
			startActivityForResult(newIntent, 0);
		}
		else if(v.getId() == R.id.ViewAttackSair){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Tem a certeza que pretende sair?")
		    .setCancelable(false)
		    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		            //TODO: enviar para o servidor os dados do avatar e sair da aplicação, voltando à actividade inicial
					startActivity(new Intent(ViewAttackAct.this.getApplicationContext(), LoginActivity.class));
		        }
		    })
		    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		             dialog.cancel();
		        }
		    });
			AlertDialog alert = builder.create();
			alert.show();
		}
	}
}
