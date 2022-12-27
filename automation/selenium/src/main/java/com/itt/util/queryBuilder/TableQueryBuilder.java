package com.itt.util.queryBuilder;

import com.itt.api.util.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TableQueryBuilder implements QueryBuilder {

    private StringBuilder queryBuilder;

    public TableQueryBuilder() {
        queryBuilder = new StringBuilder();
    }

    public static TableQueryBuilder formQuery() {
        return new TableQueryBuilder();
    }

    @Override
    public QueryBuilder addQuery(RequestParam key, String operator, String value) {
        queryBuilder.append(String.format("%s%s%s", key.toString(), operator, value));
        return this;
    }

    @Override
    public QueryBuilder addQuery(RequestParam key, String value) {
        queryBuilder.append("&");
        encode(key.toString(), value);
        return this;
    }

    @Override
    public QueryBuilder limit(int limit) {
        queryBuilder.append("&");
        encode("sysparm_limit", String.valueOf(limit));
        return this;
    }

    @Override
    public QueryBuilder and() {
        queryBuilder.append("&");
        return this;
    }

    @Override
    public QueryBuilder xand() {
        queryBuilder.append("^");
        return this;
    }

    @Override
    public QueryBuilder or() {
        queryBuilder.append("^OR");
        return this;
    }

    @Override
    public QueryBuilder equalsTo(RequestParam key, String value) {
        queryBuilder.append(String.format("%s%s%s", key.toString(), "=", value));
        return this;
    }

    @Override
    public QueryBuilder notEqualToNull(RequestParam key) {
        queryBuilder.append(String.format("%s%s", key.toString(), "!=NULL"));
        return this;
    }

    @Override
    public QueryBuilder isNotOneOf(RequestParam key , String value) {
        queryBuilder.append(String.format("%sNOT IN%s", key.toString(), value));
        return this;
    }

    @Override
    public QueryBuilder notEqualTo(RequestParam key, String value) {
        queryBuilder.append(String.format("%s!=%s", key.toString(), value));
        return this;
    }

    @Override
    public QueryBuilder equalToNull(RequestParam key) {
        queryBuilder.append(String.format("%s%s", key.toString(), "=NULL"));
        return this;
    }

    @Override
    public QueryBuilder in(RequestParam key, String value) {
        queryBuilder.append(String.format("%sIN%s", key.toString(), value));
        return this;
    }

    @Override
    public QueryBuilder startsWith(RequestParam key, String value) {
        queryBuilder.append(String.format("%sSTARTSWITH%s", key.toString(), value));
        return this;
    }

    @Override
    public StringBuilder build() {
        return queryBuilder;
    }

    private void encode(String name, String value) {
        try {
            queryBuilder.append(URLEncoder.encode(name, "UTF-8"));
            queryBuilder.append("=");
            queryBuilder.append(URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Does not support UTF-8");
        }
    }

}
