package org.kurukshetra.stark.Activities;

import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.Graphmaster;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;
import org.alicebot.ab.Timer;
import org.kurukshetra.stark.Adapters.ChatMessageAdapter;
import org.kurukshetra.stark.Entities.ChatMessage;
import org.kurukshetra.stark.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class BotActivity extends AppCompatActivity {
    private ListView mListView;
    private EditText mEditTextMessage;
    private Button mSendButton;
    private ChatMessageAdapter mAdapter;
    public Bot bot;
    public static Chat chat;

    public BotActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bot);

        mListView = (ListView) findViewById(R.id.listView);
        mSendButton = (Button) findViewById(R.id.bSend);
        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mAdapter = new ChatMessageAdapter(this,R.layout.item_my_message,new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mEditTextMessage.getText().toString();
                String response = chat.multisentenceRespond(mEditTextMessage.getText().toString());
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                sendMessage(message);
                mimicOtherMessage(response);
                mEditTextMessage.setText("");
                mListView.setSelection(mAdapter.getCount() - 1);
            }
        });

        boolean isAvailable = isSDCARDAvailable();
        AssetManager assets = getResources().getAssets();
        File dir = new File(Environment.getExternalStorageDirectory().toString()+"/kurukshetra/bots/Kurukshetra");
        boolean b = dir.mkdirs();
        if(dir.exists()){
            try {
                for(String fd:assets.list("bot")){
                    File subdir = new File(dir.getPath() + "/" + fd);
                    boolean subdir_check = subdir.mkdirs();
                    for (String file : assets.list("bot/" + fd)){
                        File f = new File(dir.getPath() + "/" + fd + "/" + file);
                        if (f.exists()) {
                            continue;
                        }
                        InputStream in = null;
                        OutputStream out = null;
                        in = assets.open("bot/" + fd + "/" + file);
                        out = new FileOutputStream(dir.getPath() + "/" + fd + "/" + file);
                        copyFile(in, out);
                        in.close();
                        in = null;
                        out.flush();
                        out.close();
                        out = null;
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        MagicStrings.root_path = Environment.getExternalStorageDirectory().toString()+"/kurukshetra";
        AIMLProcessor.extension = new PCAIMLProcessorExtension();
        bot = new Bot("Kurukshetra",MagicStrings.root_path,"chat");
        chat = new Chat(bot);
        String[] args = null;
        mainFunction(args);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    public static void mainFunction (String[] args) {
        MagicBooleans.trace_mode = false;
        System.out.println("trace mode = " + MagicBooleans.trace_mode);
        Graphmaster.enableShortCuts = true;
        Timer timer = new Timer();
        String request = "Hello.";
        String response = chat.multisentenceRespond(request);
        System.out.println("Human: "+request);
        System.out.println("Robot: " + response);
    }

    public static boolean isSDCARDAvailable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    private void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, true);
        mAdapter.add(chatMessage);
    }

    private void mimicOtherMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, false);
        mAdapter.add(chatMessage);
    }

    private void sendMessage() {
        ChatMessage chatMessage = new ChatMessage(null, true);
        mAdapter.add(chatMessage);
        mimicOtherMessage();
    }
    private void mimicOtherMessage() {
        ChatMessage chatMessage = new ChatMessage(null, false);
        mAdapter.add(chatMessage);
    }
}
