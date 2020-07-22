package com.fypic.imageclassification;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogBox2 extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Instruction:")
                .setMessage("Take a photo and Crop the picture so that the item in the picture is in focus in the next step.")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();
    }
}
