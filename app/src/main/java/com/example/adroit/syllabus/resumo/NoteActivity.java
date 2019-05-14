package com.example.adroit.myapplication.resumo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adroit.myapplication.R;

public class NoteActivity extends AppCompatActivity {

    private EditText mEtTitle;
    private EditText mEtCategoria;
    private EditText mEtContent;

    private String mNoteFileName;
    private Note mLoadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mEtTitle = (EditText) findViewById(R.id.note_et_title);
        mEtCategoria = (EditText) findViewById(R.id.note_et_categoria);
        mEtContent = (EditText) findViewById(R.id.note_et_content);

        mNoteFileName = getIntent().getStringExtra("NOTE_FILE");
        if(mNoteFileName != null && !mNoteFileName.isEmpty()) {
             mLoadedNote = Utilities.getNoteByName(this, mNoteFileName);

            if(mLoadedNote != null) {
                mEtTitle.setText(mLoadedNote.getTitle());
                mEtCategoria.setText(mLoadedNote.getCategoria());
                mEtContent.setText(mLoadedNote.getContent());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_note_save:
                saveNote();

            case R.id.action_note_delete:
                deleteNote();
                break;
        }

        return true;
    }

    private void saveNote() {
        Note note;

        if(mEtTitle.getText().toString().trim().isEmpty() || mEtContent.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Por favor coloque um título e um conteúdo", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mLoadedNote == null) {
            note = new Note(System.currentTimeMillis(), mEtTitle.getText().toString()
                        , mEtCategoria.getText().toString(), mEtContent.getText().toString());
        } else {
            note = new Note(mLoadedNote.getDateTime(), mEtTitle.getText().toString()
                    , mEtCategoria.getText().toString(), mEtContent.getText().toString());
        }

        if (Utilities.saveNote(this, note)) {
            Toast.makeText(this, "Seu Resumo foi salvo!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Não foi possível salvar o seu resumo, por favor verifique se há espaço no seu dispositivo"
                    , Toast.LENGTH_SHORT).show();
        }

        finish();
    }


    private void deleteNote() {
        if(mLoadedNote == null) {
            finish();
        } else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Excluir")
                    .setMessage("Você está prestes a excluir " + mEtTitle.getText().toString() + ", você tem certeza?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilities.deleteNote(getApplicationContext()
                                    , mLoadedNote.getDateTime() +Utilities.FILE_EXTENSION);
                            Toast.makeText( getApplicationContext(), mEtTitle.getText().toString() + " foi excluído", Toast.LENGTH_SHORT).show();
                            finish();
                            }
        })
                    .setNegativeButton("Não", null)
                    .setCancelable(false);

            dialog.show();
        }
    }
}
