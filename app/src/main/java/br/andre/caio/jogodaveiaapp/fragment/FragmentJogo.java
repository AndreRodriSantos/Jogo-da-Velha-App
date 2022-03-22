package br.andre.caio.jogodaveiaapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.andre.caio.jogodaveiaapp.R;
import br.andre.caio.jogodaveiaapp.databinding.FragmentJogoBinding;

public class FragmentJogo extends Fragment {
    //variavel para acessar os elementos na view
    private FragmentJogoBinding binding;
    //vetor para agrupar os botoes
    private Button[] botoes;

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

        for (Button bt: botoes) {
            bt.setOnClickListener(listenerBotoes);
        }
        return  binding.getRoot();
    }
    private View.OnClickListener listenerBotoes = view -> {

        //pega o nome do botao
        String nomeBotao = getContext().getResources().getResourceName(view.getId());
        //extrai os dois ultimos caracteres do botão
        String posicao = nomeBotao.substring(nomeBotao.length()-2);
        //extrai a posicao em linha e coluna
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna =Character.getNumericValue(posicao.charAt(1));

    };
}