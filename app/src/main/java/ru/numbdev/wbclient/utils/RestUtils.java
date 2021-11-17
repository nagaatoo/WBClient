package ru.numbdev.wbclient.utils;

import android.os.AsyncTask;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import ru.numbdev.wbclient.MainActivity;
import ru.numbdev.wbclient.dto.ListDTO;

public final class RestUtils {

    private final static String URL = "http://10.0.2.2:8000"; // localhost
//    private final static String URL = "http://5.188.140.221:8080";

    public static void getListProducts(MainActivity activity, int page) {

        RequestQueue queue = Volley.newRequestQueue(activity);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL + "/all",
                response -> listResponseOk(response, activity),
                error -> System.out.println("Fail list")
        ) {
            @Override
            public byte[] getBody() {
                return makeBodyJsonList(page);
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        queue.add(stringRequest);
    }

    private static byte[] makeBodyJsonList(int page) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("page", Integer.toString(page));
            return jsonBody.toString().getBytes("utf-8");
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("Parse error");
        }
    }

    private static void listResponseOk(String response, MainActivity activity) {
        try {
            List<Object> protoList = new Gson().fromJson(
                    URLDecoder.decode(URLEncoder.encode(response, "iso8859-1"),"UTF-8"),
                    ArrayList.class
            );

            ObjectMapper mapper = new ObjectMapper();
            List<ListDTO> list = new ArrayList<>();
            protoList.forEach(l -> list.add(mapper.convertValue(l, ListDTO.class)));

            activity.loadProducts(list);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        activity.loadProducts(List.of(
//                new ListDTO()
//                        .setProductName("someName")
//                        .setProductId(123L),
//                new ListDTO()
//                        .setProductName("fooName")
//                        .setProductId(345L)));
    }
}
