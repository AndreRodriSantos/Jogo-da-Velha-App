package br.andre.caio.jogodaveiaapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

import br.andre.caio.jogodaveiaapp.R;
import br.andre.caio.jogodaveiaapp.databinding.FragmentJogoBinding;

public class FragmentJogo extends Fragment {
    //variável para acessar os elementos na view
    private FragmentJogoBinding binding;
    //vetor para agrupar os botoes
    private Button[] botoes;
    //variável do tabuleiro
    private String[][] tabuleiro;
    //variável para os simbolos
    private String simbJog1, simbJog2, simbolo;
    // variável random para sortear quem começa
    private Random random;
    //variável para contar o número de jogadas
    private int numJogadas = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentJogoBinding.inflate(inflater, container, false);

        //instacia o vetor
        botoes = new Button[9];
        //agrupa os botões no vetor
        botoes[0] = binding.bt00;
        botoes[1] = binding.bt01;
        botoes[2] = binding.bt02;
        botoes[3] = binding.bt10;
        botoes[4] = binding.bt11;
        botoes[5] = binding.bt12;
        botoes[6] = binding.bt20;
        botoes[7] = binding.bt21;
        botoes[8] = binding.bt22;

        for (Button bt : botoes) {
            bt.setOnClickListener(listenerBotoes);
        }
        //inicializa o tabuleiro
        tabuleiro = new String[3][3];

        //preecher o tabuleiro com ""
        for (String[] vetor : tabuleiro) {
            Arrays.fill(vetor, "");
        }

        //instacia o Random
        random = new Random();

        //define s simbolos dos jogadores
        simbJog1 = "X";

        simbJog2 = "O";

        sorteiar();

        return binding.getRoot();
    }


    private void sorteiar() {
        //caso random gere um valor V, jogador 1 começa
        if (random.nextBoolean()) {
            simbolo = simbJog1;
        } else {
            simbolo = simbJog2;
        }
    }
    private void resetar(){
        for(int i = 0; i < botoes.length; i++){
        botoes[i].setText("");
        botoes[i].setBackgroundColor(getResources().getColor(R.color.purple_500));
        botoes[i].setClickable(true);
        }
        numJogadas = 0;
        sorteiar();
        atualizaVez();
    }

    private void atualizaVez() {
        //verifica de quem é vez e acende o placar do jogador
        if (simbolo.equals((simbJog1))) {
            binding.textView.setBackgroundColor(getResources().getColor(R.color.darkblue));
            binding.textView2.setBackgroundColor(getResources().getColor(R.color.blue));
        } else {
            binding.textView.setBackgroundColor(getResources().getColor(R.color.blue));
            binding.textView2.setBackgroundColor(getResources().getColor(R.color.darkblue));
        }
    }

    private boolean venceu() {

        //verifica se venceu nas linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0].equals(simbolo) && tabuleiro[i][1].equals(simbolo) && tabuleiro[i][2].equals(simbolo)) {
                Log.w("entrou", "VENCEU AQUI");
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i].equals(simbolo) && tabuleiro[1][i].equals(simbolo) && tabuleiro[2][i].equals(simbolo)) {
                Log.w("entrou", "VENCEU AQUI");
                return true;
            }
        }
        if (tabuleiro[0][0].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][2].equals(simbolo)) {
            return true;
        }

        if (tabuleiro[0][2].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][0].equals(simbolo)) {
            return true;
        }


        return false;

    }

    private View.OnClickListener listenerBotoes = view -> {
        numJogadas++;
        //pega o nome do botao
        String nomeBotao = getContext().getResources().getResourceName(view.getId());
        //extrai os dois ultimos caracteres do botão
        String posicao = nomeBotao.substring(nomeBotao.length() - 2);
        //extrai a posicao em linha e coluna
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));
        //marca no tabuleiro o simbolo que foi jogado
        tabuleiro[linha][coluna] = simbolo;
        //trocar o texto do botao
        Button botao = (Button) view;
        //trocar o texto do botão que foi clicado
        botao.setText(simbolo);
        //desabilitar o botao
        botao.setClickable(false);
        botao.setBackgroundColor(Color.BLUE);

        if (numJogadas >= 5 && venceu()) {
            Toast.makeText(getContext(), R.string.venceu, Toast.LENGTH_SHORT).show();
            resetar();
        }else if(numJogadas == 9){
            Toast.makeText(getContext(), R.string.velha, Toast.LENGTH_SHORT).show();
            resetar();
        }else{
            simbolo = simbolo.equals(simbJog1) ? simbJog2 : simbJog1;
            atualizaVez();
        }
        //inverter a vez
        simbolo = simbolo.equals(simbJog1) ? simbJog2 : simbJog1;

        atualizaVez();
    };
}