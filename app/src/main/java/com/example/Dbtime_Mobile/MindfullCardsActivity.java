package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MindfullCardsActivity extends AppCompatActivity {
    MindfulCard[] cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindfull_cards);

        String category = getIntent().getStringExtra("category");
        TextView tvCategory = findViewById(R.id.mindful_cards_category);
        tvCategory.setText(category);

        cards = new MindfulCard[6];
        cards[0] = getCard(R.id.mindful_card_1);
        cards[1] = getCard(R.id.mindful_card_2);
        cards[2] = getCard(R.id.mindful_card_3);
        cards[3] = getCard(R.id.mindful_card_4);
        cards[4] = getCard(R.id.mindful_card_5);
        cards[5] = getCard(R.id.mindful_card_6);

        View view = findViewById(R.id.mindful_cards_root);
        FragmentContainerView[] containers = new FragmentContainerView[6];
        containers[0] = getCardContainer(R.id.mindful_card_1);
        containers[1] = getCardContainer(R.id.mindful_card_2);
        containers[2] = getCardContainer(R.id.mindful_card_3);
        containers[3] = getCardContainer(R.id.mindful_card_4);
        containers[4] = getCardContainer(R.id.mindful_card_5);
        containers[5] = getCardContainer(R.id.mindful_card_6);

        int columnWidth = 300; //(int)(view.getWidth() / 3);
        for (int i = 0; i < 6; i++) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) containers[i].getLayoutParams();
            params.leftMargin = (i % 3) * columnWidth;
            params.topMargin = 100 + (i / 3) * 410;
            containers[i].setLayoutParams(params);
        }

        Button button = (Button)findViewById(R.id.mindful_cards_shuffle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MindfullCardsActivity.this.shuffle();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        shuffle();
    }

    private void shuffle() {
        Resources res = getResources();
        List<String> lines = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.breath)));

        for (int i = 0; i < 6; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, lines.size());
            String line = lines.get(randomNum);
            lines.remove(line);

            cards[i].closeCard();
            cards[i].setText(line);
        }
    }

    MindfulCard getCard(int id) {
        FragmentContainerView container = (FragmentContainerView)findViewById(id);
        Fragment fragment = container.getFragment();

        return (MindfulCard) fragment;
    }

    FragmentContainerView getCardContainer(int id) {
        return (FragmentContainerView) findViewById(id);
    }
}