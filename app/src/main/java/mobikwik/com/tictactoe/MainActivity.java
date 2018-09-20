package mobikwik.com.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private int[][] dashboard = new int[3][3];
    private final int PLAYER1 = 0;
    private final int PLAYER2 = 1;
    private TextView status;
    private int count;
    private Button button[] = new Button[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        resetValues();
    }

    private void resetValues() {
        for (Button button1:button) {
            button1.setText("");
            button1.setOnClickListener(this);
        }
        for(int i = 0 ;i < 3 ;i++){
            for(int j = 0 ;j < 3;j++){
                dashboard[i][j] = -1;
            }
        }
        count  = 0;
    }

    private void initView() {
        status = findViewById(R.id.player_status);
        button[0] = findViewById(R.id.button1);
        button[1] = findViewById(R.id.button2);
        button[2] = findViewById(R.id.button3);
        button[3] = findViewById(R.id.button4);
        button[4] = findViewById(R.id.button5);
        button[5] = findViewById(R.id.button6);
        button[6] = findViewById(R.id.button7);
        button[7] = findViewById(R.id.button8);
        button[8] = findViewById(R.id.button9);
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetValues();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        count++;
        int i = 0,j = 0;
        switch (id){
            case R.id.button1:
                i = 0;
                j = 0;
                break;
            case R.id.button2:
                i = 0;
                j = 1;
                break;
            case R.id.button3:
                i = 0;
                j = 2;
                break;
            case R.id.button4:
                i = 1;
                j = 0;
                break;
            case R.id.button5:
                i = 1;
                j = 1;
                break;
            case R.id.button6:
                i = 1;
                j = 2;
                break;
            case R.id.button7:
                i = 2;
                j = 0;
                break;
            case R.id.button8:
                i = 2;
                j = 1;
                break;
            case R.id.button9:
                i = 2;
                j = 2;
                break;
        }
        int player;
        if (count % 2 != PLAYER1){
            player = PLAYER1;
        }else {
            player = PLAYER2;
        }
        setDisplay((3 * i) + j,player);
        dashboard[i][j] = player;
        if ( count > 4 ){
            if (checkWinner(player)){
                playerWin(player);
            }else if(count == 9){
                status.setText("No Buddy Winner");
                resetValues();
            }
        }

    }

    private void setDisplay(int i,int player) {
        if (PLAYER1 == player) {
            button[i].setText("O");
        }else {
            button[i].setText("X");
        }
        button[i].setOnClickListener(null);
    }

    private boolean checkWinner(int player) {
        if ( !checkVertically(player) && !checkHorizontally(player) && !checkDiagnal(player) ){
            return false;
        }
        return true;

    }

    private void playerWin(int player) {
        if ( player == PLAYER1){
            status.setText("Player One Winner");
        }else {
            status.setText("Player Two Player");
        }
        resetValues();
    }

    private boolean checkDiagnal(int player) {
        for(int i = 0 ; i < 3 ;i++){
            if(dashboard[i][i] != player){
                break;
            }
            if ( i == 2){
                return true;
            }
        }
        for (int i = 0 ; i < 3 ; i++ ){
            if(dashboard[i][2 - i] != player){
                break;
            }
            if (i == 2){
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontally(int player) {
        for (int i = 0 ;i < 3 ;i++){
            for (int j = 0 ; j < 3 ; j++){
                if (dashboard[i][j] != player){
                    break;
                }
                if (j == 2){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertically(int player) {
        for (int i = 0 ;i < 3 ;i++){
            for (int j = 0 ; j < 3 ; j++){
                if (dashboard[j][i] != player){
                    break;
                }
                if (j == 2){
                    return true;
                }
            }
        }
        return false;
    }
}
