package br.andre.caio.jogodaveiaapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.andre.caio.jogodaveiaapp.R;
import br.andre.caio.jogodaveiaapp.databinding.FragmentInicioBinding;
import br.andre.caio.jogodaveiaapp.databinding.FragmentJogoBinding;
public class FragmentInicio extends Fragment {

    private FragmentInicioBinding binding;
    private Button botao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        binding.buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentInicio.this)
                        .navigate(R.id.action_fragmentInicio_to_fragmentJogo);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        // para deletar a toolbar
        //pegar uma referencia do tipo compat activity
        AppCompatActivity minhaActivity = (AppCompatActivity) getActivity();
        //oculta  a toolbar
        minhaActivity.getSupportActionBar().hide();
    }
}