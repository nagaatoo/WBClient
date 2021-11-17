package ru.numbdev.wbclient.button;

import android.content.Context;
import android.content.Intent;

import lombok.Getter;
import ru.numbdev.wbclient.activitis.ProductActivity;
import ru.numbdev.wbclient.dto.ListDTO;

@Getter
public class MainButton extends android.support.v7.widget.AppCompatButton {

    private final ListDTO dto;

    public MainButton(Context context, ListDTO dto) {
        super(context);
        this.dto = dto;

        this.setOnClickListener((i) -> {
            Intent intent = new Intent(context, ProductActivity.class);
            intent.putExtra("dto", dto);
            context.startActivity(intent);
        });
    }


}
