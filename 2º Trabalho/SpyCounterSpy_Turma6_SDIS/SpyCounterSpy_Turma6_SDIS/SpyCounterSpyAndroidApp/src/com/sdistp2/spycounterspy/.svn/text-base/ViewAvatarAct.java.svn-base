package com.sdistp2.spycounterspy;


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
import entidades.Avatar;

/**
 * Actividade para mostrar o avatar escolhido
 * @author Carlos Frias
 *
 */
public class ViewAvatarAct extends Activity implements OnClickListener{

	private Button back, gotoMap, sair;
	private TextView nomeAvatar, atackItemDescription, defenseItemDescription, attackName, defenseName;
	private Avatar avatar;
	private ImageView imageview;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewavatar);

        nomeAvatar = (TextView) findViewById(R.id.NomeLabelViewAvatar);
        nomeAvatar.setTextColor(Color.CYAN);
        atackItemDescription = (TextView) findViewById(R.id.textViewAtackItemDescription);
        atackItemDescription.setTextColor(Color.CYAN);
        defenseItemDescription = (TextView) findViewById(R.id.textViewDefenseItemDescription);
        defenseItemDescription.setTextColor(Color.CYAN);
        attackName = (TextView) findViewById(R.id.ViewAvatarAttackName);
        defenseName = (TextView) findViewById(R.id.ViewAvatarDefenseName);
        
        imageview = (ImageView) findViewById(R.id.imageView1);

        //A carregar o avatar
        Bundle bundle = this.getIntent().getExtras();
        avatar = (Avatar) bundle.getSerializable("key");
        nomeAvatar.setText(avatar.toString());
        attackName.setText("Arma de Ataque: " + avatar.getAtackItem().getName());
        defenseName.setText("Arma de Defesa: " + avatar.getDefenseItem().getName());
        atackItemDescription.setText(avatar.getAtackItem().getDescription() + "\n\nPONTOS DE ATAQUE: " + avatar.getAtackItem().getAtack() + "\nALCANCE: " + avatar.getAtackItem().getRange() + " km\n");
        defenseItemDescription.setText(avatar.getDefenseItem().getDescription() + "\n\nPONTOS DE DEFESA: " + avatar.getDefenseItem().getDefense() + "\n");
        
        //A mostrar o avatar icon
        String iconSelected = avatar.getType();
        if(iconSelected.equals("Egipcio")){
        	imageview.setImageResource(R.drawable.egipcian);	
        }
        else if(iconSelected.equals("Gaules")){
        	imageview.setImageResource(R.drawable.gaules);	
        }
        else if(iconSelected.equals("Viking")){
        	imageview.setImageResource(R.drawable.viking);	
        }
        else if(iconSelected.equals("Romano")){
        	imageview.setImageResource(R.drawable.roman2);	
        }
        else{
        	imageview.setImageResource(R.drawable.default_image);
        }
        back = (Button) findViewById(R.id.BackOnViewAvatar);
        back.setOnClickListener(this);
        gotoMap = (Button) findViewById(R.id.GotoMapOnViewAvatar);
        gotoMap.setOnClickListener(this);
        sair = (Button) findViewById(R.id.ViewAvatarSair);
        sair.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.BackOnViewAvatar){
			//recuar para a activity (Equipar) enviando o avatar...(desiquipar...)
			int points = avatar.getPoints() + avatar.getAtackItem().getCost() + avatar.getDefenseItem().getCost();
			avatar.setPoints(points);
			avatar.setAtackItem(null);
			avatar.setDefenseItem(null);
			
			Bundle bundle = new Bundle();
			bundle.putSerializable("key", avatar);
			Intent newIntent = new Intent(this.getApplicationContext(), EquipActivity.class);
			newIntent.putExtras(bundle);
			startActivityForResult(newIntent, 0);
		}else if(v.getId() == R.id.GotoMapOnViewAvatar){
			//Carrega o avatar e abre a activitie ViewMap
			Bundle bundle = new Bundle();
			bundle.putSerializable("key", avatar);
			Intent newIntent = new Intent(this.getApplicationContext(), ViewMap.class);
			newIntent.putExtras(bundle);
			startActivityForResult(newIntent, 0);
		}else if(v.getId() == R.id.ViewAvatarSair){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Tem a certeza que pretende sair?")
		    .setCancelable(false)
		    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {
		            //TODO: enviar para o servidor os dados do avatar e sair da aplicação, voltando à actividade inicial
					startActivity(new Intent(ViewAvatarAct.this.getApplicationContext(), LoginActivity.class));
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
