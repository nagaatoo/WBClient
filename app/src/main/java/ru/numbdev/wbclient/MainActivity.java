package ru.numbdev.wbclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import ru.numbdev.wbclient.button.ButtonFactory;
import ru.numbdev.wbclient.button.MainButton;
import ru.numbdev.wbclient.dto.ListDTO;
import ru.numbdev.wbclient.utils.RestUtils;

// Главная активити - список продуктов
public class MainActivity extends AppCompatActivity {

    private transient final List<MainButton> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView search = findViewById(R.id.listSearch);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sortList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sortList(newText);
                return false;
            }
        });

        RestUtils.getListProducts(this, 12);
    }

    public void loadProducts(List<ListDTO> lists) {

        LinearLayout scroll = findViewById(R.id.scrollButtonList);

        for (ListDTO item : lists) {

            MainButton button = ButtonFactory.buildListButton(this, item);

            buttons.add(button);
            scroll.addView(button);

        }
    }

    // Сортировка списка продуктов
    private void sortList(String nameSearch) {
        final String lowerNameSearch = nameSearch.toLowerCase();

        if (StringUtils.isBlank(nameSearch)) {
            buttons.forEach(b -> b.setVisibility(View.VISIBLE));
            return;
        }

        buttons
                .stream()
                .peek(b -> b.setVisibility(View.VISIBLE))
                .filter(b -> !b.getText().toString().contains(lowerNameSearch))
                .forEach(b -> b.setVisibility(View.GONE));
    }

}