package cn.zyc.lannister.release;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import cn.zyc.lannister.R;
import cn.zyc.westeros.ARouter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LannisterFragment extends Fragment {
    private TextView tv_lannister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lannister, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_lannister = view.findViewById(R.id.tv_lannister);
        tv_lannister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().JumpActivity("start/start",null);
            }
        });
    }
}
