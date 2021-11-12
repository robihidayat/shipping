package com.robihidayat.dot.shipping.client;

import com.robihidayat.dot.shipping.client.model.response.CitiesResponse;
import com.robihidayat.dot.shipping.client.model.response.CostResponse;
import com.robihidayat.dot.shipping.client.model.response.ProvinceResponse;

public interface ShippingClient {

  CitiesResponse cities();

  ProvinceResponse province();

  CostResponse cost();

}
