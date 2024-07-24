package com.apitest.RestAPI.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"firstName",
"lastName",
"age",
"address",
"phoneNumbers"
})
public class Person {

@JsonProperty("id")
private Integer id;
@JsonProperty("firstName")
private String firstName;
@JsonProperty("lastName")
private String lastName;
@JsonProperty("age")
private Integer age;
@JsonProperty("address")
private List<Address> address;
@JsonProperty("phoneNumbers")
private List<PhoneNumber> phoneNumbers=new ArrayList<>();
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("firstName")
public String getFirstName() {
return firstName;
}

@JsonProperty("firstName")
public void setFirstName(String firstName) {
this.firstName = firstName;
}

@JsonProperty("lastName")
public String getLastName() {
return lastName;
}

@JsonProperty("lastName")
public void setLastName(String lastName) {
this.lastName = lastName;
}

@JsonProperty("age")
public Integer getAge() {
return age;
}

@JsonProperty("age")
public void setAge(Integer age) {
this.age = age;
}

@JsonProperty("address")
public List<Address> getAddress() {
return address;
}

@JsonProperty("address")
public void setAddress(List<Address> address) {
this.address = address;
}

@JsonProperty("phoneNumbers")
public List<PhoneNumber> getPhoneNumbers() {
return phoneNumbers;
}

@JsonProperty("phoneNumbers")
public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
this.phoneNumbers = phoneNumbers;
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