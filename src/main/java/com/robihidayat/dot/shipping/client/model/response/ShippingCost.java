package com.robihidayat.dot.shipping.client.model.response;

import java.util.List;

public class ShippingCost extends BaseShipping{
  public OriginDetails origin_details;
  public DestinationDetails destination_details;
  public List<Results> results;
}
