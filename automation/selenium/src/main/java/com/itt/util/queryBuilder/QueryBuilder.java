package com.itt.util.queryBuilder;

import com.itt.api.util.RequestParam;

public interface QueryBuilder {

    QueryBuilder addQuery(RequestParam key, String operator, String value);
    QueryBuilder addQuery(RequestParam key, String value);
    QueryBuilder limit(int value);
    QueryBuilder and();
    QueryBuilder or();
    QueryBuilder equalsTo(RequestParam key, String value);
    QueryBuilder isNotOneOf(RequestParam key, String value);
    QueryBuilder notEqualToNull(RequestParam key);
    QueryBuilder notEqualTo(RequestParam key, String value);
    QueryBuilder equalToNull(RequestParam key);
    QueryBuilder in(RequestParam key, String value);
    QueryBuilder startsWith(RequestParam key, String value);
    StringBuilder build();
    QueryBuilder xand();

}
