package com.example.minhasanotacaoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AnotacoesActivity extends AppCompatActivity {

    private EditText texto;
    private Button botaoSalvar;

    private static final String NOME_ARQUIVO ="arquivo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacoes);

        texto = findViewById(R.id.textoId);
        botaoSalvar = findViewById(R.id.salvarId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoDigitado = texto.getText().toString();
                gravarNoArquivo(textoDigitado);

                if(textoDigitado.equals("")){
                    Toast.makeText(AnotacoesActivity.this,"Não existe anotação", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AnotacoesActivity.this,"Anotação salva com sucesso", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //recuperar o que foi gravado
        if(lerArquivo()!=null){
                texto.setText(lerArquivo());

    }}

    private void gravarNoArquivo(String texto){
        try{

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(texto);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("AnotacoesActivity",e.toString());
        }
    }

    private String lerArquivo(){
        String resultado="";

        try{
            //abrir arquivo
            InputStream arquivo = openFileInput(NOME_ARQUIVO);
            if(arquivo!= null){
                //ler aquivo
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);

                //gerar buffer do arquivo lido
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                //recuperar texto do arquivo
                String linhaArquivo="";
                while((linhaArquivo = bufferedReader.readLine()) != null){
                    resultado+=linhaArquivo;

                }
                arquivo.close();

            }


        }catch (IOException e){
            Log.v("AnotacoesActivity",e.toString());
        }

        return resultado;
    }
}
