package com.Aishwary.httpServer.HTTP;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum HTTP_Version {
    HTTP_1_1("HTTP/1.1", 1, 1);

    public final String LITERAL;
    public final int MAJOR;
    public final int MINOR;

    HTTP_Version(String LITERAL, int MAJOR, int MINOR) {
        this.LITERAL = LITERAL;
        this.MAJOR = MAJOR;
        this.MINOR = MINOR;
    }

    // simple util Method (to get the best compatible version that gets provided)
    private static final Pattern httpVersionRegexPattern = Pattern.compile("^HTTP/(?<major>\\d+).(?<minor>\\d+)");

    public static HTTP_Version getBestCompatibleVersion(String literalVersion) throws BadHTTPVersionException {
        Matcher matcher = httpVersionRegexPattern.matcher(literalVersion);
        if (!matcher.find() || matcher.groupCount() != 2) { //if this runs that means there is some error
            throw new BadHTTPVersionException();
        }
        //extract the minor and the major
        int major = Integer.parseInt(matcher.group("major"));
        int minor = Integer.parseInt(matcher.group("minor"));

        //To find the best compatible version and compare the major and minor versions

        HTTP_Version tempBestCompatible = null;
        for (HTTP_Version version : HTTP_Version.values()) { //finding the best version the is compatible
            if (version.LITERAL.equals(literalVersion)) {
                return version;
            } else { //comparing
                if (version.MAJOR == major) {
                    if (version.MINOR < minor) {
                        tempBestCompatible = version;
                    }
                }
            }
        }
        return tempBestCompatible;
    }
}
