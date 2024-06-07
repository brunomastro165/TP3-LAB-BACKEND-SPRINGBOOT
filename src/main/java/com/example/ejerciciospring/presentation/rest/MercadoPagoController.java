package com.example.ejerciciospring.presentation.rest;

import com.example.ejerciciospring.domain.entities.Pedido;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MercadoPagoController {

    public PreferenceMP getPreferenciaIdMercadoPago(Pedido pedido){
        try {
            MercadoPagoConfig.setAccessToken("TEST-6157612059305301-053022-bfed9d852d8d432e27e78ec616080ea1-713959562");


            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(pedido.getId().toString())
                    .title(pedido.getTitulo())
                    .description("Pedido realizado desde el carrito de compras")
                    .pictureUrl("https://img-global.cpcdn.com/recipes/0709fbb52d87d2d7/1200x630cq70/photo.jpg")
                    .quantity(1)
                    .currencyId("ARG")
                    .unitPrice(new BigDecimal(pedido.getTotal()))
                    .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder().success("http://localhost:5173")
                    .pending("http://localhost:5173").failure("http://localhost:5173").build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .build();
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            PreferenceMP mpPreference = new PreferenceMP();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());
            mpPreference.setId(preference.getId());

            /*
            PreferencePayerRequest payer = PreferencePayerRequest.builder()
                    .email("TESTUSER974379954")  // Email de usuario comprador de prueba
                    .build();
            */

            return mpPreference;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
