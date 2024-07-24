package com.apitest.RestAPI.model;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"addressType",
"country",
"city",
"zipcode"
})

public class Address {

@JsonProperty("addressType")
private String addressType;
@JsonProperty("country")
private String country;
@JsonProperty("city")
private String city;
@JsonProperty("zipcode")
private String zipcode;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("addressType")
public String getAddressType() {
return addressType;
}

@JsonProperty("addressType")
public void setAddressType(String addressType) {
this.addressType = addressType;
}

@JsonProperty("country")
public String getCountry() {
return country;
}

@JsonProperty("country")
public void setCountry(String country) {
this.country = country;
}

@JsonProperty("city")
public String getCity() {
return city;
}

@JsonProperty("city")
public void setCity(String city) {
this.city = city;
}

@JsonProperty("zipcode")
public String getZipcode() {
return zipcode;
}

@JsonProperty("zipcode")
public void setZipcode(String zipcode) {
this.zipcode = zipcode;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
