package com.sdistp2.spycounterspy;


import java.util.ArrayList;

import rest.RestClass;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import entidades.AtackItem;
import entidades.Avatar;
import entidades.DefenseItem;
import entidades.DummyAvatar;
import entidades.Item;

public class EquipActivity extends Activity implements OnClickListener{
	
	private Avatar avatar;
	private TextView nomeAvatar;
	private Button logoutButton, fowardButton;
	private ArrayList<Item> atackItemsAvailable, defenseItemsAvailable;
	private ArrayList<DummyAvatar> dummyAvatares;
	private Spinner atack_items_spinner, defense_items_spinner, avatar_icon_chosser_spinner;
    
	/**
     * Procedimento responsável pela criação da actividade de Equipar avatar
     */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equip);
        //A carregar o avatar da atividade anterior
        Bundle bundle = this.getIntent().getExtras();
        avatar = (Avatar) bundle.getSerializable("key");
        nomeAvatar = (TextView) findViewById(R.id.avatarNameLabel);
        nomeAvatar.setTextColor(Color.CYAN);
        nomeAvatar.setText(avatar.toString());
        //Carregar os items disponíveis
        atackItemsAvailable = getAtackAvailableItems();
        defenseItemsAvailable = getDefenseAvailableItems();
        dummyAvatares = getAvailableAvatares();
        //Por defeito os items de ataque e de defesa são os primeiros que surgem na lista
        avatar.setAtackItem((AtackItem)atackItemsAvailable.get(0));
        avatar.setDefenseItem((DefenseItem)defenseItemsAvailable.get(0));
        avatar.setType("Roman");//avatar icon escolhido por defeito
        
        
        atack_items_spinner = (Spinner) findViewById(R.id.atack_items_spinner);
        defense_items_spinner = (Spinner) findViewById(R.id.defense_items_spinner);
        avatar_icon_chosser_spinner = (Spinner) findViewById(R.id.avatar_item_chooser);
        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, getAtackItemNames());
        atack_items_spinner.setAdapter(adapter1);
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, getAvatarIconNames());
        avatar_icon_chosser_spinner.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, getDefenseItemNames());
        defense_items_spinner.setAdapter(adapter3);
        
        atack_items_spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        defense_items_spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        avatar_icon_chosser_spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
 
        logoutButton = (Button) findViewById(R.id.logoutButton);
        fowardButton = (Button) findViewById(R.id.fowardButton);
        fowardButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
    }
	
    /**
     * Esta função será resposável por verificar quais as armas que estão disponíveis para uso
     * @return
     */
    private String[] getAtackItemNames(){
    	ArrayList<Item> items = getAtackAvailableItems();
    	String[] itemNames = new String[items.size()];
    	for(int i = 0; i < items.size(); i++){
    		itemNames[i] = items.get(i).getName() + " cost: " + items.get(i).getCost() + " points";
    	}
    	return itemNames;
    }
    
    /**
     * Função que retorna os items de ataque disponíveis
     * @return array de items de ataque
     */
    private ArrayList<Item> getAtackAvailableItems(){
    	String xmlItems = RestClass.callWebService("entities.attackweapon?");
    	return RestClass.getItemsFromXML(xmlItems, "attackWeapon");
    }
    
    /**
     * Função que retorna o nomes dos itens de defesa disponíveis
     * @return
     */
    private String[] getDefenseItemNames(){
    	ArrayList<Item> items = getDefenseAvailableItems();
    	String[] itemNames = new String[items.size()];
    	for(int i = 0; i < items.size(); i++){
    		itemNames[i] = items.get(i).getName() + " cost: " + items.get(i).getCost() + " points";
    	}
    	return itemNames;
    }
    
    /**
     * Função que retorna os items de defesa disponíveis
     * @return array de items de defesa
     */
    private ArrayList<Item> getDefenseAvailableItems(){
    	String xmlItems = RestClass.callWebService("entities.defenseweapon?");
    	return RestClass.getItemsFromXML(xmlItems, "defenseWeapon");
    }
    /**
     * Função que retorna os avatares disponíveis
     * @return array de items de defesa
     */
    private ArrayList<DummyAvatar> getAvailableAvatares(){
    	String xmlItems = RestClass.callWebService("entities.avatar?");
    	return RestClass.getAvatarsNamesFromXML(xmlItems);
    }
    
    /**
     * Função que retorna os nomes dos tips de avatares
     * @return
     */
    private String[] getAvatarIconNames(){
		String xmlAvatarsNames = RestClass.callWebService("entities.avatar?");
		ArrayList<DummyAvatar> listaAvatarNames = RestClass.getAvatarsNamesFromXML(xmlAvatarsNames);
		String[] tipos = new String[listaAvatarNames.size()];
		for(int i = 0; i < listaAvatarNames.size(); i++){
			tipos[i] =(String) (listaAvatarNames.get(i)).getNome();
		}
    	return tipos;
    }
    /**
     * Procedimento responsável 
     */
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.logoutButton){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Tem a certeza que pretende sair?")
		    .setCancelable(false)
		    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int id) {

		        	//TODO: enviar para o servidor os dados do avatar e sair da aplicação, voltando à actividade inicial
					startActivity(new Intent(EquipActivity.this.getApplicationContext(), LoginActivity.class));
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
		else if(v.getId() == R.id.fowardButton){			
			String[] paramName = {"user_id", "idattackweapon","iddefenseweapon", "avatar_id", "location_id"};
			String[] paramVal = {"" + avatar.getId(), "" + avatar.getAtackItem().getId(),
				"" + avatar.getDefenseItem().getId(), "" + avatar.getDummyAvatar().getId(), "" + 1};
			
			try{
				RestClass.httpPost("entities.useravatar/new_status?", paramName, paramVal);
			}catch(Exception e){
				e.printStackTrace();
			}
			int userAvatarID = Integer.parseInt(RestClass.callWebService("entities.useravatar/count?"));
			avatar.setUserAvatarID(userAvatarID);
			Bundle bundle = new Bundle();
			//actualizar os pontos do utilizador...
			int newPoints = avatar.getPoints() - avatar.getAtackItem().getCost() - avatar.getDefenseItem().getCost();
			avatar.setPoints(newPoints);
			bundle.putSerializable("key", avatar);
			Intent newIntent = new Intent(this.getApplicationContext(), ViewAvatarAct.class);
			newIntent.putExtras(bundle);
			startActivityForResult(newIntent, 0);
		}
		
	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			if(parent.getId() == atack_items_spinner.getId()){
				avatar.setAtackItem((AtackItem)atackItemsAvailable.get(pos));
			}
			if(parent.getId() == defense_items_spinner.getId()){
				avatar.setDefenseItem((DefenseItem)defenseItemsAvailable.get(pos));
			}
			if(parent.getId() == avatar_icon_chosser_spinner.getId()){
				avatar.setType(parent.getItemAtPosition(pos).toString());
				avatar.setDummyAvatar(dummyAvatares.get(pos));
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

}
