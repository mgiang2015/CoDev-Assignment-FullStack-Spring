package app.backend.response;

public class RandomCountryResponse {
    public static final Long CODE_SG = 0L;
    public static final Long CODE_MY = 1L;
    public static final Long CODE_US = 2L;

    Country country;

    public RandomCountryResponse(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public static RandomCountryResponse fromLong(Long id) {
        return new RandomCountryResponse(Country.fromLong(id));
    }
}

class Country {
    private final String full_name;
    private final String country_code;

    public Country(String full_name, String country_code) {
        this.full_name = full_name;
        this.country_code = country_code;
    }

    public static Country fromLong(Long id) {
        if (id.equals(RandomCountryResponse.CODE_SG)) {
            String full_name = "SG";
            String country_code = "0" + id;
            return new Country(full_name, country_code);
        } else if (id.equals(RandomCountryResponse.CODE_MY)) {
            String full_name = "MY";
            String country_code = "0" + id;
            return new Country(full_name, country_code);
        } else if (id.equals(RandomCountryResponse.CODE_US)) {
            String full_name = "US";
            String country_code = "0" + id;
            return new Country(full_name, country_code);
        }

        return new Country(null, null);
    }

    public String getFull_name() {
        return full_name;
    }

    public String getCountry_code() {
        return country_code;
    }
}