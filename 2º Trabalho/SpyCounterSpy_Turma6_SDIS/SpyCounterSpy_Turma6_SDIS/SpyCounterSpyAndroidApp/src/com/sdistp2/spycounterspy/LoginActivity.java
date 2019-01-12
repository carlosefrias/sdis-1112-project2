package com.sdistp2.spycounterspy;


import rest.RestClass;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import entidades.Avatar;

public class LoginActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private EditText usernameTextField, passwordTextField;
	private Button botaoLogin;
	private TextView answerTextView;
	private int id, points = 0;
	private String username = "";
	
    /**
     * Procedimento responsável pela criação da actividade de login
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        usernameTextField = (EditText) findViewById(R.id.usernameTextField);
        passwordTextField = (EditText) findViewById(R.id.passwordTextField);
        botaoLogin = (Button) findViewById(R.id.loginButton);
        answerTextView = (TextView) findViewById(R.id.AnswerInfoLogin);
        answerTextView.setVisibility(View.INVISIBLE);
        botaoLogin.setOnClickListener((android.view.View.OnClickListener) this);
        
    }
    
	/**
	 * Procedimento que processa a interacção com os botões
	 */
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.loginButton){
			//Se o botão de login foi clicado
	        answerTextView.setVisibility(View.VISIBLE);
			if(loginValido(usernameTextField.getText().toString(), passwordTextField.getText().toString())){
				answerTextView.setTextColor(Color.GREEN);
				answerTextView.setText("Login válido");
				
				
				Avatar avatar = loadAvatar();
				
				//avançar para a activity seguinte (Equipar) enviando o avatar...
				Bundle bundle = new Bundle();
				bundle.putSerializable("key", avatar);
				Intent newIntent = new Intent(this.getApplicationContext(), EquipActivity.class);
				newIntent.putExtras(bundle);
				startActivityForResult(newIntent, 0);
			}
			else{
				answerTextView.setTextColor(Color.RED);
				answerTextView.setText("Login inválido");				
			}
		}
	}
	
	/**
	 * Este método é responsável por validar o login de um utilizador no jogo
	 * efectuanto comunicação com o servidor
	 * @param user
	 * @param pass
	 * @return true, caso login válido, false caso contrário
	 */
	private boolean loginValido(String user, String pass){
		String resultado = RestClass.callWebService("entities.user/login?userName=" + user + "&password=" + pass);
		if(resultado == "") return false;
		else{
            id = Integer.parseInt(RestClass.getFromXML("user", "id", resultado));
            points = Integer.parseInt(RestClass.getFromXML("user", "points", resultado));
			return true;
		}
	}
	
	/**
	 * Função que carrega do servidor os dados do avatar do utilizador
	 * @param username
	 * @return
	 */
	private Avatar loadAvatar(){
		Avatar avatar = new Avatar(usernameTextField.getText().toString());
		avatar.setId(id);
		avatar.setPoints(points);
		return avatar;
	}
}