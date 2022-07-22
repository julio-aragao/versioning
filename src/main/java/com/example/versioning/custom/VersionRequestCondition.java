package com.example.versioning.custom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.AbstractRequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;

@Component
@Scope("prototype")
public class VersionRequestCondition extends AbstractRequestCondition<VersionRequestCondition> {

    private String version;
    private String path;

    public VersionRequestCondition(String version, String path) {
        this.version = version;
        this.path = path;
    }

    @Override
    public VersionRequestCondition combine(VersionRequestCondition other) {
        if( path.equalsIgnoreCase(other.path) && version.equalsIgnoreCase(other.version) ) {
            return this;
        }

        String combinedPath = path.concat(other.path);
        return new VersionRequestCondition(version, combinedPath);
    }

    @Override
    public VersionRequestCondition getMatchingCondition(HttpServletRequest request) {
        String versionFromHeader = request.getHeader("X-API-VERSION");
        int versionNumberFromHeader = tryToParse(versionFromHeader.trim());
        int versionNumber = tryToParse(version);

        while( versionNumberFromHeader > versionNumber ) {
            versionNumberFromHeader--;
        }

        if ( request.getServletPath().equalsIgnoreCase(path) ) {
            return this;
        }

        return null;
    }

    int tryToParse(String versionStr) {
        try {
            int parsed = Integer.parseInt(versionStr);
            return parsed;
        }
        catch(NumberFormatException numberFormatException) {
            return 1;
        }
    }

    @Override
    public int compareTo(VersionRequestCondition other, HttpServletRequest request) {
        int versionComparison = this.version.compareToIgnoreCase(other.version);
        if( versionComparison != 0 ) {
            return versionComparison;
        }

        return path.compareTo(other.path);
    }

    @Override
    protected Collection<?> getContent() {
        return Arrays.asList(version, path);
    }

    @Override
    protected String getToStringInfix() {
        return "||";
    }
}
