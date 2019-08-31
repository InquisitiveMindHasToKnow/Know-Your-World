package org.ohmstheresistance.knowyourworld.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Country {


    private String name;
    private List<String> topLevelDomain;
    private String alpha2Code;
    private String alpha3Code;
    private List<String> callingCodes;
    private String capital;
    private List<String> altSpellings;
    private String region;
    private String subregion;
    private Integer population;
    private List<Double> latlng ;
    private String demonym;
    private Double area;
    private Double gini;
    private List<String> timezones;
    private List<String> borders;
    private String nativeName;
    private String numericCode;
    private List<Currency> currencies = null;
    private List<Language> languages = null;
    private Translations translations;
    private String flag;
    private List<RegionalBloc> regionalBlocs = null;
    private String cioc;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Country(String name, List<String> topLevelDomain, String alpha2Code, String alpha3Code, List<String> callingCodes, String capital, List<String> altSpellings, String region, String subregion, Integer population, List<Double> latlng, String demonym, Double area, Double gini, List<String> timezones, List<String> borders, String nativeName, String numericCode, List<Currency> currencies, List<Language> languages, Translations translations, String flag, List<RegionalBloc> regionalBlocs, String cioc) {
        super();
        this.name = name;
        this.topLevelDomain = topLevelDomain;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.callingCodes = callingCodes;
        this.capital = capital;
        this.altSpellings = altSpellings;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.latlng = latlng;
        this.demonym = demonym;
        this.area = area;
        this.gini = gini;
        this.timezones = timezones;
        this.borders = borders;
        this.nativeName = nativeName;
        this.numericCode = numericCode;
        this.currencies = currencies;
        this.languages = languages;
        this.translations = translations;
        this.flag = flag;
        this.regionalBlocs = regionalBlocs;
        this.cioc = cioc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country withName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public Country withTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
        return this;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public Country withAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
        return this;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public Country withAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
        return this;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public Country withCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
        return this;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Country withCapital(String capital) {
        this.capital = capital;
        return this;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public Country withAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Country withRegion(String region) {
        this.region = region;
        return this;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Country withSubregion(String subregion) {
        this.subregion = subregion;
        return this;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Country withPopulation(Integer population) {
        this.population = population;
        return this;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    public Country withLatlng(List<Double> latlng) {
        this.latlng = latlng;
        return this;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public Country withDemonym(String demonym) {
        this.demonym = demonym;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Country withArea(Double area) {
        this.area = area;
        return this;
    }

    public Double getGini() {
        return gini;
    }

    public void setGini(Double gini) {
        this.gini = gini;
    }

    public Country withGini(Double gini) {
        this.gini = gini;
        return this;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public Country withTimezones(List<String> timezones) {
        this.timezones = timezones;
        return this;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public Country withBorders(List<String> borders) {
        this.borders = borders;
        return this;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public Country withNativeName(String nativeName) {
        this.nativeName = nativeName;
        return this;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public Country withNumericCode(String numericCode) {
        this.numericCode = numericCode;
        return this;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public Country withCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Country withLanguages(List<Language> languages) {
        this.languages = languages;
        return this;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public Country withTranslations(Translations translations) {
        this.translations = translations;
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Country withFlag(String flag) {
        this.flag = flag;
        return this;
    }

    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(List<RegionalBloc> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public Country withRegionalBlocs(List<RegionalBloc> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
        return this;
    }

    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }

    public Country withCioc(String cioc) {
        this.cioc = cioc;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Country withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

public class Currency {

    private String code;
    private String name;
    private String symbol;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Currency(String code, String name, String symbol) {
        super();
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Currency withCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency withName(String name) {
        this.name = name;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Currency withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Currency withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

    public class Language {

        private String iso6391;
        private String iso6392;
        private String name;
        private String nativeName;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();


        public Language(String iso6391, String iso6392, String name, String nativeName) {
            super();
            this.iso6391 = iso6391;
            this.iso6392 = iso6392;
            this.name = name;
            this.nativeName = nativeName;
        }

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public Language withIso6391(String iso6391) {
            this.iso6391 = iso6391;
            return this;
        }

        public String getIso6392() {
            return iso6392;
        }

        public void setIso6392(String iso6392) {
            this.iso6392 = iso6392;
        }

        public Language withIso6392(String iso6392) {
            this.iso6392 = iso6392;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Language withName(String name) {
            this.name = name;
            return this;
        }

        public String getNativeName() {
            return nativeName;
        }

        public void setNativeName(String nativeName) {
            this.nativeName = nativeName;
        }

        public Language withNativeName(String nativeName) {
            this.nativeName = nativeName;
            return this;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        public Language withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }


    }

    public class RegionalBloc {

        private String acronym;
        private String name;
        private List<Object> otherAcronyms = null;
        private List<Object> otherNames = null;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();


        public RegionalBloc(String acronym, String name, List<Object> otherAcronyms, List<Object> otherNames) {
            super();
            this.acronym = acronym;
            this.name = name;
            this.otherAcronyms = otherAcronyms;
            this.otherNames = otherNames;
        }

        public String getAcronym() {
            return acronym;
        }

        public void setAcronym(String acronym) {
            this.acronym = acronym;
        }

        public RegionalBloc withAcronym(String acronym) {
            this.acronym = acronym;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public RegionalBloc withName(String name) {
            this.name = name;
            return this;
        }

        public List<Object> getOtherAcronyms() {
            return otherAcronyms;
        }

        public void setOtherAcronyms(List<Object> otherAcronyms) {
            this.otherAcronyms = otherAcronyms;
        }

        public RegionalBloc withOtherAcronyms(List<Object> otherAcronyms) {
            this.otherAcronyms = otherAcronyms;
            return this;
        }

        public List<Object> getOtherNames() {
            return otherNames;
        }

        public void setOtherNames(List<Object> otherNames) {
            this.otherNames = otherNames;
        }

        public RegionalBloc withOtherNames(List<Object> otherNames) {
            this.otherNames = otherNames;
            return this;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        public RegionalBloc withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }

    }


    public class Translations {

        private String de;
        private String es;
        private String fr;
        private String ja;
        private String it;
        private String br;
        private String pt;
        private String nl;
        private String hr;
        private String fa;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Translations(String de, String es, String fr, String ja, String it, String br, String pt, String nl, String hr, String fa) {
            super();
            this.de = de;
            this.es = es;
            this.fr = fr;
            this.ja = ja;
            this.it = it;
            this.br = br;
            this.pt = pt;
            this.nl = nl;
            this.hr = hr;
            this.fa = fa;
        }

        public String getDe() {
            return de;
        }

        public void setDe(String de) {
            this.de = de;
        }

        public Translations withDe(String de) {
            this.de = de;
            return this;
        }

        public String getEs() {
            return es;
        }

        public void setEs(String es) {
            this.es = es;
        }

        public Translations withEs(String es) {
            this.es = es;
            return this;
        }

        public String getFr() {
            return fr;
        }

        public void setFr(String fr) {
            this.fr = fr;
        }

        public Translations withFr(String fr) {
            this.fr = fr;
            return this;
        }

        public String getJa() {
            return ja;
        }

        public void setJa(String ja) {
            this.ja = ja;
        }

        public Translations withJa(String ja) {
            this.ja = ja;
            return this;
        }

        public String getIt() {
            return it;
        }

        public void setIt(String it) {
            this.it = it;
        }

        public Translations withIt(String it) {
            this.it = it;
            return this;
        }

        public String getBr() {
            return br;
        }

        public void setBr(String br) {
            this.br = br;
        }

        public Translations withBr(String br) {
            this.br = br;
            return this;
        }

        public String getPt() {
            return pt;
        }

        public void setPt(String pt) {
            this.pt = pt;
        }

        public Translations withPt(String pt) {
            this.pt = pt;
            return this;
        }

        public String getNl() {
            return nl;
        }

        public void setNl(String nl) {
            this.nl = nl;
        }

        public Translations withNl(String nl) {
            this.nl = nl;
            return this;
        }

        public String getHr() {
            return hr;
        }

        public void setHr(String hr) {
            this.hr = hr;
        }

        public Translations withHr(String hr) {
            this.hr = hr;
            return this;
        }

        public String getFa() {
            return fa;
        }

        public void setFa(String fa) {
            this.fa = fa;
        }

        public Translations withFa(String fa) {
            this.fa = fa;
            return this;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        public Translations withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }


    }
}