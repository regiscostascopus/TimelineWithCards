package com.example.regiscosta.timelinewithcards;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


/*created using Android Studio (Beta) 0.8.14
www.101apps.co.za*/

public class MainActivity extends ActionBarActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    private static ArrayList<CardData> cardList;

    static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        cardList = new ArrayList<CardData>();

        adapter = new MyCustomAdapter(this, cardList);
        recyclerView.setAdapter(adapter);
    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);

            cardList.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_add_item) {
            System.out.println("[MainActivity::onOptionsItemSelected()");
            generateNewCardToList();
            //Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void generateNewCardToList() {
        cardList.add(0, CardDataGenerator.generateNewRandomCard());
        adapter.notifyItemInserted(0);
        adapter.notifyDataSetChanged();
        //printAddedCards();
    }

    private void printAddedCards() {
        CardData card;
        for(int cont=0; cont< cardList.size(); cont++) {
            card = cardList.get(cont);

            System.out.println("######################################");
            System.out.println("[MainActivity::printAddedCards()] cont:" + cont);
            switch(card.getCardTypeID().ordinal()) {
                case 1:
                    System.out.println("[MainActivity::printAddedCards()] getCardTypeID: 1");
                    System.out.println("[MainActivity::printAddedCards()] card.getUserName:" + card.getUserName());
                    System.out.println("[MainActivity::printAddedCards()] card.getUserName:" + card.getPostInfo());
                    break;
                case 2:
                    System.out.println("[MainActivity::printAddedCards()] getCardTypeID: 2");
                    System.out.println("[MainActivity::printAddedCards()] card.getUserName:" + card.getUserName());
                    System.out.println("[MainActivity::printAddedCards()] card.getUserName:" + card.getPostInfo());
                    break;
                case 3:
                    System.out.println("[MainActivity::printAddedCards()] getCardTypeID: 3");
                    break;
            }
        }
    }
}
