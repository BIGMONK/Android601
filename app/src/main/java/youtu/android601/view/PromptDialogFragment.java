package youtu.android601.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import youtu.android601.R;

/**
 * Created by djf on 2016/8/6.
 */
public class PromptDialogFragment extends DialogFragment {

    public TextView cancel;
    public TextView confirm;
    public TextView message;
    public String messageString = "弹窗信息！";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_prompt_dialog, null);
        cancel = (TextView) view.findViewById(R.id.tv_update_cancel);
        confirm = (TextView) view.findViewById(R.id.tv_update_confirm);
        message = (TextView) view.findViewById(R.id.tv_message);
        message.setText(messageString);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromptDialogFragmentListener listener = (PromptDialogFragmentListener) getActivity();
                listener.onPromptListener(false);
                PromptDialogFragment.this.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromptDialogFragmentListener listener = (PromptDialogFragmentListener) getActivity();
                listener.onPromptListener(true);
                PromptDialogFragment.this.dismiss();

            }
        });
        this.setCancelable(false);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            dialog.getWindow().setLayout(600, 352);
//            dialog.getWindow().setLayout(DensityUtils.dp2px(getActivity(),600), DensityUtils.dp2px(getActivity(),352));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }

    public interface PromptDialogFragmentListener {
        void onPromptListener(boolean prompt);

    }
}
