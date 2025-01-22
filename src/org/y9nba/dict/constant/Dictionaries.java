package org.y9nba.dict.constant;

public enum Dictionaries {
    FourLatSymbolDict("fourLat.txt", "[a-zA-Z]{4}"),
    FiveNumberDict("fiveNumber.txt", "\\d{5}");

    private final String dictName;
    private final String requirement;

    Dictionaries(String dictName, String requirement) {
        this.dictName = dictName;
        this.requirement = requirement;
    }

    public String getDictName() {
        return this.dictName;
    }

    public String getRequirement() {
        return this.requirement;
    }


    @Override
    public String toString() {
        return String.format(
                "Dictionary{dictName='%s', requirement='%s'}",
                dictName, requirement
        );
    }
}
