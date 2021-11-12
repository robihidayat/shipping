package com.robihidayat.dot.shipping.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robihidayat.dot.shipping.cache.Cache;
import com.robihidayat.dot.shipping.client.model.request.CitiesRequest;
import com.robihidayat.dot.shipping.client.model.request.CostRequest;
import com.robihidayat.dot.shipping.client.model.request.ProvinceRequest;
import com.robihidayat.dot.shipping.client.model.response.CitiesResponse;
import com.robihidayat.dot.shipping.client.model.response.CostResponse;
import com.robihidayat.dot.shipping.client.model.response.ProvinceResponse;
import com.robihidayat.dot.shipping.exception.ThirdPartyException;
import java.io.UncheckedIOException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ShippingClientImpl implements ShippingClient {
  private static final String bashUrl = "https://api.rajaongkir.com/starter";

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;
  private final Cache cachedManager;

  public ShippingClientImpl(
      RestTemplate restTemplate, ObjectMapper objectMapper, Cache cachedManager) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
    this.cachedManager = cachedManager;
  }

  @Override
  public CitiesResponse cities() {
    String url = UriComponentsBuilder.fromUriString(bashUrl).path("city").toUriString();
    CitiesRequest body = new CitiesRequest();
    HttpEntity<CitiesRequest> request = new HttpEntity<>(body, this.createHeader());
    CitiesResponse result = null;
    try {
      String keyCache = getKeyCache(url, body);
      CitiesResponse cache = cachedManager.get(keyCache, CitiesResponse.class);

      if (cache == null) {
        ResponseEntity<CitiesResponse> response =
            restTemplate.exchange(url, HttpMethod.GET, request, CitiesResponse.class);
        if (!response.getStatusCode().isError()) {
          result = response.getBody();
          cachedManager.put(keyCache, result);
        }
      }
    } catch (HttpClientErrorException e) {
      throw new ThirdPartyException("error from third party");
    }
    return result;
  }

  @Override
  public ProvinceResponse province() {
    String url = UriComponentsBuilder.fromUriString(bashUrl).path("province").toUriString();
    ProvinceRequest body = new ProvinceRequest();

    ProvinceResponse result = null;

    try {
      String keyCache = getKeyCache(url, body);
      ProvinceResponse cache = cachedManager.get(keyCache, ProvinceResponse.class);
      if (cache == null) {
        HttpEntity<ProvinceRequest> request = new HttpEntity<>(body, this.createHeader());
        ResponseEntity<ProvinceResponse> response =
            restTemplate.exchange(url, HttpMethod.POST, request, ProvinceResponse.class);

        if (!response.getStatusCode().isError()) {
          result = response.getBody();
          cachedManager.put(keyCache, result);
        }
      }
    } catch (HttpClientErrorException e) {
      throw new ThirdPartyException("error from third party");
    }
    return result;
  }

  @Override
  public CostResponse cost() {
    String url = UriComponentsBuilder.fromUriString(bashUrl).toUriString();
    CostRequest body = new CostRequest();
    CostResponse result = null;

    try {
      String keyCache = getKeyCache(url, body);
      CostResponse cache = cachedManager.get(keyCache, CostResponse.class);
      if (cache == null) {
        HttpEntity<CostRequest> request = new HttpEntity<>(body, this.createHeader());
        ResponseEntity<CostResponse> response =
            restTemplate.exchange(url, HttpMethod.POST, request, CostResponse.class);

        if (!response.getStatusCode().isError()) {
          result = response.getBody();
          cachedManager.put(keyCache, result);
        }
      }
    } catch (HttpClientErrorException e) {
      throw new ThirdPartyException("error from third party");
    }
    return result;
  }

  private MultiValueMap<String, String> createHeader() {
    MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
    header.add("Accept", "application/json");
    header.add("key", "your-api-key");
    return header;
  }

  private String getKeyCache(String url, Object request) {
    try {
      return String.format("%s?payload=%s", url, objectMapper.writeValueAsString(request));
    } catch (JsonProcessingException e) {
      throw new UncheckedIOException(" Error get key cache", e);
    }
  }
}
