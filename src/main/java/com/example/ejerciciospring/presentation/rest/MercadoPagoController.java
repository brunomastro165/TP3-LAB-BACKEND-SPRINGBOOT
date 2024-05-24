package com.example.ejerciciospring.presentation.rest;

import com.example.ejerciciospring.domain.entities.Pedido;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MercadoPagoController {

    public PreferenceMP getPreferenciaIdMercadoPago(Pedido pedido) {

        try {
            MercadoPagoConfig.setAccessToken("TEST-4895649229987631-052014-eda3a2f76d136e7a7537103b89a01927-713959562");
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(pedido.getId().toString())
                    .title(pedido.getTitulo())
                    .description("Pedido realizado desde el carrito de compra")
                    .pictureUrl("https://www.recetasnestle.com.ec/sites/default/files/srh_recipes/4e4293857c03d819e4ae51de1e86d66a.jpg")
                    .quantity(1)
                    .currencyId("ARG")
                    .unitPrice(new BigDecimal(pedido.getTotal()))
                    .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder().success("http://localhost:5173/pagoRealizado")
                    .pending("http://localhost:5173/mppending").failure("http://localhost:5173/mpfailure").build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .build();
            PreferenceClient client = new PreferenceClient();
            System.out.println(client.create(preferenceRequest));
            Preference preference = client.create(preferenceRequest);

            PreferenceMP mpPreference = new PreferenceMP();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());
            mpPreference.setId(preference.getId());
            return mpPreference;

        } catch (MPApiException e) {
            // Obtener los detalles del error de la API
            System.err.println("API Error: " + e.getApiResponse().getContent());
            e.printStackTrace();
            return new PreferenceMP(); // Devuelve un objeto vacío para evitar null
        } catch (MPException e) {
            e.printStackTrace();
            return new PreferenceMP(); // Devuelve un objeto vacío para evitar null
        }
    }
}