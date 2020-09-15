package com.example.noteblock;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


class OnClickListenerCreateNote extends MainActivity implements View.OnClickListener {

    //    extends MainActivity
    @Override
    public void onClick(View view) {
        final Context context = view.getRootView().getContext();
//        final Context context = this;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.note_input_form, null, false);
        final EditText editNote = formElementsView.findViewById(R.id.editNote);
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Note")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
//                                Context context = getApplicationContext();
                                String userNote = editNote.getText().toString();
                                ObjectNote objectNote = new ObjectNote();
                                objectNote.note= userNote;

                                boolean createSuccessful = new TableControllerNote(context).create(objectNote);
                                if(createSuccessful){
                                    Toast.makeText(context, "Note information was saved.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to save note information.", Toast.LENGTH_SHORT).show();
                                }
//                                MainActivity.countRecords();
//                                readRecords();
                                finish();
                                Intent intent = new Intent(context,MainActivity.class);
                                context.startActivity(intent);

//                                ((MainActivity) context).readRecords();
                            }

                        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                }
        }).show();
    }
}
