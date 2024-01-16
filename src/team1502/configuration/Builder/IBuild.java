package team1502.configuration.Builder;

import team1502.configuration.Parts.Part;

public interface IBuild {
    Part getPart(String name);
    //Part createPart(String newName, String partName);
    void install(Builder builder);
}
