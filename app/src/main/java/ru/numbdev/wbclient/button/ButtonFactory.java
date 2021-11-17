package ru.numbdev.wbclient.button;

import android.content.Context;

import ru.numbdev.wbclient.dto.ListDTO;

public class ButtonFactory {

    public static MainButton buildListButton(Context context, ListDTO dto) {
        MainButton button = new MainButton(context, dto);
        button.setText(dto.getProductName() + " | " + dto.getProductId());
        return button;
    }

}
