package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    int current_player = 0; //0 represents X, 1 represents O
    int finished_game = 0;
    int[] game_state = {2, 2, 2, 2, 2, 2, 2, 2, 2}; //2 is used to represent an empty tile
    /*
    2d array used to represent the board like so
   { {0  | 1  | 2}
    ----------------
     {3  | 4  | 5}
    ----------------
     {6  | 7  | 8} }
     */
    int[][] win_states = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, { 1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int counter = 0; //used to track if the game is a draw, so all tiles are filled without a win


    public void run_game(View view)
    {
        //get player input
        ImageView image = (ImageView) view;
        int tapped_image = Integer.parseInt(image.getTag().toString());

        //update game state
        if (current_player == 0)
        {
            //input x into grid
            game_state[tapped_image] = current_player;
            image.setImageResource(R.drawable.x);
            current_player = 1;
            //TextView status = findViewById(R.id.status);
        }
        else if (current_player == 1)
        {
            //input o into grid
            game_state[tapped_image] = current_player;
            image.setImageResource(R.drawable.o);
            current_player = 0;
            //TextView status = findViewById(R.id.status);
        }

        counter++;

        //check if the game was a draw
        if (counter == 9)
        {
            TextView status = findViewById(R.id.status);
            status.setText("Game was a draw");
            reset_game(view);
        }

        //check if a player has won
        for (int[] state_iterator_array: win_states)
        {
            if (game_state[state_iterator_array[0]] != 2 && game_state[state_iterator_array[0]] == game_state[state_iterator_array[1]] && game_state[state_iterator_array[1]] == game_state[state_iterator_array[2]])
            {
                finished_game = 1;

                String winner;
                if (game_state[0] == 0)
                {
                    winner = "X has won";
                }
                else
                {
                    winner = "O has won";
                }

                TextView status = findViewById(R.id.status);
                status.setText(winner);
                break;
            }
        }

        //reset game if someone won
        if (finished_game == 1)
        {
            reset_game(view);
        }
    }


    public void reset_game(View view)
    {
        current_player = 0;
        counter = 0;
        finished_game = 0;
        for (int i = 0; i < game_state.length; i++)
        {
            game_state[i] = 2;
        }
        // remove all the images from the boxes inside the grid
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}